package dm2e.luis.preferencias54;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// Leemos las preferencias.
        SharedPreferences preferencias;
        preferencias = getPreferences(Context.MODE_PRIVATE);

        int id = preferencias.getInt(PREFERENCIA_MELODIA, R.id.silencio);

        RadioButton rb;
        rb = (RadioButton) findViewById(id);

        rb.setChecked(true);

    } // onCreate

    //--------------------------------------------------------------

    /**
     * Método ejecutado cuando se pulsa el botón de aceptar.
     * Guardamos en el fichero de preferencias asociado a la actividad
     * el valor seleccionado.
     *
     * @param v Vista (botón) que ha generado la invocación del método.
     */
    public void onAceptar(View v) {

        SharedPreferences preferencias;

        preferencias = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = preferencias.edit();

        RadioGroup rg;
        rg = (RadioGroup) findViewById(R.id.preferenciasMelodia);

        editor.putInt(PREFERENCIA_MELODIA, rg.getCheckedRadioButtonId());

        editor.commit();

        finish();

    } // onAceptar

    //--------------------------------------------------------------

    /**
     * Atributo estático con la cadena que hace de clave en el bundle
     * de las preferencias para guardar la opción seleccionada.
     */
    private final static String PREFERENCIA_MELODIA = "preferenciaMelodia";

}