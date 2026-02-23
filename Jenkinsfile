pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Run Tests with TestNG') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Publish Test Reports') {
            steps {
                sh 'mvn surefire-report:report'
                publishHTML (target: [
                    reportDir: 'target/site',
                    reportFiles: 'index.html',
                    reportName: 'TestNG Reports'
                ])
            }
        }
    }

    triggers {
        pollSCM('* * * * *')
    }
}