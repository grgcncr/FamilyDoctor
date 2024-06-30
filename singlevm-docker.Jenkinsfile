pipeline {
    agent any
   
    options {
        buildDiscarder(logRotator(numToKeepStr: '30', artifactNumToKeepStr: '30'))
    }
    environment {
         DOCKER_TOKEN = credentials('git-key')
        DOCKER_USER = 'ThanosZappas'
        DOCKER_SERVER = 'ghcr.io'
        DOCKER_PREFIX = 'ghcr.io/thanoszappas/ds-spring'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'version1', url: 'git@github.com:grgcncr/FamilyDoctor.git'
            }   
        }
        
        stage('Install docker') {
                steps {
                    sh '''
                        export ANSIBLE_CONFIG=~/workspace/ansible-singlevm-docker/ansible.cfg
                        ansible-playbook -i ~/workspace/ansible-singlevm-docker/hosts.yaml -l azure-db-server ~/workspace/ansible-singlevm-docker/playbooks/docker.yaml
                    '''
                }
        }

        stage('Docker build and push') {
            steps {
                sh '''
                    HEAD_COMMIT=$(git rev-parse --short HEAD)
                    TAG=$HEAD_COMMIT-$BUILD_ID
                    docker build --rm -t $DOCKER_PREFIX:$TAG -t $DOCKER_PREFIX:latest -f nonroot.Dockerfile .
                    echo $DOCKER_TOKEN | docker login $DOCKER_SERVER -u $DOCKER_USER --password-stdin
                    docker push $DOCKER_PREFIX --all-tags
                '''
            }
        }
        stage('run ansible pipeline') {
            steps {
                build job: 'ansible'
            }
        }
        stage('Install project with docker compose') {
                    steps {
                        sh '''
                            export ANSIBLE_CONFIG=~/workspace/ansible-singlevm-docker/ansible.cfg
                            ansible-playbook -i ~/workspace/ansible-singlevm-docker/hosts.yaml -l azure-db-server ~/workspace/ansible-singlevm-docker/playbooks/docker-compose-test.yaml
                        '''
                    }
         }
    }

}