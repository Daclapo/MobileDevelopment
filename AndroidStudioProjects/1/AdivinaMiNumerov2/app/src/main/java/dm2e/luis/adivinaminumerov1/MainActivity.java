package dm2e.luis.adivinaminumerov1;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
        // Comenzamos una partida.
        reiniciarPartida();

    } // onCreate

    //-----------------------------------------------

    /**
     * Método llamado al pulsar el botón de probar el número.
     * Llamamos al método protegido que lo procesa.
     *
     * @param v Vista que ha recibido el evento (el botón).
     */
    public void onProbar(View v) {


        procesarRespuesta();
    }

    //-----------------------------------------------
    //         Métodos protegidos/privados
    //-----------------------------------------------

    /**
     * Configuramos la aplicación para iniciar la partida.
     * Reiniciamos el número de intentos y elegimos el número
     * aleatorio.
     */
    protected void reiniciarPartida() {

        //*  // Pon/quita una barra / delante para invertir.
        _numElegido =  _dado.nextInt(100) + 1;
		/*/
		_numElegido = 27;
		/**/

        _numIntentos = 0;

    } // reiniciarPartida

    //-----------------------------------------------

    /**
     * Procesa la respuesta del usuario. Recoge el valor
     * introducido del cuadro de texto y modifica la etiqueta
     * de "feedback". Llama a partidaAcabada() cuando corresponde.
     * No hace nada útil si el texto introducido no es un número.
     */
    protected void procesarRespuesta() {

        EditText edit;
        edit = (EditText) findViewById(R.id.numero);

        String n = edit.getText().toString();
        int num;
        try {
            num = Integer.parseInt(n);
        }
        catch(Exception e) {
            // TODO:
            return;
        }

        edit.setText("");
        ++_numIntentos;
        if (num == _numElegido)
            partidaAcabada();
        else {
            String format;
            if (num < _numElegido)
                format = getResources().getString(R.string.mayor);
            else
                format = getResources().getString(R.string.menor);

            String cadFinal;
            cadFinal = String.format(format,  num);
            TextView etiqueta = (TextView) findViewById(R.id.etiquetaSuperior);
            etiqueta.setText(cadFinal);

        } // if-else hemos acertado

    } // procesarRespuesta

    //-----------------------------------------------

    /**
     * Método llamado cuando se detecta el fin de la partida
     * (el jugador ha acertado). Se pone la felicitación en
     * la etiqueta del mensaje, pero no se modifica nada más
     * de la lógica, por lo que el usuario podrá seguir escribiendo
     * intentos.
     */
    private void partidaAcabada() {

        String textoEtiqueta;
        android.content.res.Resources res = getResources();
        textoEtiqueta = res.getString(R.string.ganaste); // ¡¡Has acertado!!

        textoEtiqueta += res.getQuantityString(R.plurals.intentosFinales, _numIntentos, _numIntentos);
        // "¡¡Y sólo en <n> intento(s)!!

        TextView tv;
        tv = (TextView) findViewById(R.id.etiquetaSuperior);
        tv.setText(textoEtiqueta);

    } // partidaAcabada

    //-----------------------------------------------
    //        Atributos protegidos/privados
    //-----------------------------------------------

    /**
     * Número que hay que adivinar.
     */
    private int _numElegido;

    /**
     * Número de intentos de la partida.
     */
    private int _numIntentos;

    /**
     * Generador de números aleatorios.
     */
    private final static java.util.Random _dado = new java.util.Random();

}