package dm2e.davidclarkson.practica36;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onClick(View v) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(R.string.tururu);
        alert.setTitle(R.string.tituloAlerta);
        alert.setPositiveButton(android.R.string.ok, null);
        alert.show();

        Context contexto = getApplicationContext();
        int duracion = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(contexto, R.string.tururu, duracion);
        toast.show();
    }

}