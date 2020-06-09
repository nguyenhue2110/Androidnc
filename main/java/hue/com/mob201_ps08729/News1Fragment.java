package hue.com.mob201_ps08729;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import hue.com.mob201_ps08729.adapter.FeedAdapter;
import hue.com.mob201_ps08729.model.RssObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class News1Fragment extends Fragment {

    RecyclerView recyclerView;
    RssObject rssObject;
    private final String RSS_link="https://vietnamnet.vn/rss/giao-duc.rss";

    private final  String RSS_to_Json_API=" https://api.rss2json.com/v1/api.json?rss_url=";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_news1, container, false);
        recyclerView= view.findViewById(R.id.renew);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        loadRSS();

        return view;
    }
public void loadRSS(){
    AsyncTask<String,String,String>loadRssAsyntask= new AsyncTask<String, String, String>() {
        ProgressDialog mDialog= new ProgressDialog(getContext());


        @Override
        protected void onPreExecute() {
            mDialog.setMessage("please waiting..");
            mDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {


            String result;
            HTTPDataHander http= new HTTPDataHander();
            result= http.GetHTTpData(params[0]);


            return result;
        }

        @Override
        protected void onPostExecute(String s) {

            mDialog.dismiss();
            rssObject= new Gson().fromJson(s,RssObject.class);
            FeedAdapter adapter = new FeedAdapter(rssObject,getContext());
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    };

    StringBuilder url_get_data= new StringBuilder(RSS_to_Json_API);
    url_get_data.append(RSS_link);
    loadRssAsyntask.execute(url_get_data.toString());
}

}
