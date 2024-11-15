package dm2e.davidclarkson.faunaiberica;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioButton radioCarnivores = findViewById(R.id.radioCarnivores);
        RadioButton radioBirds = findViewById(R.id.radioBirds);

        radioCarnivores.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MainActivityCarnivoros.class);
            startActivity(intent);
        });

        radioBirds.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MainActivityAves.class);
            startActivity(intent);
        });
    }
}
