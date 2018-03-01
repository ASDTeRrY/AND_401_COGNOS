package com.miramicodigo.restful2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {

    private EditText etNombres;
    private EditText etApellidos;
    private EditText etCorreo;
    private EditText etCi;
    private EditText etUsuario;
    private EditText etPassword;

    private Button btnRegistrar, btnCancelar;

    private ProgressBar pbRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNombres = (EditText) findViewById(R.id.etNombres);
        etApellidos = (EditText) findViewById(R.id.etApellidos);
        etCorreo = (EditText) findViewById(R.id.etCorreo);
        etCi = (EditText) findViewById(R.id.etCI);
        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etPassword = (EditText) findViewById(R.id.etPassword);

        btnRegistrar = (Button) findViewById(R.id.btnAceptar);
        btnCancelar = (Button) findViewById(R.id.btnAceptar);

        pbRegistro = (ProgressBar)  findViewById(R.id.pbRegistro);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registra();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void registra(){
        final String nombres = etNombres.getText().toString().trim();
        final String apellidos = etApellidos.getText().toString().trim();
        final String correo = etCorreo.getText().toString().trim();
        final String ci = etCi.getText().toString().trim();
        final String usuario = etUsuario.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();

        pbRegistro.setIndeterminate(true);
        pbRegistro.setVisibility(View.VISIBLE);




    }

    public void responseOk(String response) throws JSONException{
        try {



        }catch (Throwable e){
            System.out.println("No se pudo convertir");
        }
    }

    public void responseFail(VolleyError error) {
        Toast.makeText(getApplicationContext(),
                "Registro Incorrecto: "+error.getMessage(),
                Toast.LENGTH_SHORT).show();
    }
}
