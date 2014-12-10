package org.jenkinsci.plugins.emotional_mascot;

import hudson.model.*;

import java.util.logging.Logger;

public final class EmotionalMascotAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(EmotionalMascotRecorder.class.getName());
    public Emotion   emotion;
    public Character character;

    public EmotionalMascotAction() {
    }

    public EmotionalMascotAction(Emotion emotion, Character character) {
        super();
        this.emotion   = emotion;
        this.character = character;
    }

    public EmotionalMascotAction(AbstractProject<?, ?> project) {
        super();
        int currentBuildNum = project.getNextBuildNumber() - 1;
        int successCount = 0;
        if(currentBuildNum >= 5){
            for(int i = 0; i < 5; i++){
                AbstractBuild<?, ?> build = project.getBuildByNumber(currentBuildNum - i);
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
        }else{
            this.emotion = Emotion.get(project.getLastBuild().getResult());
        }
        this.character = Character.getCycle(currentBuildNum);
        LOGGER.info(Integer.toString(successCount));
    }

    public Emotion getEmotion() {
        if(emotion == null) LOGGER.info("emotion is blank");
        return emotion;
    }

    public Character getCharacter() {
        if(character == null) LOGGER.info("character is blank");
        return character;
    }

    public String getIconFileName() { return null;}

    public String getDisplayName() {
        return "";
    }

    public String getUrlName() {
        return "";
    }
}
