package br.ufpe.cin.if1001.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SpinnerActivity extends Activity
        implements AdapterView.OnItemSelectedListener {
    private TextView escolha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                Constants.lista);

        escolha = findViewById(R.id.tv_SpinnerActivity);

        Spinner spinner = findViewById(R.id.spinner);
        //Como vão se parecer os itens do dropdown
        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(stringArrayAdapter);

        //Ao invés de definirmos uma implementação anônima, a classe estende a interface associada
        // com o tipo esperado como argumento deste método.
        spinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        escolha.setText(((TextView) view).getText());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //não estou fazendo nada...
    }
}
