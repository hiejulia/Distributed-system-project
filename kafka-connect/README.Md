+ Kafka connect source API 
    + data from file into kafka topic 
    + get data remotely into kafka topic 
+ kafka connect sink 
    + store data from kafka to elasticsearch - 
    + store data from kafka to postgresql 
    + transfer cassandra db -> postgresql 

+ distributed kafka connect cluster architect

+ Start Kafka connect cluster using Docker compose
    `docker run --rm --net=host lensesio/fast-data-dev`
    + https://github.com/lensesio/fast-data-dev

+ view kafka connect logs 

# Kafka connect run in Distributed mode 
+ Multiple workers run connectors and tasks 
+ configuration 
+ easy to scale, fault tolerant - rebalancing in case a worker dies 
+ production deployment of connectors 
+ logs : 127.0.0.1:3030/logs/ - `connect-distributed.log`


# kafka for development: fast-data-dev 
+ kafka 
+ zookeeper 
+ schema registry 
+ kafka connect 
+ landoop tools 
+ run on cloud: 
    + run kafka instance GCE - AWS 
    + start VM - open firewall 
    + Once the firewall is open try:

docker run -d --net=host -e ADV_HOST=[VM_EXTERNAL_IP] \
           -e RUNNING_SAMPLEDATA=1 lensesio/fast-data-dev
Alternatively just export the ports you need. E.g:

docker run -d -p 2181:2181 -p 3030:3030 -p 8081-8083:8081-8083 \
           -p 9581-9585:9581-9585 -p 9092:9092 -e ADV_HOST=[VM_EXTERNAL_IP] \
           -e RUNNING_SAMPLEDATA=1 lensesio/fast-data-dev
    
    
+ config kafka component 
    + broker, schema registry, connect, rest proxy 
+ production 
    + enable SSL on broker 
    + advanced connector settings 
        + enable additional connectors 
        + explicitly disable connectors 
        + explicitly enable connectors
        + kafka connect clusters 
        + jmx metrics 
        + jconsole metrics 


# Set up Kafka in production 
+ Set up Kafka Connect in Production 
    + download kafka binaries 
    + set up connect-distributed.properties 
    + jars 
    + launch kafka connect 
    + rest api - kafka connect 
    + UI kafka connect : `https://github.com/lensesio/kafka-connect-ui`
    