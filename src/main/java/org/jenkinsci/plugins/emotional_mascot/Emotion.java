package org.jenkinsci.plugins.emotional_mascot;

import hudson.model.Result;

/**
 * Created by Hi on 2014/11/15.
 */
public enum Emotion {
    GREAT, BAD, WORRY;

    public static Emotion get(final Result result) {
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
