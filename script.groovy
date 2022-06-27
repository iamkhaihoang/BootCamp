def buildJar() {
    echo "building the app.."
    sh 'mvn package'
}

def buildDockerImage() {
    echo "Building the Docker image..."
    withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t iamkhaihoang/demo-app:1.0 .'
        sh 'echo $PASS | docker login -u $USER --password-stdin'
        sh 'docker push iamkhaihoang/demo-app:1.0'
    }
}

def testApp() {
    echo "test the app.."
}

def deployApp() {
    echo "deploy the app.."
    echo "version: ${params.VERSION}"
}

return this