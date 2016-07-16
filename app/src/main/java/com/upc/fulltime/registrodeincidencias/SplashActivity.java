package com.upc.fulltime.registrodeincidencias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity {

    private Button btnTrabajador;
    private Button btnCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();

    }

    private void initView()
    {
        btnTrabajador = (Button) findViewById(R.id.btnTrabajador);
        btnCliente = (Button) findViewById(R.id.btnCliente);

        btnTrabajador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irALogin();
            }
        });

        btnCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irALogin();
            }
        });

    }

    private void irALogin()
    {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
