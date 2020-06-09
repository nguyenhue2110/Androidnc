package hue.com.mob201_ps08729.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dphelper extends SQLiteOpenHelper {
    public static String TB_KhoaHoc="KHOAHOC";
    public static String TB_KhoaHoc_Ma="MAKH";
    public static String TB_KhoaHoc_TenKH="TENKH";
    public static String TB_KhoaHoc_MoTa="MOTA";
    public static String TB_KhoaHoc_ThoiGianBD="THOIGIANBD";
    public static String TB_KhoaHoc_ThoiGianKT="THOIGIANKT";
    public static String TB_KhoaHoc_GiangVien="GIANGVIEN";



    public dphelper( Context context) {
        super(context, "QuanLyKhoaHoc", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String KHOAHOC="CREATE TABLE " +TB_KhoaHoc+"(" +TB_KhoaHoc_Ma + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +TB_KhoaHoc_TenKH +" TEXT, " + TB_KhoaHoc_MoTa+" TEXT, " + TB_KhoaHoc_ThoiGianBD+" TEXT, "
                +TB_KhoaHoc_ThoiGianKT +" TEXT, " + TB_KhoaHoc_GiangVien +" TEXT)";

        db.execSQL(KHOAHOC);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
    public SQLiteDatabase open(){
        return this.getWritableDatabase();
    }
}
