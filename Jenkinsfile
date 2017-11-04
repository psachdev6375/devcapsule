pipeline {
    agent { docker 'psachdev/microservices-build:latest' }
    stages {
        stage('build') {
            steps {
                sh 'mvn --version'
            }
        }
    }
}
