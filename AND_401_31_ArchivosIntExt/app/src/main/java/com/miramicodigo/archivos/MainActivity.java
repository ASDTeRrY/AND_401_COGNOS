package com.miramicodigo.archivos;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etInterno;
    private EditText etExterno;

    private Button btnInternoGuardar;
    private Button btnInternoLeer;
    private Button btnExternoGuardar;
    private Button btnExternoLeer;

    // /data/data/nombrepaquete/

    private String nombreArchivoInterno = "prueba_archivo_int.txt";
    private String nombreArchivoExterno = "prueba_archivo_ext.txt";
    private String nombreCarpeta = "/COGNOS/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        btnInternoGuardar.setOnClickListener(this);
        btnInternoLeer.setOnClickListener(this);
        btnExternoGuardar.setOnClickListener(this);
        btnExternoLeer.setOnClickListener(this);

        verificaPermiso();

    }

    public void initUI(){
        etInterno = (EditText) findViewById(R.id.etInterno);
        etExterno = (EditText) findViewById(R.id.etExterno);

        btnInternoGuardar = (Button) findViewById(R.id.btnInternoGuardar);
        btnInternoLeer = (Button) findViewById(R.id.btnInternoLeer);
        btnExternoGuardar = (Button) findViewById(R.id.btnExternoGuardar);
        btnExternoLeer = (Button) findViewById(R.id.btnExternoLeer);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnInternoGuardar:
                guardarInterno();
                break;
            case R.id.btnInternoLeer:
                leerInterno();
                break;
            case R.id.btnExternoGuardar:
                guardarExterno();
                break;
            case R.id.btnExternoLeer:
                etExterno.setText(leerExterno());
                break;
        }
    }

    public void guardarInterno() {
        if(!etInterno.getText().toString().equals("")) {
            try {
                OutputStreamWriter output = new OutputStreamWriter(
                        openFileOutput(nombreArchivoInterno, Context.MODE_PRIVATE));
                output.write(etInterno.getText().toString());
                output.close();
                etInterno.setText("");
                Toast.makeText(getApplicationContext(),
                        "Se guardo exitosamente", Toast.LENGTH_SHORT).show();
            } catch(Exception e){
                System.out.println("Error: "+e.getMessage());
            }
        }else {
            Toast.makeText(getApplicationContext(),
                    "Debe ingresar datos para guardar", Toast.LENGTH_SHORT).show();
        }
    }

    public void leerInterno() {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(openFileInput(nombreArchivoInterno)));
            String cadena, resultado = "";
            while ((cadena = br.readLine()) != null) {
                resultado = resultado + cadena + "\n";
            }
            br.close();
            etInterno.setText(resultado);
        } catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    public void guardarExterno() {
        if (!etExterno.getText().toString().equals("")) {
            boolean sdDisponible = false;
            boolean sdAccesoEscritura = false;
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)){
                sdDisponible = true;
                sdAccesoEscritura = true;
            }else {
                if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
                    sdDisponible = true;
                    sdAccesoEscritura = false;
                } else {
                    sdDisponible = false;
                    sdAccesoEscritura = false;
                }
            }
            if (sdDisponible && sdAccesoEscritura) {
                try {
                    File dir = new File(
                            Environment.getExternalStorageDirectory()+ nombreCarpeta);
                    if(!dir.exists()){
                        dir.mkdirs();
                    }
                    File file = new File(dir, nombreArchivoExterno);
                    String val = etExterno.getText().toString();
                    try {
                        OutputStreamWriter osw = new OutputStreamWriter(
                                new FileOutputStream(file, false));
                        osw.write(val);
                        osw.close();
                        etExterno.setText("");
                        Toast.makeText(getApplicationContext(),
                                "Se guardo en memoria externa exitosamente",
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e){
                        System.out.println("Error: "+e.getMessage());
                    }
                } catch (Exception e) {
                    System.out.println("Error: "+e.getMessage());
                }
            } else {
                System.out.println("No se puede escribir en su memoria");
            }
        } else {
            Toast.makeText(getApplicationContext(),
                    "Debe ingresar datos para guardar",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public String leerExterno() {
        try {
            File file = Environment.getExternalStorageDirectory();
            File f = new File(file.getAbsolutePath(), nombreCarpeta + nombreArchivoExterno);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(f))
            );
            String cadena, resultado = "";
            while((cadena = br.readLine()) != null) {
                resultado = resultado + cadena + "\n";
            }
            br.close();
            return resultado;
        }catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return "";
        }
    }

    public void verificaPermiso() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(this)) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("Permiso", "Concedido");
                } else {
                    Log.e("Permiso", "Denegado");
                }
                return;
            }
        }
    }
}
