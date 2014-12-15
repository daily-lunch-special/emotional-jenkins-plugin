package org.jenkinsci.plugins.emotional_mascot;

import hudson.model.*;

import java.util.TreeMap;
import java.util.logging.Logger;

/*
 * TODO: ライセンスを記載する
 * TODO: コードが汚いので, もっとシンプルになるように書き直す
 *      プロジェクトのビューとビルド結果のビューでアクションの振る舞いが変わっているので, 別のクラスに分割できると思う
 * TODO: テストの書き方がわからなくてだいぶ苦労したので, テストの書き方について調査する
 * TODO: メソッド名の付け方がいけてないので, 分かりやすい名前にリファクタする
 */
public final class EmotionalMascotAction implements Action {
//    private static final Logger LOGGER = Logger.getLogger(EmotionalMascotRecorder.class.getName());
    public  Emotion         emotion;
    public  Character       character;
    private AbstractProject project;

    public EmotionalMascotAction(Emotion emotion, Character character) {
        super();
        this.emotion   = emotion;
        this.character = character;
        this.project   = null;
    }

    public EmotionalMascotAction(AbstractBuild<?, ?> build) {
        this(Emotion.byResult(build.getResult()), Character.byRotation(build.getNumber()));
    }

    public EmotionalMascotAction(AbstractProject<?, ?> project) {
        super();
        this.project = project;
    }

    public Emotion getEmotion() {
        if(this.project != null){
            reloadAction(this.project);
        }
        return this.emotion;
    }

    public Character getCharacter() {
        if(this.project != null){
            reloadAction(this.project);
        }
        return this.character;
    }

    private void reloadAction(AbstractProject project){
        TreeMap<Integer, AbstractBuild> builds = new TreeMap<Integer, AbstractBuild>();
        for(int i = project.getNextBuildNumber();builds.size() < 5 && i > 0; i-- ){
            AbstractBuild build = project.getNearestBuild(i);
            if(build != null){
                builds.put(build.getNumber(), build);
            }
        }
        if(builds.size() >= 5){
            int successCount = 0;
            for(AbstractBuild build: builds.values()){
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
        }else if(builds.isEmpty()){
            this.emotion = Emotion.WORKING;
        }else{
            AbstractBuild lastBuild = builds.lastEntry().getValue();
            this.emotion = Emotion.byResult(lastBuild.getResult());
        }

        this.character = Character.byRotation(project.getNextBuildNumber());
    }

    public String getIconFileName() { return null;}

    public String getDisplayName() {
        return "";
    }

    public String getUrlName() {
        return "";
    }
}
