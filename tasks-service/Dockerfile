FROM amazoncorretto:19.0.2-alpine

ARG WD="/opt/app"
ARG USR=appuser
ARG VERSION="1.0.0-SNAPSHOT"

RUN mkdir -p ${WD} \
  && addgroup --system ${USR} \
  && adduser -S -s /bin/false -G ${USR} ${USR} \
  && chown -R ${USR}:${USR} ${WD}
WORKDIR ${WD}
USER ${USR}

ENV LANG en_US.UTF-8

COPY  target/tasks-service-${VERSION}.jar ${WD}/app.jar
CMD ["java", "-jar", "/opt/app/app.jar"]
