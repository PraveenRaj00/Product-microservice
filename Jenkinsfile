pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from your Git repository
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/PraveenRaj00/Product-microservice.git']]])
            }
        }

        stage('Build') {
            steps {
                // Build the Maven project for Product-microservice
                script {
                    def mvnHome = tool 'Maven'
                    if (isUnix()) {
                        sh "'${mvnHome}/bin/mvn' -f Product-microservice/pom.xml clean install"
                    } else {
                        bat(/"${mvnHome}\bin\mvn" -f Product-microservice\pom.xml clean install/)
                    }
                }
            }
        }

        stage('Test') {
            steps {
                // Run Maven test phase for Product-microservice
                script {
                    def mvnHome = tool 'Maven'
                    if (isUnix()) {
                        sh "'${mvnHome}/bin/mvn' -f Product-microservice/pom.xml test"
                    } else {
                        bat(/"${mvnHome}\bin\mvn" -f Product-microservice\pom.xml test/)
                    }
                }
            }
        }
    }

    post {
        always {
            // Clean up or perform any other actions after the build
            cleanWs()
        }
    }
}
