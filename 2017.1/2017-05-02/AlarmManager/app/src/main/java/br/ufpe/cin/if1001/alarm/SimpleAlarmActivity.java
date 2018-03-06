package br.ufpe.cin.if1001.alarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

public class SimpleAlarmActivity extends Activity {

    private static final int ALARME_ID = 1001;
    private static final int INTERVALO = 3500;
    private PendingIntent pendingIntent = null;
    private AlarmManager alarmManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_alarm);

        //pegando manager...
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //Criando PendingIntent que envia resultados para Activity em onActivityResult
        pendingIntent = createPendingResult(ALARME_ID, new Intent(), 0);

        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + INTERVALO, INTERVALO, pendingIntent);
    }

    @Override
    protected void onDestroy() {
        //ao remover activity da memoria, cancela o alarme
        //neste caso, basta um PedingIntent que seja equivalente
        alarmManager.cancel(pendingIntent);
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ALARME_ID) {
            Toast.makeText(this,"Vou aparecer de novo...",Toast.LENGTH_SHORT).show();
            Log.d(getClass().getSimpleName(), "executou um alarme...");
        }
    }
}
