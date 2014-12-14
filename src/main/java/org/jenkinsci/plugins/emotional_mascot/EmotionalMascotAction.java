package org.jenkinsci.plugins.emotional_mascot;

import hudson.model.*;

import java.util.List;
import java.util.logging.Logger;

public final class EmotionalMascotAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(EmotionalMascotRecorder.class.getName());
    public  Emotion   emotion;
    public  Character character;
    private Project   project;

    public EmotionalMascotAction(Emotion emotion, Character character) {
        super();
        this.emotion   = emotion;
        this.character = character;
    }

    public EmotionalMascotAction(AbstractProject<?, ?> project) {
        super();
        this.project = (Project) project;
        refreshFace(this.project);
    }

    public EmotionalMascotAction(AbstractBuild<?, ?> build) {
        super();
        this.emotion = Emotion.get(build.getResult());
        this.character = Character.getCycle(build.getNumber());
    }

    public Emotion getEmotion() {
        if(this.project != null){
            refreshFace(this.project);
        }
        return emotion;
    }

    public Character getCharacter() {
        if(this.project != null){
            refreshFace(this.project);
        }
        return character;
    }

    public String getIconFileName() { return null;}

    public String getDisplayName() {
        return "";
    }

    public String getUrlName() {
        return "";
    }

    private void refreshFace(Project project){
        int currentBuildNum = project.getNextBuildNumber() - 1;
        int successCount = 0;
        List builders = project.getBuilders();

        if(builders.size() >= 5){
            int to = builders.size();
            int from = to - 5;
            List<Build> recentBuilds = builders.subList(from, to);

            for(Build build: recentBuilds){
                if(Result.SUCCESS.equals(build.getResult())){
                    successCount++;
                }
            }
            if(successCount >= 5){
                this.emotion = Emotion.GREAT;
            }else if(successCount >= 3){
                this.emotion = Emotion.WORRY;
            }else{
                this.emotion = Emotion.BAD;
            }
        }else if(builders.size() == 0){
            this.emotion = Emotion.WORKING;
        }else{
            this.emotion = Emotion.get(project.getLastBuild().getResult());
        }

        this.character = Character.getCycle(currentBuildNum);
    }
}
