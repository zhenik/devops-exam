# Dev ops exam  
`travis`  
[![Build Status](https://travis-ci.com/NikitaZhevnitskiy/devops-exam.svg?token=6FYqXrfAk2ZHo34Tq8Gp&branch=master)](https://travis-ci.com/NikitaZhevnitskiy/devops-exam)  

`jenkins`   
[![Build Status](http://95.85.15.63:8080/buildStatus/icon?job=master-Jenkinsfile)](http://95.85.15.63:8080/job/master-Jenkinsfile/)

### Docker
build  
`docker build -t zhenik/calculator .`  
run  
`docker run -d -p8080:8080 zhenik/calculator`

sudo rm -r -f ./target/

// connect minikube to ur local docker  
`minikube docker-env`  
`eval $(minikube docker-env)`  
// verify  
`docker ps`  
`docker pull zhenik/calculator`  
`kubectl create -f ./kubernetes/deployments/calc.yml`  
`kubectl create -f ./kubernetes/services/calc.yml`
