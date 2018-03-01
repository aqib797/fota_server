FROM openjdk:8u121-jre-alpine

MAINTAINER Kai Zimmermann <kai.zimmermann@bosch-si.com>

ENV HAWKBIT_VERSION=0.2.0M4 \
    HAWKBIT_HOME=/opt/hawkbit

# Http port
<<<<<<< HEAD
EXPOSE 8085
=======
EXPOSE 8080

COPY KEY .
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

RUN set -x \
    && apk add --no-cache gnupg unzip \
    && apk add --no-cache libressl wget \
<<<<<<< HEAD
    && gpg --keyserver pgp.mit.edu --recv-keys 385CBC1C7F667FAE \

    && mkdir -p $HAWKBIT_HOME \
    && cd $HAWKBIT_HOME \
    && wget -O hawkbit-update-server.jar --no-verbose http://repo1.maven.org/maven2/org/eclipse/hawkbit/hawkbit-update-server/$HAWKBIT_VERSION/hawkbit-update-server-$HAWKBIT_VERSION.jar \
    && wget -O hawkbit-update-server.jar.asc --no-verbose http://repo1.maven.org/maven2/org/eclipse/hawkbit/hawkbit-update-server/$HAWKBIT_VERSION/hawkbit-update-server-$HAWKBIT_VERSION.jar.asc \
=======
    && gpg --import KEY \
    && mkdir -p $HAWKBIT_HOME \
    && cd $HAWKBIT_HOME \
    && wget -O hawkbit-update-server.jar --no-verbose https://repo1.maven.org/maven2/org/eclipse/hawkbit/hawkbit-update-server/$HAWKBIT_VERSION/hawkbit-update-server-$HAWKBIT_VERSION.jar \
    && wget -O hawkbit-update-server.jar.asc --no-verbose https://repo1.maven.org/maven2/org/eclipse/hawkbit/hawkbit-update-server/$HAWKBIT_VERSION/hawkbit-update-server-$HAWKBIT_VERSION.jar.asc \
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
    && gpg --batch --verify hawkbit-update-server.jar.asc hawkbit-update-server.jar

VOLUME "$HAWKBIT_HOME/data"

WORKDIR $HAWKBIT_HOME
ENTRYPOINT ["java","-jar","hawkbit-update-server.jar","-Xmx768m -Xmx768m -XX:MaxMetaspaceSize=250m -XX:MetaspaceSize=250m -Xss300K -XX:+UseG1GC -XX:+UseStringDeduplication -XX:+UseCompressedOops -XX:+HeapDumpOnOutOfMemoryError"]
