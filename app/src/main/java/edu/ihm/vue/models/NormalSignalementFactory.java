package edu.ihm.vue.models;

public class NormalSignalementFactory extends SignalementFactory {
    public Signalement build(int type) throws Throwable{
        switch(type){
            case DECHET: return new DechetSignalement();
            case ENCOMBREMENT: return new EncombrementSignalement();
            default:throw new Throwable("Signalement not created");
        }
    }
}
