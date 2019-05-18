#!/usr/bin/env bash

./gradlew clean build
docker build -t mazerunner .
docker run -p 5000:8080 mazerunner