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

        escolha = findViewById(R.id.tv_autoCompleteActivity);
        acTV = findViewById(R.id.autoCompleteTV);

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line,
                Constants.palavrasSimilares);

        acTV.setAdapter(stringArrayAdapter);

        acTV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //nao utilizado

                //This method is called to notify you that, within s, the count characters
                // beginning at start are about to be replaced by new text with length after.
                // It is an error to attempt to make changes to s from this callback.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //escolha.setText(acTV.getText());

                //This method is called to notify you that, within s, the count characters
                // beginning at start have just replaced old text that had length before.
                // It is an error to attempt to make changes to s from this callback.
            }

            @Override
            public void afterTextChanged(Editable s) {
                escolha.setText(s.toString());

                //This method is called to notify you that, somewhere within s, the text
                // has been changed. It is legitimate to make further changes to s from
                // this callback, but be careful not to get yourself into an infinite
                // loop, because any changes you make will cause this method to be called
                // again recursively. (You are not told where the change took place because
                // other afterTextChanged() methods may already have made other changes and
                // invalidated the offsets. But if you need to know here, you can use
                // setSpan(Object, int, int, int) in onTextChanged(CharSequence, int, int, int)
                // to mark your place and then look up from here where the span ended up.
            }
        });
    }
}
