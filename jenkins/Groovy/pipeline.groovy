import java.lang.System
import hudson.model.*
import jenkins.model.*
import javaposse.jobdsl.plugin.*
import hudson.triggers.TimerTrigger
import org.jenkinsci.plugins.workflow.job.WorkflowJob
import org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition
import org.jenkinsci.plugins.scriptsecurity.scripts.ScriptApproval


def home_dir = System.getenv("JENKINS_HOME")
def instance = Jenkins.getInstanceOrNull()

println ">>> Create Pipeline Job"

def project = instance.createProject(WorkflowJob.class, "Pipeline")
String pipelineScript = new File("$home_dir/config/initials/pipeline.file").text

project.setDefinition(new CpsFlowDefinition(pipelineScript))

project.save()
instance.reload()

ScriptApproval scriptApproval = ScriptApproval.get()
scriptApproval.pendingScripts.each {
    scriptApproval.approveScript(it.hash)
}