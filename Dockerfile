FROM java:8
VOLUME /tmp
ADD ./target/store-1.0-SNAPSHOT.jar store.jar
RUN sh -c 'touch /store.jar'
ENTRYPOINT [ "sh", "-c", "java -jar /store.jar" ]