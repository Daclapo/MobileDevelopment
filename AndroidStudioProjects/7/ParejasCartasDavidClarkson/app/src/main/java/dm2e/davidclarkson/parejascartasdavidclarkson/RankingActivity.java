package dm2e.davidclarkson.parejascartasdavidclarkson;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RankingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        LinearLayout rankingLayout = findViewById(R.id.rankingLayout);
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        Cursor cursor = dbHelper.obtenerRanking();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
                int puntuacion = cursor.getInt(cursor.getColumnIndexOrThrow("puntuacion"));

                TextView jugadorView = new TextView(this);
                jugadorView.setText(nombre + " - " + puntuacion + " intentos");
                jugadorView.setTextSize(18);
                jugadorView.setPadding(16, 16, 16, 16);

                rankingLayout.addView(jugadorView);
            }
            cursor.close();
        }
    }
}
