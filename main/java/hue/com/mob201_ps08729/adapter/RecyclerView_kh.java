package hue.com.mob201_ps08729.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hue.com.mob201_ps08729.R;
import hue.com.mob201_ps08729.Update_khActivity;
import hue.com.mob201_ps08729.dao.KhDao;
import hue.com.mob201_ps08729.model.KhoaHoc;
import hue.com.mob201_ps08729.model.kh;

public class RecyclerView_kh {
  private Context mcontext;
  private khadapter khadapter;
  public void set(RecyclerView recyclerView,Context context,List<kh>khList,List<String>keys){
      mcontext= context;
      khadapter= new khadapter(khList,keys);
      recyclerView.setLayoutManager(new LinearLayoutManager(context));
      recyclerView.setAdapter(khadapter);
  }
  class KhoaHocItemView extends RecyclerView.ViewHolder{
      private TextView txtTenKH1a;
      private TextView txtMotaa;
      private TextView txtGiangViena;
      private TextView txtngbda;
      private TextView txtnkta;

      private String key;

      public KhoaHocItemView(ViewGroup parent){
          super(LayoutInflater.from(mcontext).inflate(R.layout.item_kh,parent,false));

          txtTenKH1a= itemView.findViewById(R.id.txtTenKH1a);
          txtMotaa= itemView.findViewById(R.id.txtMotaa);
          txtGiangViena= itemView.findViewById(R.id.txtGiangViena);
          txtngbda= itemView.findViewById(R.id.txtngbda);
          txtnkta=itemView.findViewById(R.id.txtnkta);


          itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  CharSequence[] items = {"Update", "Delete"};
                  AlertDialog.Builder dialog = new AlertDialog.Builder(mcontext);
                  dialog.setTitle("Choose an action");
                  dialog.setItems(items, new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialogInterface, int i) {

                          if (i == 0) {

                              Intent intent= new Intent(mcontext, Update_khActivity.class);
                              intent.putExtra("key",key);
                              intent.putExtra("tenKH",txtTenKH1a.getText().toString());
                              intent.putExtra("moTa",txtMotaa.getText().toString());
                              intent.putExtra("thoigianBD",txtngbda.getText().toString());
                              intent.putExtra("thoigianKT",txtnkta.getText().toString());
                              intent.putExtra("giangVien",txtGiangViena.getText().toString());
                              mcontext.startActivity(intent);


                          }
                          if (i == 1) {
                              AlertDialog.Builder dialogdelete= new AlertDialog.Builder(mcontext);
                              dialogdelete.setTitle("Xóa");
                              dialogdelete.setMessage("bạn có muốn xóa không");
                              dialogdelete.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                  @Override
                                  public void onClick(DialogInterface dialogInterface, int i) {
                                    new KhDao().delete(key, new KhDao.DataStatus() {
                                        @Override
                                        public void upLoaded(List<kh> khoaHocList, List<String> keys) {

                                        }

                                        @Override
                                        public void insert() {

                                        }

                                        @Override
                                        public void update() {

                                        }

                                        @Override
                                        public void delete() {
                                            Toast.makeText(mcontext, "da xoa", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                  }
                              });

                              dialogdelete.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                                  @Override
                                  public void onClick(DialogInterface dialogInterface, int i) {
                                      dialogInterface.dismiss();
                                  }
                              });
                              dialogdelete.show();

                          }

                      }
                  });
                  dialog.show();

              }
          });

      }

      public void bind(kh kh,String key){
          txtTenKH1a.setText(kh.getTenKH());
          txtMotaa.setText(kh.getMoTa());
          txtnkta.setText(kh.getThoigianKT());
          txtngbda.setText(kh.getThoigianBD());
          txtGiangViena.setText(kh.getGiangVien());
          this.key =  key;


      }
  }

  class khadapter extends RecyclerView.Adapter<KhoaHocItemView>{
      public List<kh>mkhlist;
      private List<String>mkeys;

      public khadapter(List<kh> mkhlist, List<String> mkeys) {
          this.mkhlist = mkhlist;
          this.mkeys = mkeys;
      }

      @NonNull
      @Override
      public KhoaHocItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          return new KhoaHocItemView(parent);
      }

      @Override
      public void onBindViewHolder(@NonNull KhoaHocItemView holder, int position) {
        holder.bind(mkhlist.get(position),mkeys.get(position));
      }

      @Override
      public int getItemCount() {
          return mkhlist.size();
      }
  }

}
