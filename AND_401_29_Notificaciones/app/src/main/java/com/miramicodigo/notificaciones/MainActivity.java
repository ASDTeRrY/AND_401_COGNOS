package com.miramicodigo.notificaciones;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;

public class MainActivity extends Activity implements View.OnClickListener {

    private static NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        mNotificationManager =
                (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
    }


    private void initUI () {
        setContentView(R.layout.activity_main);
        findViewById(R.id.simple_notification).setOnClickListener(this);
        findViewById(R.id.big_notification).setOnClickListener(this);
        findViewById(R.id.progress_notification).setOnClickListener(this);
        findViewById(R.id.button_notifcation).setOnClickListener(this);
    }


    @Override
    public void onClick (View v) {
        switch (v.getId()) {

            case R.id.simple_notification:
                createSimpleNotification(this);
                break;

            case R.id.big_notification:
                createExpandableNotification(this);
                break;

            case R.id.progress_notification:
                createProgressNotification(this);
                break;

            case R.id.button_notifcation:
                createButtonNotification(this);
        }

    }

    /**
     * Shows a simple notification
     * @param context aplication context
     */
    public void createSimpleNotification(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        builder.setSmallIcon(android.R.drawable.ic_dialog_alert);

        Intent intent = new Intent(context, ResultadoActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        builder.setContentIntent(pendingIntent);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));
        builder.setContentTitle("Titulo de notificacion");
        builder.setContentText("Contenido notificacion");
        builder.setSubText("Sub texto pequeño");

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(1, builder.build());
    }


    public void createExpandableNotification (Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            // Building the expandable content
            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
            String lorem = context.getResources().getString(R.string.long_lorem);
            String [] content = lorem.split("\\.");

            inboxStyle.setBigContentTitle("This is a big title");
            for (String line : content) {
                inboxStyle.addLine(line);
            }

            // Building the notification
            NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                    .setContentTitle("Expandable notification") // title of notification
                    .setContentText("This is an example of an expandable notification") // text inside the notification
                    .setStyle(inboxStyle); // adds the expandable content to the notification

            mNotificationManager.notify(11, nBuilder.build());

        } else {
            Toast.makeText(context, "Can't show", Toast.LENGTH_LONG).show();
        }
    }


    /**
     * Show a determinate and undeterminate progress notification
     * @param context, activity context
     */
    public void createProgressNotification (final Context context) {

        // used to update the progress notification
        final int progresID = new Random().nextInt(1000);

        // building the notification
        final NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.ic_notification_clear_all)
                .setContentTitle("Progres notification")
                .setContentText("Now waiting")
                .setTicker("Progress notification created")
                .setUsesChronometer(true)
                .setProgress(100, 0, true);



        AsyncTask<Integer, Integer, Integer> downloadTask = new AsyncTask<Integer, Integer, Integer>() {
            @Override
            protected void onPreExecute () {
                super.onPreExecute();
                mNotificationManager.notify(progresID, nBuilder.build());
            }

            @Override
            protected Integer doInBackground (Integer... params) {
                try {
                    // Sleeps 2 seconds to show the undeterminated progress
                    Thread.sleep(5000);

                    // update the progress
                    for (int i = 0; i < 101; i+=5) {
                        nBuilder
                                .setContentTitle("Progress running...")
                                .setContentText("Now running...")
                                .setProgress(100, i, false)
                                .setSmallIcon(android.R.drawable.ic_notification_overlay)
                                .setContentInfo(i + " %");

                        // use the same id for update instead created another one
                        mNotificationManager.notify(progresID, nBuilder.build());
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

                nBuilder.setContentText("Progress finished :D")
                        .setContentTitle("Progress finished !!")
                        .setTicker("Progress finished !!!")
                        .setSmallIcon(android.R.drawable.ic_dialog_alert)
                        .setUsesChronometer(false);

                mNotificationManager.notify(progresID, nBuilder.build());
            }
        };

        // Executes the progress task
        downloadTask.execute();
    }


    public void createButtonNotification (Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            // Prepare intent which is triggered if the  notification button is pressed
            Intent intent = new Intent(context, ResultadoActivity.class);
            PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);

            // Building the notifcation
            NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                    .setContentTitle("Button notification") // notification title
                    .setContentText("Expand to show the buttons...") // content text
                    .setTicker("Showing button notification") // status bar message
                    .addAction(android.R.drawable.ic_dialog_email, "Accept", pIntent) // accept notification button
                    .addAction(android.R.drawable.ic_dialog_info, "Cancel", pIntent); // cancel notification button

            mNotificationManager.notify(1001, nBuilder.build());

        } else {
            Toast.makeText(context, "You need a higher version", Toast.LENGTH_LONG).show();
        }
    }
}



