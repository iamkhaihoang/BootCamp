// def scriptApproval = org.jenkinsci.plugins.scriptsecurity.scripts.ScriptApproval.get()

// String[] signs = [
//     "method org.jenkinsci.plugins.workflow.steps.FlowInterruptedException getCauses",
//     "method org.jenkinsci.plugins.workflow.support.steps.input.Rejection getUser"
//     ]

// for( String sign : signs ) {
//     scriptApproval.approveSignature(sign)
// }

// scriptApproval.save()

def buildJar() {
    echo "building the app.."
    sh 'mvn package'
}

def buildImage() {
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