package edu.ihm.vue.models;

public abstract class SignalementFactory {
    public static final int DECHET=1;
    public static final int ENCOMBREMENT=2;
    public static final int FETCH=3;
    abstract public Signalement build(int type) throws Throwable;
}
