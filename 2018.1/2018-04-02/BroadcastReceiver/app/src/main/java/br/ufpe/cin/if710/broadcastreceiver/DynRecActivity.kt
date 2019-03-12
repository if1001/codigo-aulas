package br.ufpe.cin.if710.broadcastreceiver

import android.Manifest
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Telephony
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.telephony.SmsMessage
import android.view.View
import android.widget.Button
import android.widget.Toast

class DynRecActivity : Activity() {
    private val intentFilter = IntentFilter(DYN_BROADCAST_ACTION)
    private val receiver = DynamicReceiver()

    internal var smsReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Toast.makeText(context, "Chegou um SMS", Toast.LENGTH_SHORT).show()
            //int activePhone = TelephonyManager.getDefault().getCurrentPhoneType();
            //String format = (PHONE_TYPE_CDMA == activePhone) ? SmsConstants.FORMAT_3GPP2 : SmsConstants.FORMAT_3GPP;
            val rawMsgs = intent.extras!!.get("pdus") as Array<Any>
            for (raw in rawMsgs) {
                val msg = SmsMessage.createFromPdu(raw as ByteArray)
                if (msg.messageBody.toUpperCase().contains("IF1001")) {
                    Toast.makeText(context, "Tem algo que nos interessa...", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dyn_rec)

        if (podeSMS()) {
            val enviarBroadcastSta = findViewById<Button>(R.id.enviarBroadcastSta)
            enviarBroadcastSta.setOnClickListener { sendBroadcast(Intent(MainActivity.STA_BROADCAST_ACTION)) }
            val enviarBroadcastDyn = findViewById<Button>(R.id.enviarBroadcastDyn)
            enviarBroadcastDyn.setOnClickListener { sendBroadcast(Intent(DYN_BROADCAST_ACTION)) }
        } else {
            ActivityCompat.requestPermissions(this, SMS_PERMISSIONS, SMS_REQUEST)
        }
    }

    fun podeSMS(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            SMS_REQUEST -> if (!podeSMS()) {
                Toast.makeText(this, "Sem permissão para SMS", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        //registerReceiver(receiver, intentFilter);
        registerReceiver(smsReceiver, IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION))
        Toast.makeText(this, "Registrando...", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        //unregisterReceiver(receiver);
        unregisterReceiver(smsReceiver)
        Toast.makeText(this, "Removendo registro...", Toast.LENGTH_SHORT).show()
        super.onStop()
    }

    companion object {
        val DYN_BROADCAST_ACTION = "br.ufpe.cin.residencia.broadcasts.dinamico"

        private val SMS_PERMISSIONS = arrayOf(Manifest.permission.RECEIVE_SMS)
        private val SMS_REQUEST = 710
    }


}
