package dm2e.luismanuel.cambiarimagen;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class CambiarImagen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_imagen);
    }
    protected  boolean anillo=true;
    public void cambiarImagen(View v){
        ImageButton btn = (ImageButton)findViewById(R.id.botonImagen);

        if (anillo) {
            btn.setImageResource(R.drawable.portada);
            anillo=false;
        }
        else {
            btn.setImageResource(R.drawable.anillo);
            anillo=true;
        }
    }
}
