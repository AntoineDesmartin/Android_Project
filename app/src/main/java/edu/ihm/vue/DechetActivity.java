package edu.ihm.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DechetActivity extends AppCompatActivity {

    private Dechet dechet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dechet);

        dechet=getIntent().getParcelableExtra("dechet");
        TextView title = (TextView) findViewById(R.id.titreDechet);
        title.setText(dechet.getTitle());
        TextView adress = (TextView) findViewById(R.id.adress);
        adress.setText(dechet.getAdress());
        TextView type = (TextView) findViewById(R.id.textType);
        adress.setText(dechet.getType());

        ImageView img = (ImageView) findViewById(R.id.imgDechet);
        //img.setImageResource(dechet.getPhoto());


    }
}