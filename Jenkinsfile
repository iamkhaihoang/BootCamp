pipeline {
    agent any
    environment {
        NEW_VERSION = '1.3.0'
        SERVER_CREDENTIALS = credentials('github-credential')
    }
    stages {
        stage("build") {
            steps {
                echo "building the app.."
                echo "building version ${NEW_VERSION}"
            }
        }
        stage("test") {
            steps {
                echo "test the app.."
            }
        }
        stage("deploy") {
            steps {
                echo "deploy the app.."
                echo "deploying with ${SERVER_CREDENTIALS}"
                withCredentials([
                    usernamePassword(credentials: 'github-credential', usernameVariable: USER, passwordVariable: PWD)
                ]) {
                    sh "some script ${USER} ${PWD}"
                }
            }
        }  
    }
}