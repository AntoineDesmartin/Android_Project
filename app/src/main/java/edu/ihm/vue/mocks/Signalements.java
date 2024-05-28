package edu.ihm.vue.mocks;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import edu.ihm.vue.R;
import edu.ihm.vue.models.DechetSignalement;
import edu.ihm.vue.models.EncombrementSignalement;
import edu.ihm.vue.models.Signalement;

public class Signalements {
    public static ArrayList<Signalement> signalementsMock;
    public Signalements(Context context){

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.poub);
        signalementsMock=new ArrayList<>();
        signalementsMock.add(new EncombrementSignalement(
                "Poubelle à la rue",
                new Date(2024 - 1900, 6 - 1, 22),
                bitmap,
                "20 Rue Saint-Sébastien",
                "Biot",
                "06410",
                "La poubelle émet beaucoup d'odeurs",
                "1",
                "2"
        ));
        signalementsMock.add(new DechetSignalement(
                "Dêchet animal",
                new Date(2024 - 1900, 4 - 1, 12),
                bitmap,
                "5 Avenue Georges Clemenceau",
                "Biot",
                "06410",
                "L'animal commence à se décomposer",
                "1",
                "3"
        ));
        signalementsMock.add(new EncombrementSignalement(
                "Dêchets informatiques",
                new Date(2024 - 1900, 9 - 1, 13),
                bitmap,
                "15 Chemin des Combes",
                "Biot",
                "06410",
                "Danger de blessure",
                "0",
                "2"
        ));
    }
}
