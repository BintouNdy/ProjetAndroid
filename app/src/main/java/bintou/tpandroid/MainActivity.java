package bintou.tpandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;

public class MainActivity extends SubActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* méthode create dans SubActivity */
        setView(R.layout.content_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Choisissez votre jeu", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void Carre(View v) {
        Intent intent = new Intent(this, Carre_magique.class);
        startActivity(intent);
    }

    public void Morpion(View v) {
        Intent intent = new Intent(this, Morpion.class);
        startActivity(intent);
    }

    public void Memory(View v) {
        Intent intent = new Intent(this, Memory.class);
        startActivity(intent);
    }
}
