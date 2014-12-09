package org.jenkinsci.plugins.emotional_mascot;

import hudson.Launcher;
import hudson.model.*;
import hudson.model.Fingerprint.RangeSet;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Builder;
import hudson.tasks.Recorder;
import org.kohsuke.stapler.DataBoundConstructor;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.logging.Logger;

public class EmotionalMascotRecorder extends Recorder {

    private static final Logger LOGGER = Logger.getLogger(EmotionalMascotRecorder.class.getName());

    @DataBoundConstructor
    public EmotionalMascotRecorder(){}

    /*
     *
     */
    @Override
    public final Action getProjectAction(final AbstractProject<?, ?> project) {
        int currentBuildNum = project.getNextBuildNumber() - 1;
        int successCount = 0;
        Action action;
        Character character = Character.getCycle(currentBuildNum);
        if(currentBuildNum >= 5){
            for(int i = 0; i < 5; i++){
                AbstractBuild<?, ?> build = project.getBuildByNumber(currentBuildNum - i);
                if(Result.SUCCESS.equals(build.getResult())){
                    successCount++;
                }
            }
            if(successCount >= 5){
                action = new EmotionalMascotAction(Emotion.GREAT, character);
                LOGGER.info("GREAT");
            }else if(successCount >= 3){
                action = new EmotionalMascotAction(Emotion.WORRY, character);
                LOGGER.info("WORRY");
            }else{
                action = new EmotionalMascotAction(Emotion.BAD, character);
                LOGGER.info("BAD");
            }
        }else{
            action = new EmotionalMascotAction(Emotion.get(project.getLastBuild().getResult()), character);
        }
        return action;
    }

    /**
     * Adds RoundhouseAction to the build actions. This is applicable for each
     * build.
     *
     * @param build    the build
     * @param launcher the launcher
     * @param listener the listener
     * @return true
     * @throws InterruptedException when there's an interruption
     * @throws IOException          when there's an IO error
     */
    public boolean perform(final AbstractBuild<?, ?> build,
                           final Launcher launcher, final BuildListener listener) throws InterruptedException, IOException {
        LOGGER.info("calling Recorder#perform");
        Emotion emotion = Emotion.get(build.getResult());
        Character character = Character.getCycle(build.getNumber());
        build.addAction(new EmotionalMascotAction(emotion, character));
        return true;
    }

    public BuildStepMonitor getRequiredMonitorService() {
        return BuildStepMonitor.BUILD;
    }
}
