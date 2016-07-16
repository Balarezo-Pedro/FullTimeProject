package com.upc.fulltime.registrodeincidencias;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtUsuario;
    private EditText edtClave;
    private Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

    }

    private void initView()
    {
        edtUsuario = (EditText) findViewById(R.id.edtUsuario);
        edtClave = (EditText) findViewById(R.id.edtClave);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
    }

    private void ingresar()
    {

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.btnIngresar:
                ingresar();
                break;
        }

    }
}
