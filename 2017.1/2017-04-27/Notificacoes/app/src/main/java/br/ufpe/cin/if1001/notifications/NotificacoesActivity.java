package br.ufpe.cin.if1001.notifications;

import java.io.File;
import java.util.Random;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

public class NotificacoesActivity extends Activity {

	// Notification ID permite associar e agrupar notificacoes no futuro
	private static final int MY_NOTIFICATION_ID = 1;
	private static final int CUSTOM_NOTIFICATION_ID = 12;
	private static final int PROGRESS_NOTIFICATION_ID = 123;
    private static final int CUSTOM_ACTION_NOTIFICATION_ID = 1234;
    private static final int BIG_TEXT_NOTIFICATION_ID = 1337;
    private static final int HEADS_NOTIFICATION_ID = 1338;
    private static final int FULL_NOTIFICATION_ID = 1339;


	// contador de notificacoes
	private int mNotificationCount;

	// elementos de texto
	private final CharSequence tickerText = "Mensagem muito, mas muito, mas muito longa para ser exibida!";
	private final CharSequence contentTitle = "Notificacao";
	private final CharSequence contentText = "Foi notificado!";

	// Actions (intents a serem transmitidos)
	private Intent mNotificationIntent;
	private PendingIntent mContentIntent;

	// Som e padrao de vibracao da Notification
	private Uri soundURI = Uri.parse("android.resource://br.ufpe.cin.if1001.notifications/" + R.raw.alarm_rooster);
	private long[] mVibratePattern = { 0, 200, 200, 300 };
	
	NotificationManager mNotifyManager;
	Notification.Builder mBuilder;

