package br.ufpe.cin.if1001.services;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DownloadActivity extends Activity {
    Button botaoDownload;

    public static final String downloadLink = "http://s2.glbimg.com/gRLYk-GmK-2-FbSvOTV20IahiKk=/0x0:5312x2988/600x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_59edd422c0c84a879bd37670ae4f538a/internal_photos/bs/2017/Q/d/HQEHUXSseqgqg86sGvtQ/20170425-180848.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        botaoDownload = (Button) findViewById(R.id.botaoDownload);
        botaoDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botaoDownload.setEnabled(false);
                Intent downloadService = new Intent(getApplicationContext(),DownloadService.class);
                downloadService.setData(Uri.parse(downloadLink));
                startService(downloadService);
            }
        });

        Button botaoVisualizar = (Button) findViewById(R.id.botaoVisualizar);

        botaoVisualizar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent viewFile = new Intent(getApplicationContext(),DownloadViewActivity.class);
                startActivity(viewFile);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter f = new IntentFilter(DownloadService.DOWNLOAD_COMPLETE);
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(onDownloadCompleteEvent, f);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(onDownloadCompleteEvent);
    }

    private BroadcastReceiver onDownloadCompleteEvent=new BroadcastReceiver() {
        public void onReceive(Context ctxt, Intent i) {
            botaoDownload.setEnabled(true);
            Toast.makeText(ctxt, "Download finalizado!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(ctxt,DownloadViewActivity.class));
        }
    };
}
