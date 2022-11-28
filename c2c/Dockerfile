FROM debian:bullseye-slim as builder

RUN apt-get update \
  && apt-get install -y --no-install-recommends unzip wget curl maven openjdk-11-jdk-headless openjdk-11-jre-headless

RUN mkdir -p /data && mkdir -p /graphhopper

COPY . /graphhopper/

WORKDIR /graphhopper

# Disable maven progress bar
ENV MAVEN_OPTS=-Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn

RUN mvn clean install -DskipTests
RUN mvn test -Dtest=SchmFlagEncoderTest#test* -DfailIfNoTests=false


FROM debian:bullseye-slim
RUN apt-get update \
  && apt-get install -y --no-install-recommends curl openjdk-11-jre-headless \
  && apt-get clean \
  && rm -rf /var/lib/apt/lists/*

COPY --from=builder /graphhopper/web/target/graphhopper-web-6.0-SNAPSHOT.jar /graphhopper/web/target/graphhopper-web-6.0-SNAPSHOT.jar
WORKDIR /graphhopper
COPY config.yml .

EXPOSE 8989
CMD [ "echo Should call something like ./graphhopper.sh -a import -f some_osm.pbf" ]
