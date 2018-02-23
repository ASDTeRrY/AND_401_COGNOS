package com.miramicodigo.notificaciones;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity implements View.OnClickListener {

    private static NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initUI();

        mNotificationManager = (NotificationManager)
                getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("my_channel_01",
                    "Channel human readable title", NotificationManager.IMPORTANCE_DEFAULT);
            mNotificationManager.createNotificationChannel(channel);
        }
    }

    private void initUI () {
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnNotificacionSimple).setOnClickListener(this);
        findViewById(R.id.btnNotificacionGrande).setOnClickListener(this);
        findViewById(R.id.btnNotificacionProgreso).setOnClickListener(this);
        findViewById(R.id.btnNotificacionAcciones).setOnClickListener(this);
    }

    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.btnNotificacionSimple:
                createSimpleNotification(this);
                break;
            case R.id.btnNotificacionGrande:
                createExpandableNotification(this);
                break;
            case R.id.btnNotificacionProgreso:
                createProgressNotification(this);
                break;
            case R.id.btnNotificacionAcciones:
                createButtonNotification(this);
                break;
        }
    }

    public void createSimpleNotification(Context context) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context);

        notification.setSmallIcon(R.drawable.ic_android_black_24dp);

        Intent intent = new Intent(context, ResultadoActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, 0, intent, 0);

        notification.setContentIntent(pendingIntent);
        notification.setLargeIcon(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.ic_android_black_24dp));
        notification.setContentTitle("Titulo Notificacion");
        notification.setContentText("Aca colocamos en contenido de la notificacion");
        notification.setSubText("Contenido Subtitulo notificacion");

        mNotificationManager.notify(1, notification.build());
    }

    public void createExpandableNotification (Context context) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
            String lorem = context.getResources().getString(R.string.long_lorem);
            String [] content = lorem.split("\\.");

            inboxStyle.setBigContentTitle("Titulo Notificacion");
            for (String line : content) {
                inboxStyle.addLine(line);
            }

            NotificationCompat.Builder notification = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.ic_android_black_24dp)
                    .setContentTitle("Expandable Notificacion")
                    .setContentText("Esto es el contendio de mi notificacion")
                    .setStyle(inboxStyle);
            mNotificationManager.notify(2, notification.build());
        } else {

        }
    }

    public void createProgressNotification (final Context context) {
        final int progresID = new Random().nextInt(1000);

        final NotificationCompat.Builder notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setContentTitle("Titulo Notificacion")
                .setContentText("Contenido Notificacion")
                .setTicker("Notificacion de progreso creada")
                .setUsesChronometer(true)
                .setProgress(100, 0, true);

        AsyncTask<Integer, Integer, Integer> downloadTask = new AsyncTask<Integer, Integer, Integer>() {
            @Override
            protected void onPreExecute () {
                super.onPreExecute();
                mNotificationManager.notify(progresID, notification.build());
            }

            @Override
            protected Integer doInBackground (Integer... params) {
                try {
                    Thread.sleep(5000);
                    for (int i = 0; i < 101; i+=5) {
                        notification
                                .setContentTitle("En progreso...")
                                .setContentText("Se esta ejecutando...")
                                .setProgress(100, i, false)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentInfo(i + "%");
                        mNotificationManager.notify(progresID, notification.build());
                        Thread.sleep(500);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute (Integer integer) {
                super.onPostExecute(integer);
                notification
                        .setContentTitle("Progreso terminado")
                        .setContentText("El progreso termino.")
                        .setTicker("Termino el progreso.")
                        .setSmallIcon(R.drawable.ic_android_black_24dp)
                        .setUsesChronometer(false);
                mNotificationManager.notify(progresID, notification.build());
            }
        };
        downloadTask.execute();
    }


    public void createButtonNotification (Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            Intent intent = new Intent(context, ResultadoActivity.class);
            PendingIntent pendingIntent =
                    PendingIntent.getActivity(context, 0, intent, 0);
            NotificationCompat.Builder notification = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.ic_android_black_24dp)
                    .setContentTitle("Titulo notificacion con botones")
                    .setContentText("Aca abajo estan los botones")
                    .addAction(R.drawable.ic_android_black_24dp, "Aceptar", pendingIntent)
                    .addAction(R.mipmap.ic_launcher, "Cancelar", pendingIntent);

            mNotificationManager.notify(1000, notification.build());
        } else {
            Toast.makeText(context, "Necesitas una version mas alta", Toast.LENGTH_LONG).show();
        }
    }

}



