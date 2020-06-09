package hue.com.mob201_ps08729;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hue.com.mob201_ps08729.model.News;


public class NewsFragment extends Fragment {

    ListView listView;
    ArrayList<String> arrayList = new ArrayList<String>();
    ArrayList<String> arrayLink = new ArrayList<String>();
    ArrayAdapter arrayAdapter;
        ArrayList<News>newsArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_news, container, false);


        listView = view.findViewById(R.id.lv1);
        AsyncTask<String,Void,String> content = new RssFeed().execute("https://vnexpress.net/rss/giao-duc.rss");
        arrayAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String link = arrayLink.get(i);
                Intent intent= new Intent(getActivity(),TintucActivity.class);
                intent.putExtra("linkURL",link);
                startActivity(intent);
            }
        });




    return view;


    }

    public class RssFeed extends AsyncTask<String,Void ,String>{

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader reader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line="";
                while ((line = bufferedReader.readLine())!=null)
                {
                    content.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            XMLParser xmlParser = new XMLParser();
            try {
                Document document = xmlParser.getDocument(s);
                NodeList nodeList = document.getElementsByTagName("item");
                  NodeList nodeList1 = document.getElementsByTagName("description");
                String title="";
                String hinhanh="";
                String link="";
                for(int i=0;i<nodeList.getLength();i++)
                {
                    String cdata= nodeList1.item(i+1).getTextContent();
                    Pattern p= Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                    Matcher matcher=p.matcher(cdata);
                    if(matcher.find()){
                        hinhanh=matcher.group(1);
                    }
                    Element element = (Element)nodeList.item(i);
                    title+=xmlParser.getValue(element,"title")+"\n";
                    link=xmlParser.getValue(element,"link");
                    arrayList.add(title);
                    arrayLink.add(xmlParser.getValue(element,"link"));

                    newsArrayList.add(new News(title,link,hinhanh));

                }
                arrayAdapter.notifyDataSetChanged();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }

        }
    }


}
