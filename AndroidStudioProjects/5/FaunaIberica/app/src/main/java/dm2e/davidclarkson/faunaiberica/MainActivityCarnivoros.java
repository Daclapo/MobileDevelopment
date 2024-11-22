package dm2e.davidclarkson.faunaiberica;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivityCarnivoros extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_carnivoros);

        // He creado un Map, para asociar el id del RadioButton con el nombre del animal.
        Map<Integer, String> animalMap = new HashMap<>();
        animalMap.put(R.id.radioComadreja, "comadreja");
        animalMap.put(R.id.radioGineta, "gineta");
        animalMap.put(R.id.radioNutria, "nutria");
        animalMap.put(R.id.radioLobo, "lobo");
        animalMap.put(R.id.radioZorro, "zorro");
        animalMap.put(R.id.radioLince, "lince");

        RadioGroup radioGroupCarnivoros = findViewById(R.id.radioGroupCarnivoros);

        radioGroupCarnivoros.setOnCheckedChangeListener((group, checkedId) -> {
            if (animalMap.containsKey(checkedId)) {
                String animal = animalMap.get(checkedId);

                Intent intent = new Intent(MainActivityCarnivoros.this, MainActivityDatosAnimal.class);
                intent.putExtra("animal", animal);

                startActivity(intent);
            }
        });
    }
}
