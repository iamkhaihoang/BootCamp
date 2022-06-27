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
        stage("Build jar") {
            steps {
                script {
                    echo "Building the app..."
                    sh 'mvn package'
                }
            }
        }
        stage("Build Docker image") {
            steps {
                script {
                    echo "Building the Docker image..."
                    withCredentials([usernamePassword(credentialId: 'dockerhub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        sh 'docker build -t iamkhaihoang/demo-app:1.0 .'
                        sh 'echo $PASS | docker login -u $USER --password-stdin'
                        sh 'docker push iamkhaihoang/demo-app:1.0'
                    }
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