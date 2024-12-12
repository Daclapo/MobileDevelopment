package dm2e.davidclarkson.parejascartasdavidclarkson;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JuegoActivity extends AppCompatActivity {

    private String nombreJugador;
    private TextView tvNombreJugador, tvIntentos;
    private GridLayout gridCartas;
    private int intentos = 0;
    private Button primeraCarta = null;
    private Button segundaCarta = null;
    private final List<Integer> valoresCartas = new ArrayList<>();
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        // Recuperar el nombre del jugador
        Intent intent = getIntent();
        nombreJugador = intent.getStringExtra("nombreJugador");

        // Configurar vistas
        tvNombreJugador = findViewById(R.id.tvNombreJugador);
        tvIntentos = findViewById(R.id.tvIntentos);
        gridCartas = findViewById(R.id.gridCartas);

        tvNombreJugador.setText("Jugador: " + nombreJugador);
        actualizarIntentos();

        // Inicializar cartas
        inicializarCartas(4); // Nivel 1 por defecto
    }

    private void inicializarCartas(int numCartas) {
        // Limpiar estado previo
        valoresCartas.clear();
        gridCartas.removeAllViews();

        // Configurar GridLayout
        int columnas = (int) Math.sqrt(numCartas);
        gridCartas.setColumnCount(columnas);

        // Crear pares de cartas y mezclarlos
        for (int i = 0; i < numCartas / 2; i++) {
            valoresCartas.add(i);
            valoresCartas.add(i);
        }
        Collections.shuffle(valoresCartas);

        // Crear botones para las cartas
        for (int i = 0; i < numCartas; i++) {
            Button carta = new Button(this);
            carta.setTag(valoresCartas.get(i)); // Guardar valor
            carta.setBackgroundResource(R.drawable.trasera);
            carta.setOnClickListener(this::manejarClickCarta);
            gridCartas.addView(carta);
        }
    }

    private void manejarClickCarta(View view) {
        Button cartaSeleccionada = (Button) view;

        if (primeraCarta == null) {
            // Primera carta seleccionada
            primeraCarta = cartaSeleccionada;
            int valor = (int) cartaSeleccionada.getTag();
            cartaSeleccionada.setBackgroundResource(obtenerImagenCarta(valor));
        } else if (segundaCarta == null && cartaSeleccionada != primeraCarta) {
            // Segunda carta seleccionada
            segundaCarta = cartaSeleccionada;
            int valor = (int) cartaSeleccionada.getTag();
            cartaSeleccionada.setBackgroundResource(obtenerImagenCarta(valor));

            intentos++;
            actualizarIntentos();

            // Comparar valores
            if ((int) primeraCarta.getTag() == (int) segundaCarta.getTag()) {
                marcarCartasEmparejadas();
            } else {
                reiniciarCartas();
            }
        }
    }

    private void marcarCartasEmparejadas() {
        handler.postDelayed(() -> {
            primeraCarta.setBackgroundResource(R.color.amarillo);
            segundaCarta.setBackgroundResource(R.color.amarillo);
            primeraCarta.setEnabled(false);
            segundaCarta.setEnabled(false);
            primeraCarta = null;
            segundaCarta = null;

            // Revisar si todas las cartas están emparejadas
            if (todasEmparejadas()) {
                avanzarNivel();
            }
        }, 500);

        // Reproducir sonido
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.aplausos);
        mediaPlayer.start();
    }

    private void reiniciarCartas() {
        handler.postDelayed(() -> {
            primeraCarta.setBackgroundResource(R.drawable.trasera);
            segundaCarta.setBackgroundResource(R.drawable.trasera);
            primeraCarta = null;
            segundaCarta = null;
        }, 1000);
    }

    private boolean todasEmparejadas() {
        for (int i = 0; i < gridCartas.getChildCount(); i++) {
            if (gridCartas.getChildAt(i).isEnabled()) {
                return false;
            }
        }
        return true;
    }

    private void avanzarNivel() {
        int siguienteNivel = gridCartas.getChildCount() == 4 ? 6 : 12;
        if (siguienteNivel <= 12) {
            inicializarCartas(siguienteNivel);
        } else {
            guardarPuntuacion();
        }
    }

    private void guardarPuntuacion() {
        // Guardar puntuación en base de datos (pendiente de implementar)
    }

    private int obtenerImagenCarta(int valor) {
        // Retorna el recurso drawable para una carta específica
        switch (valor) {
            case 0: return R.drawable.asdeespadas;
            case 1: return R.drawable.caballodeespadas;
            case 2: return R.drawable.sotadeoros;
            case 3: return R.drawable.asdecopas;
            case 4: return R.drawable.caballodebastos;
            case 5: return R.drawable.sotadeespadas;
            default: return R.drawable.trasera;
        }
    }

    private void actualizarIntentos() {
        tvIntentos.setText("Intentos: " + intentos);
    }
}
