package hue.com.mob201_ps08729;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import hue.com.mob201_ps08729.dao.KhDao;
import hue.com.mob201_ps08729.model.kh;

public class Update_khActivity extends AppCompatActivity {
EditText edtTenkh1u,edtMota1u,txttgbd1u,edttgkt1u,edtGV1u;
Button btnThem1u,btnHuy1u;
ImageView imgbackkh;

private String key,tenkh,mota,tgbd,tgkt,giangvien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_kh);
        edtTenkh1u= findViewById(R.id.edtTenkh1u);
        edtMota1u=findViewById(R.id.edtMota1u);
        edttgkt1u=findViewById(R.id.edttgkt1u);
        txttgbd1u=findViewById(R.id.txttgbd1u);
        edtGV1u=findViewById(R.id.edtGV1u);
        btnHuy1u=findViewById(R.id.btnHuy1u);
        btnThem1u=findViewById(R.id.btnThem1u);
            imgbackkh= findViewById(R.id.imgbackkh);
            imgbackkh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });

        key=getIntent().getStringExtra("key");
        tenkh=getIntent().getStringExtra("tenKH");
        mota=getIntent().getStringExtra("moTa");
        tgbd=getIntent().getStringExtra("thoigianBD");
        tgkt=getIntent().getStringExtra("thoigianKT");
        giangvien=getIntent().getStringExtra("giangVien");

        edtTenkh1u.setText(tenkh);
        edtMota1u.setText(mota);
        edttgkt1u.setText(tgkt);
        txttgbd1u.setText(tgbd);
        edtGV1u.setText(giangvien);

        btnThem1u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kh kh= new kh();
                kh.setTenKH(edtTenkh1u.getText().toString());
                kh.setMoTa(edtMota1u.getText().toString());
                kh.setThoigianBD(txttgbd1u.getText().toString());
                kh.setThoigianKT(edttgkt1u.getText().toString());
                kh.setGiangVien(edtGV1u.getText().toString());

                new KhDao().update(key, kh, new KhDao.DataStatus() {
                    @Override
                    public void upLoaded(List<hue.com.mob201_ps08729.model.kh> khoaHocList, List<String> keys) {

                    }

                    @Override
                    public void insert() {

                    }

                    @Override
                    public void update() {
                        Toast.makeText(Update_khActivity.this, "update thanh cong", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void delete() {

                    }
                });
            }
        });

        btnHuy1u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


}
