package com.example.gestionempleados;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Adaptador extends ArrayAdapter<Empleado> {
    TextView nombre;
    TextView apellidos;
    TextView dni;


    public Adaptador(Context context, ArrayList<Empleado> empleados) {

        super(context, R.layout.empleados_lv, empleados);
    }

    private static class ViewHolder {

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Empleado empleado   = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.empleados_lv, parent, false);
        }
        // Creamos las variables que apuntan a los TextView definidos en el layout
        nombre=  convertView.findViewById(R.id.tvListaNombre);
        apellidos =  convertView.findViewById(R.id.tvListaApellidos);
        dni =  convertView.findViewById(R.id.tvListaDni);

        // Informamos los valores de los TextView
        nombre.setText(empleado.nombre);
        apellidos.setText(empleado.apellidos);
        dni.setText(empleado.dni);

        // Definimos una variable para poder saber el contexto
        View finalConvertView = convertView;

        // Devolvemos la vista para que se pinte por la pantalla
        return convertView;
    }
}

