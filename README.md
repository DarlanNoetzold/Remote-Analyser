# Remote-Analyser
## Development:
* Java 1.8 was used as the base language;
* Developed with Spring Boot;
* PostgreSQL was used as the database;
* Login was implemented with Spring Security;
* The Front-End was made in Thymeleaf;
* API Gateway consumption was done with Feign Client.

## Project:
* Proof of concept project for the development of malware so that we can learn how to avoid and recognize them;
* This web application is part of a larger project which is a system developed by me, for collecting suspicious data on corporate and/or institutional computers. Thus, serving as a more efficient monitoring of these entities' assets;
* A Web application made with Spring Framework. This application consumes data from the API Gateway, has authentication through a login, and a web interface for data visualization. It has three screens, one for login, another for Home (which will load all Alerts, separated by pagination) and a Search screen (where it will be possible to filter data by MAC address).

---

## How to use:
* The complete application containing all configured microservices can be obtained at [DockerHub](https://hub.docker.com/repository/docker/darlannoetzold/tcc-spyware/general).
* To run it more easily, just execute the following commands:
```
docker container run --platform=linux/amd64 -it -p 8091:8091 -p 8090:8090 -p 5000:5000 -p 9091:9090 -p 3000:3000 --name=app -d darlannoetzold/tcc-spyware:4.0

docker exec -itd app /init-spyware-api.sh
docker exec -itd app /init-remoteanalyser.sh
docker exec -itd app /init-handler-hatespeech.sh
```


## API:
* GitHub Repository:
<br>Link: [https://github.com/DarlanNoetzold/spyware-API](https://github.com/DarlanNoetzold/spyware-API)

---
## HateSpeech API:
* GitHub Repository:
<br>Link: [https://github.com/DarlanNoetzold/HateSpeech-portuguese](https://github.com/DarlanNoetzold/HateSpeech-portuguese)

---
## Spyware Script:
* GitHub Repository:
<br>Link: [https://github.com/DarlanNoetzold/spyware](https://github.com/DarlanNoetzold/spyware)

---

## Diagrams:
### Use Case Diagram:
![image](https://github.com/DarlanNoetzold/Remote-Analyser/assets/41628589/20a013ea-f19c-4ae1-8b41-1028cf4f47a8)


### Activity Diagrams:
![image](https://github.com/DarlanNoetzold/Remote-Analyser/assets/41628589/9da75e73-432b-4a14-bce0-c3ad3ce49058)
![image](https://github.com/DarlanNoetzold/Remote-Analyser/assets/41628589/a9eda03c-f7a6-4b6c-b84e-8096f880455a)
![image](https://github.com/DarlanNoetzold/Remote-Analyser/assets/41628589/3ba82116-f562-45b0-b98b-ef0ba012efa6)
  ![image](https://github.com/DarlanNoetzold/Remote-Analyser/assets/41628589/e3b657e5-37a2-4e07-8140-6cb2a69fa652)


### Architecture Diagram:
![image](https://github.com/DarlanNoetzold/Remote-Analyser/assets/41628589/e92ad344-b319-4b21-91cb-28ff6b1e79d7)

---

⭐️ From [DarlanNoetzold](https://github.com/DarlanNoetzold)

