package org.jenkinsci.plugins.emotional_mascot;

public enum Character {
    PRONAMA, CONOHA, ANZU, CLAUDIA, UNITY, QUERY;

    public static Character byRotation(int num) {
        Character[] list = Character.class.getEnumConstants();
        int at = num % list.length;
        return list[at];
    }
}
