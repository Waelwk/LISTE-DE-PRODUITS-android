package com.example.listedeproduit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {
    Button btnAjt,btnUpd,btnDel;
    EditText libelle,codebarre,prix;
    CheckBox dipo;
    private ListView listView;
    private ArrayList<Produit> listeDesProduit = new ArrayList<>();
    private produitLi adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.liste) ;

        adapter = new produitLi(MainActivity.this,listeDesProduit);
        listView.setAdapter(adapter);

        btnAjt=findViewById(R.id.ajouter);
        btnAjt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, MainActivity2.class);
                startActivityForResult(intent, 101);
            }
        });




    }



    protected void onUpdate(View view) {


        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.liste) ;

        adapter = new produitLi(MainActivity.this,listeDesProduit);
        listView.setAdapter(adapter);

        btnUpd=findViewById(R.id.modifier);
        btnUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, MainActivity2.class);
                startActivityForResult(intent, 101);
            }
        });




    }

    public void deleteProductButtonClick(View view)
    {
        ArrayList<Produit> checkedItems = adapter.getChecked();
        Toast.makeText(this,String.valueOf(checkedItems.size()),Toast.LENGTH_LONG).show();
        int itemCount = checkedItems.size();
        for(int i=itemCount-1; i >= 0; i--){
            adapter.remove(checkedItems.get(i));
        }

    }













    @Override
    protected  void  onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 101 && resultCode == Activity.RESULT_OK)
        {
            Produit t = new Produit();
            String libelle=data.getStringExtra("libelle");
            String codebarre=data.getStringExtra("codeBarre");
            String prix=data.getStringExtra("prix");
            boolean dispo=data.getBooleanExtra("disponible",false);
            String image=data.getStringExtra("image");
            t.setLibelle(libelle);
            t.setCodeBarre(codebarre);
            t.setPrix(prix);
            t.setDisponible(dispo);
            t.setImage(image);
            listeDesProduit.add(t);
            adapter.notifyDataSetChanged();
        }
    }

    }