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
  
  post {
    always {
      echo 'I will always say Hello again!'
    }
    success {
      echo 'success!'
      // slackSend channel: '#integration', color: 'good', message: "success ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)", teamDomain: 'agileworks-tw', token: 'JhXFKEl6cBFoQ4v52BEJw9Mr'
    }
    failure {
      echo 'failure!'
      // slackSend channel: '#integration', color: 'danger', message: "fail ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)", teamDomain: 'agileworks-tw', token: 'JhXFKEl6cBFoQ4v52BEJw9Mr'
    }
  }
}