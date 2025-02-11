<a href="https://www.buymeacoffee.com/hientech" target="_blank"><img src="https://img.shields.io/badge/-buy_me_a%C2%A0coffee-gray?logo=buy-me-a-coffee" alt="Buy Me A Coffee"></a>
  <br>
# Distributed-system-project : `Kafki` 

## Project description 
Distributed logging, messaging and data connector cluster on top of Kafka 
+ Publish and subcribe to streams of records, similar to message queue
+ Store streams of records in a fault tolerant way 
+ Process streams of records 
+ APIs : Producer, Consumer, Streams, Connector 
+ Migrate data sources built on top of Kafka 
- Cloud native kafka 
## Run the project 

<a href="https://imgur.com/p71IZ7z"><img src="https://i.imgur.com/p71IZ7z.png" title="source: imgur.com" /></a>
<a href="https://imgur.com/GMbKxSm"><img src="https://i.imgur.com/GMbKxSm.png" title="source: imgur.com" /></a>
<a href="https://imgur.com/l1CBSvp"><img src="https://i.imgur.com/l1CBSvp.png" title="source: imgur.com" /></a>
<a href="https://imgur.com/kJmdit4"><img src="https://i.imgur.com/kJmdit4.png" title="source: imgur.com" /></a>
<a href="https://imgur.com/I8xkmGb"><img src="https://i.imgur.com/I8xkmGb.png" title="source: imgur.com" /></a>
<a href="https://imgur.com/mxxCI5I"><img src="https://i.imgur.com/mxxCI5I.png" title="source: imgur.com" /></a>
<a href="https://imgur.com/ZSKrAOr"><img src="https://i.imgur.com/ZSKrAOr.png" title="source: imgur.com" /></a>


## Install necessary softwares 
+ Java 8 
+ Maven / Gradle
- kafka
    - stream API 
        - Changelog topic 
        - event schema
    - aggregate
    - connect
        - transfer,sync data from DB -> ElasticSearch, HDFS,etc with Kafka 
    - orchestration vs choreography 
- Flink
- microservices
- large scale event processing 
- Apache Airflow 
- DB : RockDB 

<a href="https://imgur.com/Iz3fCSs"><img src="https://i.imgur.com/Iz3fCSs.png" title="source: imgur.com" /></a>

## Install Kafka source code 
+ Download Kafka source code : `git clone https://github.com/<your github id>/kafka.git`
+ Build Kafka source code 
+ Run Zookeeper
+ Testing Kafka setup
Create a new topic named “test” with a single partition and only one replica:

> bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
We can now see that topic if we run the list topic command:

> bin/kafka-topics.sh --list --zookeeper localhost:2181
test
Run the producer and then type a few messages into the console to send to the server.

> bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test
This is a message
This is another message
Kafka also has a command line consumer that will dump out messages to standard output.

> bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning
This is a message
This is another message
If you have each of the above commands running in a different terminal then you should now be able to type messages into the producer terminal and see them appear in the consumer terminal.

## Cloud native Kafka 





## Network 
+ Socket and streams 
+ Bandwidth problems 
+ Communication between distributed applications 
  + TCP - UDP 
  + Multicasting 
+ HPC cluster computing 
  + Parallel system architecture 
  + Massively parallel processor MPP
  + Symmetric multiprocesssor SMP
  + 
+ Ethernet 
+ Wireless networks
+ OSI model 
+ RPC
+ Clusters - High performance computing cluster 
+ Shard service 
+ Cache shard
+ Hot sharding system 
+ Multi threading 
+ P
## Multi tenant distributed system 
+ Scheduling 
+ Hardware bottleneck 
+ CPU performance
+ Algorithm efficiency
+ Kernel scheduling
+ I/O and CPU cache 
+ 



## Distributed databases 
+ Distributed and decentralized databases 
+ Replication 
+ Horizontal/vertical partition
+ Hybrid set up 
+ Distributed DBMS architecture 
+ Distributed transaction 
+ Replicated data 
+ 



## Distributed algorithms 
+ Atomic operations
+ Guarded actions 
+ Nondeterminism 
+ Fairness 
+ Theorem 4.1
+ Chandy-Lamport algorithm
+ Lai-Yang algorithm
+ All- to - All broadcasting 
+ Termination-detection algorithm
+ Dijkstra-scholten algorithm 
+ Wave algorithm
+ Graph algorithm 
+ Coordination algorithms 
+ 
## Thread 


## Distributed fault tolerance 


## Security  
+ 2 way SSL 
+ SECaaS

## Big data   
+ Data replication 
+ Sequential update mechanism 
+ Process synchronization 
+ 

## Distributed system architecture 
+ Functions and event processing 
+ Master election  
+ Batch computational pattern 
+ Work queue system 
+ 


## AWS 
+ Distributed application on AWS
## Debug distributed applications 
+ AppDynamics 
+ Distributed debugging 

## Real world system 
+ Distributed discrete event simulation 
+ Sensor network 
+ Social and peer to peer network 
+ Security in distributed environment
+

## TODO 
- DB update -> Kafka connect with Debezium 
- Distributed kafka connect
- Cloud native Kafka architecture
- Kafka with Schema registry 
