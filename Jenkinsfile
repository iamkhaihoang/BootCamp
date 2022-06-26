pipeline {
    agent any
    environment {
        IMAGE_TAG = "1.0"
    }
    stages {
        stage("build") {
            steps {
                sh """
                    mvn package
                """
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
            }
        }  
    }
}