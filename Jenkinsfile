pipeline {
    agent any

    tools {
        maven 'Maven Auto'
    }

    environment {
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-21.0.10'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                url: 'https://github.com/Rubiya-123/DevOps_Group7_FinalProject.git'
            }
        }

        stage('Build Backend') {
            steps {
                dir('TaskReminder') {
                    echo "Building backend..."

                    withCredentials([
                        string(credentialsId: 'DB_URL_T', variable: 'DB_URL_T'),
                        string(credentialsId: 'DB_USERNAME', variable: 'DB_USERNAME'),
                        string(credentialsId: 'DB_PASSWORD', variable: 'DB_PASSWORD'),
                        string(credentialsId: 'MAIL_HOST', variable: 'MAIL_HOST'),
                        string(credentialsId: 'MAIL_PORT', variable: 'MAIL_PORT'),
                        string(credentialsId: 'MAIL_USERNAME', variable: 'MAIL_USERNAME'),
                        string(credentialsId: 'MAIL_PASSWORD', variable: 'MAIL_PASSWORD'),
                        string(credentialsId: 'JWT_SECRET', variable: 'JWT_SECRET')
                    ]) {

                        bat """
                        mvn clean install -DskipTests ^
                        -DDB_URL_T=%DB_URL_T% ^
                        -DDB_USERNAME=%DB_USERNAME% ^
                        -DDB_PASSWORD=%DB_PASSWORD% ^
                        -DMAIL_HOST=%MAIL_HOST% ^
                        -DMAIL_PORT=%MAIL_PORT% ^
                        -DMAIL_USERNAME=%MAIL_USERNAME% ^
                        -DMAIL_PASSWORD=%MAIL_PASSWORD% ^
                        -DJWT_SECRET=%JWT_SECRET%
                        """
                    }
                }
            }
        }

        stage('Test Backend') {
            steps {
                dir('TaskReminder') {
                    echo "Running test stage (no real tests present)"
                    bat 'mvn test -DskipTests'
                }
            }
        }

       stage('Build Frontend') {
    steps {
        dir('frontend') {
            echo "Building frontend..."

            bat 'npm install'

            bat '''
                set CI=false
                npm run build
            '''
        }
    }
}

     stage('SonarQube Analysis') {
    steps {
        echo "SonarQube analysis (mocked - no server configured)"
    }
}

stage('Deploy to Dev') {
    steps {
        echo "Deploying to DEV (mock)"
    }
}

stage('Deploy to QAT') {
    steps {
        echo "Deploying to QAT (mock)"
    }
}

stage('Deploy to Staging') {
    steps {
        echo "Deploying to STAGING (mock)"
    }
}

stage('Deploy to Production') {
    steps {
        echo "Deploying to PROD (mock)"
    }
}
}   // closes stages

post {
    success {
        echo "Pipeline SUCCESS"
    }
    failure {
        echo "Pipeline FAILED - check logs"
    }
}
}
