package com.example.user.mysoup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.os.AsyncTask;

import android.widget.Button;

import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainActivity extends AppCompatActivity {

    String s="http://birder.in/info/41";
    Button btnGo;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGo = (Button) findViewById(R.id.button);
        txt = (TextView) findViewById(R.id.textView);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ( new ParseURL() ).execute(new String[]{s});
            }
        });
}
    private class ParseURL extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuffer buffer = new StringBuffer();
            try {

                Document doc  = Jsoup.connect(strings[0]).get();

                Elements topicList1= doc.select("div.col-md-4");
                Element spanText1= topicList1.select("p").get(0);

                String data1 = spanText1.text();
                buffer.append("\n"+data1+"\r\n");

                Element spanText2= topicList1.select("p").get(1);

                String data2 = spanText2.text();
                buffer.append("\n"+data2+"\r\n");

                Element spanText3= topicList1.select("p").get(2);

                String data3 = spanText3.text();
                buffer.append("\n"+data3+"\r\n");


                Elements topicList6= doc.select("table");
                Elements spanText6= topicList6.select("tr:eq(0)");


                Elements spanText7= spanText6.select("td:eq(0)");
                for (Element topic : spanText7) {
                    String data7 = topic.text();

                    buffer.append("\n"+data7+"\r\n");
                }


                Elements spanText8= spanText6.select("td:eq(1)");
                for (Element topic : spanText8) {
                    String data8 = topic.text();

                    buffer.append("\n"+data8+"\r\n");
                }

                Elements spanText9= topicList6.select("tr:eq(1)");

                for (Element topic : spanText9) {
                    String data9 = topic.text();

                    buffer.append("\n" + data9+ "\r\n");
                }




                Elements topicList4= doc.select("div.tab-content");
                Element spanText4= topicList4.select("p").get(0);

                String data4 = spanText4.text();
                buffer.append("\n"+data4+"\r\n");


                Elements topicList5= doc.select("div.tab-content");
                Element spanText5= topicList5.select("p").get(1);

                String data5 = spanText5.text();
                buffer.append("\n"+data5+"\r\n");




            }
            catch(Throwable t) {
                t.printStackTrace();
            }

            return buffer.toString();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txt.setText(s);
        }
    }
}



