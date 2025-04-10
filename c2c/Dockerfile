FROM debian:bookworm-slim AS builder

RUN apt-get update \
  && apt-get install -y --no-install-recommends maven openjdk-17-jdk-headless

RUN mkdir -p /data && mkdir -p /graphhopper
COPY . /graphhopper/
WORKDIR /graphhopper

RUN mvn clean install -DskipTests


FROM debian:bookworm-slim
RUN apt-get update \
  && apt-get install -y --no-install-recommends curl openjdk-17-jre-headless \
  && apt-get clean \
  && rm -rf /var/lib/apt/lists/*

COPY --from=builder /graphhopper/web/target/graphhopper-web-*-SNAPSHOT.jar /graphhopper/web/target/graphhopper-web.jar
WORKDIR /graphhopper

EXPOSE 8989
