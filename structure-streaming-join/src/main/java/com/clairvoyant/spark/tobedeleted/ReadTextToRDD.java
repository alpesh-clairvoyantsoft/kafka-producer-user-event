package com.clairvoyant.spark.tobedeleted;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;


public class ReadTextToRDD {
 
    public static void main(String[] args) {
        // configure spark
        SparkConf sparkConf = new SparkConf().setAppName("Read Text to RDD")
                                        .setMaster("local[2]").set("spark.executor.memory","2g");
        // start a spark context
        JavaSparkContext sc = new JavaSparkContext(sparkConf);
        
        // provide path to input text file
        String path = "/Users/alpeshpatel/json.txt";
        
        // read text file to RDD
        JavaRDD<String> lines = sc.textFile(path);
        List<String> lineList =new ArrayList<>();
        // collect RDD for printing
        for(String line:lines.collect()){
        	lineList.add(line);
            System.out.println(line);
        }
        
        StructType userSchema =  DataTypes.createStructType(new StructField[] { 
				DataTypes.createStructField("userId", DataTypes.StringType, true),
				DataTypes.createStructField("firstname", DataTypes.StringType, true),
				DataTypes.createStructField("lastname", DataTypes.StringType, true),
				DataTypes.createStructField("phonenumber", DataTypes.StringType, true),
				DataTypes.createStructField("timestamp", DataTypes.TimestampType, true)
				});
        
        SparkSession spark = SparkSession
				  .builder()
				  .appName("StreamTopic")
				  .getOrCreate();
        
        Dataset<String> users = spark.createDataset(lineList, Encoders.STRING());
        Dataset<Row> anotherPeople = spark.read().json(users);
        
        
        Dataset<Row> userValues = anotherPeople.selectExpr("CAST(key AS STRING)","CAST(value AS STRING)").select(functions.from_json(functions.col("value"),userSchema).
				as("userTable")).select("userId","firstname","lastname","phonenumber","timestamp");
		
        
        
        
    }
 
}
