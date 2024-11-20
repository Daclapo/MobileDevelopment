package dm2e.davidclarkson.faunaiberica;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivityAves extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_aves);

        // Crear el Map para asociar el id del RadioButton con el nombre del animal
        Map<Integer, String> aveMap = new HashMap<>();
        aveMap.put(R.id.radioAguila, "aguila");
        aveMap.put(R.id.radioBuho, "buho");
        aveMap.put(R.id.radioCernicalo, "cernicalo");
        aveMap.put(R.id.radioHalcon, "halcon");
        aveMap.put(R.id.radioQuebrantahuesos, "quebrantahuesos");
        aveMap.put(R.id.radioBuitre, "buitre");

        // RadioGroup y RadioButtons
        RadioGroup radioGroupAves = findViewById(R.id.radioGroupAves);

        // Listener para cambios en el RadioGroup
        radioGroupAves.setOnCheckedChangeListener((group, checkedId) -> {
            // Verificamos si el checkedId está en el Map
            if (aveMap.containsKey(checkedId)) {
                String ave = aveMap.get(checkedId);

                // Creamos el Intent y pasamos el nombre del animal
                Intent intent = new Intent(MainActivityAves.this, MainActivityDatosAnimal.class);
                intent.putExtra("animal", ave);

                // Iniciamos la actividad
                startActivity(intent);
            }
        });
    }
}
