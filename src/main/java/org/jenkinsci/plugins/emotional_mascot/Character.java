package org.jenkinsci.plugins.emotional_mascot;

/**
 * Created by hash on 2014/11/16.
 */
public enum Character {
    KONOHA("美雲 このは"),
    ANZU("美雲 あんず"),
    PRONAMA("暮井 慧"),
    CLAUDIA("クラウディア・窓辺"),
    UNITY("Unity-Chan"),
    QUERY("クエリ・ラブクラフト");

    public String name;

    Character(String name) {
        this.name = name;
    }
}
