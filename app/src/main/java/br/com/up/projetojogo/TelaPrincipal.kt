package br.com.up.projetojogo

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.view.View

class TelaPrincipal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_principal)

        val inicio = findViewById<Button>(R.id.inicio)
        inicio.setOnClickListener {

            startActivity(Intent(this@TelaPrincipal, MainActivity::class.java))
        }

    }
}