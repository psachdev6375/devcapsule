pipeline {
    agent any 
    stages {
        stage('build') {
            steps {
              sh 'mvn clean install'
            }
        }
        stage('static-analysis') {
          mvn cobertura:cobertura -Dcobertura.report.format=xml -DwithHistory org.pitest:pitest-maven:mutationCoverage sonar:sonar -Dsonar.pitest.mode=reuseReport
        }
    }
}
