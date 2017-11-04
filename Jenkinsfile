pipeline {
    agent { 
        docker { image 'psachdev/microservices-build:latest' }
    }
    stages {
        stage('build') {
            steps {
                sh 'mvn --version'
            }
        }
    }
}
