#!/bin/sh
/opt/apache-maven/bin/mvn clean install

docker login -u blockhaineveris -p jrasdf311?
docker build -t blockhaineveris/blockchain-middleware:master .
docker push blockhaineveris/blockchain-middleware:master