package com.example.gestionempleados;

import static com.example.gestionempleados.R.id.lvEmpleados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText casillaDNI;
    ArrayList<Empleado> empleados;
    Adaptador adaptadorEmpleados;
    Empleado empleado = null;
    ListView listview_empleados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnOpenActivity = findViewById(R.id.btnNuevoEmpleado);
        Button botonBuscar = findViewById(R.id.btnBuscar);
        casillaDNI = findViewById(R.id.campoDni);
        listview_empleados = findViewById(lvEmpleados);


        btnOpenActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, formularioEmpleados.class);
                startActivity(intent);
            }
        });


    }


    public void onClickPropio(View view) {
        empleados = new ArrayList<>();

        empleados.add(new Empleado("Idurre", "Aldama Gardeazabal", "11111111A"));
        empleados.add(new Empleado("Meng", "Du", "22222222B"));
        empleados.add(new Empleado("Rafael", "Aguilar Garrido", "33333333C"));


        // Definimos el adaptador propio. En este caso no posee layout.
        adaptadorEmpleados = new Adaptador(this, empleados);
        // Attach the adapter to a ListView


        listview_empleados.setAdapter(adaptadorEmpleados);
        filtrar(String.valueOf(casillaDNI.getText()));

        listview_empleados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), verEmpleado.class);
                startActivity(intent);
            }


        });

    }


    public void filtrar(String texto) {

        ArrayList<Empleado> filtrarlista = new ArrayList<>();
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).toString().contains(texto)&&!texto.isEmpty()) {

                filtrarlista.add(empleados.get(i));
                adaptadorEmpleados.clear();
                adaptadorEmpleados.addAll(filtrarlista);
                adaptadorEmpleados.notifyDataSetChanged();

            }


        }

    }
}


