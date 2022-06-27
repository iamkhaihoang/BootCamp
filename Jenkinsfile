pipeline {
    agent any
    environment {
        ENV = "staging" 
    }
    parameters {
        choice(name: 'VERSION', choices: ['1.0', '1.1', '1.2'], description: '')
        //booleanParam(name: 'executeTests', defaultValue: true, description: '')
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
        stage("test") {
            steps {
                script {
                    gv.testApp()
                }
            }
        }
        stage("Build jar") {
            when {
                expression {
                    BRANCH_NAME == 'main'
                }
            }
            steps {
                script {
                    gv.buildJar()
                }
            }
        }
        stage("Build Docker image") {
            when {
                expression {
                    BRANCH_NAME == 'main'
                }
            }
            steps {
                script {
                   gv.buildDockerImage()
                }
            }
        }
                
        stage("deploy") {
            when {
                expression {
                    BRANCH_NAME == 'main'
                }
            }
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
                    gv.deployApp()
                    echo "Deploying to ${ENV}"
                }
            }
        }  
    }
}