pipeline {
    agent any

    environment {
        PATH = "/opt/homebrew/bin:/usr/local/bin:${env.PATH}"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Compile') {
            steps {
                sh 'javac *.java'
            }
        }

        stage('Run') {
            steps {
                sh 'java Main'
            }
        }
    }

    post {
        success {
            echo 'Build and run completed successfully.'
        }
        failure {
            echo 'Build failed — check console output above.'
        }
    }
}
