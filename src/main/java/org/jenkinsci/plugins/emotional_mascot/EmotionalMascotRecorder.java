package org.jenkinsci.plugins.emotional_mascot;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.Action;
import hudson.model.BuildListener;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Publisher;
import hudson.tasks.Recorder;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

import java.io.IOException;
import java.util.logging.Logger;

public class EmotionalMascotRecorder extends Recorder {

    @DataBoundConstructor
    public EmotionalMascotRecorder(){}

    @Override
    public Action getProjectAction(AbstractProject<?, ?> project) {
        return new EmotionalMascotAction(project);
    }

    @Override
    public BuildStepDescriptor getDescriptor() {
        return DESCRIPTOR;
    }

    public boolean perform(final AbstractBuild<?, ?> build,
                           final Launcher launcher, final BuildListener listener) throws InterruptedException, IOException {
        build.replaceAction(new EmotionalMascotAction(build));
        return true;
    }

    public BuildStepMonitor getRequiredMonitorService() {
        return BuildStepMonitor.BUILD;
    }

    @Extension
    public static final DescriptorImpl DESCRIPTOR = new DescriptorImpl();

    public static final class DescriptorImpl extends BuildStepDescriptor<Publisher>{

        DescriptorImpl(){
            super(EmotionalMascotRecorder.class);
            load();
        }

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> jobType) {
            return true;
        }

        @Override
        public String getDisplayName() {
            return "Summon Your Mascot";
        }

        @Override
        public boolean configure(StaplerRequest req, JSONObject json) throws FormException {
            save();
            return super.configure(req, json);
        }

        @Override
        public Publisher newInstance(StaplerRequest req, JSONObject formData) throws FormException {
            return new EmotionalMascotRecorder();
        }
    }
}
