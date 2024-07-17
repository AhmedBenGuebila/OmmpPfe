pipeline {
    agent any

    environment {
        DOCKER_IMAGE_NAME = 'ommp'
        DOCKER_IMAGE_TAG = "v${BUILD_NUMBER}"
        DOCKERHUB_REPO = 'ahmed1990909/ommppfe'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
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

        stage('SonarQube') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=ahmed2000'
            }
        }

        stage('Deploy') {
            steps {
                sh 'mvn deploy -DskipTests=true'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG} -f Dockerfile ./"
            }
        }

        stage('Push to DockerHub') {
            steps {
                sh "docker login -u ahmed1990909 -p ahmed2000"
                sh "docker tag ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG} ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}"
                sh "docker push ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}"
            }
        }
stage('Prepare Docker Compose') {
            steps {
                script {
                    writeFile file: '.env', text: "BUILD_NUMBER=${env.BUILD_NUMBER}\n"
                }
            }
        }
        stage('Run Docker Compose and OMMP Project') {
            steps {
                sh 'docker compose up -d'
            }
        }
    }

     post {
            success {
                mail to: "ahmed.benguebila@esprit.tn",
                subject: "Pipeline Backend Success",
                body: " project ommppfe  Backend : Success on job ${env.JOB_NAME}, Build Num: ${env.BUILD_NUMBER}, Build URL: ${env.BUILD_URL}"
            }
            failure {
                mail to: "ahmed.benguebila@esprit.tn",
                subject: "Pipeline backend Failure",
                body: "project ommppfe  Backend : Failure on job ${env.JOB_NAME}, Build Num: ${env.BUILD_NUMBER}, Build URL: ${env.BUILD_URL} "
            }
        }
}
