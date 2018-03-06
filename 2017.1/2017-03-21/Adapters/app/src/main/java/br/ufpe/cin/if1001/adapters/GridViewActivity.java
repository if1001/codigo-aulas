package br.ufpe.cin.if1001.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class GridViewActivity extends Activity implements AdapterView.OnItemClickListener {

    private TextView escolha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this,
                R.layout.celula,
                Constants.listaLonga);
        escolha = (TextView) findViewById(R.id.tv_gridViewActivity);

        GridView gv = (GridView) findViewById(R.id.gridView);
        gv.setAdapter(stringArrayAdapter);
        gv.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        escolha.setText(Constants.listaLonga[position]);
    }
}
