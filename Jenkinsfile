pipeline {
    agent any

    triggers {
        pollSCM('* * * * *') // check GitHub every minute
    }

    tools {
        maven 'Maven Auto'   // make sure this exists in Jenkins
    }

    environment {
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-21.0.10'
        PATH = "${tool 'Maven Auto'}\\bin;${env.PATH}"
    }

    stages {

        stage('Build Backend') {
            steps {
                dir('TaskReminder') {
                    echo "Building backend..."
                    bat 'mvn clean install'
                }
            }
        }

        stage('Test Backend') {
            steps {
                dir('TaskReminder') {
                    echo "Running backend tests..."
                    bat 'mvn test'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('frontend') {
                    echo "Building frontend..."
                    bat 'npm install'
                    bat 'npm run build'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo 'Skipping for now (can add later)'
            }
        }

        stage('Deliver') {
            steps {
                echo 'Packaging artifact (JAR + frontend build)'
            }
        }

        stage('Deploy to Dev') {
            steps {
                echo 'Deploying to DEV (placeholder)'
            }
        }

        stage('Deploy to QAT') {
            steps {
                echo 'Deploying to QAT (placeholder)'
            }
        }

        stage('Deploy to Staging') {
            steps {
                echo 'Deploying to STAGING (placeholder)'
            }
        }

        stage('Deploy to Production') {
            steps {
                echo 'Deploying to PROD (placeholder)'
            }
        }
    }

    post {
        success {
            echo "Pipeline completed successfully!"
        }
        failure {
            echo "Pipeline failed. Check logs."
        }
    }
}
