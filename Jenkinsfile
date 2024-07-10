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
                    userRemoteConfigs: [[url: 'https://github.com/AhmedBenGuebila/OmmpPfe.git']]
                ])
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
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

                                            //    sh '''
                                            //    #!/bin/bash
                                            //    NEXUS_URL="http://localhost:8081"
                                            //    REPOSITORY="maven-releases"
                                            //    GROUP_ID="org.ommp"
                                            //    ARTIFACT_ID="ommpSpring"
                                            //    VERSION="0.0.1"
                                            //    USERNAME="admin"
                                            //    PASSWORD="ahmed2000"

                                            //    response=$(curl -u "$USERNAME:$PASSWORD" -s "$NEXUS_URL/service/rest/v1/search?repository=$REPOSITORY&group=$GROUP_ID&name=$ARTIFACT_ID&version=$VERSION")

                                            //    if echo "$response" | grep -q "$ARTIFACT_ID"; then
                                             //       echo "Artefact trouvé, exécution de nexus-staging:drop"
                                             //       mvn nexus-staging:drop -DnexusUrl="$NEXUS_URL/repository/$REPOSITORY"
                                            //        mvn deploy -DskipTests=true
                                            //    else
                                            //        echo "Artefact non trouvé, pas besoin d'exécuter nexus-staging:drop"
                                             //       mvn deploy -DskipTests=true
                                            //    fi
                                            //
                                            //    '''


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
