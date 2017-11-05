pipeline {
    agent any 
    stages {
        stage('build') {
            steps {
              sh 'mvn clean install -f services/pom.xml'
            }
        }
        stage('static-analysis') {
          'mvn cobertura:cobertura -Dcobertura.report.format=xml -DwithHistory org.pitest:pitest-maven:mutationCoverage sonar:sonar -Dsonar.pitest.mode=reuseReport -f services/pom.xml'
        }
    }
}
