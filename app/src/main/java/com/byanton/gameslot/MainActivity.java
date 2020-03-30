package com.byanton.gameslot;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;




import java.util.Random;


import ImageViewScroll.ImageViewScroll;

public class MainActivity extends AppCompatActivity{
    Button spin1,spin2;
    ImageViewScroll image,image2,image3;
    TextView score;

    int count_down = 0;
    int bp = 300, lp = 150, lose = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        spin1 = (Button) findViewById(R.id.spin1);
        spin2 = (Button) findViewById(R.id.spin2);

        image = (ImageViewScroll) findViewById(R.id.image);
        image2 = (ImageViewScroll) findViewById(R.id.image2);
        image3 = (ImageViewScroll) findViewById(R.id.image3);

        score = (TextView) findViewById(R.id.score);

        image.setEventEnd(MainActivity.this);
        image2.setEventEnd(MainActivity.this);
        image3.setEventEnd(MainActivity.this);

        spin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Common.SCORE >= 25){
                    spin1.setVisibility(View.GONE);
                    spin2.setVisibility(View.VISIBLE);

                    image.setValueRandom(new Random().nextInt(3),new Random().nextInt((15-5)+1)+5);
                    image2.setValueRandom(new Random().nextInt(3),new Random().nextInt((15-5)+1)+5);
                    image3.setValueRandom(new Random().nextInt(3),new Random().nextInt((15-5)+1)+5);

                    Common.SCORE -= 50;
                    score.setText(String.valueOf(Common.SCORE));
                }
                else {
                    Toast.makeText(MainActivity.this,"You not enoght money",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void eventEnd(int result, int count) {
        if(count_down < 2){
            count_down++;
        }
        else{
            spin2.setVisibility(View.GONE);
            spin1.setVisibility(View.VISIBLE);

            count_down = 0;


            if(image.getValue() == image2.getValue() && image2.getValue() == image3.getValue()){
                Toast.makeText(this,"You won big prize", Toast.LENGTH_SHORT).show();
                Common.SCORE +=bp;
                score.setText(String.valueOf(Common.SCORE));
            }
            else if (image.getValue() == image2.getValue() || image2.getValue() == image3.getValue() || image.getValue() == image3.getValue()){
                Toast.makeText(this,"You won little prise", Toast.LENGTH_SHORT).show();
                Common.SCORE +=lp;
                score.setText(String.valueOf(Common.SCORE));
            }
            else if (image.getValue() != image2.getValue() || image2.getValue() != image3.getValue() || image.getValue() != image3.getValue()){
                Toast.makeText(this,"You lose", Toast.LENGTH_SHORT).show();
                Common.SCORE +=lose;
                score.setText(String.valueOf(Common.SCORE));
            }

        }


    }



}