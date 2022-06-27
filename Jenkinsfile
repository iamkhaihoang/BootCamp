pipeline {
    agent any
    parameters {
        choice(name: 'VERSION', choices: ['1.0', '1.1', '1.2'], description: '')
        booleanParam(name: 'executeTests', defaultValue: true, description: '')
    }
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
        stage("Build jar") {
            steps {
                script {
                    gv.buildJar()
                }
            }
        }
        stage("Build Docker image") {
            steps {
                script {
                   gv.buildDockerImage()
                }
            }
        }
                
        stage("test") {
            when {
                expression {
                    params.executeTests
                }
            }
            steps {
                script {
                    gv.testApp()
                }
            }
        }
        stage("deploy") {
            // input {
            //     message "Select the environment to deploy to:"
            //     ok "Done"
            //     parameters {
            //         choice(name: 'ENV', choices: ['dev', 'staging', 'production'], description: '')
            //     }
            // }
            steps {
                script {
                    env.ENV = input message: "Select the environment to deploy to:", ok:"Done", parameters: [choice(name: 'ENV', choices: ['dev', 'staging', 'production'], description: '')]
                    gv.deployApp()
                    echo "Deploying to ${ENV}"
                }
            }
        }  
    }
}