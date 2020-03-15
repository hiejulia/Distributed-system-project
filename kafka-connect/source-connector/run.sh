# Source connector 

# Start kafka connector 

# FileStreamSourceConnector in standalone mode 
# worker.properties file 
# file-stream.properties file

# Run docker container 
docker run --rm -it -v "${pwd}":/demo --net=host landoop/fast-data-dev bash 

# start topic - with 3 partitions 


# worker.properties - connector1.properties - connector2.properties 
kafka-topics --create --topic demo1-standalone --partitions 3 --replication-factor 1 --zookeeper 127.0.0.1:2181

connect-standalone worker.properties file-stream-demo-standalone.properties 

# FileStreamSourceConnector - distributed mode 
# topic - 3 partitions 
docker run --rm -it --net=host landoop/fast-data-dev bash 
kafka-topics --create --topic demo2-distributed --partitions 3 --replication-factor 1 --zookeeper 127.0.0.1:2181 

