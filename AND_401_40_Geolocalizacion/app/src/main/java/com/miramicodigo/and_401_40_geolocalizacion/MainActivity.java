package com.miramicodigo.and_401_40_geolocalizacion;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView textoLatitud;
    private TextView textoLongitud;
    private TextView textoDireccion;
    private Button botonFino;
    private Button botonAmbos;

    private LocationManager locationManager;

    private Handler handler;

    private boolean disponibleGeocoder;
    private boolean usarFino;
    private boolean usarAmbos;

    private static final String CLAVE_FINO = "usar_fino";
    private static final String CLAVE_AMBOS = "usar_ambos";

    private static final int ACTUALIZAR_DIRECCION = 1;
    private static final int ACTUALIZAR_LATITUD = 2;
    private static final int ACTUALIZAR_LONGITUD = 3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            usarFino = savedInstanceState.getBoolean(CLAVE_FINO);
            usarAmbos = savedInstanceState.getBoolean(CLAVE_AMBOS);
        } else {
            usarFino = false;
            usarAmbos = false;
        }

        textoLatitud = (TextView) findViewById(R.id.textoLatitud);
        textoLongitud = (TextView) findViewById(R.id.textoLongitud);
        textoDireccion = (TextView) findViewById(R.id.textoDireccion);
        botonFino = (Button) findViewById(R.id.botonFino);
        botonAmbos = (Button) findViewById(R.id.botonAmbos);

        disponibleGeocoder = true;

        handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case ACTUALIZAR_DIRECCION:
                        textoDireccion.setText((String) msg.obj);
                        break;
                    case ACTUALIZAR_LATITUD:
                        textoLatitud.setText((String) msg.obj);
                        break;
                    case ACTUALIZAR_LONGITUD:
                        textoLongitud.setText((String) msg.obj);
                        break;
                }
            }
        };
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(CLAVE_FINO, usarFino);
        outState.putBoolean(CLAVE_AMBOS, usarAmbos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        configurar();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocationManager locationManager2 = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gpsHabilitado = locationManager2.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsHabilitado) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Quieres activar el GPS?")
                    .setCancelable(false)
                    .setPositiveButton("Si",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    abrirConfiguracionesUbicacionYSeguridad();
                                }
                            })
                    .setNegativeButton("No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog alerta = builder.create();
            alerta.show();
        }
    }

    public void abrirConfiguracionesUbicacionYSeguridad() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        locationManager.removeUpdates(escuchador);
    }

    public void configurar() {
        Location localizacionGPS = null;
        Location localizacionRED = null;
        locationManager.removeUpdates(escuchador);
        textoLatitud.setText(R.string.desconocido);
        textoLongitud.setText(R.string.desconocido);
        textoDireccion.setText(R.string.desconocido);

        if (usarFino) {
            botonFino.setBackgroundResource(R.drawable.degradado_naranja);
            botonAmbos.setBackgroundResource(R.drawable.degradado_celeste);
            // Obtener la localizacion desde el proveedor GPS



        } else
            if (usarAmbos) {
                botonFino.setBackgroundResource(R.drawable.degradado_celeste);
                botonAmbos.setBackgroundResource(R.drawable.degradado_naranja);
                // Obtener la localizacion de ambos proveedores



            }
    }

    private Location pedirActualizacionesDeProveedor(String proveedor, int mensajeError) {
        Location localizacion = null;
        if (locationManager.isProviderEnabled(proveedor)) {
            int DIEZ_SEGUNDOS = 10000;
            int DIEZ_METROS = 10;

            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED) {




            } else {
                solicitarPermiso();
            }
        } else {
            Toast.makeText(this, mensajeError, Toast.LENGTH_LONG).show();
        }
        return localizacion;
    }

    public void solicitarPermiso() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
        } else {ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);}
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1) {
            if(grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            }}
    }

    public void usarProveedorFino(View view) {
        usarFino = true;
        usarAmbos = false;
        configurar();
    }

    public void usarAmbosProveedores(View view) {
        usarFino = false;
        usarAmbos = true;
        configurar();
    }

    private void hacerGeocodificacionReversa(Location location) {
        (new TareaGeocodificacionReversa(this)).execute(new Location[] { location });
    }

    private void actualizarIU(Location localizacion) {
        Message.obtain(handler, ACTUALIZAR_LATITUD, localizacion.getLatitude() + "").sendToTarget();
        Message.obtain(handler, ACTUALIZAR_LONGITUD,localizacion.getLongitude() + "").sendToTarget();
        if (disponibleGeocoder) {
            hacerGeocodificacionReversa(localizacion);
        }
    }

    private final LocationListener escuchador = new LocationListener() {

        public void onLocationChanged(Location location) {
            actualizarIU(location);
        }

        public void onProviderDisabled(String provider) {

        }
        public void onProviderEnabled(String provider) {

        }
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
    };

    protected Location getMejorLocalizacion(Location newLocation, Location currentBestLocation) {
        int TWO_MINUTES = 1000 * 60 * 2;
        if (currentBestLocation == null) {
            return newLocation;
        }

        long timeDelta = newLocation.getTime() - currentBestLocation.getTime();
        boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
        boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
        boolean isNewer = timeDelta > 0;

        if (isSignificantlyNewer) {
            return newLocation;
        } else if (isSignificantlyOlder) {
            return currentBestLocation;
        }

        int accuracyDelta = (int) (newLocation.getAccuracy() - currentBestLocation.getAccuracy());
        boolean isLessAccurate = accuracyDelta > 0;
        boolean isMoreAccurate = accuracyDelta < 0;
        boolean isSignificantlyLessAccurate = accuracyDelta > 200;

        boolean isFromSameProvider = esMismoProveedor(
                newLocation.getProvider(), currentBestLocation.getProvider());

        if (isMoreAccurate) {
            return newLocation;
        } else if (isNewer && !isLessAccurate) {
            return newLocation;
        } else if (isNewer && !isSignificantlyLessAccurate
                && isFromSameProvider) {
            return newLocation;
        }
        return currentBestLocation;
    }

    private boolean esMismoProveedor(String provider1, String provider2) {
        if (provider1 == null) {
            return provider2 == null;
        }
        return provider1.equals(provider2);
    }

    private class TareaGeocodificacionReversa extends AsyncTask<Location, Void, Void> {
        Context mContext;

        public TareaGeocodificacionReversa(Context context) {
            super();
            mContext = context;
        }

        @Override
        protected Void doInBackground(Location... params) {
            Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());

            Location loc = params[0];
            List<Address> addresses = null;
            try {
                addresses = geocoder.getFromLocation(loc.getLatitude(),
                        loc.getLongitude(), 1);
            } catch (IOException e) {
                e.printStackTrace();
                Message.obtain(handler, ACTUALIZAR_DIRECCION, e.toString())
                        .sendToTarget();
            }
            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);
                String addressText = String.format(
                        "%s, %s, %s", address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "", address.getLocality(),
                        address.getCountryName());
                Message.obtain(handler, ACTUALIZAR_DIRECCION, addressText).sendToTarget();
            }
            return null;
        }
    }
}
