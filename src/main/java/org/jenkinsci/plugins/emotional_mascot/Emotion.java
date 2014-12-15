package org.jenkinsci.plugins.emotional_mascot;

import hudson.model.Result;

public enum Emotion {
    GREAT, BAD, WORRY, WORKING;

    public static Emotion byResult(final Result result) {
        Emotion emotion;
        if (Result.FAILURE.equals(result)) {
            emotion = BAD;
        } else if (Result.SUCCESS.equals(result)) {
            emotion = GREAT;
        } else {
            emotion = WORRY;
        }
        return emotion;
    }
}
