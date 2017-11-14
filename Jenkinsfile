#!groovy


pipeline {
    agent any
    stages{
        stage('Echo test')  {
            steps{
                sh 'echo Hello'
            }
        }

        stage('checkout')  {
            steps{
                git branch: 'master', credentialsId: 'f41ee166-3661-42e2-a229-84cb2bd96ad0', url: 'git@github.com:NikitaZhevnitskiy/devops-exam.git'
            }
        }

        stage('mvn clean verify')  {
            tools{
                jdk "JDK 8"
                maven "maven-3.3.9"
            }
            steps{
                sh('mvn clean')
                sh('mvn verify')
            }
        }
    }
}