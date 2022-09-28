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

    var jogador1 = 0
    var jogador2 = 1
    var jogadorAtual = jogador1

    lateinit var sequencia : IntArray
    lateinit var textView : TextView

    var jogadorVencedor = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sequencia = intArrayOf(0,-1,-1,-1,-1,-1,-1,-1,-1,-1)

        textView = findViewById(R.id.textView2)

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

        if(jogadorVencedor)
            return

        var btnClicado = findViewById<Button>(v.id)
        var btnTag = Integer.parseInt(btnClicado.tag.toString())

        if(sequencia[btnTag]!= -1)
            return

        sequencia[btnTag] = jogadorAtual

        if(jogadorAtual == jogador1){
            btnClicado.setText("X")
            jogadorAtual = jogador2
            textView.setText("Vez do jogador 2")
            textView.setTextColor(Color.RED)
            btnClicado.setTextColor(Color.BLACK)
            btnClicado.backgroundTintList = getColorStateList(R.color.verde)
        }else{
            btnClicado.setText("0")
            jogadorAtual = jogador1
            textView.setText("Vez do jogador 1")
            textView.setTextColor(Color.GREEN)
            btnClicado.setTextColor(Color.BLACK)
            btnClicado.backgroundTintList = getColorStateList(R.color.vermelho)
        }

        validarVencedor()
    }

    private fun validarVencedor() {

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
        for(i in 0 until vencedor.size){
            var p1 = vencedor[i][0]
            var p2 = vencedor[i][1]
            var p3 = vencedor[i][2]

            if(sequencia[p1] == sequencia[p2] && sequencia[p1] == sequencia[p3]){

                if(sequencia[p1]!= -1){
                    jogadorVencedor = true

                    if(sequencia[p1] == jogador1){
                        textView.setText("Jogador 1 ganhou!")
                        textView.setTextColor(Color.GREEN)
                        reiniciar("Jogador 1 ganhou!")
                    }else{
                        textView.setText("Jogador 2 ganhou!")
                        textView.setTextColor(Color.RED)
                        reiniciar("Jogador 2 ganhou!")
                    }
                }
            }
        }

        if(jogadorVencedor == false){
            var empate = true

            for(i in 0 until sequencia.size){
                if(sequencia[i]== -1){
                    empate = false
                }
            }
            if(empate == true){
                textView.setText("EMPATE!")
                textView.setTextColor(Color.YELLOW)
                reiniciar("EMPATE!")
            }
        }
    }

    private fun reiniciar(s: String) {
        AlertDialog.Builder(this)
            .setMessage(s)
            .setTitle("Resultado")
            .setPositiveButton("Reiniciar jogo", DialogInterface.OnClickListener {
                    _, _ -> reiniciarJogo()
            })
            .setCancelable(false)
            .show()
    }

    private fun reiniciarJogo() {

        sequencia = intArrayOf(0,-1,-1,-1,-1,-1,-1,-1,-1,-1)

        jogadorAtual = jogador1
        jogadorVencedor = false

        textView.setText("Aperte para começar")
        textView.setTextColor(Color.GRAY)

        b1.setText("")
        b2.setText("")
        b3.setText("")
        b4.setText("")
        b5.setText("")
        b6.setText("")
        b7.setText("")
        b8.setText("")
        b9.setText("")

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