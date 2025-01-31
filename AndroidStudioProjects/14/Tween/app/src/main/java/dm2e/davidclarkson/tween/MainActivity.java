package dm2e.davidclarkson.tween;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

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

    }

    public void onPulsame(View v) {
        View imagen = findViewById(R.id.imagen);
        Animation anim;
        anim = AnimationUtils.loadAnimation(this, R.anim.tween);
        imagen.startAnimation(anim);
    }

}