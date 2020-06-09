package hue.com.mob201_ps08729.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import hue.com.mob201_ps08729.ItemClickListener;
import hue.com.mob201_ps08729.R;
import hue.com.mob201_ps08729.TintucActivity;
import hue.com.mob201_ps08729.model.RssObject;

public class FeedAdapter extends  RecyclerView.Adapter<FeedViewHolder> {
    private RssObject r;
    private Context mcontext;
    private LayoutInflater inflater;

    public FeedAdapter(RssObject r, Context mcontext) {
        this.r = r;
        this.mcontext = mcontext;
        inflater = LayoutInflater.from(mcontext);

    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.row_news, parent, false);


        return new FeedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {

        holder.txttilen.setText(r.getItems().get(position).getTitle());
        holder.txtPubdate.setText(r.getItems().get(position).getPubDate());
        holder.txtContent.setText(r.getItems().get(position).getDescription());


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onclick(View view, int position, boolean isLongclick) {
                Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse(r.getItems().get(position).getLink()));
//
//         String ur= r.getItems().get(position).getLink();
//           Intent intent= new Intent(mcontext, TintucActivity.class);
//              intent.putExtra("link",ur);

                mcontext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return r.getItems().size();
    }

}
