package dm2e.davidclarkson.cargaresraw;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_res_raw);
        RadioGroup rg;
        rg = (RadioGroup) findViewById(R.id.rgOpciones);
        InputStream is;
        is = getResources().openRawResource(R.raw.melodias);
        Scanner sc = new Scanner(is);
        while (sc.hasNextLine()) {
            String melodia = sc.nextLine();
            RadioButton rb;
            rb = new RadioButton(this);
            rb.setText(melodia);
            rg.addView(rb);
        }
        ((RadioButton)rg.getChildAt(0)).setChecked(true);
    } // onCreate


    public void onAceptar(View v) {

    }
}