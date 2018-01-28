#Login into Openshift origin (local minishift)
oc login developer:developer 

#Create a new project for Pizza services 
oc new-project devcapsule 

#Create new application (Installs Redhat JDK8 Image, Builds applications and sets env variables)
oc new-app registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift~https://github.com/psachdev6375/devcapsule --context-dir=services -e RDS_HOST=<AWS-RDS-Endpoint> -e RDS_PORT=3306 -e RDS_DB=capsuledb -e RDS_USER=capsuledb -e RDS_PASSWORD=capsuledb

#List services in the project 
oc get services 

#Create a route for the service for external access 
oc expose service devcapsule



