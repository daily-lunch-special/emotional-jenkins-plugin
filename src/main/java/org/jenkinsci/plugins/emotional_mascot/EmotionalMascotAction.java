package org.jenkinsci.plugins.emotional_mascot;

import hudson.model.ProminentProjectAction;

public final class EmotionalMascotAction implements ProminentProjectAction {

    public Emotion   emotion;
    public Character character;

    public EmotionalMascotAction() {
    }

    public EmotionalMascotAction(Emotion emotion, Character character) {
        super();
        this.emotion   = emotion;
        this.character = character;
    }

    public String getIconFileName() { return null;}

    public String getDisplayName() {
        return "";
    }

    public String getUrlName() {
        return "";
    }
}
