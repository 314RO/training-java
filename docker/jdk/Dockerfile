FROM maven:3-jdk-8-alpine

RUN mkdir -p /home/excilys/workspace/ComputerDatabase/training-java/docker/jdk
WORKDIR /home/excilys/workspace/ComputerDatabase/training-java/docker/jdk

ONBUILD ADD . /home/excilys/workspace/ComputerDatabase/training-java/docker/jdk

ONBUILD RUN mvn install
