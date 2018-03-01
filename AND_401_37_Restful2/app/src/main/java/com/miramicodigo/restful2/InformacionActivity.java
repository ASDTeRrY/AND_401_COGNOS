package com.miramicodigo.restful2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class InformacionActivity extends AppCompatActivity {

    private TextView tvNombre;
    private TextView tvApellido;
    private TextView tvCI;
    private TextView tvCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvApellido = (TextView) findViewById(R.id.tvApellido);
        tvCI = (TextView) findViewById(R.id.tvCi);
        tvCorreo = (TextView) findViewById(R.id.tvCorreo);

        Bundle bundle = getIntent().getExtras();

        tvNombre.setText(bundle.getString("nom"));
        tvApellido.setText(bundle.getString("ape"));
        tvCI.setText(bundle.getString("ci"));
        tvCorreo.setText(bundle.getString("cor"));
    }
}
