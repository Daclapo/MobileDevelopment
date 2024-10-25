package dm2e.davidclarkson.adivinaminumerodavidclarkson;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int _numElegido;
    private int _numIntentos;
    private final static java.util.Random _dado = new java.util.Random();

    private EditText numeroField;
    private Button reiniciarBtn;
    private TextView intentosTextView;
    private int numVeces = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numeroField = findViewById(R.id.numero);
        reiniciarBtn = findViewById(R.id.reiniciar);
        intentosTextView = findViewById(R.id.intentos);

        numeroField.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    procesarRespuesta();
                    return true;
                }
                return false;
            }
        });
        reiniciarPartida();
    }


/*
    public void pulsarBoton(View v) {
        ++numVeces;
        Button boton = (Button) findViewById(R.id.boton);
        String text = getString(R.string.mensaje, numVeces);
        boton.setText(text);
    }

    public void cambiarColorFondo(View v) {
        View root = findViewById(android.R.id.content); // Obtiene el layout raíz
        java.util.Random random = new java.util.Random();
        int color = android.graphics.Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        root.setBackgroundColor(color);
    }
*/


    /**
     * Método que reinicia la partida, elige un nuevo número aleatorio
     * y reinicia el contador de intentos.
     */
    protected void reiniciarPartida() {
        _numElegido = _dado.nextInt(100) + 1;
        _numIntentos = 0;

        TextView etiquetaSuperior = findViewById(R.id.etiquetaSuperior);
        etiquetaSuperior.setText(R.string.hePensadoNumero);

        numeroField.setEnabled(true);
        numeroField.setVisibility(View.VISIBLE);

        reiniciarBtn.setVisibility(View.GONE);
        intentosTextView.setText("");
    }

    /**
     * Procesa la respuesta del usuario. Recoge el valor
     * introducido del cuadro de texto, lo convierte a número,
     * y lo compara con el número a adivinar.
     */
    private void procesarRespuesta() {
        String numUsuarioTxt = numeroField.getText().toString();

        try {
            int numUsuario = Integer.parseInt(numUsuarioTxt);
            _numIntentos++;
            comprobarNumero(numUsuario);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor, introduce un número válido", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Compara el número introducido por el usuario con el número generado
     * y actualiza el texto con pistas.
     */
    private void comprobarNumero(int numUsuario) {
        TextView etiquetaSuperior = findViewById(R.id.etiquetaSuperior);

        if (numUsuario == _numElegido) {
            partidaAcabada();
        } else if (numUsuario < _numElegido) {
            String mensaje = getString(R.string.mayor, numUsuario);
            etiquetaSuperior.setText(mensaje);
        } else {
            String mensaje = getString(R.string.menor, numUsuario);
            etiquetaSuperior.setText(mensaje);
        }

        intentosTextView.setText(getString(R.string.intentos, _numIntentos));
    }

    /**
     * Método llamado cuando se detecta el fin de la partida
     * (el jugador ha acertado). Se pone la felicitación en
     * la etiqueta del mensaje y se deshabilita el campo de texto.
     */
    private void partidaAcabada() {
        String textoEtiqueta;
        android.content.res.Resources res = getResources();
        textoEtiqueta = res.getString(R.string.ganaste); //
        textoEtiqueta += res.getQuantityString(R.plurals.intentosFinales, _numIntentos, _numIntentos);

        TextView tv = findViewById(R.id.etiquetaSuperior);
        tv.setText(textoEtiqueta);

        numeroField.setEnabled(false);
        numeroField.setVisibility(View.GONE);

        reiniciarBtn.setVisibility(View.VISIBLE);
    }

    /**
     * Método para reiniciar la partida cuando el botón "Reiniciar" es pulsado.
     */
    public void reiniciarPartida(View v) {
        reiniciarPartida();
    }
}
