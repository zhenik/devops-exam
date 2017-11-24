# DevOps exam  
`travis`  
[![Build Status](https://travis-ci.com/NikitaZhevnitskiy/devops-exam.svg?token=6FYqXrfAk2ZHo34Tq8Gp&branch=master)](https://travis-ci.com/NikitaZhevnitskiy/devops-exam)  
`jenkins`   
[![Build Status](http://95.85.15.63:8080/buildStatus/icon?job=master-Jenkinsfile)](http://95.85.15.63:8080/job/master-Jenkinsfile/)

Original repository : https://github.com/NikitaZhevnitskiy/devops-exam   
Current repository contains app source code, Jenkinsfile, ansible scripts, kubernetes scripts, maven wrapper and process documentation.
## 1 Application
Build server accessible on  [95.85.15.63:8082](http://95.85.15.63:8082/health-check)  
Calculator app is RESTful web-api with several endpoints. Client can make request to endpoints 
to get result of math operations. Application contains unit & controllers (RestAssured) tests.

`Numbers` json representation (example).
for request that requires body
```
 {
    "n1":4,
    "n2":3.2
 }
```  

`Endpoints`  
 
| Endpoint              | Result                |
| ----------------------|  :------------------: | 
| GET /health-check     | App name              | 
| POST /math/add        | result of +, requre body    | 
| POST /math/subtract   | result of -, requre body    | 
| POST /math/divide     | result of /, requre body, cant divide by zero    | 
| POST /math/multiply   | result of *, requre body    | 

App implemented using Spring framework, package in fat-jar.
No needs to deploy on server, jar file contains embedded tomcat server. 

I chose simple application, which allow me to focus on main part of exam. Automation processes for CI & CD. 

## 2 Cloud 
I use Digital Ocean, because I had previous experience with it and account with 40$ there :)   
Machine stats:  
```
IP      : 95.85.15.63
OS      : Ubuntu 16.04.3 x64 
CORES   : 2
RAM     : 2GB
DISK    : 20GB
``` 
Access to machine via ssh or account on digital ocean. Access to account is 2-steps auth.  
Installations (I install it manually): 
* java
* jenkins
* kubectl
* minikube
* docker
* ansible

Next actions done:
* setup JAVA_HOME
* mv kubectl to /user/bin/
* generated rsa pub/private key as deployment key to access private repo on github.
* create jenkins user and add this user to docker, ansible, kubectl groups, to be able run commands without sudo.


Notes: 
I experienced lack of resources at the beginning for Jenkins server. 
I used 1 core and 512MB RAM machine, Jenkins works very slow and crushes once per 6 hours. Solution was to upgrade machine.  

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
