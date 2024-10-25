package dm2e.davidclarkson.practica32;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
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

        RadioGroup rg = (RadioGroup) findViewById(R.id.grupo);
        rg.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    public void  onCheckedChanged(RadioGroup group, int checkedId){
                        onPulsame(rg);
                    }
                }
        );
    }

    protected void onPulsame(View v) {
        RadioGroup radio = (RadioGroup) findViewById(R.id.grupo);
        TextView tv = (TextView) findViewById(R.id.etiqueta1);
        int num = radio.getCheckedRadioButtonId();

        if (R.id.idandroid == num)
            tv.setText(R.string.and);
        else if (R.id.idios == num)
                tv.setText(R.string.io);
        else if (R.id.idwindows == num)
            tv.setText(R.string.win);
        else if (R.id.idsymbian == num)
            tv.setText(R.string.sym);
        else
            tv.setText(R.string.otr);

    }





    /*

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

    public void onPulsame(View v) {
        RadioGroup radio = (RadioGroup) findViewById(R.id.grupo);
        TextView tv = (TextView) findViewById(R.id.etiqueta1);
        int num = radio.getCheckedRadioButtonId();

        if (R.id.idandroid == num)
            tv.setText(R.string.and);
        else if (R.id.idios == num)
            tv.setText(R.string.io);
        else if (R.id.idwindows == num)
            tv.setText(R.string.win);
        else if (R.id.idsymbian == num)
            tv.setText(R.string.sym);
        else
            tv.setText(R.string.otr);

    }*/
}

