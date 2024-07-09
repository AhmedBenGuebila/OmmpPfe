pipeline {
    agent any

    environment {
        DOCKER_IMAGE_NAME = 'ommp'
        DOCKER_IMAGE_TAG = "v${BUILD_NUMBER}" // Utilisation du BUILD_NUMBER de Jenkins comme tag
    }

    stages {
        stage('Setup') {
            steps {
                script {
                    // Configurer les tampons Git
                    sh 'git config --global http.postBuffer 1048576000'
                    sh 'git config --global http.maxRequestBuffer 1048576000'
                    sh 'git config --global core.compression 0'
                    sh 'git config --global http.lowSpeedLimit 0'
                    sh 'git config --global http.lowSpeedTime 999999'
                }
            }
        }

        stage('Checkout') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/Devops']],
                    doGenerateSubmoduleConfigurations: false,
                    extensions: [[$class: 'CloneOption', timeout: 30]],
                    userRemoteConfigs: [[url: '<repository-url>']]
                ])
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test with Maven') {
            steps {
                sh 'mvn test'
            }
        }
    }

    post {
        success {
            echo 'Build successfully'
        }
        failure {
            echo 'Build failed'
        }
    }
}
