pipeline {
    agent any

    environment {
        DOCKER_IMAGE_NAME = 'ommp'
        DOCKER_IMAGE_TAG = "v100"
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

        stage('SonarQube ') {

                     steps {

                            sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=ahmed2000'
                           }
                     }

         stage('Deploy') {
                     steps {
                     sh 'mvn deploy -DskipTests=true'

                        }
                             }

                stage('building docker image')
                {
                     steps {
                                     sh 'docker build -t $DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG -f Dockerfile ./'
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
