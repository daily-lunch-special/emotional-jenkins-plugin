package org.jenkinsci.plugins.emotional_mascot;

import hudson.model.ProminentProjectAction;

public final class EmotionalMascotAction implements ProminentProjectAction {

    public Emotion emotion;

    public EmotionalMascotAction() {
    }

    public EmotionalMascotAction(Emotion emotion) {
        super();
        this.emotion = emotion;
    }

    public String getIconFileName() {
        return null;
    }

    public String getDisplayName() {
        return "";
    }

    public String getUrlName() {
        return "";
    }
}
