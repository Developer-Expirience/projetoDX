pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'tchuko0/dx-back'

    }

    stages {
        stage('Deploy') {
            steps {
                script {
                        sh "docker pull ${DOCKER_IMAGE}"
                        sh "docker run -d -p 80:8080 ${DOCKER_IMAGE}"
                }
            }
        }
    }
}
