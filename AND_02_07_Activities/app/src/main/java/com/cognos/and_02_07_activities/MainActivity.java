package com.cognos.and_02_07_activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btnBoton);
        resultado = (TextView) findViewById(R.id.tvResultado);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultado.setText("Click desde Java");
            }
        });

    }

    public void miBoton(View v) {
        resultado.setText("Click desde XML");
        Intent intent = new Intent(this, SegundaActivity.class);
        startActivity(intent);
    }

}
