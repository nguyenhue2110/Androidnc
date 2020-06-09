package hue.com.mob201_ps08729.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hue.com.mob201_ps08729.ItemClickListener;
import hue.com.mob201_ps08729.R;
import hue.com.mob201_ps08729.dao.KhoaHocDao;
import hue.com.mob201_ps08729.model.KhoaHoc;

public class RecyclerViewHolder extends RecyclerView.ViewHolder
implements View.OnClickListener,View.OnLongClickListener {

    private ItemClickListener itemClickListener;
        RecyclerViewHolder adapter;
    TextView txtTenKH,txtMota,txtGiangVien,txtngbd,txtnkt;
    ImageView imgkh;
    CardView card1;
    KhoaHocDao khoaHocDao;
    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        card1= itemView.findViewById(R.id.card1);
        txtGiangVien=itemView.findViewById(R.id.txtGiangVien);
        txtTenKH=itemView.findViewById(R.id.txtTenKH1);
        txtMota=itemView.findViewById(R.id.txtMota);
        txtngbd=itemView.findViewById(R.id.txtngbd);
        txtnkt=itemView.findViewById(R.id.txtnkt);
        itemView.setOnLongClickListener(this);
        itemView.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        itemClickListener.onclick(view,getAdapterPosition(),false);

    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener= itemClickListener;
    }

    @Override
    public boolean onLongClick(View view) {

        itemClickListener.onclick(view,getAdapterPosition(),true);
        return true;
    }

    public static class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{
        private Context context;
        private List<KhoaHoc> khoaHocList= new ArrayList<>();
        KhoaHocDao khoaHocDao;

        public RecyclerAdapter(Context context, List<KhoaHoc> khoaHocList) {
            this.context = context;
            this.khoaHocList = khoaHocList;
        }

        @NonNull
        @Override
        public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater= LayoutInflater.from(parent.getContext());
            View itemView= inflater.inflate(R.layout.cardview_item_khoahoc,parent,false);

            return new RecyclerViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int i) {
            khoaHocDao= new KhoaHocDao(context);

            holder.txtTenKH.setText(khoaHocList.get(i).getTenKH());
            holder.txtMota.setText(khoaHocList.get(i).getMoTa());
            holder.txtngbd.setText(khoaHocList.get(i).getThoigianBD());
            holder.txtnkt.setText(khoaHocList.get(i).getThoigianKT());
            holder.txtGiangVien.setText(khoaHocList.get(i).getGiangVien());

            final KhoaHoc kh= khoaHocList.get(i);
                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onclick(View view, int position, boolean isLongclick) {
                        if(isLongclick){
                            Toast.makeText(context, "long clik", Toast.LENGTH_SHORT).show();
                        }else {
                            CharSequence[] items = {"Update", "Delete"};
                            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                            dialog.setTitle("Choose an action");

                            dialog.setItems(items, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    if (i == 0) {
                                        final Dialog dialog= new Dialog(context);
                                        dialog.setContentView(R.layout.dialog_update_kh);
                                        final EditText edtTenkh1=dialog.findViewById(R.id.edtTenkh1);
                                       Button btnThem1= dialog.findViewById(R.id.btnThem1);
                                       final EditText edtMota1= dialog.findViewById(R.id.edtMota1);
                                       final EditText edtGV1=dialog.findViewById(R.id.edtGV1);
                                        final EditText edttgkt1= dialog.findViewById(R.id.edttgkt1);
                                        final EditText txttgbd1= dialog.findViewById(R.id.txttgbd1);
                                        Button  btnHuy1=dialog.findViewById(R.id.btnHuy1);
                                        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * 1);
                                        int height = (int) (context.getResources().getDisplayMetrics().heightPixels * 0.7);
                                        dialog.getWindow().setLayout(width, height);
                                        dialog.show();

                                        edtTenkh1.setText(kh.getTenKH());
                                        edtMota1.setText(kh.getMoTa());
                                        edttgkt1.setText(kh.getThoigianKT());
                                        txttgbd1.setText(kh.getThoigianBD());
                                        edtGV1.setText(kh.getGiangVien());

                                        btnThem1.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                               int ma= kh.getMa();
                                               String tenkh= edtTenkh1.getText().toString();
                                               String mota= edtMota1.getText().toString();
                                               String tgbd= txttgbd1.getText().toString();
                                               String tgkt= edttgkt1.getText().toString();
                                               String giangvien= edtGV1.getText().toString();
                                               KhoaHoc kh= new KhoaHoc(ma,tenkh,mota,tgbd,tgkt,giangvien);
                                               int kt= khoaHocDao.update(kh);
                                               if(kt>0){
                                                   Toast.makeText(context, "cntc", Toast.LENGTH_SHORT).show();
                                                   hienthi();
                                                   notifyDataSetChanged();
                                                   dialog.dismiss();
                                               }else {
                                                   Toast.makeText(context, "cntb", Toast.LENGTH_SHORT).show();
                                               }
                                            }
                                        });

                                        btnHuy1.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                dialog.dismiss();
                                            }
                                        });


                                    }
                                    if (i == 1) {
                                        AlertDialog.Builder dialogdelete= new AlertDialog.Builder(context);
                                        dialogdelete.setTitle("Xóa");
                                        dialogdelete.setMessage("bạn có muốn xóa không");
                                        dialogdelete.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                showdeletedialog(kh);

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
                    }
                });
        }

        @Override
        public int getItemCount() {
            return khoaHocList.size();
        }


        public void showdeletedialog(KhoaHoc kh){

            try {
                khoaHocDao.deletekh(kh);
                khoaHocList.remove(kh);
                notifyDataSetChanged();
                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();

            }catch (Exception e){
                Toast.makeText(context, "xóa thất bại", Toast.LENGTH_SHORT).show();
            }
        }
        public void hienthi(){
            khoaHocList=khoaHocDao.getkh();
            RecyclerAdapter adapter= new RecyclerAdapter(context,khoaHocList);

            adapter.notifyDataSetChanged();
        }

    }



}
