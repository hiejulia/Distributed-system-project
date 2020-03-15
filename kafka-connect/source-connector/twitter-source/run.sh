#!/bin/bash
# TwitterSourceConnector in distributed mode: 
# create the topic we're going to write to
docker run --rm -it --net=host landoop/fast-data-dev bash
kafka-topics --create --topic twitter-source --partitions 3 --replication-factor 1 --zookeeper 127.0.0.1:2181
# Start a console consumer on that topic
kafka-console-consumer --topic twitter-source --bootstrap-server 127.0.0.1:9092

# Follow the instructions at: https://github.com/Eneco/kafka-connect-twitter#creating-a-twitter-application
# To obtain the required keys, visit https://apps.twitter.com/ and Create a New App. Fill in an application name & description & web site and accept the developer aggreement. Click on Create my access token and populate a file twitter-source.properties with consumer key & secret and the access token & token secret using the example file to begin with.

# Setup instructions for the connector are at: https://github.com/Eneco/kafka-connect-twitter#setup
# fill in the required information at demo-3/source-twitter-distributed.properties
# Launch the connector and start seeing the data flowing in!

