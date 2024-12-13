package dm2e.davidclarkson.parejascartasdavidclarkson;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JuegoActivityDos extends AppCompatActivity {

    private String nombreJugador;
    private TextView tvNombreJugador;
    private GridLayout gridCartas;
    private int intentos = 0;
    private int nivelActual = 1;
    private Button primeraCarta = null;
    private Button segundaCarta = null;
    private final List<Integer> valoresCartas = new ArrayList<>();
    private final Handler handler = new Handler();
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_dos);

        tvNombreJugador = findViewById(R.id.tvNombreJugador);
        gridCartas = findViewById(R.id.gridCartas);

        dbHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        nombreJugador = intent.getStringExtra("nombreJugador");
        tvNombreJugador.setText("Jugador: " + nombreJugador + " | Intentos: 0");

        inicializarCartas(4);
    }

    private void inicializarCartas(int numCartas) {
        valoresCartas.clear();
        gridCartas.removeAllViews();

        int columnas = (int) Math.sqrt(numCartas);
        gridCartas.setColumnCount(columnas);

        for (int i = 0; i < numCartas / 2; i++) {
            valoresCartas.add(i);
            valoresCartas.add(i);
        }
        Collections.shuffle(valoresCartas);

        for (int i = 0; i < numCartas; i++) {
            Button carta = new Button(this);
            carta.setTag(valoresCartas.get(i));
            carta.setBackgroundResource(R.drawable.trasera);
            carta.setOnClickListener(this::manejarClickCarta);
            gridCartas.addView(carta);
        }
    }

    private void manejarClickCarta(View view) {
        Button cartaSeleccionada = (Button) view;

        if (primeraCarta == null) {
            primeraCarta = cartaSeleccionada;
            int valor = (int) cartaSeleccionada.getTag();
            cartaSeleccionada.setBackgroundResource(obtenerImagenCarta(valor));
        } else if (segundaCarta == null && cartaSeleccionada != primeraCarta) {
            segundaCarta = cartaSeleccionada;
            int valor = (int) cartaSeleccionada.getTag();
            cartaSeleccionada.setBackgroundResource(obtenerImagenCarta(valor));

            intentos++;
            actualizarNombreYIntentos();

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

            if (todasEmparejadas()) {
                avanzarNivel();
            }
        }, 500);

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
        if (nivelActual == 1) {
            inicializarCartas(6);
            nivelActual++;
        } else if (nivelActual == 2) {
            inicializarCartas(12);
            nivelActual++;
        } else {
            guardarPuntuacion();
            mostrarRanking();
        }
    }

    private void guardarPuntuacion() {
        int mejorPuntuacion = dbHelper.obtenerPuntuacion(nombreJugador);
        if (mejorPuntuacion == -1 || intentos < mejorPuntuacion) {
            dbHelper.guardarOActualizarPuntuacion(nombreJugador, intentos);
        }
    }

    private void mostrarRanking() {
        Intent intent = new Intent(this, RankingActivity.class);
        startActivity(intent);
        finish();
    }

    private int obtenerImagenCarta(int valor) {
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

    private void actualizarNombreYIntentos() {
        tvNombreJugador.setText("Jugador: " + nombreJugador + " | Intentos: " + intentos);
    }
}
