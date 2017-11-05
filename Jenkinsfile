pipeline {
    agent any 
    tools {
      maven 'maven-3.2.5'
    }
    stages {
        stage('build') {
            steps {
              sh 'mvn clean install -f services/pom.xml'
            }
        }
        stage('static-analysis') {
          steps {
              sh 'mvn cobertura:cobertura -Dcobertura.report.format=xml -DwithHistory org.pitest:pitest-maven:mutationCoverage sonar:sonar -Dsonar.pitest.mode=reuseReport -f services/pom.xml'
          }
        }
    }
}
