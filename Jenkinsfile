pipeline {
  agent any
  stages {
    stage('checkout project') {
      steps {
        checkout scm
      }
    }
    stage('check env') {
      parallel {
        stage('check mvn') {
          steps {
            sh 'mvn -v'
          }
        }
        stage('check java') {
          steps {
            sh 'java -version'
          }
        }
      }
    }

    stage('package') {
      steps {
        sh 'mvn package'
      }
    }

    stage('artifact') {
      steps {
        archiveArtifacts(artifacts: '**/target/*.jar', fingerprint: true)
      }
    }
    stage('deploy') {
      steps {
        sh 'make deploy-default'
      }
    }
  }

}