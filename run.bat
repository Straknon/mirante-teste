@echo off
call mvn clean install || echo Falha no build do Maven
call docker build -t demomirante . || echo Falha no build da imagem
call docker images
call docker-compose up || echo Falha ao subir containers