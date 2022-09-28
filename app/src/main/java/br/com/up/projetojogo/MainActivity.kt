package br.com.up.projetojogo

import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var b1 : Button
    lateinit var b2 : Button
    lateinit var b3 : Button
    lateinit var b4 : Button
    lateinit var b5 : Button
    lateinit var b6 : Button
    lateinit var b7 : Button
    lateinit var b8 : Button
    lateinit var b9 : Button

    lateinit var textView : TextView
    lateinit var textView2 : TextView
    lateinit var sequencia : IntArray

    var jogador1 = 1
    var jogador2 = 2
    var jogadorAtual = jogador1

    var jogadorVencedor = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Vetor de posições dos botões de 1 a 9 (Obs: o index é contado a partir de 1).
        sequencia = intArrayOf(0,0,0,0,0,0,0,0,0,0)

        textView = findViewById(R.id.textView)
        textView2 = findViewById(R.id.textView2)

        b1 = findViewById(R.id.b1)
        b2 = findViewById(R.id.b2)
        b3 = findViewById(R.id.b3)
        b4 = findViewById(R.id.b4)
        b5 = findViewById(R.id.b5)
        b6 = findViewById(R.id.b6)
        b7 = findViewById(R.id.b7)
        b8 = findViewById(R.id.b8)
        b9 = findViewById(R.id.b9)

        b1.setOnClickListener(this)
        b2.setOnClickListener(this)
        b3.setOnClickListener(this)
        b4.setOnClickListener(this)
        b5.setOnClickListener(this)
        b6.setOnClickListener(this)
        b7.setOnClickListener(this)
        b8.setOnClickListener(this)
        b9.setOnClickListener(this)
    }

    override fun onClick(v: View) {

        if(jogadorVencedor) // Se o valor retornar verdadeiro o jogo pausa.
            return

        var btnClicado = findViewById<Button>(v.id) // Id do botão.
        var btnTag = Integer.parseInt(btnClicado.tag.toString()) // Número do botão.

        if(sequencia[btnTag] != 0) // Validar se o botão já foi selecionado.
            return

        sequencia[btnTag] = jogadorAtual

        if(jogadorAtual == jogador1){ // Alternar jogador atual.

            btnClicado.setText("X")
            jogadorAtual = jogador2
            textView2.setText("Vez do jogador 2!")
            textView2.setTextColor(Color.RED)
            btnClicado.setTextColor(Color.BLACK)
            btnClicado.backgroundTintList = getColorStateList(R.color.verde)
        }else{
            btnClicado.setText("0")
            jogadorAtual = jogador1
            textView2.setText("Vez do jogador 1!")
            textView2.setTextColor(Color.GREEN)
            btnClicado.setTextColor(Color.BLACK)
            btnClicado.backgroundTintList = getColorStateList(R.color.vermelho)
        }

        validarVencedor() // Função para definir jogador vitorioso ou empate.
    }

    private fun validarVencedor() {
        // Matriz de posições válidas.
        var vencedor = arrayOf(
            intArrayOf(1,2,3),
            intArrayOf(4,5,6),
            intArrayOf(7,8,9),
            intArrayOf(1,4,7),
            intArrayOf(2,5,8),
            intArrayOf(3,6,9),
            intArrayOf(1,5,9),
            intArrayOf(3,5,7)
        )

        for(i in 0 until vencedor.size) {
            // Validando se as posições tem o mesmo valor.
            var p1 = vencedor[i][0]
            var p2 = vencedor[i][1]
            var p3 = vencedor[i][2]

            if(sequencia[p1] == sequencia[p2] && sequencia[p1] == sequencia[p3]) {

                if(sequencia[p1] != 0) { // Validando se o vetor possui o valor inicial.

                    jogadorVencedor = true
                    textView.setTextColor(Color.rgb(55,0,179)) // PURPLE_700

                    if(sequencia[p1] == jogador1) {

                        textView2.setText("Jogador 1 ganhou!")
                        textView2.setTextColor(Color.GREEN)
                        reiniciar("Jogador um é o vencedor.")
                    }else{
                        textView2.setText("Jogador 2 ganhou!")
                        textView2.setTextColor(Color.RED)
                        reiniciar("Jogador dois é o vencedor.")
                    }
                }
            }
        }

        if(jogadorVencedor == false) {
            var empate = true

            for(i in 1 until sequencia.size) {
                // Se o index retornar 0 o vetor ainda não foi preenchido.
                if(sequencia[i] == 0) {
                    empate = false
                }
            }
            if(empate == true) {

                textView2.setText("Empate!")
                textView2.setTextColor(Color.YELLOW)
                textView.setTextColor(Color.rgb(55,0,179)) // PURPLE_700
                reiniciar("Deu velha.")
            }
        }
    }

    private fun reiniciar(s: String) {
        // Caixa de diálogo com a função para reiniciar o jogo.
        AlertDialog.Builder(this)
            .setMessage(s)
            .setTitle("Resultado")
            .setPositiveButton("Reiniciar jogo", DialogInterface.OnClickListener {
                    _, _ -> reiniciarJogo()
            })
            .setCancelable(false)
            .show()
    }

    private fun reiniciarJogo() { // O jogo retorna ao estado inicial.

        sequencia = intArrayOf(0,0,0,0,0,0,0,0,0,0)

        jogadorVencedor = false
        jogadorAtual = jogador1

        textView2.setText("Aperte para começar")
        textView2.setTextColor(Color.rgb(160,160,160)) // CINZA

        textView.setTextColor(Color.rgb(98,0,238)) // PURPLE_500

        b1.setText("")
        b2.setText("")
        b3.setText("")
        b4.setText("")
        b5.setText("")
        b6.setText("")
        b7.setText("")
        b8.setText("")
        b9.setText("")
        // PURPLE_500
        b1.backgroundTintList = getColorStateList(R.color.cor_padrao)
        b2.backgroundTintList = getColorStateList(R.color.cor_padrao)
        b3.backgroundTintList = getColorStateList(R.color.cor_padrao)
        b4.backgroundTintList = getColorStateList(R.color.cor_padrao)
        b5.backgroundTintList = getColorStateList(R.color.cor_padrao)
        b6.backgroundTintList = getColorStateList(R.color.cor_padrao)
        b7.backgroundTintList = getColorStateList(R.color.cor_padrao)
        b8.backgroundTintList = getColorStateList(R.color.cor_padrao)
        b9.backgroundTintList = getColorStateList(R.color.cor_padrao)
    }
}