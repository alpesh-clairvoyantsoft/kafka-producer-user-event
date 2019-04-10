//package com.clairvoyant.properties;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//
//@Getter @Setter @ToString
//@ConfigurationProperties(prefix = "app")
//
//public class AppProperties {
//	@Value("${app.kafka.bootstrap.server}")
//	private String bootstrapServers;
//	
//	@Value("${app.kafka.topics}")
//	private List<String> topics;
//
//	@Value("${app.kafka.groupid}")
//	private String kafkaGroupId;
//	
//	@Value("${app.spark.master}")
//	private String sparkMaster;
//	
//	@Value("${app.spark.watermark.time}")
//	private String waterMarkTime;
//	
//	@Value("${app.spark.startingOffsets}")
//	private String startingOffsets;
//	
//	@Value("${app.spark.format}")
//	private String streamFormat;
//	
//	@Value("${app.spark.failOnDataLoss}")
//	private Boolean failOnDataLoss;
//	
//	@Value("${app.spark.join.processing.time}")
//	private String processingTime;
//	
//	@Value("${app.spark.checkpoint.path}")
//	private String checkPointPath;
//	
//	@Value("${app.spark.output.path}")
//	private String outputPath;
//		
//}
