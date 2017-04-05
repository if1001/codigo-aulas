package br.ufpe.cin.if1001.rss;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {

    private final String RSS_FEED = "http://rss.cnn.com/rss/edition.rss";
    private TextView conteudoRSS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conteudoRSS = (TextView) findViewById(R.id.conteudoRSS);
        /*
        try {
            String feed = getRssFeed(RSS_FEED);
            conteudoRSS.setText(feed);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**/
    }

    @Override
    protected void onStart() {
        super.onStart();
        new CarregaRSStask().execute(RSS_FEED);
    }

    private class CarregaRSStask extends AsyncTask<String,Void,String> {
        @Override
        protected void onPreExecute() {
            Toast.makeText(getApplicationContext(),"iniciando...",Toast.LENGTH_SHORT).show();
        }
        @Override
        protected String doInBackground(String... params) {
            String conteudo = "provavelmente deu erro...";
            try {
                conteudo = getRssFeed(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return conteudo;
        }
        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getApplicationContext(),"terminando...",Toast.LENGTH_SHORT).show();
            conteudoRSS.setText(s);
        }
    }

    private String getRssFeed(String feed) throws IOException {
        InputStream in = null;
        String rssFeed = "";
        try {
            URL url = new URL(feed);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            in = conn.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            for (int count; (count = in.read(buffer)) != -1; ) {
                out.write(buffer,0,count);
            }
            byte[] response = out.toByteArray();
            rssFeed = new String(response,"UTF-8");
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return rssFeed;
    }


        /*
    private String getRssFeedDeprecated(String feed) throws IOException {
        HttpResponse response = null;
        HttpGet httpGet = null;
        HttpClient mHttpClient = null;
        String rssFeed = "";
        try {
            if (mHttpClient==null) {
                mHttpClient = new DefaultHttpClient();
            }
            httpGet = new HttpGet(feed);
            response = mHttpClient.execute(httpGet);
            rssFeed = EntityUtils.toString(response.getEntity(), "UTF-8");
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return rssFeed;
    }


    /**/
}
