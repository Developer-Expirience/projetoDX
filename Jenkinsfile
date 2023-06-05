pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'tchuko0/dx-back'

    }

    stages {
        stage('Deploy') {
            steps {
                script {
                        sh "docker stop f7f2d3e54ef91fb50863a225bc916dd4be5b52211460f2138eef4b7cf42c5da1"
                        sh "docker pull ${DOCKER_IMAGE}"
                        sh "docker run -d -p 8090:8080 ${DOCKER_IMAGE}"
                }
            }
        }
    }
}
