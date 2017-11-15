#!groovy

pipeline {
    agent any
    stages {
        stage('Test')  {
            tools {
                jdk "JDK 8"
                maven "maven-3.3.9"
            }
            steps {
                sh('mvn clean')
                sh('mvn install')
            }
        }

        stage('Package'){
            tools {
                jdk "JDK 8"
                maven "maven-3.3.9"
            }
            steps {
                sh('mvn clean')
                sh('mvn package')
            }
            post {
                success {
                    echo 'Archiving...'
                    archiveArtifacts artifacts: '**/target/calculator.jar'
                }
            }
        }
        stage('Smoke test'){
            tools {
                jdk "JDK 8"
            }
            steps {
                sh 'nohup java -jar -Dserver.port=8081 ./target/calculator.jar > /dev/null 2>&1&'
                sh 'while ! curl http://localhost:8081/health-check ; do sleep 5;  done'
            }
            post {
                success {
                    sh 'curl -X POST http://localhost:8081/shutdown'
                }
            }
        }
    }
}