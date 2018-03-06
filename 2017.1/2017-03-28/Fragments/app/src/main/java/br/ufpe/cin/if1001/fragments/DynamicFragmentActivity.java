package br.ufpe.cin.if1001.fragments;

import android.app.Activity;
import android.os.Bundle;

public class DynamicFragmentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //nao tem setContentView

        //FragmentManager sabe os fragmentos que existem na activity
        if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, new DynamicFragment())
                    .commit();
            //iniciamos a transacao, para adicionar o objeto correspondente ao fragmento
            // e damos commit para confirmar a transacao

            //android.R.id.content eh onde normalmente vai parar o layout passado em setContentView
            //o teste do if eh para poder lidar com mudancas de configuracao... ainda detalharemos
        }
    }
}
