node {
    env.PATH = "${tool 'maven-3.3.9'}/bin:${env.PATH}"
    env.PATH = "${tool 'JDK 8'}/bin:${env.PATH}"
    def image;

    stage('Git checkout') {
        git branch: 'master', credentialsId: 'f41ee166-3661-42e2-a229-84cb2bd96ad0', url:'git@github.com:NikitaZhevnitskiy/devops-exam.git'
    }
    stage('Build, Test'){
        sh 'mvn clean install -Pjacoco checkstyle:checkstyle findbugs:findbugs'
    }
    stage("Reports"){
        parallel (
            "Check style report" : {
                step([$class: 'hudson.plugins.checkstyle.CheckStylePublisher', pattern: '**/target/checkstyle-result.xml', unstableTotalAll:'200'])
            },
            "Find bugs report" : {
                step([$class: 'FindBugsPublisher', pattern: '**/findbugsXml.xml', unstableTotalAll:'10'])
            },
            "Test coverage report" :{
                jacoco exclusionPattern: ' **/*Test.class', maximumBranchCoverage: '70', maximumClassCoverage: '70', maximumComplexityCoverage: '70', maximumInstructionCoverage: '80', maximumLineCoverage: '70', maximumMethodCoverage: '70'
            }
        )
    }
    stage("Package jar"){
        archiveArtifacts artifacts: '**/target/calculator.jar'
    }
    stage("Smoke test"){
        timeout(2){
            sh 'nohup java -jar -Dserver.port=8081 ./target/calculator.jar > /dev/null 2>&1&'
            sh 'while ! curl http://localhost:8081/health-check ; do sleep 5;  done'
        }
        sh 'curl -X POST http://localhost:8081/shutdown'
    }
    stage("Build docker image"){
        image = docker.build "zhenik/calculator:latest"
    }
    stage("Push docker image"){
        withDockerRegistry(registry:[credentialsId: 'c2f55a10-0c46-496b-b7f2-d1c65222f2fc']) {
            image.push()
        }
    }
}