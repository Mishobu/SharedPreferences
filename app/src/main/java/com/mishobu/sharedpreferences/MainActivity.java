package com.mishobu.sharedpreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    SharedPreferences misDatos;
    EditText editNombre, editCoordX, editCoordY;
    String nombre;
    Float x, y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNombre = (EditText) findViewById(R.id.edtNombre);
        editCoordX = (EditText) findViewById(R.id.edtCordX);
        editCoordY = (EditText) findViewById(R.id.edtCoordY);

        misDatos = getSharedPreferences("Preferencias", MODE_PRIVATE);

        nombre = misDatos.getString("nombre", "Valor por defecto");
        x = misDatos.getFloat("x", 0);
        y = misDatos.getFloat("y", 0);

        editNombre.setText(nombre);
        editCoordX.setText("" + x);
        editCoordY.setText("" + y);
    }

    @Override
    protected void onPause() {
        super.onPause();

        nombre = editNombre.getText().toString();
        x = Float.parseFloat(editCoordX.getText().toString());
        y = Float.parseFloat(editCoordY.getText().toString());

        SharedPreferences.Editor miEditor = misDatos.edit();
        miEditor.putString("nombre", nombre);
        miEditor.putFloat("x", x);
        miEditor.putFloat("y", y);

        miEditor.commit();
        Toast.makeText(this, "Preferencias Guardadas", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}