import java.lang.System
import hudson.model.*
import jenkins.model.*

def home_dir = System.getenv("JENKINS_HOME")
def numExecutorsOnMaster = 1

println ">>> set number of executors on master to ${numExecutorsOnMaster}"
Jenkins.instance.setNumExecutors(numExecutorsOnMaster)