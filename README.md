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

## Interface Images:
### Login Screen:
![image](https://user-images.githubusercontent.com/41628589/160593312-90b87d92-2403-4dcf-a635-b91360c191cf.png)

### Home:
![image](https://user-images.githubusercontent.com/41628589/200910622-72464226-5a8a-4320-99d4-7e888531272a.png)

### Home Pagination:
![image](https://user-images.githubusercontent.com/41628589/160594065-62737d49-b071-44a1-afec-772d92094578.png)

### Search:
![image](https://user-images.githubusercontent.com/41628589/160594303-f9e669ce-7d2f-4d45-ad08-63bb4eff3f3f.png)

---

## Diagrams:
### Use Case Diagram:
![image](https://user-images.githubusercontent.com/41628589/200906769-9158761e-9169-4cfe-b894-58e7859fc8c1.png)

### Activity Diagrams:
![image](https://user-images.githubusercontent.com/41628589/200906846-cf6603ad-d6ae-4640-b77e-ac35bb5f1f30.png)

![image](https://user-images.githubusercontent.com/41628589/200906913-822d3be4-65bd-4c3f-88b2-9ec4b24f03a0.png)

### Architecture Diagram:
![image](https://user-images.githubusercontent.com/41628589/200907023-eb8111e5-e6f1-42af-a8b6-55cfbeabbaf8.png)

---

⭐️ From [DarlanNoetzold](https://github.com/DarlanNoetzold)

