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
