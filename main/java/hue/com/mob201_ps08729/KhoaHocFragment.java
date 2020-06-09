package hue.com.mob201_ps08729;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import hue.com.mob201_ps08729.adapter.RecyclerViewHolder;

import hue.com.mob201_ps08729.adapter.RecyclerView_kh;
import hue.com.mob201_ps08729.dao.KhDao;
import hue.com.mob201_ps08729.dao.KhoaHocDao;
import hue.com.mob201_ps08729.model.KhoaHoc;
import hue.com.mob201_ps08729.model.kh;


/**
 * A simple {@link Fragment} subclass.
 */
public class KhoaHocFragment extends Fragment {
FloatingActionButton fab1;
EditText edtTenkh, edtMota,txttgbd,edttgkt,edtGV;
Button btnThem,btnHuy;
KhoaHocDao khoaHocDao;
RecyclerView recycle;
RecyclerViewHolder.RecyclerAdapter adapter;

    private FirebaseDatabase mdatabase;
    private DatabaseReference mReferencekh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_khoa_hoc, container, false);
        mdatabase=FirebaseDatabase.getInstance();
        mReferencekh=mdatabase.getReference();
       fab1= view.findViewById(R.id.fab1);
        fab1.setBackgroundTintList(getResources().getColorStateList(R.color.white));

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogthemkh();
            }
        });

        khoaHocDao= new KhoaHocDao(getActivity());
       recycle= view.findViewById(R.id.rcl1);

      new KhDao().readkh(new KhDao.DataStatus() {
          @Override
          public void upLoaded(List<kh> khoaHocList, List<String> keys) {
              new RecyclerView_kh().set(recycle,getContext(),khoaHocList,keys);
          }

          @Override
          public void insert() {

          }

          @Override
          public void update() {

          }

          @Override
          public void delete() {

          }
      });
//
//        recycle.setHasFixedSize(true);
//
//        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
//        recycle.setLayoutManager(layoutManager);

//        arrayList= new ArrayList<>();
//        arrayList=khoaHocDao.getkh();
//        adapter= new RecyclerViewHolder.RecyclerAdapter(getContext(),arrayList);
//        recycle.setAdapter(adapter);
//        adapter.notifyDataSetChanged();


//       fab1.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//              dialogkh();
//           }
//       });
       return view;
    }


    public void dialogthemkh(){
        final Dialog dialog= new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_khoahoc);

        edtTenkh=dialog.findViewById(R.id.edtTenkh);
        btnThem= dialog.findViewById(R.id.btnThem);
        edtMota= dialog.findViewById(R.id.edtMota);
        edtGV=dialog.findViewById(R.id.edtGV);
        edttgkt= dialog.findViewById(R.id.edttgkt);
        txttgbd= dialog.findViewById(R.id.txttgbd);
        btnHuy=dialog.findViewById(R.id.btnHuy);
        int width = (int) (getContext().getResources().getDisplayMetrics().widthPixels * 1);
        int height = (int) (getContext().getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(width, height);
        dialog.show();

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            mReferencekh.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if(edtTenkh.getText().toString().isEmpty()||edtGV.getText().toString().isEmpty()||edtMota.getText().toString().isEmpty()||
                    edttgkt.getText().toString().isEmpty()|| txttgbd.getText().toString().isEmpty()){
                        Toast.makeText(getContext(), "không bỏ trống", Toast.LENGTH_SHORT).show();
                    }else if(dataSnapshot.child("tenKH").exists()){
                        Toast.makeText(getContext(), "da ton tai", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        kh kh = new kh();
                        kh.setTenKH(edtTenkh.getText().toString());
                        kh.setMoTa(edtMota.getText().toString());
                        kh.setGiangVien(edtGV.getText().toString());
                        kh.setThoigianBD(txttgbd.getText().toString());
                        kh.setThoigianKT(edttgkt.getText().toString());


                        new KhDao().addkh(kh, new KhDao.DataStatus() {
                            @Override
                            public void upLoaded(List<hue.com.mob201_ps08729.model.kh> khoaHocList, List<String> keys) {

                            }

                            @Override
                            public void insert() {
                                Toast.makeText(getContext(), "thêm tc", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }

                            @Override
                            public void update() {

                            }

                            @Override
                            public void delete() {

                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



            }
        });

        edttgkt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChonNgaykt();
            }
        });

        txttgbd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChonNgaybd();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }
    public void dialogkh(){

        final Dialog dialog= new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_khoahoc);

        edtTenkh=dialog.findViewById(R.id.edtTenkh);
        btnThem= dialog.findViewById(R.id.btnThem);
        edtMota= dialog.findViewById(R.id.edtMota);
        edtGV=dialog.findViewById(R.id.edtGV);
        edttgkt= dialog.findViewById(R.id.edttgkt);
        txttgbd= dialog.findViewById(R.id.txttgbd);
        btnHuy=dialog.findViewById(R.id.btnHuy);
        int width = (int) (getContext().getResources().getDisplayMetrics().widthPixels * 1);
        int height = (int) (getContext().getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(width, height);
        dialog.show();

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String tenkh = edtTenkh.getText().toString();
                    String mota= edtMota.getText().toString();
                    String ngaybd= txttgbd.getText().toString();
                    String ngatkt= edttgkt.getText().toString();
                    String giangvien= edtGV.getText().toString();
                    if(edtTenkh.length()==0|| edtMota.length()==0||edttgkt.length()==0||
                    txttgbd.length()==0||edtGV.length()==0){
                        Toast.makeText(getActivity(), "vui long nhap ", Toast.LENGTH_SHORT).show();
                    }else {

                        KhoaHoc kh= new KhoaHoc();
                        kh.setTenKH(tenkh);

                        kh.setMoTa(mota);
                        kh.setThoigianBD(ngaybd);
                        kh.setThoigianKT(ngatkt);
                        kh.setGiangVien(giangvien);

                        boolean kiemtra= khoaHocDao.ThemKH(kh);
                        if(kiemtra){

//                            hienthi();
                            Toast.makeText(getActivity(), " đã thêm ", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }else {
                            Toast.makeText(getActivity(), "that bai", Toast.LENGTH_SHORT).show();
                        }
                    }






            }
        });


        edttgkt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChonNgaykt();
            }
        });

        txttgbd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChonNgaybd();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }


    private void ChonNgaybd(){
        final Calendar calendar= Calendar.getInstance();
        int ngay= calendar.get(Calendar.DATE);
        int thang= calendar.get(Calendar.MONTH);
        int nam= calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog= new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i,i1,i2);
                SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd/MM/yyyy");
                txttgbd.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }

    private void ChonNgaykt(){
        final Calendar calendar= Calendar.getInstance();
        int ngay= calendar.get(Calendar.DATE);
        int thang= calendar.get(Calendar.MONTH);
        int nam= calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog= new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i,i1,i2);
                SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd/MM/yyyy");
                edttgkt.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }

//public void hienthi(){
//    arrayList=khoaHocDao.getkh();
//    adapter= new RecyclerViewHolder.RecyclerAdapter(getContext(),arrayList);
//    recycle.setAdapter(adapter);
//    adapter.notifyDataSetChanged();
//}
}
