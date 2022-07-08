#!/usr/bin/env groovy

library identifier: 'jenkins-shared-libray@main', retriever: modernSCM(
    [$class: 'GitSCMSource',
    remote: 'https://github.com/iamkhaihoang/jenkins-shared-library.git',
    credentialsId: 'github-credential'
    ]
)

def gv

pipeline {
    agent any
    environment {
        ENV = "staging" 
        VERSION = "1.0.1"
        DOCKERHUB_REPO_NAME = "iamkhaihoang/demo-app"
    }
//    parameters {
        //choice(name: 'VERSION', choices: ['1.0', '1.1', '1.2'], description: '')
        //booleanParam(name: 'executeTests', defaultValue: true, description: '')
//    }
    tools {
        maven 'Maven'
        jdk 'java8'
    }
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("test") {
            steps {
                script {
                    gv.testApp()
                }
            }
        }
        stage("Build jar") {
            // when {
            //     expression {
            //         BRANCH_NAME == 'main'
            //     }
            // }
            steps {
                script {
                    buildJar()
                }
            }
        }
        stage("Build and Push Docker image") {
            // when {
            //     expression {
            //         BRANCH_NAME == 'main'
            //     }
            // }
            steps {
                script {
                   buildImage "$DOCKERHUB_REPO_NAME:$VERSION"
                   dockerLogin()
                   dockerPush "$DOCKERHUB_REPO_NAME:$VERSION"
                }
            }
        }
                
        stage("deploy") {
            // when {
            //     expression {
            //         BRANCH_NAME == 'main'
            //     }
            // }
            // input {
            //     message "Select the environment to deploy to:"
            //     ok "Done"
            //     parameters {
            //         choice(name: 'ENV', choices: ['dev', 'staging', 'production'], description: '')
            //     }
            // }
            steps {
                script {
            //        env.ENV = input message: "Select the environment to deploy to:", ok:"Done", parameters: [choice(name: 'ENV', choices: ['dev', 'staging', 'production'], description: '')]
                    // gv.deployApp()
                    echo "Deploying to EC2..."
                    //def dockerCmd = 'docker run -d iamkhaihoang/demo-app:1.0.1'
                    def dockerComposeCmd = "docker-compose -f docker-compose.yaml up --detach"
                    def EC2-IP = "18.222.51.205"
                    sshagent(['ec2-server-key']) {
                        sh "scp docker-compose.yaml ec2-user@EC2-IP$:/home/ec2-user"
                        sh "ssh -o StrictHostKeyChecking=no ec2-user@EC2-IP ${dockerComposeCmd}"
                    }
                }
            }
        }  
    }
}