pipeline {
    agent any

    tools {
        // Install the Maven version configured as "Maven3" and add it to the path.
        maven "Maven3"
    }

    environment {
        APPLICATION_SOURCE_JAR_PATH = "${env.WORKSPACE}/application/target/application.jar"
        APPLICATION_TARGET_DIR_PATH = "/var/jenkins_home/latest_build"
        APPLICATION_TARGET_JAR_PATH = "${APPLICATION_TARGET_DIR_PATH}/application.jar"
    }

    stages {
        stage('Initialize'){
            steps {
                script {
                    def dockerHome = tool 'Docker'
                    env.PATH = "${dockerHome}/bin:${env.PATH}"
                }
            }
        }
        stage('Build') {
            steps {
                publishChecks name: 'docker-builder', status: 'QUEUED'
                publishChecks name: 'build-test', title: 'Build and Test', status: 'QUEUED'

                // Run Maven on a Unix agent.
                sh "mvn -Dmaven.test.failure.ignore=false clean package"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    sh "mkdir -p ${APPLICATION_TARGET_DIR_PATH}"
                    sh "cp -a ${APPLICATION_SOURCE_JAR_PATH} ${APPLICATION_TARGET_JAR_PATH}"
                    publishChecks name: 'build-test'
                    archiveArtifacts '**/target/*.jar'
                }
            }
        }
        stage('Coverage') {
            steps {
                script {
                    jacoco()
                }
            }
        }
        stage('Build docker image'){
            when {
               branch "dev"
            }
            steps{
                script{
                    publishChecks name: 'docker-builder', status: 'IN_PROGRESS'
                    sh "docker build -t springboot-deploy:${env.BUILD_NUMBER} ."
                }
            }

            post {
                success {
                    publishChecks name: 'docker-builder'
                }
                failure {
                    publishChecks conclusion: 'FAILURE', name: 'docker-builder'
                }
            }
        }
        stage('Deploy to docker'){
            when {
               branch "dev"
            }
            steps{
                script{
                    sh "docker stop springboot-deploy || true && docker rm springboot-deploy || true"
                    configFileProvider([configFile(fileId: '4f6af0b4-9d12-4f73-ae16-ccce994d6162', variable: 'APP_ENV_VARS')]) {
                        sh "docker run --name springboot-deploy -d --env-file $APP_ENV_VARS -p 9999:9999 springboot-deploy:${env.BUILD_NUMBER}"
                    }
                }
            }
        }
    }
}
