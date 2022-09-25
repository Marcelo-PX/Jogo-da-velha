package br.com.up.projetojogo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    override fun onClick(v: View?) {
        var btnClicado = findViewById<Button>(v!!.id)

        if(jogadorAtual == jogador1){
            btnClicado.setText("0")
            jogadorAtual = jogador2
        }else{
            btnClicado.setText("X")
            jogadorAtual = jogador1
        }
    }
}