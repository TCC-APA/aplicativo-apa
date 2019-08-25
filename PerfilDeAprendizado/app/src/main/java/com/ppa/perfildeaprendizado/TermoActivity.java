package com.ppa.perfildeaprendizado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class TermoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termo);
        TextView textoTermo = findViewById(R.id.textoTermo);
        textoTermo.setMovementMethod(new ScrollingMovementMethod());
    }
}
