package com.example.gestionempleados;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
public class formularioEmpleados extends AppCompatActivity {
    EditText nombreEditText;
    EditText apellidosEditText;
    EditText dniEditText;
    EditText telefonoEditText; // Variables de tipo EditText que apuntan a los campos del formulario
    EditText emailEditText;
    EditText puestoEditText;
    EditText horarioEditText;
    EditText antiguedadEditText;
    EditText salarioEditText;
    Button guardarButton;

    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_empleados);
        scrollView = findViewById(R.id.scrollView);

        nombreEditText = findViewById(R.id.nombreEditText);
        apellidosEditText = findViewById(R.id.apellidosEditText);
        dniEditText = findViewById(R.id.dniEditText);
        telefonoEditText = findViewById(R.id.telefonoEditText); // Variables de tipo EditText que apuntan a los campos del formulario
        emailEditText = findViewById(R.id.emailEditText);
        puestoEditText = findViewById(R.id.puestoEditText);
        horarioEditText = findViewById(R.id.horarioEditText);
        antiguedadEditText = findViewById(R.id.antiguedadEditText);
        salarioEditText = findViewById(R.id.salarioEditText);

        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject json = crearJSON();
                RequestTask requestTask = new RequestTask();
                requestTask.execute("http://192.168.1.131/test/insertar_empleado.php", json.toString());
                requestTask.execute("http://192.168.1.131/test/list_trabajadores.php");
            }
        });

        nombreEditText.setOnKeyListener(new View.OnKeyListener() {  // Metodo que comprueba si el Focus esta en el campo de texto Nombre y si es asi
            // al pulsar la tecla enter se desplaza al siguiente EditText en este caso el campo apellidos.
            // se crea un metodo por cada campo de texto
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    apellidosEditText.requestFocus();
                    return true;
                }
                return false;
            }
        });

        apellidosEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    dniEditText.requestFocus();
                    return true;
                }
                return false;
            }
        });

        dniEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    telefonoEditText.requestFocus();
                    return true;
                }
                return false;
            }
        });

        telefonoEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    emailEditText.requestFocus();
                    return true;
                }
                return false;
            }
        });

        emailEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    puestoEditText.requestFocus();
                    return true;
                }
                return false;
            }
        });

        puestoEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    horarioEditText.requestFocus();
                    return true;
                }
                return false;
            }
        });

        horarioEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    antiguedadEditText.requestFocus();
                    return true;
                }
                return false;
            }
        });

        antiguedadEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    salarioEditText.requestFocus();
                    return true;
                }
                return false;
            }
        });





        final View activityRootView = findViewById(R.id.scrollView);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                activityRootView.getWindowVisibleDisplayFrame(r);
                int screenHeight = activityRootView.getRootView().getHeight();
                int keypadHeight = screenHeight - r.bottom;

                // Si el teclado virtual está visible, ajusta el desplazamiento del ScrollView
                if (keypadHeight > screenHeight * 0.15) {
                    scrollView.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollView.scrollTo(0, scrollView.getBottom());
                        }
                    });
                } else {
                    // Si el teclado virtual no está visible, ajusta el desplazamiento del ScrollView al principio
                    scrollView.scrollTo(0, 0);
                }
            }
        });
    }
    public JSONObject crearJSON(){
        JSONObject json = new JSONObject();
        try {
            json.put("nombre", nombreEditText.getText());
            json.put("dni", dniEditText.getText());
            json.put("apellidos", apellidosEditText.getText());
            json.put("telefono", telefonoEditText.getText());
            json.put("email", emailEditText.getText());
            json.put("puesto", puestoEditText.getText());
            json.put("horario", horarioEditText.getText());
            json.put("salario", salarioEditText.getText());
            return json;
        } catch (JSONException e) {
            System.err.println("Error al crear el json: " + e.toString());
        }
        return null;
    }
    public static String sendGet(String surl){
        try{
            URL url = new URL(surl);
            //Creamos la connexión
            HttpURLConnection con =  (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            Log.d("formularioEmpleados", "GET Response code: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                //Leemos la respuesta del servidor
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return response.toString();
            } else {
                Log.e("formularioEmpleados", "El metodo get no ha funcionado: " + responseCode);
            }

        }catch (Exception e){
            Log.e("formularioEmpleados", "Error al hacer el get: " + e.toString());
        }
        return null;
    }
    public static void sendPost(String surl, String jsonData)  {
        try{
            URL url = new URL(surl);
            //Creamos la connexión
            HttpURLConnection con =  (HttpURLConnection) url.openConnection();
            //Ponemos cabeceras http para indicar que mandamos un json
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Content-Length", String.valueOf(jsonData.getBytes().length));
            //Especificamos el metodo http (POST/GET/PUT/DELETE/HEAD/OPTIONS)
            con.setRequestMethod("POST");
            //Especificamos que mandamos datos
            con.setDoOutput(true);
            //Enviamos la peticion, escribiendo el json
            OutputStream os = con.getOutputStream();
            byte[] input = jsonData.getBytes("utf-8");
            os.write(input, 0, input.length);
            os.close();

            //Si el servidor devuelve 200 es que ha ido bien
            if(con.getResponseCode() == 200) {
                //Leemos la respuesta del servidor
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                Log.d("formularioEmpleados", "Respuesta: " + response.toString());
            }

        }catch (Exception e){
            Log.e("formularioEmpleados", "Error: " + e.toString());
        }


    }
    class RequestTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... surl) {

            //sendPost(surl[0], surl[1]);
            String resultado = sendGet(surl[0]);


            return resultado;
        }

        @Override
        protected void onPostExecute(String resultado) {
            super.onPostExecute(resultado);
            Log.d("formularioEmpleados", resultado);
            // JSONArray jsonArray = new JSONArray(re)
            nombreEditText.setText(resultado);

        }
    }
}
