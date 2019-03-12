package br.ufpe.cin.if710.datamanagement;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MemoriaInternaActivity extends Activity {

    //Nome do arquivo
    private final static String arquivo = "teste.txt";
    //Vai ser usado para exibir o conteúdo do arquivo
    LinearLayout ll_conteudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoria_interna);

        //Pegando referência dos widgets
        final EditText texto = findViewById(R.id.campoTexto);
        ll_conteudo = findViewById(R.id.ll_conteudoArquivo);
        Button salvar = findViewById(R.id.botaoSalvar);
        Button ler = findViewById(R.id.botaoLer);
        Button limpar = findViewById(R.id.botaoLimpar);
        Button apagar = findViewById(R.id.botaoApagar);

        //O que acontece ao clicar em salvar
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pega o texto digitado
                String txt = texto.getText().toString();
                try {
                    //escreve no arquivo
                    escreverArquivo(txt);
                    //limpa caixa de texto
                    texto.setText("");
                }
                //como é operação de IO, pode ter exceção
                catch (FileNotFoundException e) {
                    Log.i("MemoriaInterna", e.getMessage());
                    e.printStackTrace();
                }
            }
        });

        limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //limpa o conteúdo abaixo dos botões
                limparConteudo();
            }
        });

        ler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFileStreamPath(arquivo).exists()) {
                    try {
                        //limpa o conteúdo abaixo dos botões
                        limparConteudo();
                        //lê o conteúdo do arquivo linha a linha
                        lerArquivo();
                    } catch (IOException e) {
                        Log.i("MemoriaInterna", e.getMessage());
                        e.printStackTrace();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Arquivo inexistente",Toast.LENGTH_SHORT).show();
                }
            }
        });

        apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //apaga o arquivo da memória interna
                apagarArquivo();
                //limpa o conteúdo abaixo dos botões
                limparConteudo();
            }
        });


    }

    private void escreverArquivo(String txt) throws FileNotFoundException {
        //MODE_PRIVATE
        //MODE_APPEND
        //abrindo o arquivo para escrever e manter o que já tem lá (MODE_APPEND)
        FileOutputStream fos = openFileOutput(arquivo, MODE_APPEND);
        //PrintWriter permite que a gente escreva linhas em um arquivo de texto, passando strings
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(fos)));
        //Escreve o que estiver na String txt e coloca uma quebra de linha após
        pw.println(txt);
        //fecha o arquivo
        pw.close();
    }

    private void lerArquivo() throws IOException {
        //abre o arquivo para leitura
        FileInputStream fis = openFileInput(arquivo);
        //BufferedReader permite ler o arquivo linha a linha
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        String linha = "";

        //Para cada linha faça
        while (null != (linha = br.readLine())) {
            //Criar novo objeto TextView
            TextView tv = new TextView(this);
            //Definir tamanho do texto
            tv.setTextSize(16);
            //Colocar neste objeto a string da linha
            tv.setText(linha);
            //Adicionar textview na tela
            ll_conteudo.addView(tv);
        }
        //fechando o arquivo
        br.close();
    }

    private void apagarArquivo() {
        //se o arquivo existe, apaga
        if (getFileStreamPath(arquivo).exists()) {
            deleteFile(arquivo);
        }
        else {
            Toast.makeText(getApplicationContext(),"Arquivo inexistente", Toast.LENGTH_SHORT).show();
        }
    }

    void limparConteudo() {
        //apaga todos os textviews que estiverem neste linear layout
        ll_conteudo.removeAllViews();
    }
}
