package org.jenkinsci.plugins.emotional_mascot;

import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Publisher;

/**
 * Created by Hi on 2014/11/15.
 */
@Extension
public class EmotionalMascotDescriptor extends BuildStepDescriptor<Publisher> {

    public EmotionalMascotDescriptor() {
        super(EmotionalMascotRecorder.class);
    }

    @Override
    public String getDisplayName() {
        return "Summon Your Mascot";
    }

    @Override
    public boolean isApplicable(Class<? extends AbstractProject> aClass) {
        return true;
    }
}
