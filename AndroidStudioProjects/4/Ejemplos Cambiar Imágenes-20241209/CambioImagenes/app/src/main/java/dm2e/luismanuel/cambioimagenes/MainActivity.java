package dm2e.luismanuel.cambioimagenes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton m1;
    ImageButton m2;
    ImageButton m3;
    boolean cambiar=false;
    int boton;
    int aux=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         m1=(ImageButton)findViewById(R.id.boton1);
         m2=(ImageButton)findViewById(R.id.boton2);
         m3=(ImageButton)findViewById(R.id.boton3);

        m1.setImageResource(R.drawable.imagen1);
        m1.setTag(R.drawable.imagen1);

        m2.setImageResource(R.drawable.imagen2);
        m2.setTag(R.drawable.imagen2);

        m3.setImageResource(R.drawable.imagen3);
        m3.setTag(R.drawable.imagen3);


    }
    public void cambiar(View v){


        if (!cambiar && aux==-1) {
            if (v.getId()==R.id.boton1) {
                aux = (Integer) m1.getTag();
                boton=1;
            }
            else if (v.getId()==(R.id.boton2)) {
                aux = (Integer) m2.getTag();
                boton=2;

            }
            else {
                aux = (Integer) m3.getTag();
                boton=3;
            }
            cambiar=true;
        }
        else{
            if (v.getId()==(R.id.boton1)) {
                switch (boton){
                    case 1:
                        m1.setImageResource((Integer)m1.getTag());
                        m1.setTag((Integer)m1.getTag());
                        break;
                    case 2:
                        m2.setImageResource((Integer)m1.getTag());
                        m2.setTag((Integer)m1.getTag());
                        break;
                    case 3:
                        m3.setImageResource((Integer)m1.getTag());
                        m3.setTag((Integer)m1.getTag());
                        break;
                }
                m1.setImageResource(aux);
                m1.setTag(aux);
            }
            else if (v.getId()==(R.id.boton2)) {
                switch (boton){
                    case 1:
                        m1.setImageResource((Integer)m2.getTag());
                        m1.setTag((Integer)m2.getTag());
                        break;
                    case 2:
                        m2.setImageResource((Integer)m2.getTag());
                        m2.setTag((Integer)m2.getTag());
                        break;
                    case 3:
                        m3.setImageResource((Integer)m2.getTag());
                        m3.setTag((Integer)m2.getTag());
                        break;
                }
                m2.setImageResource(aux);
                m2.setTag(aux);
            }
            else {
                switch (boton){
                    case 1:
                        m1.setImageResource((Integer)m3.getTag());
                        m1.setTag((Integer)m3.getTag());
                        break;
                    case 2:
                        m2.setImageResource((Integer)m3.getTag());
                        m2.setTag((Integer)m3.getTag());
                        break;
                    case 3:
                        m3.setImageResource((Integer)m3.getTag());
                        m3.setTag((Integer)m3.getTag());
                        break;
                }
                m3.setImageResource(aux);
                m3.setTag(aux);
            }
            cambiar=false;
            aux=-1;

        }


    }
}
