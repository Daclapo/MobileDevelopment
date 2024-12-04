package dm2e.davidclarkson.basededatos;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class BorrarActivity extends AppCompatActivity {

    private ClaseBaseDatos dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar);

        dbHelper = new ClaseBaseDatos(this);

        EditText etId = findViewById(R.id.et_id);
        Button btnDelete = findViewById(R.id.btn_delete);

        btnDelete.setOnClickListener(view -> {
            String id = etId.getText().toString();

            if (id.isEmpty()) {
                Toast.makeText(BorrarActivity.this, "Por favor, introduce un ID válido", Toast.LENGTH_SHORT).show();
                return;
            }

            dbHelper.getWritableDatabase().execSQL(
                    "DELETE FROM " + ClaseBaseDatos.TABLE_NAME + " WHERE " +
                            ClaseBaseDatos.COLUMN_ID + " = ?",
                    new Object[]{id});
            Toast.makeText(BorrarActivity.this, "Registro eliminado correctamente", Toast.LENGTH_SHORT).show();
        });
    }
}
