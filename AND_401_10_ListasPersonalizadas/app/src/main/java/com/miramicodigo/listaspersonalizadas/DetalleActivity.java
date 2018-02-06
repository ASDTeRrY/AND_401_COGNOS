package com.miramicodigo.listaspersonalizadas;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleActivity extends AppCompatActivity {

    private ImageView ivImagen;
    private TextView tvNombre;
    private TextView tvHAbilidades;

    private Typeface tfBold;
    private Typeface tfThin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        ivImagen = (ImageView) findViewById(R.id.ivDetalleImagen);
        tvNombre = (TextView) findViewById(R.id.tvDetalleNombre);
        tvHAbilidades = (TextView) findViewById(R.id.tvDetalleHabilidades);

        Pokemon pokemon = (Pokemon) getIntent().getSerializableExtra("poke");

        tfBold = Typeface.createFromAsset(getAssets(), "fonts/roboto_black.ttf");
        tfThin = Typeface.createFromAsset(getAssets(), "fonts/roboto_thin.ttf");

        tvNombre.setTypeface(tfBold);
        tvHAbilidades.setTypeface(tfThin);

        tvNombre.setText(pokemon.getNombre());
        tvHAbilidades.setText(pokemon.getHabilidades());
        ivImagen.setImageResource(pokemon.getImagen());
    }
}
