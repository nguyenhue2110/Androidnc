package hue.com.mob201_ps08729.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import hue.com.mob201_ps08729.ItemClickListener;
import hue.com.mob201_ps08729.R;
import hue.com.mob201_ps08729.model.RssObject;

public class FeedViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener,View.OnLongClickListener {

   public TextView txttilen, txtPubdate,txtContent;
   private ItemClickListener itemClickListener;


   public FeedViewHolder(View itemView){
       super(itemView);
       txtContent= itemView.findViewById(R.id.txtContent);
       txtPubdate=itemView.findViewById(R.id.txtpubdate);
       txttilen=itemView.findViewById(R.id.txttitlen);

       itemView.setOnClickListener(this);
       itemView.setOnLongClickListener(this);
   }

   public void setItemClickListener(ItemClickListener itemClickListener){
       this.itemClickListener=itemClickListener;

   }

    @Override
    public void onClick(View v) {
        itemClickListener.onclick(v,getAdapterPosition(),false);

    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onclick(v,getAdapterPosition(),true);
        return true;

   }



}

