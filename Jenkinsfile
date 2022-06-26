pipeline {
    agent any
    parameters {
        choice(name: 'VERSION', choices: ['1.0', '1.1.', '1.2'], description: '')
        booleanParam(name: 'executeTests', defaultValue: true, description: '')
    }
    tools {
        maven 'Maven'
        jdk 'java8'
    }
    environment {
        NEW_VERSION = '1.3.0'
    }
    stages {
        stage("build") {
            steps {
                echo "building the app.."
                sh """
                    mvn package
                """
            }
        }
        stage("test") {
            when {
                expression {
                    params.executeTests
                }
            }
            steps {
                echo "test the app.."
            }
        }
        stage("deploy") {
            steps {
                echo "deploy the app.."
                echo "version: ${params.VERSION}"
            }
        }  
    }
}