package dm2e.davidclarkson.faunaiberica;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainActivityDatosAnimal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_datos_animal);

        String animal = getIntent().getStringExtra("animal");

        TextView textView = findViewById(R.id.textViewDatosAnimal);
        ImageView imageView = findViewById(R.id.imageViewAnimal);

        boolean hasText = doesAnimalHaveDescription(animal);

        if (hasText) {
            textView.setText(readAnimalDescription(animal));
            imageView.setImageResource(getResources().getIdentifier(animal, "drawable", getPackageName()));
        } else {
            textView.setText(readAnimalDescription("mensajeerror"));
            imageView.setImageResource(R.drawable.oso);
        }
    }


    private boolean doesAnimalHaveDescription(String animal) {
        int resId = getResources().getIdentifier(animal + "texto", "raw", getPackageName());
        return resId != 0;
    }


    private String readAnimalDescription(String animal) {
        int resId = getResources().getIdentifier(animal + "texto", "raw", getPackageName());
        if (resId == 0) {
            resId = getResources().getIdentifier("mensajeerror", "raw", getPackageName());
        }

        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getResources().openRawResource(resId)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        } catch (Exception e) {
            stringBuilder.append(getString(R.string.error_default_text));
        }
        return stringBuilder.toString().trim();
    }
}
