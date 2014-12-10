package org.jenkinsci.plugins.emotional_mascot;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.Action;
import hudson.model.BuildListener;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Notifier;
import hudson.tasks.Publisher;
import org.kohsuke.stapler.DataBoundConstructor;

import java.io.IOException;

/**
 * Created by hash on 2014/12/09.
 */
public class EmotionalMascotNotifier extends Notifier {

    @Override
    public BuildStepMonitor getRequiredMonitorService() {
        return BuildStepMonitor.NONE;
    }
    
    @DataBoundConstructor
    public EmotionalMascotNotifier(){}

    @Override
    public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) throws InterruptedException, IOException {
        Emotion emotion = Emotion.get(build.getResult());
        Character character = Character.getCycle(build.getNumber());
        build.addAction(new EmotionalMascotAction(emotion, character));
        return true;
    }

    @Override
    public Action getProjectAction(AbstractProject<?, ?> project) {
        return new EmotionalMascotAction(project);
    }

    @Extension
    public static final DescriptorImpl DESCRIPTOR = new DescriptorImpl();

    public static class DescriptorImpl extends BuildStepDescriptor{

        @Override
        public boolean isApplicable(Class jobType) {
            return true;
        }

        @Override
        public String getDisplayName() {
            return null;
        }
    }

    @Override
    public BuildStepDescriptor<Publisher> getDescriptor() {
        return DESCRIPTOR;
    }
}
