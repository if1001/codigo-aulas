package br.ufpe.cin.if1001.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class AutoCompleteActivity extends Activity {
    private TextView escolha;
    private AutoCompleteTextView acTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete);

        escolha = (TextView) findViewById(R.id.tv_autoCompleteActivity);
        acTV = (AutoCompleteTextView) findViewById(R.id.autoCompleteTV);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line,
                Constants.listaLonga);

        acTV.setAdapter(stringArrayAdapter);
        acTV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //nao utilizado
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                escolha.setText(acTV.getText());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //nao utilizado
            }
        });
    }
}
