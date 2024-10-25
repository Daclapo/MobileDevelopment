package dm2e.davidclarkson.practica22;

import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    /**
     * Método llamado cuando se crea la actividad.
     *
     * @param savedInstanceState Ignoramos el parámetro.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.util.Log.i(TAG, "onCreate");
    }

    public void botonPulsado(View v) {
        _boton = (Button) findViewById(R.id.boton1);
        ++_numVeces;
        android.content.res.Resources res = getResources();
        String numPulsados;
        numPulsados = res.getQuantityString(
                R.plurals.numPulsaciones,
                _numVeces,
                _numVeces);
        _boton.setText(numPulsados);
        // android.util.Log.i(TAG, "botonPulsado " + _numVeces);
    }

    protected void onStart() {
        super.onStart();
        android.util.Log.i(TAG, "onStart");
    }

    protected void onRestart() {
        super.onRestart();
        android.util.Log.i(TAG, "onRestart");
    }

    protected void onResume() {
        super.onResume();
        android.util.Log.i(TAG, "onResume");
    }

    protected void onPause() {
        super.onPause();
        android.util.Log.i(TAG, "onPause");
    }

    protected void onStop() {
        super.onStop();
        android.util.Log.i(TAG, "onStop");
    }

    protected void onDestroy() {
        super.onDestroy();
        android.util.Log.i(TAG, "onDestroy");
    }

    private static final String TAG = "CicloDeVida";

    private void botonPulsado() {
        ++_numVeces;
    }

    public  Button _boton;
    private int _numVeces;
}
