package hue.com.mob201_ps08729.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hue.com.mob201_ps08729.database.dphelper;
import hue.com.mob201_ps08729.model.KhoaHoc;

public class KhoaHocDao {

    private SQLiteDatabase database;

    public KhoaHocDao(Context context){
        dphelper dphelper = new dphelper(context);
        database= dphelper.getWritableDatabase();

    }


    public boolean ThemKH(KhoaHoc kh){
        ContentValues contentValues= new ContentValues();
        contentValues.put(dphelper.TB_KhoaHoc_TenKH,kh.getTenKH());
        contentValues.put(dphelper.TB_KhoaHoc_MoTa,kh.getMoTa());
        contentValues.put(dphelper.TB_KhoaHoc_ThoiGianBD,kh.getThoigianBD());
        contentValues.put(dphelper.TB_KhoaHoc_ThoiGianKT,kh.getThoigianKT());
        contentValues.put(dphelper.TB_KhoaHoc_GiangVien,kh.getGiangVien());

        long kiemtra= database.insert(dphelper.TB_KhoaHoc,null,contentValues);
        if(kiemtra !=0){
            return true;
        }else {
            return false;
        }

    }


    public int update(KhoaHoc kh) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(dphelper.TB_KhoaHoc_TenKH, kh.getTenKH());
        contentValues.put(dphelper.TB_KhoaHoc_MoTa,kh.getMoTa());
        contentValues.put(dphelper.TB_KhoaHoc_ThoiGianBD,kh.getThoigianBD());
        contentValues.put(dphelper.TB_KhoaHoc_ThoiGianKT,kh.getThoigianKT());
        contentValues.put(dphelper.TB_KhoaHoc_GiangVien,kh.getGiangVien());

        return database.update(dphelper.TB_KhoaHoc,contentValues,
                dphelper.TB_KhoaHoc_Ma+"=?",new String[]{String.valueOf(kh.getMa())});



    }


    public void deletekh(KhoaHoc kh){
        database.delete(dphelper.TB_KhoaHoc,dphelper.TB_KhoaHoc_Ma +"=?",
                new String[]{String.valueOf(kh.getMa())});
    }
    public ArrayList<KhoaHoc> getkh(){
        ArrayList<KhoaHoc> list = new ArrayList<KhoaHoc>();
        String truyvan= " SELECT * from KHOAHOC"  ;
        Cursor cursor= database.rawQuery(truyvan,null);
        while (cursor.moveToNext()){
            KhoaHoc kh= new KhoaHoc();
            int makh= cursor.getInt(0);
            String tenkh= cursor.getString(1);
            String mota= cursor.getString(2);
            String Ngaybd= cursor.getString(3);
            String Ngaykt= cursor.getString(4);
            String giangVien=cursor.getString(5);


            list.add(new KhoaHoc(makh,tenkh,mota,Ngaybd,Ngaykt,giangVien));
        }
        return list;
    }

}
