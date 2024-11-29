package dm2e.davidclarkson.basededatos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAdd = findViewById(R.id.btn_add);
        Button btnUpdate = findViewById(R.id.btn_update);
        Button btnDelete = findViewById(R.id.btn_delete);
        Button btnListAll = findViewById(R.id.btn_list_all);
        Button btnListOne = findViewById(R.id.btn_list_one);

        btnAdd.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, AniadirActivity.class)));
        btnUpdate.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ModificarActivity.class)));
        btnDelete.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, BorrarActivity.class)));
        btnListAll.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ListarTodosActivity.class)));
        btnListOne.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ListarUnoActivity.class)));
    }
}
