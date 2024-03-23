pipeline {
    agent any

    tools {
        // Install the Maven version configured as "Maven3" and add it to the path.
        maven "Maven3"
    }

    stages {
        stage('Build and Test') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Deploy') {
            steps {
                sh 'mvn -X clean deploy'
            }
        }
    }
}
