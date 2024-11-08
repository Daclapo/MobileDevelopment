package dm2e.davidclarkson.adivinaminumeroestable1;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String STATE_NUM_ELEGIDO = "numElegido";
    private static final String STATE_NUM_INTENTOS = "numIntentos";
    private static final String STATE_MENSAJE = "mensajeActual";

    private int _numElegido;
    private int _numIntentos;
    private String _mensajeActual;

    private final static java.util.Random _dado = new java.util.Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            // Estamos iniciándonos desde cero.
            // Comenzamos una partida.
            reiniciaPartida();
        } else {
            // Tenemos que reconstruirnos desde el bundle.
            _numElegido = savedInstanceState.getInt(STATE_NUM_ELEGIDO);
            _numIntentos = savedInstanceState.getInt(STATE_NUM_INTENTOS);
            _mensajeActual = savedInstanceState.getString(STATE_MENSAJE);

            if (_numElegido == -1) {
                // La partida está terminada.
                partidaAcabada();
            } else {
                // La partida está a mitad. Ponemos el texto de la
                // etiqueta superior.
                TextView tv = findViewById(R.id.etiquetaSuperior);
                tv.setText(_mensajeActual);
                // Actualizamos la etiqueta del número de intentos
                actualizarIntentos();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_NUM_ELEGIDO, _numElegido);
        outState.putInt(STATE_NUM_INTENTOS, _numIntentos);
        outState.putString(STATE_MENSAJE, _mensajeActual);
    }

    public void onProbar(View v) {
        procesarRespuesta();
    }

    protected void reiniciaPartida() {
        _numElegido = _dado.nextInt(100) + 1;
        _numIntentos = 0;
        _mensajeActual = getString(R.string.hePensadoNumero);
        TextView tv = findViewById(R.id.etiquetaSuperior);
        tv.setText(_mensajeActual);
    }

    protected void procesarRespuesta() {
        EditText edit = findViewById(R.id.numero);
        String n = edit.getText().toString();
        int num;
        try {
            num = Integer.parseInt(n);
        } catch (Exception e) {
            return;
        }

        edit.setText("");
        ++_numIntentos;
        if (num == _numElegido) {
            partidaAcabada();
        } else {
            String format;
            if (num < _numElegido) {
                format = getString(R.string.mayor);
            } else {
                format = getString(R.string.menor);
            }
            _mensajeActual = String.format(format, num);
            TextView etiqueta = findViewById(R.id.etiquetaSuperior);
            etiqueta.setText(_mensajeActual);
        }
    }

    private void partidaAcabada() {
        _numElegido = -1;
        String textoEtiqueta = getString(R.string.ganaste);
        textoEtiqueta += getResources().getQuantityString(R.plurals.intentosFinales, _numIntentos, _numIntentos);
        TextView tv = findViewById(R.id.etiquetaSuperior);
        tv.setText(textoEtiqueta);
    }

    private void actualizarIntentos() {
        TextView tv = findViewById(R.id.etiquetaSuperior);
        String textoEtiqueta = _mensajeActual;
        textoEtiqueta += getResources().getQuantityString(R.plurals.intentosFinales, _numIntentos, _numIntentos);
        tv.setText(textoEtiqueta);
    }
}