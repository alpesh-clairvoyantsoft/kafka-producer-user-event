//package com.clairvoyant.spark.tobedeleted;
//
//import org.apache.spark.SparkConf;
//import org.apache.spark.sql.Dataset;
//import org.apache.spark.sql.Encoders;
//import org.apache.spark.sql.Row;
//import org.apache.spark.sql.SparkSession;
//import org.apache.spark.sql.functions;
//import org.apache.spark.sql.streaming.StreamingQueryException;
//import org.apache.spark.sql.streaming.Trigger;
//import org.apache.spark.sql.types.DataTypes;
//import org.apache.spark.sql.types.StructField;
//import org.apache.spark.sql.types.StructType;
//import org.apache.spark.streaming.Durations;
//import org.apache.spark.streaming.api.java.JavaStreamingContext;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class StreamTopic  implements CommandLineRunner{
//	
//	@Value("${kafka.bootstrap.server}")
//	private String bootstrapServers;
//	
//	@Value("${kafka.userevent}")
//	private String usereventTopic;
//	
//	public void processData() {
//		
//		System.out.println(bootstrapServers);
//		System.out.println(usereventTopic);
//		
//		SparkSession spark = SparkSession
//				  .builder()
//				  .master("local")
//				  .appName("StreamTopic")
//				  .getOrCreate();
//		
//		
//	
//		spark.sparkContext().setLogLevel("ERROR");
//		
//		StructType userSchema =  DataTypes.createStructType(new StructField[] { 
//				DataTypes.createStructField("userId", DataTypes.StringType, true),
//				DataTypes.createStructField("firstname", DataTypes.StringType, true),
//				DataTypes.createStructField("lastname", DataTypes.StringType, true),
//				DataTypes.createStructField("phonenumber", DataTypes.StringType, true),
//				DataTypes.createStructField("usertimestamp", DataTypes.TimestampType, true)
//				});
//		
//		
//
//		Dataset<Row> userDataSet=spark.readStream().format("kafka")
//				  .option("kafka.bootstrap.servers", bootstrapServers)
//				  .option("subscribe", usereventTopic)
//				  .option("startingOffsets","earliest")
//				  .load();
//		
//		Dataset<Row> userValues = userDataSet.selectExpr("CAST(value AS STRING)").select(functions.from_json(functions.col("value"),userSchema).
//				as("userTable")).select("userTable.*");
//		
//		
//		userValues.printSchema();
////		userValues.show();
//		
////		Dataset<Row> userJSON = userValues
////		System.out.println(System.currentTimeMillis());
//		try {
//			userValues.writeStream().outputMode("append").format("console").trigger(Trigger.ProcessingTime("10 seconds")).start().awaitTermination();;
//			
//		} catch (StreamingQueryException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////		System.out.println(System.currentTimeMillis());
//		
//		spark.stop();
////		jssc.stop();
//		
//
//		System.out.println("CommmandLine App");
//    }
//
//	@Override
//	public void run(String... args) throws Exception {
//		processData();
//		
//	}
//	
//	public static void main(String[] args) throws Exception {
//		
//		System.setProperty("hadoop.home.dir", "/Users/alpeshpatel/workspace/java/spark-kafka-streaming");
//		
//		SpringApplication.run(StreamTopic.class, args);
//	}
//
//}
