pipeline {
    agent any

    parameters {
        string(name: 'ENV', defaultValue: 'dev', description: 'اختر البيئة')
        booleanParam(name: 'RUN_TESTS', defaultValue: true, description: 'هل تشغل الاختبارات؟')
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Using local files"
            }
        }

        stage('Setup Python') {
            steps {
                script {
                    def withPyEnv = load 'shared-lib/withPyEnv.groovy'
                    withPyEnv {
                        echo "Python setup completed"
                    }
                }
            }
        }

        stage('Install Dependencies') {
            steps {
                bat 'pip install -r requirements.txt'
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    if (params.RUN_TESTS) {
                        bat 'pytest tests/'
                    }
                }
            }
        }

        stage('Parallel Tasks') {
            parallel {
                stage('Task 1') {
                    steps {
                        echo "Running Task 1 in parallel"
                        bat "echo Task 1 complete"
                    }
                }
                stage('Task 2') {
                    steps {
                        echo "Running Task 2 in parallel"
                        bat "echo Task 2 complete"
                    }
                }
            }
        }

        stage('Print Shared Library Value') {
            steps {
                script {
                    def bounds = load 'shared-lib/bounds.groovy'
                    echo "Bounds Value: ${bounds()}"
                }
            }
        }
    }

    post {
        success {
            script {
                def notify = load 'shared-lib/notify.groovy'
                notify("SUCCESS")
            }
        }
        failure {
            script {
                def notify = load 'shared-lib/notify.groovy'
                notify("FAILURE")
            }
        }
    }
}
