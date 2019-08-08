pipeline {
    agent any
    stages {
        stage ('Initialize') {
            steps {
				sh 'echo "initial-------------"'
            }
        }

        stage ('Build') {
            steps {
				sh 'echo "Build-------------"'
                sh 'mvn -Dmaven.test.failure.ignore=true install' 
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml' 
                }
            }
        }
		
		 stage ('Package') {
            steps {
				sh 'echo "Pakcage-------------"'
                sh 'mvn -Dmaven.test.failure.ignore=true package' 
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml' 
                }
            }
        }
    }
}