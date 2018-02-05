# Elastic-Spring

## Issues / notes during development
1. ~~Finally managed to establish connection between java app and docker container.~~ Unfortunately elastic in docker throws IllegalArg exception with version incompability information. This is related to elasticsearch-spring [version compatibility matrix](https://github.com/spring-projects/spring-data-elasticsearch/wiki/Spring-Data-Elasticsearch---Spring-Boot---version-matrix).  In the end I decided to dump the up-to-date docker container in favor of slightly older 2.4.6 container.

2. ~~Afterwards I build a standalone Dockerfile just for the Spring Boot Project.~~

3. And finally built a docker-compose that brings both elasticsearch container and Spring Boot container up.

    _dockerfile maven plugin got slightly edited, I don't need that troublesome $JARFILE argument for building._
    

## Important settings for the project
To run the project use:

```
docker-compose up
```

~~Right now to run the project you have to use (for me):~~
```
docker run -p 9200:9200 -p 9300:9300 -v //c/Users/epiobob/Documents/Dev/elastic-spring/src/main/resources/c
ustom_elasticsearch.yml:/usr/share/elasticsearch/config/elasticsearch.yml elasticsearch:2.4.6
```

### Other troublesome settings
1. What is very important and troublesome are the connection settings inside docker container. It is absolutely crucial (as of now ;)) to bind custom_elasticsearch.yml to elasticsearch.yml inside the container and pass some settings:
```
cluster.name: "cars-cluster"
http.host: 0.0.0.0
transport.host: 0.0.0.0
```

2. Another setting inside @Config class:
```
Settings settings = Settings.settingsBuilder()
    .put("cluster.name", clusterName)
    .put("client.transport.sniff", false)
    .build();
```

### Testing Dockerized elasticsearch
This was also a bummer until I realised that (atleast on windows) you have to do:
```
docker-machine ip <name>
```
And then you can do (use [httpie](https://github.com/jakubroztocil/httpie), its awesome!):
```
http GET 192.168.99.100:9200
```