	RemoteViews mContentView = new RemoteViews("br.ufpe.cin.if1001.notifications", R.layout.custom_notification);
	RemoteViews mCustomActionView = new RemoteViews("br.ufpe.cin.if1001.notifications", R.layout.custom_notification_action);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);
		
		//toast simples
		final Button button1 = (Button) findViewById(R.id.button1);	
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
		        Toast.makeText(getApplicationContext(), R.string.toast_simples_string, Toast.LENGTH_LONG).show();
			}
		});

		final Button button2 = (Button) findViewById(R.id.button2);	
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
		        Toast toast = new Toast(getApplicationContext());
		        //centralizando na tela
		        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		        //duracao
		        toast.setDuration(Toast.LENGTH_LONG);
		        //definindo layout personalizado
		        toast.setView(getLayoutInflater().inflate(R.layout.custom_toast,null));
		        //exibindo
				toast.show();
			}
		});

    
		mNotificationIntent = new Intent(getApplicationContext(), NotificacoesSubActivity.class);
		mContentIntent = PendingIntent.getActivity(getApplicationContext(), 0, mNotificationIntent, Intent.FLAG_ACTIVITY_NEW_TASK);
		

		final Button button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// define a mensagem, icone a ser exibido, qual o intent que vai ser passado
				Notification.Builder notificationBuilder = new Notification.Builder(
						getApplicationContext())
						.setTicker(tickerText)
						.setSmallIcon(android.R.drawable.stat_sys_warning)
						.setAutoCancel(true)
						.setContentTitle(contentTitle)
						.setContentText(contentText)
						.setContentIntent(mContentIntent)
						.setSound(soundURI)
						.setVibrate(mVibratePattern);

				// passa notificacao para o notification manager
				NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				mNotificationManager.notify(MY_NOTIFICATION_ID,notificationBuilder.build());
			}
		});


		mNotificationIntent = new Intent(getApplicationContext(), NotificacoesSubCustomActivity.class);
		mContentIntent = PendingIntent.getActivity(getApplicationContext(), 0, mNotificationIntent, Intent.FLAG_ACTIVITY_NEW_TASK);

		final Button button4 = (Button) findViewById(R.id.button4);
		button4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// define a mensagem expandida da notificacao personalizada

				mContentView.setTextViewText(R.id.text, contentText + " ("+ ++mNotificationCount + ")");

				// construir notificacao, semelhante ao anterior, apenas atencao para o setContent, que recebe a view

				Notification.Builder notificationBuilder = new Notification.Builder(
						getApplicationContext())
					.setTicker(tickerText)
					.setSmallIcon(android.R.drawable.stat_sys_warning)
					.setAutoCancel(false)
					.setContentIntent(mContentIntent)
					.setSound(soundURI)
					.setVibrate(mVibratePattern)
					.setContent(mContentView);
                    //setCustomContentView - requer API 24
				// passa notificacao para o notification manager
				NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				mNotificationManager.notify(MY_NOTIFICATION_ID,
						notificationBuilder.build());

			}
		});

		final Button button5 = (Button) findViewById(R.id.button5);
		button5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				mBuilder = new Notification.Builder(
						getApplicationContext()).setContentTitle("Baixando arquivo...")
					    .setContentText("Download em progresso")
					    .setSmallIcon(android.R.drawable.ic_menu_save);
				
				mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				
				// iniciar uma background thread - na verdade isso seria um Service
				new Thread(
				    new Runnable() {
				        @Override
				        public void run() {
				            int incr;
				            // Repetir 10 vezes
				            for (incr = 0; incr <= 100; incr+=10) {
				                    // Definir indicador de progresso, de acordo 
				            		// com o 'estado' atual
				                    mBuilder.setProgress(100, incr, false);
				                    // atualiza notificacao
				                    mNotifyManager.notify(PROGRESS_NOTIFICATION_ID, mBuilder.build());
				                        // simular operação que leva tempo
				                        try {
				                            // 1 segundo
				                            Thread.sleep(1*1000);
				                        } catch (InterruptedException e) {
				                            Log.d("ERRO", "sleep failure");
				                        }
				            }
				            // quando loop for finalizado, atualizar notification
				            // Remover progress bar
				            mBuilder.setContentText("Download completo").setProgress(0,0,false);
		                    // atualiza notificacao
				            mNotifyManager.notify(PROGRESS_NOTIFICATION_ID, mBuilder.build());
				        }
				    }
				// iniciar thread
				).start();


			}
		});
		
		final Button button6 = (Button) findViewById(R.id.button6);
		button6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent int1 = new Intent(getApplicationContext(), CustomAction.class).putExtra("informacao", "valor 1");
				PendingIntent pendInt1 = PendingIntent.getActivity(getApplicationContext(), 0, int1, 0);
				
				Intent int2 = new Intent(getApplicationContext(), CustomAction.class).putExtra("informacao", "valor 2");
				PendingIntent pendInt2 = PendingIntent.getActivity(getApplicationContext(), 1, int2, 0);
				
				// construindo notification com action buttons
				Notification n  = new Notification.Builder(getApplicationContext())
				        .setContentTitle("Titulo aqui")
				        .setContentText("Texto adicional")
				        .setSmallIcon(android.R.drawable.stat_sys_upload)
				        .setContentIntent(mContentIntent)
				        .addAction(android.R.drawable.stat_sys_upload, "Action1", pendInt1)
				        .addAction(android.R.drawable.stat_sys_download, "Action2", pendInt2).build();

                       //Notification.Action.Builder a1 = new Notification.Action.Builder(Icon.createWithResource("", android.R.drawable.stat_sys_upload)."Action1", pendInt1).build();
				
				// passa notificacao para o notification manager
				NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				mNotificationManager.notify(CUSTOM_ACTION_NOTIFICATION_ID, n);
			}
		});


        final Button button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(Settings.ACTION_SECURITY_SETTINGS);
                PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, i, 0);

                Notification.Builder b = new Notification.Builder(getApplicationContext());

                b.setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setContentTitle("Titulo")
                        .setContentText("Texto")
                        .setContentIntent(pi)
                        .setSmallIcon(android.R.drawable.stat_sys_download_done)
                        .setTicker("Ticker text")
                        .setPriority(Notification.PRIORITY_HIGH)
                        .addAction(android.R.drawable.ic_media_play,"action",pi);

                Notification.InboxStyle big= new Notification.InboxStyle();
                big.setBuilder(b);

                big.setSummaryText("Summary text")
                        .addLine("Linha 1")
                        .addLine("Linha 2")
                        .addLine("Linha 3")
                        .addLine("Linha 4")
                        .addLine("Linha 5");
                // passa notificacao para o notification manager
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(BIG_TEXT_NOTIFICATION_ID, big.build());
            }
        });

        final Button button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(Settings.ACTION_SECURITY_SETTINGS);
                PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, i, 0);

                Notification.Builder b = new Notification.Builder(getApplicationContext());

                b.setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setContentTitle("Titulo")
                        .setContentIntent(pi)
                        .setSmallIcon(android.R.drawable.stat_sys_download_done)
                        .addAction(android.R.drawable.ic_media_play,"alguma action",pi);

                //requer APIs mais altas
                //b.setVisibility(Notification.VISIBILITY_PUBLIC);

                //esconde da lock screen
                //b.setVisibility(Notification.VISIBILITY_SECRET);

                //heads-up notification
                b.setPriority(Notification.PRIORITY_HIGH);

                // passa notificacao para o notification manager
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(HEADS_NOTIFICATION_ID, b.build());
            }
        });

        final Button button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(Settings.ACTION_SECURITY_SETTINGS);
                PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, i, 0);

                Notification.Builder b = new Notification.Builder(getApplicationContext());

                b.setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setContentTitle("Titulo")
                        .setContentText("Texto")
                        .setContentIntent(pi)
                        .setSmallIcon(android.R.drawable.stat_sys_download_done)
                        .setTicker("ticker...")
                        .setFullScreenIntent(pi, true)
                        .addAction(android.R.drawable.ic_media_play, "action...", pi);

                // passa notificacao para o notification manager
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(FULL_NOTIFICATION_ID, b.build());
            }
        });
    }

}