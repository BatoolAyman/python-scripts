def call(body) {
    node {
        stage('Setup Python') {
            bat 'python --version'
            bat 'pip install -r requirements.txt'
        }
        body()
    }
}