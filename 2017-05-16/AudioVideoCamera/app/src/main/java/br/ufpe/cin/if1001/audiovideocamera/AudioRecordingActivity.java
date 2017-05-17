package br.ufpe.cin.if1001.audiovideocamera;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.media.MediaRecorder;
import android.media.MediaRecorder.OnErrorListener;
import android.media.MediaRecorder.OnInfoListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import java.io.File;
import java.util.ArrayList;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class AudioRecordingActivity extends Activity implements
        OnCheckedChangeListener, OnErrorListener, OnInfoListener, View.OnClickListener {

    private static final String FILENAME = "gravacao.3gp";
    private static final int ID_PERMISSION_REQUEST = 4815;
    private static final String KEY_IN_PERMISSION = "temPermissao";
    private boolean isInPermission=false;
    private Bundle state;

    private ToggleButton toggleButton;
    private Button btnPlay;
    private MediaRecorder mediaRecorder = null;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.state=savedInstanceState;

        if (state!=null) {
            isInPermission=state.getBoolean(KEY_IN_PERMISSION, false);
        }

        if (hasAllPermissions(getDesiredPermissions())) {
            onReady(state);
        }
        else if (!isInPermission) {
            isInPermission=true;
            ActivityCompat.requestPermissions(this, netPermissions(getDesiredPermissions()), ID_PERMISSION_REQUEST);
        }
    }

    public void onReady(Bundle savedInstanceState) {
        setContentView(R.layout.activity_audio_recording);
        toggleButton = (ToggleButton) findViewById(R.id.btnRecord);
        toggleButton.setOnCheckedChangeListener(this);
        btnPlay = (Button) findViewById(R.id.btnPlayRecAudio);
        btnPlay.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setOnErrorListener(this);
        mediaRecorder.setOnInfoListener(this);
    }

    @Override
    public void onStop() {
        mediaRecorder.release();
        mediaRecorder = null;

        super.onStop();
    }


    @Override
    public void onError(MediaRecorder mr, int what, int extra) {
        Toast.makeText(this, "Houve algum erro durante gravação!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInfo(MediaRecorder mr, int what, int extra) {
        String msg = "Houve algum erro não identificado durante gravação!";
        if (what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED) {
            msg = "Atingiu limite de duração de áudio!";
        }
        else if(what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_FILESIZE_REACHED) {
            msg = "Atingiu limite de tamanho de arquivo!";
        }
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            File output= new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), FILENAME);

            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setOutputFile(output.getAbsolutePath());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD_MR1) {
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
                mediaRecorder.setAudioEncodingBitRate(160 * 1024);
            }
            else {
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            }

            mediaRecorder.setAudioChannels(2);

            try {
                mediaRecorder.prepare();
                mediaRecorder.start();
            }
            catch (Exception e) {
                Log.e(getClass().getSimpleName(), "Erro ao preparar recorder", e);
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
        else {
            try {
                mediaRecorder.stop();
                btnPlay.setEnabled(true);
            }
            catch (Exception e) {
                Log.w(getClass().getSimpleName(), "Erro ao encerrar recorder", e);
            }
            mediaRecorder.reset();
        }
    }

    @Override
    public void onClick(View v) {
        try {
            File output= new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), FILENAME);
            if (output.exists()) {
                Uri gravacao = Uri.fromFile(output);
                Toast.makeText(this, gravacao.toString(), Toast.LENGTH_SHORT).show();
                mp = MediaPlayer.create(this, gravacao);
                mp.start();
            }
        }
        catch (Exception e) {
            Toast.makeText(this,"Algum problema: "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }


    protected String[] getDesiredPermissions() {
        return(new String[]{RECORD_AUDIO, WRITE_EXTERNAL_STORAGE});
    }

    protected void onPermissionDenied() {
        Toast.makeText(this, "Permissao negada!", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {
        isInPermission=false;

        if (requestCode==ID_PERMISSION_REQUEST) {
            if (hasAllPermissions(getDesiredPermissions())) {
                onReady(state);
            }
            else {
                onPermissionDenied();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_IN_PERMISSION, isInPermission);
    }

    private boolean hasAllPermissions(String[] perms) {
        for (String perm : perms) {
            if (!hasPermission(perm)) {
                return(false);
            }
        }

        return(true);
    }

    protected boolean hasPermission(String perm) {
        return(ContextCompat.checkSelfPermission(this, perm)== PackageManager.PERMISSION_GRANTED);
    }

    private String[] netPermissions(String[] wanted) {
        ArrayList<String> result=new ArrayList<String>();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return(result.toArray(new String[result.size()]));
    }
}