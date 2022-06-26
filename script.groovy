def buildApp() {
    echo "building the app.."
    sh """
        mvn package
    """
}

def testApp() {
    echo "test the app.."
}

def deployApp() {
    echo "deploy the app.."
    echo "version: ${params.VERSION}"
}

return this