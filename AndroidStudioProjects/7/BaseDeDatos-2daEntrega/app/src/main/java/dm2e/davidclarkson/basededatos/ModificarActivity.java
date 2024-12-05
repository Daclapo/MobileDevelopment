package dm2e.davidclarkson.basededatos;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ModificarActivity extends AppCompatActivity {

    private ClaseBaseDatos dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        dbHelper = new ClaseBaseDatos(this);

        EditText etId = findViewById(R.id.et_id);
        EditText etName = findViewById(R.id.et_name);
        EditText etAge = findViewById(R.id.et_age);
        EditText etEmail = findViewById(R.id.et_email);
        EditText etDate = findViewById(R.id.et_date);
        EditText etNote = findViewById(R.id.et_note);
        Button btnLoad = findViewById(R.id.btn_load);
        Button btnUpdate = findViewById(R.id.btn_update);

        btnLoad.setOnClickListener(view -> {
            String id = etId.getText().toString();

            if (id.isEmpty()) {
                Toast.makeText(ModificarActivity.this, "Por favor, introduce un ID válido", Toast.LENGTH_SHORT).show();
                return;
            }

            Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
                    "SELECT * FROM " + ClaseBaseDatos.TABLE_NAME + " WHERE " + ClaseBaseDatos.COLUMN_ID + " = ?",
                    new String[]{id});

            if (cursor != null && cursor.moveToFirst()) {
                etName.setText(cursor.getString(cursor.getColumnIndexOrThrow(ClaseBaseDatos.COLUMN_NAME)));
                etAge.setText(String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow(ClaseBaseDatos.COLUMN_AGE))));
                etEmail.setText(cursor.getString(cursor.getColumnIndexOrThrow(ClaseBaseDatos.COLUMN_EMAIL)));
                etDate.setText(cursor.getString(cursor.getColumnIndexOrThrow(ClaseBaseDatos.COLUMN_DATE)));
                etNote.setText(cursor.getString(cursor.getColumnIndexOrThrow(ClaseBaseDatos.COLUMN_NOTE)));
                cursor.close();
            } else {
                Toast.makeText(ModificarActivity.this, "No se encontró ningún registro con ese ID", Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdate.setOnClickListener(view -> {
            String id = etId.getText().toString();
            String name = etName.getText().toString();
            String age = etAge.getText().toString();
            String email = etEmail.getText().toString();
            String date = etDate.getText().toString();
            String note = etNote.getText().toString();

            if (id.isEmpty()) {
                Toast.makeText(ModificarActivity.this, "Por favor, introduce un ID válido", Toast.LENGTH_SHORT).show();
                return;
            }

            dbHelper.getWritableDatabase().execSQL(
                    "UPDATE " + ClaseBaseDatos.TABLE_NAME + " SET " +
                            ClaseBaseDatos.COLUMN_NAME + " = ?, " +
                            ClaseBaseDatos.COLUMN_AGE + " = ?, " +
                            ClaseBaseDatos.COLUMN_EMAIL + " = ?, " +
                            ClaseBaseDatos.COLUMN_DATE + " = ?, " +
                            ClaseBaseDatos.COLUMN_NOTE + " = ? WHERE " +
                            ClaseBaseDatos.COLUMN_ID + " = ?",
                    new Object[]{name, age, email, date, note, id});

            Toast.makeText(ModificarActivity.this, "Registro actualizado correctamente", Toast.LENGTH_SHORT).show();
        });
    }
}
