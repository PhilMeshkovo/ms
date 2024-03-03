FROM centos

RUN yum install -y java

VOLUME /tmp
ADD /target/producer-1.jar myapp.jar
RUN sh -c 'touch /myapp.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/myapp.jar"]