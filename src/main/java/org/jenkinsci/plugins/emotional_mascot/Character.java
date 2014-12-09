package org.jenkinsci.plugins.emotional_mascot;

/**
 * Created by hash on 2014/11/16.
 */
public enum Character {
    CONOHA, ANZU, PRONAMA, CLAUDIA, UNITY, QUERY;

    public static Character getCycle(int num) {
        Character[] list = Character.class.getEnumConstants();
        int at = num % list.length;
        return list[at];
    }
}
