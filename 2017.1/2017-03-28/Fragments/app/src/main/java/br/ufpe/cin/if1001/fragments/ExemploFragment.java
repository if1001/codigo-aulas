package br.ufpe.cin.if1001.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class ExemploFragment extends Fragment implements View.OnClickListener {

    //assim como activities, fragments tem callback methods
    //principal metodo para override eh onCreateView, que retorna a UI a ser exibida
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        //usa o inflater passado como argumento
        //podemos usar inflaters para inflar qualquer XML e retornar uma View
        //le o XML, faz o parsing, cria objetos Java e devolve View
        View result=inflater.inflate(R.layout.fragmento_botao, container, false);

        //findViewById chamado na view resultante do inflate
        //usamos this, entao esta classe implementa interface...
        result.findViewById(R.id.btn_outro).setOnClickListener(this);

        return(result);
    }

    //por causa do implements View.On...
    @Override
    public void onClick(View v) {

        Toast.makeText(getActivity(), "teste", Toast.LENGTH_SHORT).show();

        //qualquer objeto fragmento pode chamar getActivity
        //como queremos executar um metodo especifico, damos o cast
        ((FragmentActivity)getActivity()).iniciarActivity(v);
    }

    //public void onAttach(Activity a) deprecated
    //chamado primeiro, pra avisar que o fragment foi anexado a uma Activity
    @Override
    public void onAttach(Context a) {
        super.onAttach(a);
        Log.d(getClass().getSimpleName(), "onAttach()");
    }

    //chamado primeiro, pra avisar que o fragment foi anexado a uma Activity
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(getClass().getSimpleName(), "onCreate()");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(getClass().getSimpleName(), "onActivityCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(getClass().getSimpleName(), "onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(getClass().getSimpleName(), "onResume()");
    }

    @Override
    public void onPause() {
        Log.d(getClass().getSimpleName(), "onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(getClass().getSimpleName(), "onStop()");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d(getClass().getSimpleName(), "onDestroyView()");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d(getClass().getSimpleName(), "onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d(getClass().getSimpleName(), "onDetach()");
        super.onDetach();
    }
}
