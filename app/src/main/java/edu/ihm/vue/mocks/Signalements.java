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
                "Rue robert",
                "Nice",
                06000,
                "La poubelle émet beaucoup d'odeurs"
        ));
        signalementsMock.add(new DechetSignalement(
                "Dêchet animal",
                new Date(2024 - 1900, 4 - 1, 12),
                bitmap,
                "Rue Philip",
                "Nice",
                06200,
                "L'animal commence à se décomposer"
        ));
        signalementsMock.add(new EncombrementSignalement(
                "Dêchets informatiques",
                new Date(2024 - 1900, 9 - 1, 13),
                bitmap,
                "Rue Lorian",
                "Nice",
                06000,
                "Danger de blessure"
        ));
    }
}
