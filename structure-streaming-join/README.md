# structure-streaming-join



Steps to follow to run this POC

1. Run Kafka Zookeeper
2. Run Kafka Server
3. Create Kafka-topic using below command
	kafka-topics.sh --create --zookeeper  localhost:2181 --replication-factor 1 --partitions 1 --topic userevent
	
4. Update your application.properties accordingly if you are using different topic mame and host name  

