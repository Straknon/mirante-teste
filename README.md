# Run
docker-compose down -v
mvn clean install
docker build -t demomirante .
docker images
docker-compose up

# Swagger
Docs: http://localhos:8080/swagger-ui.html