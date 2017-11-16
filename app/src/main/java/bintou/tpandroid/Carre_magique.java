package bintou.tpandroid;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Carre_magique extends SubActivity {

    Chronometer simpleChronometer = null;

    Button envoyer = null;
    Button raz = null;
    Button exit = null;
    Button next = null;

    EditText l1c1 = null;
    EditText l1c2 = null;
    EditText l1c3 = null;
    EditText l2c1 = null;
    EditText l2c2 = null;
    EditText l2c3 = null;
    EditText l3c1 = null;
    EditText l3c2 = null;
    EditText l3c3 = null;

    TextView resL1 = null;
    TextView resL2 = null;
    TextView resL3 = null;
    TextView resC1 = null;
    TextView resC2 = null;
    TextView resC3 = null;
    TextView result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* méthode create dans SubActivity */
        setView(R.layout.activity_carre_magique);

        // On active le chronomètre
        simpleChronometer = findViewById(R.id.chrono); // initiate a chronometer
        simpleChronometer.start(); // start a chronometer

        // On récupère toutes les vues dont on a besoin
        envoyer = findViewById(R.id.envoyer);
        next = findViewById(R.id.next);
        exit = findViewById(R.id.exit);
        raz = findViewById(R.id.nouveau);

        l1c1 = findViewById(R.id.l1c1);
        l1c2 = findViewById(R.id.l1c2);
        l1c3 = findViewById(R.id.l1c3);
        l2c1 = findViewById(R.id.l2c1);
        l2c2 = findViewById(R.id.l2c2);
        l2c3 = findViewById(R.id.l2c3);
        l3c1 = findViewById(R.id.l3c1);
        l3c2 = findViewById(R.id.l3c2);
        l3c3 = findViewById(R.id.l3c3);

        resL1 = findViewById(R.id.resL1);
        resL2 = findViewById(R.id.resL2);
        resL3 = findViewById(R.id.resL3);
        resC1 = findViewById(R.id.resC1);
        resC2 = findViewById(R.id.resC2);
        resC3 = findViewById(R.id.resC3);
        result = findViewById(R.id.resultat);

        // On attribue un listener adapté aux vues qui en ont besoin
        envoyer.setOnClickListener(envoyerListener);
        next.setOnClickListener(nextListener);
        exit.setOnClickListener(exitListener);
        raz.setOnClickListener(razListener);

        l1c1.addTextChangedListener(textWatcher);
        l1c2.addTextChangedListener(textWatcher);
        l1c3.addTextChangedListener(textWatcher);
        l2c1.addTextChangedListener(textWatcher);
        l2c2.addTextChangedListener(textWatcher);
        l2c3.addTextChangedListener(textWatcher);
        l3c1.addTextChangedListener(textWatcher);
        l3c2.addTextChangedListener(textWatcher);
        l3c3.addTextChangedListener(textWatcher);

        // On désactive les boutons qui ne doivent pas être activé en début de partie
        raz.setEnabled(false);
        raz.setClickable(false);
//        next.setClickable(false);  //changement de métode

        //On instancie les resultats attendu
        resL1.setText("12");
        resL2.setText("13");
        resL3.setText("20");
        resC1.setText("11");
        resC2.setText("20");
        resC3.setText("14");
    }

    // On défini les action du textWatcher
    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            result.setText(R.string.defaut);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // Vide, mais nécessaire
        }

        @Override
        public void afterTextChanged(Editable s) {
            raz.setEnabled(true);
            raz.setClickable(true);
        }
    };

    // Uniquement pour le bouton "envoyer"
    protected View.OnClickListener envoyerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // On vérifie que les données existe bien
            if (TextUtils.isEmpty(l1c1.getText()) || TextUtils.isEmpty(l1c2.getText()) || TextUtils.isEmpty(l1c3.getText())) {
                Toast.makeText(Carre_magique.this, "Il ne manquerait pas un chiffre a la ligne 1 par hasard", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(l2c1.getText()) || TextUtils.isEmpty(l2c2.getText()) || TextUtils.isEmpty(l2c3.getText())) {
                Toast.makeText(Carre_magique.this, "Il ne manquerait pas un chiffre a la ligne 2 par hasard", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(l3c1.getText()) || TextUtils.isEmpty(l3c2.getText()) || TextUtils.isEmpty(l3c3.getText())) {
                Toast.makeText(Carre_magique.this, "Il ne manquerait pas un chiffre a la ligne 3 par hasard", Toast.LENGTH_SHORT).show();
                return;
            }

            // Conversion des données
            int v1 = Integer.parseInt(l1c1.getText().toString());
            int v2 = Integer.parseInt(l1c2.getText().toString());
            int v3 = Integer.parseInt(l1c3.getText().toString());
            int v4 = Integer.parseInt(l2c1.getText().toString());
            int v5 = Integer.parseInt(l2c2.getText().toString());
            int v6 = Integer.parseInt(l2c3.getText().toString());
            int v7 = Integer.parseInt(l3c1.getText().toString());
            int v8 = Integer.parseInt(l3c2.getText().toString());
            int v9 = Integer.parseInt(l3c3.getText().toString());

            int rL1 = Integer.parseInt(resL1.getText().toString());
            int rL2 = Integer.parseInt(resL2.getText().toString());
            int rL3 = Integer.parseInt(resL3.getText().toString());
            int rC1 = Integer.parseInt(resC1.getText().toString());
            int rC2 = Integer.parseInt(resC2.getText().toString());
            int rC3 = Integer.parseInt(resC3.getText().toString());

            // Vérification : chiffre & unicité des réponses
            if ((v1 > 9) || (v2 > 9) || (v3 > 9) || (v4 > 9) || (v5 > 9) || (v6 > 9) || (v7 > 9) || (v8 > 9) || (v9 > 9)) {
                Toast.makeText(Carre_magique.this, "Attention, les valeurs accépté sont comprise entre [0-9]", Toast.LENGTH_SHORT).show();
            } else {
                if (v1 + v2 + v3 != rL1)
                    Toast.makeText(Carre_magique.this, "Erreur, ligne 1", Toast.LENGTH_SHORT).show();
                else if (v4 + v5 + v6 != rL2)
                    Toast.makeText(Carre_magique.this, "Erreur, ligne 2", Toast.LENGTH_SHORT).show();
                else if (v7 + v8 + v9 != rL3)
                    Toast.makeText(Carre_magique.this, "Erreur, ligne 3", Toast.LENGTH_SHORT).show();
                else if (v1 + v4 + v7 != rC1)
                    Toast.makeText(Carre_magique.this, "Erreur, Colonne 1", Toast.LENGTH_SHORT).show();
                else if (v2 + v5 + v8 != rC2)
                    Toast.makeText(Carre_magique.this, "Erreur, Colonne 2", Toast.LENGTH_SHORT).show();
                else if (v3 + v6 + v9 != rC3)
                    Toast.makeText(Carre_magique.this, "Erreur, Colonne 3", Toast.LENGTH_SHORT).show();
                else {
                    // On coupe le chronometre
                    simpleChronometer.stop(); //TODO bloque le chrono a l'affichage mais continue si on rappui sur soumettre :/
                    float chrono = SystemClock.elapsedRealtime() - simpleChronometer.getBase();
                    int time = (int) chrono / 1000;
                    int min = time / 60;
                    int sec = time - (min * 60);
                    Resources res = getResources();
                    if (time >= 60) {
                        result.setText(res.getString(R.string.winM, min, sec));
                    } else
                        result.setText(res.getString(R.string.winS, sec));
                }
            }
        }
    };

    // Listener du bouton de Continuer (désactivé)
    private View.OnClickListener nextListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(Carre_magique.this, "Les nouveau niveau arrivent, il ne plus qu'a attendre la mise a jour", Toast.LENGTH_SHORT).show();
            result.setText(R.string.continuer);
            next.setClickable(false);
            next.setEnabled(false);
        }
    };

    // Listener du bouton Quitter
    private View.OnClickListener exitListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // On prévient
            Toast.makeText(Carre_magique.this, "information: votre partie n'a pas été sauvegardé", Toast.LENGTH_SHORT).show();
            finish();
        }
    };

    // Listener du bouton de remise à zéro
    private View.OnClickListener razListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // On efface les donnée du tableau
            l1c1.getText().clear();
            l1c2.getText().clear();
            l1c3.getText().clear();
            l2c1.getText().clear();
            l2c2.getText().clear();
            l2c3.getText().clear();
            l3c1.getText().clear();
            l3c2.getText().clear();
            l3c3.getText().clear();

            // On change le résultat, et on bloque le bouton
            result.setText(R.string.start);
            raz.setEnabled(false);
            raz.setClickable(false);

            // On réinitialise le chrono
            simpleChronometer.setBase(SystemClock.elapsedRealtime());
            simpleChronometer.start();
        }
    };
}

/*
  Solution :

   1   8   3
   4   7   2
   6   5   9
 */