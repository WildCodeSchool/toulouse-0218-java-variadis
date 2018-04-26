package fr.wildcodeschool.variadis;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import static fr.wildcodeschool.variadis.MapsActivity.sBackPress;

public class HerbariumActivity extends AppCompatActivity {

    public static final String EXTRA_PARCEL_VEGETAL = "EXTRA_PARCEL_VEGETAL";
    public static final String CLASS_FROM = "CLASS_FROM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_herbarium);

        final GridView herbView = findViewById(R.id.herbview);
        final TextView emptyHerbarium = findViewById(R.id.empty_herbarium);
        final CheckBox checkIfEmpty = findViewById(R.id.check_if_empty);
        final ArrayList<VegetalModel> vegetalList = new ArrayList<>();
        final GridAdapter adapter = new GridAdapter(this, vegetalList);
        ImageView ivProfil = findViewById(R.id.img_profile);
        ImageView ivMap = findViewById(R.id.img_map);
        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() == null) {
            Intent intent = new Intent(HerbariumActivity.this, ConnexionActivity.class);
            startActivity(intent);
            finish();
        }

        ivProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HerbariumActivity.this, ProfilActivity.class);
                startActivity(intent);
            }
        });

        ivMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HerbariumActivity.this.startActivity(new Intent(HerbariumActivity.this, MapsActivity.class));
            }
        });

        //Checkbox temporaire jusqu'à l'implémentation de l'API
        checkIfEmpty.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    vegetalList.clear();
                } else {
                    vegetalList.add(new VegetalModel(R.drawable.tilleul_arbre_300x300, "Tilleul"));
                    vegetalList.add(new VegetalModel(R.drawable.erable_sucre_fr_500_0006237, "Erable"));
                    vegetalList.add(new VegetalModel(R.drawable.img_ulmus_americana_2209, "Orme"));
                    vegetalList.add(new VegetalModel(R.drawable.micocoulier_300x300, "Micocoulier"));
                    vegetalList.add(new VegetalModel(R.drawable.pinus_pinea_pin_parasol_ou_pin_pignon, "Pin Parasol"));
                    vegetalList.add(new VegetalModel(R.drawable.c_dre_liban_ch_teau_de_hautefort_23, "Cèdre"));
                    vegetalList.add(new VegetalModel(R.drawable.charme_commun_fastigiata_, "Charme"));
                    vegetalList.add(new VegetalModel(R.drawable.murier_platane_sterile, "Platane"));
                    vegetalList.add(new VegetalModel(R.drawable.betula_papyrifera, "Bouleau"));

                }

                herbView.setAdapter(adapter);
                herbView.setEmptyView(emptyHerbarium);

            }
        });

        //TODO Remplacer les Parcelables par des requêtes Firebase
        herbView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Parcelable vegetal = new VegetalModel(vegetalList.get(i).getPicture(), vegetalList.get(i).getName());
                Intent intent = new Intent(HerbariumActivity.this, VegetalActivity.class);
                intent.putExtra(CLASS_FROM, "herbarium");
                intent.putExtra(EXTRA_PARCEL_VEGETAL, vegetal);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.quitter)
                .setMessage(R.string.confirm_quit)
                .setPositiveButton(R.string.oui, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                        HerbariumActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton(R.string.non, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

}
