package edu.ihm.vue.models;

public class UrgentSignalementFactory extends SignalementFactory {
    public Signalement build(int type) throws Throwable {
        Signalement sig;
        switch (type) {
            case DECHET:
                sig = new DechetSignalement();
                sig.setNiveau(sig.getNiveau() + 2);
                return sig;
            case ENCOMBREMENT:
                sig = new EncombrementSignalement();
                sig.setNiveau(sig.getNiveau() + 2);
                return sig;
            default:
                throw new Throwable("Signalement not created");
        }
    }
}
