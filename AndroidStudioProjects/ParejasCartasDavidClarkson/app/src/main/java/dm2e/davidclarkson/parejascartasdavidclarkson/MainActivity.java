package dm2e.davidclarkson.parejascartasdavidclarkson;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText inputNombre = findViewById(R.id.inputNombre);

        inputNombre.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    String nombre = inputNombre.getText().toString().trim();
                    if (nombre.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Por favor, introduce tu nombre", Toast.LENGTH_SHORT).show();
                    } else {
                        // Iniciar la segunda actividad
                        Intent intent = new Intent(MainActivity.this, JuegoActivity.class);
                        intent.putExtra("nombreJugador", nombre);
                        startActivity(intent);
                    }
                    return true;
                }
                return false;
            }
        });
    }
}
