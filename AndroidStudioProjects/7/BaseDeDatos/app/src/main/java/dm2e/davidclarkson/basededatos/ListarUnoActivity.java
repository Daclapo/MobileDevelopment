package dm2e.davidclarkson.basededatos;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ListarUnoActivity extends AppCompatActivity {

    private ClaseBaseDatos dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_uno);

        dbHelper = new ClaseBaseDatos(this);

        EditText etId = findViewById(R.id.et_id);
        TextView tvResult = findViewById(R.id.tv_result);
        Button btnSearch = findViewById(R.id.btn_search);

        btnSearch.setOnClickListener(view -> {
            String id = etId.getText().toString();

            if (id.isEmpty()) {
                Toast.makeText(ListarUnoActivity.this, "Por favor, introduce un ID válido", Toast.LENGTH_SHORT).show();
                return;
            }

            Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
                    "SELECT * FROM " + ClaseBaseDatos.TABLE_NAME + " WHERE " +
                            ClaseBaseDatos.COLUMN_ID + " = ?", new String[]{id});

            if (cursor != null && cursor.moveToFirst()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(ClaseBaseDatos.COLUMN_NAME));
                int age = cursor.getInt(cursor.getColumnIndexOrThrow(ClaseBaseDatos.COLUMN_AGE));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(ClaseBaseDatos.COLUMN_EMAIL));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(ClaseBaseDatos.COLUMN_DATE));
                String note = cursor.getString(cursor.getColumnIndexOrThrow(ClaseBaseDatos.COLUMN_NOTE));

                tvResult.setText("ID: " + id + "\nNombre: " + name + "\nEdad: " + age +
                        "\nCorreo: " + email + "\nFecha: " + date + "\nNota: " + note);
                cursor.close();
            } else {
                Toast.makeText(ListarUnoActivity.this, "Registro no encontrado", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
