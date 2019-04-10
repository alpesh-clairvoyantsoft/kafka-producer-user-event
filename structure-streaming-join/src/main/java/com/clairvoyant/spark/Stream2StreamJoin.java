package com.clairvoyant.spark;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.expr;
import static org.apache.spark.sql.functions.from_json;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.streaming.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.clairvoyant.schema.SchemaRegistry;

@SpringBootApplication
public class Stream2StreamJoin  implements CommandLineRunner{
	
	
	
	private static final Logger LOGGER =
		      LoggerFactory.getLogger(Stream2StreamJoin.class);
	
	@Value("${kafka.bootstrap.server}")
	private String bootstrapServers;
	
	@Value("${kafka.topics}")
	private String[] topics;


	//	
	@Value("${kafka.payment.topics}")
	private String paymentTopic;
	
	@Value("${kafka.user.topics}")
	private String userTopic;
	
	public void processData() {
		
		System.out.println(bootstrapServers);
		System.out.println(userTopic);
		System.out.println(paymentTopic);
		for (String topic : topics) {
			System.out.println(topic);
		}
  
		SparkSession spark = SparkSession
				  .builder()
				  .appName("Stream2StreamJoin")
				  .master("local[*]")
				  .getOrCreate();
		
		spark.sparkContext().setLogLevel("ERROR");
		
		//--------Stream1 from user Topic  --------
		Dataset<Row> rawUserData=spark.readStream().format("kafka")
						  	.option("kafka.bootstrap.servers", bootstrapServers)
						  	.option("subscribe", userTopic)
						  	.option("startingOffsets", "earliest")
						  	.option("failOnDataLoss", "false")
						  	.load();
		
		Dataset<Row> userDataSet = rawUserData
							.selectExpr("cast (value as string) as json")
						 	.select(from_json(col("json"), SchemaRegistry.getSchema("user")).as("userData"))
						 	.select("userData.*");
				  
		Dataset<Row> rawPaymentData=spark.readStream().format("kafka")
						  	.option("kafka.bootstrap.servers", bootstrapServers)
						  	.option("subscribe", paymentTopic)
						  	.option("startingOffsets", "earliest")
						  	.option("failOnDataLoss", "false")
						  	.load();
		Dataset<Row> paymentDataSet = rawPaymentData
							.selectExpr("cast (value as string) as json")
						 	.select(functions.from_json(functions.col("json"), SchemaRegistry.getSchema("payment")).as("paymentData"))
						 	.select("paymentData.*");
		
		Dataset<Row> userDataSetWithWatermark = userDataSet.withWatermark("usertimestamp", "20 seconds");
		
		Dataset<Row> paymentDataSetWithWatermark = paymentDataSet.withWatermark("paymenttimestamp", "20 seconds");
		
		Dataset<Row> joindataSet =	userDataSetWithWatermark.join(
				paymentDataSetWithWatermark,
				  expr(
						  "userId = paymentUserId")
				);
		

				
		try {
//			Saving the result stream to file
			joindataSet.writeStream()
		      .format("console")
		      .outputMode("append")
		      .option("checkpointLocation", "/tmp/clairvoyant/checkpoint")
		      .option("truncate","false")
		      .trigger(Trigger.ProcessingTime("20 seconds"))
		      .option("path", "/tmp/clairvoyant/output-streams")
		      .start();
			
			paymentDataSetWithWatermark.writeStream()
		      .format("console")
		      .outputMode("append")
//		      .option("checkpointLocation", "/tmp/clairvoyant/checkpoint")
		      .option("truncate","false")
		      .trigger(Trigger.ProcessingTime("20 seconds"))
		      .option("path", "/tmp/clairvoyant/output-streams")
		      .start();	
			
		      userDataSetWithWatermark.writeStream()
		      .format("console")
		      .outputMode("append")
//		      .option("checkpointLocation", "/tmp/clairvoyant/checkpoint")
		      .option("truncate","false")
		      .trigger(Trigger.ProcessingTime("20 seconds"))
		      .option("path", "/tmp/clairvoyant/output-streams")
		      .start();
		      
//		      .awaitTermination();
			spark.streams().awaitAnyTermination();
			spark.stop();
		} catch (StreamingQueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

	@Override
	public void run(String... args) throws Exception {
		processData();
		
	}
	
	public static void main(String[] args) throws Exception {
		
		System.setProperty("hadoop.home.dir", "/Users/alpeshpatel/workspace/java/spark-kafka-streaming");
		
		SpringApplication.run(Stream2StreamJoin.class, args);
	}

}
