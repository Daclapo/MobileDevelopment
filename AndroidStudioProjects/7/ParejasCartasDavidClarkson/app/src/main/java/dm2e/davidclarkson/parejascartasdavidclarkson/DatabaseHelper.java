package dm2e.davidclarkson.parejascartasdavidclarkson;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ranking.db";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_NAME = "ranking";
    private static final String COLUMN_NAME = "nombre";
    private static final String COLUMN_SCORE = "puntuacion";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT UNIQUE, " +
                COLUMN_SCORE + " INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public int obtenerPuntuacion(String nombre) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_SCORE},
                COLUMN_NAME + "=?", new String[]{nombre}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int puntuacion = cursor.getInt(0);
            cursor.close();
            return puntuacion;
        }
        return -1;
    }

    public void guardarOActualizarPuntuacion(String nombre, int puntuacion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, nombre);
        values.put(COLUMN_SCORE, puntuacion);

        if (obtenerPuntuacion(nombre) == -1) {
            db.insert(TABLE_NAME, null, values);
        } else if (puntuacion < obtenerPuntuacion(nombre)) {
            db.update(TABLE_NAME, values, COLUMN_NAME + "=?", new String[]{nombre});
        }
    }

    public Cursor obtenerRanking() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_SCORE + " ASC", null);
    }
}

