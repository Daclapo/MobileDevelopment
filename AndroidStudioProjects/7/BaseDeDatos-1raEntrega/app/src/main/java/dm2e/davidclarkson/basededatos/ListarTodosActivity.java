package dm2e.davidclarkson.basededatos;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListarTodosActivity extends AppCompatActivity {

    private ClaseBaseDatos dbHelper;
    private RecyclerView recyclerView;
    private RegistroAdapter adapter;
    private ArrayList<Registro> registros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_todos);

        dbHelper = new ClaseBaseDatos(this);
        registros = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RegistroAdapter(registros);
        recyclerView.setAdapter(adapter);

        cargarRegistros();
    }

    private void cargarRegistros() {
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
                "SELECT * FROM " + ClaseBaseDatos.TABLE_NAME, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(ClaseBaseDatos.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(ClaseBaseDatos.COLUMN_NAME));
                int age = cursor.getInt(cursor.getColumnIndexOrThrow(ClaseBaseDatos.COLUMN_AGE));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(ClaseBaseDatos.COLUMN_EMAIL));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(ClaseBaseDatos.COLUMN_DATE));
                String note = cursor.getString(cursor.getColumnIndexOrThrow(ClaseBaseDatos.COLUMN_NOTE));

                registros.add(new Registro(id, name, age, email, date, note));
            } while (cursor.moveToNext());

            cursor.close();
        } else {
            Toast.makeText(this, "No hay registros disponibles", Toast.LENGTH_SHORT).show();
        }

        adapter.notifyDataSetChanged();
    }
}
