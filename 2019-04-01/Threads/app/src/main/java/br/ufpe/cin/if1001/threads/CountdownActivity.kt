package br.ufpe.cin.if1001.threads

import android.app.Activity
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_countdown.*

class CountdownActivity : Activity() {

    internal var pontoPartida = 5
    internal var pontoChegada = 0
    internal var delay = 500
    internal var tarefa: ContagemRegressivaTask? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countdown)

        btn_iniciarCountdown.setOnClickListener {
            tarefa = ContagemRegressivaTask()
            tarefa?.execute(pontoPartida, pontoChegada)
        }

        btn_cancelarCountdown.setOnClickListener { tarefa?.cancel(true) }
    }

    internal fun contagemRegressiva(passos: Int) {
        for (i in passos downTo 0) {
            try {
                Thread.sleep(500)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            valorContagem.text = Integer.toString(i)
        }
    }

    internal inner class ContagemRegressivaTask : AsyncTask<Int, String, Unit>() {

        override fun onPreExecute() {
            valorContagem.text = "vai começar!"
        }

        override fun doInBackground(vararg integers: Int?): Unit {
            //seria interessante checar se tem dois argumentos
            if (integers!=null) {
                var pontoPartida = 5
                val pontoChegada = 0

                //assumindo que pontoChegada < pontoPartida
                while (pontoChegada <= pontoPartida) {
                    if (!isCancelled) {
                        //atualizar o TextView com o valor atual
                        //não pode dar setText... (thread separada)
                        //valorContagem.setText("erro!");
                        try {
                            Thread.sleep(1000)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }

                        publishProgress(pontoPartida?.toString())
                        pontoPartida?.minus(1)
                    }
                }
            }
        }


        override fun onProgressUpdate(vararg values: String) {
            //garantido de rodar na thread principal
            valorContagem.text = values[0]
        }

        override fun onPostExecute(aVoid: Unit) {
            Toast.makeText(applicationContext, "Terminou!", Toast.LENGTH_SHORT).show()
        }
    }

}