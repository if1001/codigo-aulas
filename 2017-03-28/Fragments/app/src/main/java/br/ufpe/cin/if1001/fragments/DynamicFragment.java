package br.ufpe.cin.if1001.fragments;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class DynamicFragment extends ListFragment {

    private static final String[] listaLonga = {
            "um","exemplo","de","lista","mais","longa","para","servir","de","entrada",
            "e","potencialmente","exceder","o","limite","da","tela","pra","testarmos","scrolling"
    };

    //chamado apos onCreateView - como estamos herdando ListFragment
    // ja vamos utilizar uma implementacao padrao de onCreateView que usa ListView
    //  aqui vamos apenas definir o adapter
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,listaLonga));
    }
}
