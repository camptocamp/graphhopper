FROM debian:buster-slim

RUN apt-get update \
  && mkdir /usr/share/man/man1 \
  && apt-get install -y --no-install-recommends unzip wget curl maven openjdk-11-jdk-headless openjdk-11-jre-headless \
  && apt-get clean \
  && rm -rf /usr/share/man/man1 \
  && rm -rf /var/lib/apt/lists/*

RUN mkdir -p /data && mkdir -p /graphhopper

COPY . /graphhopper/

WORKDIR /graphhopper

# Disable maven progress bar
ENV MAVEN_OPTS=-Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn 

RUN ./graphhopper.sh build
RUN mvn test -Dtest=SchmFlagEncoderTest#test* -DfailIfNoTests=false

EXPOSE 8989

CMD [ "echo Should call something like ./graphhopper.sh -a import -f some_osm.pbf" ]
