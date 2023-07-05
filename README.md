# Remote-Analyser
## Desenvolvimento:
* Foi usado Java 1.8 como linguagem base;
* Foi desenvolvido com Spring Boot;
* O Banco utilizado foi o PostgreSQL;
* O login foi implementados com Spring Security;
* O Front-End foi feito em Thymeleaf;
* Já o consumo do API Gateway foi feito com Feign Client.

## Projeto:
* Projeto de Prova de conceito para o desenvolvimento de malware's para que assim possamos aprender como evitá-los e reconhece-los;
* Esta aplicação web faz parte de um projeto maior o qual é um sistema desenvolvido por mim, para coleta de dados suspeitos em computadores empresarias e/ou institucionais. Servindo assim, como um monitoramento mais eficiente do patrimônio destas entidades;
* Uma aplicação Web feita com Spring Framework. Esta aplicação consome os dados da API Gateway, tem autenticação através de um login e uma interface web para a visualização dos dados. Tendo três telas, uma para login, outra para Home (que irá carregar todos os Alertas, separados por paginação) e uma tela de Busca (que será possível filtrar os dados por endereço MAC).

---

## Como utilizar:
* A aplicação completa contendo todos os microserviços configurados pode ser obtida no [DockerHub](https://hub.docker.com/repository/docker/darlannoetzold/tcc-spyware/general).
* Para executá-lo de maneira mais fácil basta excutar os seguintes comandos:
```
docker container run --platform=linux/amd64 -it -p 8091:8091 -p 8090:8090 -p 5000:5000 -p 9091:9090 -p 3000:3000 --name=app -d darlannoetzold/tcc-spyware:4.0

docker exec -itd app /init-spyware-api.sh
docker exec -itd app /init-remoteanalyser.sh
docker exec -itd app /init-handler-hatespeech.sh
```


## API:
* A API:
<br>Link: https://spyware-api.herokuapp.com/
* Documentação da API:
<br>Link: https://spyware-api.herokuapp.com/swagger-ui/index.html
* Repositório no GitHub:
<br>Link: https://github.com/DarlanNoetzold/spyware-API

---
## API do HateSpeech:
* A API:
<br>Link: https://hate-speech-portuguese.herokuapp.com
* Repositório no GitHub:
<br>Link: https://github.com/DarlanNoetzold/HateSpeech-portuguese

---
## Script do Spyware:
* Repositório no GitHub:
<br>Link: https://github.com/DarlanNoetzold/spyware

---

## Imagens da interface:
### Tela de Login:
![image](https://user-images.githubusercontent.com/41628589/160593312-90b87d92-2403-4dcf-a635-b91360c191cf.png)

### Home:
![image](https://user-images.githubusercontent.com/41628589/200910622-72464226-5a8a-4320-99d4-7e888531272a.png)

### Pagination da Home:
![image](https://user-images.githubusercontent.com/41628589/160594065-62737d49-b071-44a1-afec-772d92094578.png)

### Busca:
![image](https://user-images.githubusercontent.com/41628589/160594303-f9e669ce-7d2f-4d45-ad08-63bb4eff3f3f.png)

---

## Diagramas:
### Diagrama de Casos de uso:
![image](https://user-images.githubusercontent.com/41628589/200906769-9158761e-9169-4cfe-b894-58e7859fc8c1.png)

### Diagramas de Atividade:
![image](https://user-images.githubusercontent.com/41628589/200906846-cf6603ad-d6ae-4640-b77e-ac35bb5f1f30.png)

![image](https://user-images.githubusercontent.com/41628589/200906913-822d3be4-65bd-4c3f-88b2-9ec4b24f03a0.png)

### Diagrama de arquitetura:
![image](https://user-images.githubusercontent.com/41628589/200907023-eb8111e5-e6f1-42af-a8b6-55cfbeabbaf8.png)

---


⭐️ From [DarlanNoetzold](https://github.com/DarlanNoetzold)
