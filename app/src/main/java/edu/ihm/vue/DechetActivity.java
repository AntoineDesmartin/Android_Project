package edu.ihm.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DechetActivity extends AppCompatActivity {

    private Dechet dechet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dechet);

        dechet=getIntent().getParcelableExtra("dechet");
        TextView txt = (TextView) findViewById(R.id.texttest);
        txt.setText(dechet.getTitle());
    }
}