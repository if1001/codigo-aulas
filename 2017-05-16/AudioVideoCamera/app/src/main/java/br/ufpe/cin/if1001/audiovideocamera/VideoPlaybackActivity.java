package br.ufpe.cin.if1001.audiovideocamera;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoPlaybackActivity extends Activity {

    private VideoView videoView;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //altera formato da tela
        getWindow().setFormat(PixelFormat.TRANSLUCENT);

        setContentView(R.layout.activity_video_playback);

        videoView = (VideoView) findViewById(R.id.videoView);
        mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);

        //pode carregar arquivo de external storage com File...
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video);
        videoView.setVideoURI(video);


    }

    @Override
    protected void onResume() {
        super.onResume();
        videoView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoView.stopPlayback();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
