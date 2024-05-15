# KITE-api
KITE-api - это api IT-HUB'а для студенческих проектов, написанные на Java Spring Boot, разработанный на хакатоне.

Используемый стек технологий: Java, Spring Boot, Spring Jpa, Spring Security, PostgreSQL, Liquibase, Docker

## Installation
Чтобы установить KITE-api, следуйте следующим шагам:

1. Склонируйте репозиторий:
`git clone https://github.com/aWatLove/ithub-backend.git`
2. Переместитесь в репозиторий:
`cd ithub-backend`
3. Соберите проект:
   1. `mvn clean package`
   2. `docker-compose up --build`


## Usage
Чтобы запустить KITE-api, следуйте следующим шагам:

1. docker-compose up
API будет доступно по ссылке http://localhost:8080.

## API Documentation
KITE-api документация может быть найдена в основной директории `/docs.md`. Так же можно получить JSON-документацию посетив http://localhost:8080/api/docs