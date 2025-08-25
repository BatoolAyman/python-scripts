// shared-lib/withPyEnv.groovy
def call(Closure body) {
    echo "🔧 Setting up Python environment..."
    bat 'python --version'
    bat 'pip --version'
    body()
}
