package br.ufpe.cin.if1001.alarm;

import java.text.DateFormat;
import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class LoggingReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO: This method is called when the BroadcastReceiver is receiving
		// an Intent broadcast.
		Log.i("IF1001","Alarme registrado em:" + DateFormat.getDateTimeInstance().format(new Date()));
	}
}
