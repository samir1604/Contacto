package com.example.crystal.contacto;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
    implements DatePicker.OnDateChangedListener {

    public static final String EXTRA_NOMBRE = "nombre";
    public static final String EXTRA_FECHA = "fecha";
    public static final String EXTRA_TELEFONO = "telefono";
    public static final String EXTRA_EMAIL = "email";
    public static final String EXTRA_DESCRIPCION = "descripcion";

    private TextInputEditText nombre;
    private DatePicker fecha;
    private TextInputEditText telefono;
    private TextInputEditText email;
    private TextInputEditText descripcion;
    private long pickDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();

        try {
            getExtras();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Button btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityDetail();
            }
        });
    }

    private void setViews() {
        nombre = (TextInputEditText) findViewById(R.id.editNombre);
        fecha = (DatePicker) findViewById(R.id.editDate);
        telefono = (TextInputEditText) findViewById(R.id.editTelefono);
        email = (TextInputEditText) findViewById(R.id.editEmail);
        descripcion = (TextInputEditText) findViewById(R.id.editDescripcion);
    }

    private void getExtras() throws ParseException {
        Bundle params = getIntent().getExtras();
        if(params != null) {

            nombre.setText(params.getString(EXTRA_NOMBRE));
            telefono.setText(params.getString(EXTRA_TELEFONO));
            email.setText(params.getString(EXTRA_EMAIL));
            descripcion.setText(params.getString(EXTRA_DESCRIPCION));

            long d = params.getLong(EXTRA_FECHA);
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTimeInMillis(d);
            setInitialDate(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
        } else {
            GregorianCalendar now = new GregorianCalendar();
            setInitialDate(now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH));
        }
    }

    private void setInitialDate(int year, int month, int day) {
        fecha.init(year, month, day, this);
    }

    private void startActivityDetail() {
        Intent intent = new Intent(MainActivity.this, DetailContact.class);
        intent.putExtra(EXTRA_NOMBRE, nombre.getText().toString());
        intent.putExtra(EXTRA_FECHA, pickDate);
        intent.putExtra(EXTRA_TELEFONO, telefono.getText().toString());
        intent.putExtra(EXTRA_EMAIL, email.getText().toString());
        intent.putExtra(EXTRA_DESCRIPCION, descripcion.getText().toString());
        startActivity(intent);
        finish();
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar then = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        pickDate = then.getTimeInMillis();
    }
}
