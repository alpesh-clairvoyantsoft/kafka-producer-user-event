package com.clairvoyant.schema;

import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class SchemaRegistry {
	
	public static StructType getSchema(String entityName){
		StructType schema = null; 
		
		if(entityName.equalsIgnoreCase("user")) {
			schema =  DataTypes.createStructType(new StructField[] { 
					DataTypes.createStructField("userId", DataTypes.StringType, true),
					DataTypes.createStructField("firstname", DataTypes.StringType, true),
					DataTypes.createStructField("lastname", DataTypes.StringType, true),
					DataTypes.createStructField("phonenumber", DataTypes.StringType, true),
					DataTypes.createStructField("usertimestamp", DataTypes.TimestampType, true)
					});
			
		}else if(entityName.equalsIgnoreCase("payment")) {
			schema =  DataTypes.createStructType(new StructField[] { 
					DataTypes.createStructField("paymentUserId", DataTypes.StringType, true),
					DataTypes.createStructField("amount", DataTypes.StringType, true),
					DataTypes.createStructField("location", DataTypes.StringType, true),				
					DataTypes.createStructField("paymenttimestamp", DataTypes.TimestampType, true)
					});
		}
			
		return schema;
	}
}
