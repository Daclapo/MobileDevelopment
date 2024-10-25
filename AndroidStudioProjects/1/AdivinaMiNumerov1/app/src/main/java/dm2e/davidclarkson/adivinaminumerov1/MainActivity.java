package dm2e.davidclarkson.adivinaminumerov1;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

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

/*
    int numIntenteos = 0;
    int _numElegido;

    protected void procesarRespuesta(){
        EditText edit;
        edit = (EditText) findViewById(R.id.numero);
        if (num == _numElegido)
            partidaAcabada();
        else {
            String format;
            if (num < _numElegido)
                format = getResources().getString(R.string.mayor);
            else
                format  = getResources().getString(R.string.menor);
        }
        String cadFinal;
        cadFinal = String.format(format, num);
        TextView etiqueta = (TextView) findViewById(R.id.etiquetaSuperior);
        etiqueta.setText(cadFinal);
    }

 */
}