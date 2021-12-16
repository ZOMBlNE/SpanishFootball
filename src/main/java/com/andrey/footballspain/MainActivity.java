package com.andrey.footballspain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView []tv =  new TextView[10];
    Button Btn1;
    private Document doc;
    private Thread secThread;
    private Runnable runnable;
    private int i, j, k;
    private Match_info []matchInfo = new Match_info[10];
    private EditText edit1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1 = (EditText) findViewById(R.id.edit1);
        Btn1 = (Button) findViewById(R.id.Btn1);

        tv[0] = findViewById(R.id.tv0);
        tv[1] = findViewById(R.id.tv1);
        tv[2] = findViewById(R.id.tv2);
        tv[3] = findViewById(R.id.tv3);
        tv[4] = findViewById(R.id.tv4);
        tv[5] = findViewById(R.id.tv5);
        tv[6] = findViewById(R.id.tv6);
        tv[7] = findViewById(R.id.tv7);
        tv[8] = findViewById(R.id.tv8);
        tv[9] = findViewById(R.id.tv9);
        Btn1.setOnClickListener(this);
        init(1);
    }


    private void init(int num)
    {
        runnable = new Runnable() {
            @Override
            public void run() {
               getWeb(num);
            }
        };
        secThread = new Thread(runnable);
        secThread.start();
    }

    private void getWeb(int matchday) {
        try {
            doc = Jsoup.connect("https://www.laliga.com/en-US/laliga-santander/results/2021-22/gameweek-"+matchday).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements element1 = doc.selectXpath("//p[@class='styled__TextRegularStyled-sc-1raci4c-0 fYuQIM']"),
                element2 = doc.selectXpath("//p[@class='styled__TextRegularStyled-sc-1raci4c-0 hvREvZ']");
        String[] date = new String[10];
        String[] score = new String[10];
        String[] tmp2 = new String[element2.size()];
        if (element1.size() == 40) k = 4;
        else k = 2;
        for (i = 0, j = 0; i < 10; i++, j += k) {
            date[i] = (element1.get(j).text() + " " + element1.get(j + 1).text());
            if (k == 4)
                score[i] = (element1.get(j + 2).text() + " - " + element1.get(j + 3).text());
            else score[i] = "T.B.D.";
        }
        for (i = 0; i < element2.size(); i++) {
            tmp2[i] = element2.get(i).text();
        }
        for (i = 0, j = 0; i < 10; i++, j += 2) {
            matchInfo[i] = new Match_info(date[i], score[i], tmp2[j], tmp2[j + 1]);
        }
        for(i=0; i<10; i++)
            tv[i].setText(matchInfo[i].getFirstTeam() +"    ["+ matchInfo[i].getMatchScore() +"]    "+ matchInfo[i].getSecondTeam()
                    + "\n" + matchInfo[i].getDate());
    }

    @Override
    public void onClick(View view) {
        String g = edit1.getText().toString();
        init(Integer.parseInt(g));
    }
}