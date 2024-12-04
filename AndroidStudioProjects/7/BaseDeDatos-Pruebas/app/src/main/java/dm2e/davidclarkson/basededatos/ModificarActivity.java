package dm2e.davidclarkson.basededatos;

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
        Button btnUpdate = findViewById(R.id.btn_update);

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
