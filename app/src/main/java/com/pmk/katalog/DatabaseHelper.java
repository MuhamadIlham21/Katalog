package com.pmk.katalog;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pmk.katalog.Model.m_barang;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    static Context ctx;
    private static final int DATABASE_VERSION  = 1;
    private static final String DATABASE_NAME  = "katalog.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        ctx = context;

        ctx.getApplicationContext();
        ctx.getFilesDir().getPath();
    }

    //untuk mengkopi database pada asset ke dalam device (hp)
    public void CopyDataBaseFromAsset() throws IOException {
        InputStream myInput = ctx.getAssets().open(DATABASE_NAME);
        String outFileName = getDatabasePath();
        File f = new File(ctx.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
        if (!f.exists())
            f.mkdir();
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    private static String getDatabasePath() {
        return ctx.getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }

    //buka database
    public SQLiteDatabase openDataBase() throws SQLException {
        File dbFile = ctx.getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()) {
            try {
                CopyDataBaseFromAsset();
                Log.d("","Copying sucess from Assets folder");
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }
        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);
    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }

    @SuppressLint("Range")
    public ArrayList<m_barang> getBarang() {
        openDataBase();
        ArrayList<m_barang> s = new ArrayList<>();
        String sql = "select * from barang";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        if(c.moveToFirst()){
            do{
                s.add(new m_barang(
                        c.getString(c.getColumnIndex("id_barang")),
                        c.getString(c.getColumnIndex("nama_barang")),
                        c.getString(c.getColumnIndex("harga_barang")),
                        c.getString(c.getColumnIndex("stok_barang")),
                        c.getString(c.getColumnIndex("satuan")))
                );
            }while(c.moveToNext());
        }
        return s;
    }

    public void insertBarang(m_barang brg){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nama_barang", brg.getNama_barang());
        values.put("harga_barang", brg.getHarga());
        values.put("stok_barang", brg.getStok());
        values.put("satuan", brg.getSatuan());
        db.insert("barang",null,  values);
    }

    public void updateBarang(m_barang brg){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nama_barang", brg.getNama_barang());
        values.put("harga_barang", brg.getHarga());
        values.put("stok_barang", brg.getStok());
        values.put("satuan", brg.getSatuan());

        db.update("barang", values, "id_barang" + " = ?",
                new String[]{brg.getId_barang()});
    }

    public void deleteBarang(String id_brang){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("barang",  "id_barang" + " = ?",
                new String[]{id_brang});
    }


}
