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

        String animal = getIntent().getStringExtra("animal");

        // Referencias a los elementos del layout
        TextView textView = findViewById(R.id.textViewDatosAnimal);
        ImageView imageView = findViewById(R.id.imageViewAnimal);
        ScrollView scrollView = findViewById(R.id.scrollView);

        // Leer archivo de texto del animal
        String descripcion = readAnimalDescription(animal);
        if (descripcion == null) {
            descripcion = readAnimalDescription("error");
            imageView.setImageResource(R.drawable.oso);  // Imagen por defecto (oso)
        } else {
            // Asignar la imagen del animal
            int resId = getResources().getIdentifier(animal, "drawable", getPackageName());
            imageView.setImageResource(resId);
        }

        // Mostrar los datos
        textView.setText(descripcion);
    }

    private String readAnimalDescription(String animal) {
        AssetManager assetManager = getAssets();
        try {
            // Intentamos abrir el archivo correspondiente
            BufferedReader reader = new BufferedReader(new InputStreamReader(assetManager.open(animal + "texto.txt")));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            reader.close();
            return stringBuilder.toString();
        } catch (IOException e) {
            // Si no encuentra el archivo, devolver null
            return null;
        }
    }
}
