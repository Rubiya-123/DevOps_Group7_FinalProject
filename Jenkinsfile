pipeline {
    agent any

    triggers {
        pollSCM('* * * * *') // checks every minute
    }

    stages {

        stage('Checkout') {
            steps {
                git 'YOUR_GITHUB_REPO_URL'
            }
        }

        stage('Build Backend') {
            steps {
                dir('backend') {
                    sh 'mvn clean install'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('frontend') {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }

        stage('Test') {
            steps {
                dir('backend') {
                    sh 'mvn test'
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
