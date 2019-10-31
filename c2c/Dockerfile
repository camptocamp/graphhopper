FROM debian:buster-slim

RUN apt-get update \
  && mkdir /usr/share/man/man1 \
  && apt-get install -y --no-install-recommends unzip wget maven openjdk-11-jdk-headless openjdk-11-jre-headless \
  && apt-get clean \
  && rm -rf /usr/share/man/man1 \
  && rm -rf /var/lib/apt/lists/*

RUN mkdir -p /data && mkdir -p /graphhopper

COPY . /graphhopper/

WORKDIR /graphhopper

RUN ./graphhopper.sh build

EXPOSE 8989

CMD [ "echo Should call something like ./graphhopper.sh -a import -f some_osm.pbf" ]
