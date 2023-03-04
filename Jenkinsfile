// declarative pipeline
pipeline {
    agent any
    //agent { docker { image "maven:3.8.6" } }
    
    environment {
        dockerHome = tool "myDocker"
        mavenHome = tool "myMaven"
        PATH = "$dockerHome/bin:$mavenHome/bin:$PATH"
    }
    
    stages {
        stage ("Checkout") {
            steps {
                sh "mvn --version"
                sh "docker version"
                echo "Build"
                echo "PATH - $PATH"
                echo "JOB_NAME - $env.JOB_NAME"
                echo "BUILD_NUMBER - $env.BUILD_NUMBER"
                echo "BUILD_ID - $env.BUILD_ID"
                echo "BUILD_TAG - $env.BUILD_TAG"
                echo "BUILD_URL - $env.BUILD_URL"
            }
        }
        
        stage ("Compile") {
            steps {
                sh "mvn clean compile"
            }
        }
        
        stage ("Test") {
            steps {
                sh "mvn test"
            }
        }
        
        stage ("Integration Test") {
            steps {
                sh "mvn failsafe:integration-test failsafe:verify"
            }
        }
        
        stage ("Package") {
            steps {
                sh "mvn package -DskipTests"// -DskipTests because we already ran the tests
            }
        }
        
        stage ("Build docker image") {
            steps {
                //"docker build -t joeymars2323/adminvic-backend:$env.BUILD_TAG"
                script {
                    dockerImage = docker.build("joeymars2323/adminvic-backend:${env.BUILD_TAG}");
                }
            }
        }
        
        // add dockerhub credencials to jenkins
        stage ("Push docker image") {
            steps {
                script {
                    docker.withRegistry("", "dockerhub") {//dockerhub is the id registered in jenkins
                        dockerImage.push();
                        dockerImage.push("latest");
                    }
                }
            }
        }
    }
    
    post {
        always {
            echo "execute always"
        }
        success {
            echo "execute in success"
        }
        failure {
            echo "execute in failure"
        }
        changed {
            echo "execute when the status change"
        }
    }
}
