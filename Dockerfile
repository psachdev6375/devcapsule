FROM --platform=linux/amd64 amazoncorretto:8
MAINTAINER Puneet Sachdev (puneet.sachdev@gmail.com)

ENV RDS_HOST=127.0.0.1
ENV RDS_PORT=3306
ENV RDS_DB=devcapsule
ENV RDS_USER=root
ENV RDS_PASSWORD=password

#Copy the application jar file 
ADD /services/target/devcapsule-spring-1.0.jar myapp.jar 
ADD /entrypoint.sh entrypoint.sh
RUN chmod +x entrypoint.sh

RUN sh -c 'touch /myapp.jar'

ENTRYPOINT ["sh", "-c", "/entrypoint.sh"]
