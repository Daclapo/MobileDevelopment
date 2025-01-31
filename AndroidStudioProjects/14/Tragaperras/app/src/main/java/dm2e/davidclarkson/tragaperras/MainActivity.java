package dm2e.davidclarkson.tragaperras;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView fruta1, fruta2, fruta3;
    private Button btnJugar, btnParar;
    private AnimationDrawable anim1, anim2, anim3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fruta1 = findViewById(R.id.fruta1);
        fruta2 = findViewById(R.id.fruta2);
        fruta3 = findViewById(R.id.fruta3);
        btnJugar = findViewById(R.id.btn_jugar);
        btnParar = findViewById(R.id.btn_parar);

        // Obtenemos la animación de cada ImageView
        anim1 = (AnimationDrawable) fruta1.getDrawable();
        anim2 = (AnimationDrawable) fruta2.getDrawable();
        anim3 = (AnimationDrawable) fruta3.getDrawable();



/*        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim1.start();
                anim2.start();
                anim3.start();} });
          Esto es lo que tenia, pero AndroidStudio me ha sugerido que lo cambiase por una lambda */

        btnJugar.setOnClickListener(v -> {
            anim1.start();
            anim2.start();
            anim3.start();
        });



/*        btnParar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim1.stop();
                anim2.stop();
                anim3.stop();} });
            Y aqui he hecho lo mismo, lo he sustituido por una lambda */

        btnParar.setOnClickListener(v -> {
            anim1.stop();
            anim2.stop();
            anim3.stop();
        });
    }
}


