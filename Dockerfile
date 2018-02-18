FROM centos
MAINTAINER Puneet Sachdev (puneet.sachdev@gmail.com)

#Install Java 
RUN yum install -y java 

#Copy the application jar file 
ADD /services/target/devcapsule-spring-1.0.jar myapp.jar 
ADD /entrypoint.sh entrypoint.sh 
RUN chmod +x entrypoint.sh 
RUN sh -c 'touch /myapp.jar'
RUN sh -c 'touch /entrypoint.sh'

ENTRYPOINT ["sh", "-c", "/entrypoint.sh"]
