package dm2e.davidclarkson.tragaperrras3;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView fruta1, fruta2, fruta3;
    private Button btnJugar, btnParar;
    private TextView tvPulsaciones, tvPuntuacion;
    private AnimationDrawable anim1, anim2, anim3;

    private int pulsacionesCount = 0;
    private int puntuacion = 0;

    private int[] frutasIds = {R.drawable.tomate, R.drawable.pera,R.drawable.manzana,
            R.drawable.melon,R.drawable.granada,R.drawable.melocoton,R.drawable.naranja,
            R.drawable.cereza, R.drawable.sandia, R.drawable.limon,R.drawable.fresa,
            R.drawable.pina,R.drawable.platano,R.drawable.uvas
    };

    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fruta1 = findViewById(R.id.fruta1);
        fruta2 = findViewById(R.id.fruta2);
        fruta3 = findViewById(R.id.fruta3);
        btnJugar = findViewById(R.id.btn_jugar);
        btnParar = findViewById(R.id.btn_parar);
        tvPulsaciones = findViewById(R.id.tv_pulsaciones);
        tvPuntuacion = findViewById(R.id.tv_puntuacion);

        fruta1.setImageResource(R.drawable.frutas_animacion1);
        fruta2.setImageResource(R.drawable.frutas_animacion2);
        fruta3.setImageResource(R.drawable.frutas_animacion3);

        anim1 = (AnimationDrawable) fruta1.getDrawable();
        anim2 = (AnimationDrawable) fruta2.getDrawable();
        anim3 = (AnimationDrawable) fruta3.getDrawable();

        btnParar.setEnabled(true);

        btnJugar.setOnClickListener(v -> {
            btnParar.setEnabled(true);

            fruta1.setImageResource(R.drawable.frutas_animacion1);
            fruta2.setImageResource(R.drawable.frutas_animacion2);
            fruta3.setImageResource(R.drawable.frutas_animacion3);

            anim1 = (AnimationDrawable) fruta1.getDrawable();
            anim2 = (AnimationDrawable) fruta2.getDrawable();
            anim3 = (AnimationDrawable) fruta3.getDrawable();

            anim1 = actualizarVelocidadAnimacion(anim1);
            anim2 = actualizarVelocidadAnimacion(anim2);
            anim3 = actualizarVelocidadAnimacion(anim3);

            fruta1.setImageDrawable(anim1);
            fruta2.setImageDrawable(anim2);
            fruta3.setImageDrawable(anim3);

            anim1.start();
            anim2.start();
            anim3.start();
        });

        btnParar.setOnClickListener(v -> {
            anim1.stop();
            anim2.stop();
            anim3.stop();

            pulsacionesCount++;
            actualizarPulsaciones();

            int index1 = random.nextInt(frutasIds.length);
            int index2 = random.nextInt(frutasIds.length);
            int index3 = random.nextInt(frutasIds.length);

            fruta1.setImageResource(frutasIds[index1]);
            fruta2.setImageResource(frutasIds[index2]);
            fruta3.setImageResource(frutasIds[index3]);

            if (index1 == index2 && index1 == index3) {
                puntuacion += 10;
            } else if (index1 == index2 || index1 == index3 || index2 == index3) {
                puntuacion += 3;
            }

            actualizarPuntuacion();

            btnParar.setEnabled(false);
        });
    }

    private AnimationDrawable actualizarVelocidadAnimacion(AnimationDrawable anim) {
        AnimationDrawable nuevaAnim = new AnimationDrawable();
        nuevaAnim.setOneShot(false);
        int numFrames = anim.getNumberOfFrames();
        for (int i = 0; i < numFrames; i++) {
            Drawable frame = anim.getFrame(i);
            int duracion = 100 + random.nextInt(101);
            nuevaAnim.addFrame(frame, duracion);
        }
        return nuevaAnim;
    }

    private void actualizarPulsaciones() {
        String pulsacionesText = getResources().getQuantityString(
                R.plurals.num_pulsaciones, pulsacionesCount, pulsacionesCount);
        tvPulsaciones.setText("Has pulsado " + pulsacionesText + " el botón de parar");
    }


    private void actualizarPuntuacion() {
        String puntuacionText = getResources().getQuantityString(
                R.plurals.num_puntos, puntuacion, puntuacion);
        tvPuntuacion.setText("Tu puntuación es de: " + puntuacionText);
    }


}
