package dm2e.davidclarkson.basededatos73;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Abrimos la base de datos 'DBContactos' en modo escritura
        UsuariosSQLiteHelper usdbh = new UsuariosSQLiteHelper(this, "DBContactos", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();
//Si hemos abierto correctamente la base de datos
        if(db != null) {
//Insertamos 5 usuarios de ejemplo
            for(int i=1; i<=5; i++) {
//Generamos los datos
                int telefono = 11111111+i;
                String nombre = "Usuario" + i;
//Insertamos los datos en la tabla Usuarios
                db.execSQL("INSERT INTO Contactos (nombre, telefono) " +
                "VALUES ('" + nombre +"', " + telefono + " )");
            }
//Cerramos la base de datos
            db.close();
        }
    }
}

// Para hacer consultas e introducir tablas a esta bbdd, se hara lo siguiente en la consola:

// Conectarse a el dispositivo virtual:
// cd Android/Sdk/platform-tools/
// ./adb -e shell
