package com.example.radiobuttondavidclarkson;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView comunidadTextView;
    private RadioGroup grupoRadio;
    private List<String> comunidades;
    private String comunidadActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        comunidadTextView = findViewById(R.id.etiqueta2);
        grupoRadio = findViewById(R.id.grupo);

        // Lista de nombres de comunidades
        comunidades = new ArrayList<>();
        comunidades.add("Andalucía");
        comunidades.add("Aragón");
        comunidades.add("Asturias");
        comunidades.add("Cantabria");
        comunidades.add("Castilla la Mancha");
        comunidades.add("Castilla y León");
        comunidades.add("Cataluña");
        comunidades.add("Ceuta");
        comunidades.add("Comunidad Valenciana");
        comunidades.add("Extremadura");
        comunidades.add("Galicia");
        comunidades.add("Islas Baleares");
        comunidades.add("Islas Canarias");
        comunidades.add("La Rioja");
        comunidades.add("Madrid");
        comunidades.add("Melilla");
        comunidades.add("Murcia");
        comunidades.add("Navarra");
        comunidades.add("País Vasco");

        Collections.shuffle(comunidades);
        siguienteComunidad();

        grupoRadio.setOnCheckedChangeListener((group, checkedId) -> verificarRespuesta(checkedId));
    }

    private void siguienteComunidad() {
        if (!comunidades.isEmpty()) {
            comunidadActual = comunidades.remove(0);
            comunidadTextView.setText(comunidadActual);
            grupoRadio.clearCheck();
        } else {
            comunidadTextView.setText("¡Has terminado todas las comunidades!");
        }
    }

    private void verificarRespuesta(int checkedId) {
        RadioButton radioButtonSeleccionado = findViewById(checkedId);
        if (radioButtonSeleccionado != null) {
            String idComunidadSeleccionada = getResources().getResourceEntryName(checkedId);

            AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
            dialogo.setCancelable(false);

            if (comunidadActual.equals(obtenerNombreComunidad(idComunidadSeleccionada))) {
                dialogo.setMessage(getString(R.string.correcto));
            } else {
                dialogo.setMessage(getString(R.string.incorrecto));
            }

            dialogo.setPositiveButton("OK", (dialog, which) -> siguienteComunidad());
            dialogo.show();
        }
    }

    private String obtenerNombreComunidad(String idComunidad) {
        switch (idComunidad) {
            case "andalucia":
                return "Andalucía";
            case "aragon":
                return "Aragón";
            case "asturias":
                return "Asturias";
            case "cantabria":
                return "Cantabria";
            case "castillalamancha":
                return "Castilla la Mancha";
            case "castillayleon":
                return "Castilla y León";
            case "catalunia":
                return "Cataluña";
            case "ceuta":
                return "Ceuta";
            case "comvalenciana":
                return "Comunidad Valenciana";
            case "extremadura":
                return "Extremadura";
            case "galicia":
                return "Galicia";
            case "islas_baleares":
                return "Islas Baleares";
            case "islas_canarias":
                return "Islas Canarias";
            case "la_rioja":
                return "La Rioja";
            case "madrid":
                return "Madrid";
            case "melilla":
                return "Melilla";
            case "murcia":
                return "Murcia";
            case "navarra":
                return "Navarra";
            case "pais_vasco":
                return "País Vasco";
            default:
                return "";
        }
    }
}
