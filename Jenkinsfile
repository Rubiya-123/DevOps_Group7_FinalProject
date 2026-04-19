pipeline {
    agent any

    triggers {
        pollSCM('* * * * *') // checks every minute
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Rubiya-123/DevOps_Group7_FinalProject.git'
            }
        }

        stage('Build Backend') {
            steps {
                dir('backend') {
                    bat 'mvn clean install'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('frontend') {
                    bat 'npm install'
                    bat 'npm run build'
                }
            }
        }

        stage('Test') {
            steps {
                dir('backend') {
                    bat 'mvn test'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo 'Running SonarQube (mock)'
            }
        }

        stage('Deliver') {
            steps {
                echo 'Packaging artifact'
            }
        }

        stage('Deploy to Dev') {
            steps {
                echo 'Deploying to DEV'
            }
        }

        stage('Deploy to QAT') {
            steps {
                echo 'Deploying to QAT'
            }
        }

        stage('Deploy to Staging') {
            steps {
                echo 'Deploying to STAGING'
            }
        }

        stage('Deploy to Production') {
            steps {
                echo 'Deploying to PROD'
            }
        }
    }
}
