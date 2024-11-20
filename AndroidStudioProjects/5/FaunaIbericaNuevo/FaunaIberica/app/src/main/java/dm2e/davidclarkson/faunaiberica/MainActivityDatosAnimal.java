package dm2e.davidclarkson.faunaiberica;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivityDatosAnimal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_datos_animal);

        // Obtener el nombre del animal pasado por Intent
        String animal = getIntent().getStringExtra("animal");

        // Referencias a los elementos del layout
        TextView textView = findViewById(R.id.textViewDatosAnimal);
        ImageView imageView = findViewById(R.id.imageViewAnimal);

        // Leer archivo de texto del animal
        String descripcion = readAnimalDescription(animal);

        if (descripcion == null) {
            descripcion = readAnimalDescription("mensajeerror");
            imageView.setImageResource(R.drawable.oso);  // Imagen por defecto para errores
        } else {
            int resId = getResources().getIdentifier(animal, "drawable", getPackageName());
            imageView.setImageResource(resId);  // Imagen del animal
        }

        // Mostrar los datos del animal
        textView.setText(descripcion);
    }

    private String readAnimalDescription(String animal) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open(animal + ".txt")))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            return null;
        }
    }
}

