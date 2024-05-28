package edu.ihm.vue.utils;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Utils {
    public static File bitmapToFile(Context context, Bitmap bitmap, String fileName) {
        File file = new File(context.getCacheDir(), fileName);
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Error writing bitmap to file");
            e.printStackTrace();
        }
        return file;
    }
}
