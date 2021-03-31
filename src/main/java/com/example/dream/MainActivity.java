package com.example.dream;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public  class MainActivity extends Activity {
     Button button;
     TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         button = findViewById(R.id.button);
         textView = findViewById(R.id.textView);




    }
        public void ClickMe(View v) {
        MyTask mt = new MyTask();
        mt.execute();
    }

        class MyTask extends AsyncTask<Void, Void, Void> {

            String title;//Тут храним значение заголовка сайта

            @Override
            protected Void doInBackground(Void... params) {

                Document doc = null;//Здесь хранится будет разобранный html документ
                try {
                    //Считываем заглавную страницу
                   doc = Jsoup.connect("https://store.steampowered.com/?l=russian").get();


                } catch (IOException e) {
                    //Если не получилось считать
                    e.printStackTrace();
                }

                //Если всё считалось, что вытаскиваем из считанного html документа заголовок
                if (doc!=null)
                    title = doc.title();
                else
                    title = "Ошибка";

                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                textView.setText(title);
            }
        }

        }


