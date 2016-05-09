package com.example.crystal.contacto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DetailContact extends AppCompatActivity {

    private String nombre;
    private long fecha;
    private String telefono;
    private String email;
    private String descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contact);
        getExtraParams();
        setTextViewsValues();
        Button btnEnviar = (Button) findViewById(R.id.btnEditar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMainActivity();
            }
        });
    }

    private void setTextViewsValues() {
        TextView txNombre = txNombre = (TextView) findViewById(R.id.textNombre);
        TextView txtFecha = txtFecha = (TextView) findViewById(R.id.textFecha);
        TextView txtTelefono = txtTelefono = (TextView) findViewById(R.id.textTelefono);
        TextView txtEmail = txtEmail = (TextView) findViewById(R.id.textEmail);
        TextView txtDescripcion = txtDescripcion = (TextView) findViewById(R.id.textDescripcion);
        txNombre.setText(nombre);
        txtFecha.setText(getDateFromMiliseconds(fecha));
        txtTelefono.setText(telefono);
        txtEmail.setText(email);
        txtDescripcion.setText(descripcion);
    }

    private void getExtraParams() {
        Bundle parametros = getIntent().getExtras();
        if(parametros != null) {
            nombre = parametros.getString(MainActivity.EXTRA_NOMBRE);
            fecha = parametros.getLong(MainActivity.EXTRA_FECHA);
            telefono = parametros.getString(MainActivity.EXTRA_TELEFONO);
            email = parametros.getString(MainActivity.EXTRA_EMAIL);
            descripcion = parametros.getString(MainActivity.EXTRA_DESCRIPCION);
        }
    }

    private void backToMainActivity() {
        Intent intent = new Intent(DetailContact.this, MainActivity.class);
        intent.putExtra(MainActivity.EXTRA_NOMBRE, nombre);
        intent.putExtra(MainActivity.EXTRA_FECHA, fecha);
        intent.putExtra(MainActivity.EXTRA_TELEFONO, telefono);
        intent.putExtra(MainActivity.EXTRA_EMAIL, email);
        intent.putExtra(MainActivity.EXTRA_DESCRIPCION, descripcion);
        startActivity(intent);
        finish();
    }

    private String getDateFromMiliseconds(long milliSecondsDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(milliSecondsDate);
        return dateFormat.format(calendar.getTime());
    }
}
