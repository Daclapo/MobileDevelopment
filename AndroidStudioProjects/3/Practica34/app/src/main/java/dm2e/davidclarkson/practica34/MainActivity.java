package dm2e.davidclarkson.practica34;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

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

    }

    public void onViernes(View v){
        CheckBox cb = (CheckBox) v;
        Toast toast;
        Context contexto = getApplicationContext();
        int duracion = Toast.LENGTH_SHORT;
        if (cb.isChecked()){
            toast = Toast.makeText(contexto, R.string.mensajeviernesno, duracion);
        }else {
            toast = Toast.makeText(contexto, R.string.mensajeviernes, duracion);
        }
        toast.show();

    }
    public void onSabado(View v){
        CheckBox cb = (CheckBox) v;
        TextView tv = (TextView) findViewById(R.id.resultado1);
        tv.setVisibility(cb.isChecked()?v.VISIBLE:v.INVISIBLE);
    }
    public void onDomingo(View v){
        CheckBox cb = (CheckBox) v;
        TextView tv = (TextView) findViewById(R.id.resultado2);
        tv.setVisibility(cb.isChecked()?v.VISIBLE:v.INVISIBLE);
    }
}