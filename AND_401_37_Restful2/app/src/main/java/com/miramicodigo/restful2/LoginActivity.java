package com.miramicodigo.restful2;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
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

public class LoginActivity extends AppCompatActivity {

    private EditText etUsuario;
    private EditText etPassword;
    private Button btnIngresar;
    private Button btnRegistrar;
    private ProgressBar pbLogin;

    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        btnRegistrar = (Button) findViewById(R.id.btnRegistro);
        pbLogin = (ProgressBar) findViewById(R.id.pbLogin);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valida();
            }
        });

        btnRegistrar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegistroActivity.class));
            }
        });
    }

    public void valida(){
        if (etUsuario.getText().toString().equals("") || etPassword.getText().toString().equals("")) {
            esVacio();
        } else {
            login();
        }
    }

    public void login() {
        username = etUsuario.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        pbLogin.setIndeterminate(true);
        pbLogin.setVisibility(View.VISIBLE);




    }

    public void responseOk(String response) throws JSONException{
        try {

        }catch (Throwable e){
            System.out.println("No se pudo convertir");
        }
    }

    public void esVacio() {
        Toast.makeText(getApplicationContext(),
                "Existen campos vacios",
                Toast.LENGTH_SHORT).show();
        Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vib.vibrate(500);
    }

    public void responseFail(VolleyError error) {
        Toast.makeText(getApplicationContext(),
                "Registro Incorrecto: "+error.getMessage(),
                Toast.LENGTH_SHORT).show();
    }
}

