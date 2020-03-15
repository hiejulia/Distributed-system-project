#!/bin/bash

docker run --rm -it --net=host landoop/fast-data-dev bash

# install jq to pretty print json 
apk update && apk add jq

# get worker properties 
curl -s 127.0.0.1:8083/ | jq

# list connectors on a worker
curl -s 127.0.0.1:8083/connector-plugins | jq

# infor of connector task and config
curl -s 127.0.0.1:8083/connectors/source-twitter-distributed/tasks | jq


