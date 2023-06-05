pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'tchuko0/dx-back'

    }

    stages {
        stage('Deploy') {
            steps {
                script {
                        sh "sudo chmod 666 /var/run/docker.sock"
                        sh "docker rm -f app || true"
                        sh "docker pull ${DOCKER_IMAGE}"
                        sh "docker run -d --name app -p 8091:8080 ${DOCKER_IMAGE}"
                }
            }
        }
    }
}
