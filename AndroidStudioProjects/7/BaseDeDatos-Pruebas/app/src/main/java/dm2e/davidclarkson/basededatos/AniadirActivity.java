package dm2e.davidclarkson.basededatos;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AniadirActivity extends AppCompatActivity {

    private ClaseBaseDatos dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniadir);

        dbHelper = new ClaseBaseDatos(this);

        EditText etName = findViewById(R.id.et_name);
        EditText etAge = findViewById(R.id.et_age);
        EditText etEmail = findViewById(R.id.et_email);
        EditText etDate = findViewById(R.id.et_date);
        EditText etNote = findViewById(R.id.et_note);
        Button btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(view -> {
            String name = etName.getText().toString();
            String age = etAge.getText().toString();
            String email = etEmail.getText().toString();
            String date = etDate.getText().toString();
            String note = etNote.getText().toString();

            if (name.isEmpty() || age.isEmpty() || email.isEmpty() || date.isEmpty() || note.isEmpty()) {
                Toast.makeText(AniadirActivity.this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            } else {
                dbHelper.getWritableDatabase().execSQL(
                        "INSERT INTO " + ClaseBaseDatos.TABLE_NAME + " (" +
                                ClaseBaseDatos.COLUMN_NAME + ", " +
                                ClaseBaseDatos.COLUMN_AGE + ", " +
                                ClaseBaseDatos.COLUMN_EMAIL + ", " +
                                ClaseBaseDatos.COLUMN_DATE + ", " +
                                ClaseBaseDatos.COLUMN_NOTE + ") VALUES (?, ?, ?, ?, ?)",
                        new Object[]{name, Integer.parseInt(age), email, date, note});
                Toast.makeText(AniadirActivity.this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
