package org.jenkinsci.plugins.emotional_mascot;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Publisher;
import hudson.tasks.Recorder;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.StaplerRequest;

import java.io.IOException;
import java.util.logging.Logger;

public class EmotionalMascotRecorder extends Recorder {

    private static final Logger LOGGER = Logger
            .getLogger(EmotionalMascotRecorder.class.getName());

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
        build.addAction(new EmotionalMascotAction(emotion));
        return true;
    }

    public BuildStepMonitor getRequiredMonitorService() {
        return BuildStepMonitor.BUILD;
    }
}
