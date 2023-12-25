package shengxiao;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.luck.R;

public class shengxiaosecond extends AppCompatActivity {
    TextView textView;
    ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shengxiaosecond);
        textView = (TextView)findViewById(R.id.textInfo);
        imageView = (ImageView)findViewById(R.id.imageView);
        int year=getIntent().getIntExtra("year",0);
        int [] infoArr={
                R.string.mouse, R.string.cattle,R.string.tiger,R.string.rabbit,R.string.dragon,R.string.snake,
                R.string.horse,R.string.sheep,R.string.monkey,R.string.chicken, R.string.dog,R.string.pig
        };
        int [] imageArr={
                R.drawable.mouse,R.drawable.cattle,R.drawable.tiger,R.drawable.rabbit,R.drawable.dragon,R.drawable.snake,
                R.drawable.horse,R.drawable.sheep,R.drawable.monkey,R.drawable.chicken, R.drawable.dog,R.drawable.pig
        };
        int i=find(year);
        imageView.setImageResource(imageArr[i]);
        String str= shengxiaosecond.this.getString(infoArr[i]);
        textView.setText("您好！您的生肖信息如下：\n"+str);
    }
    private int find(int year){
        int i=0;
        int year1=(year-1900)%12;
        if(year1==0){
            i=0;
        }
        if(year1==1){
            i=1;
        }
        if(year1==2){
            i=2;
        }
        if(year1==3){
            i=3;
        }
        if(year1==4){
            i=4;
        }
        if(year1==5){
            i=5;
        }
        if(year1==6){
            i=6;
        }
        if(year1==7){
            i=7;
        }
        if(year1==8){
            i=8;
        }
        if(year1==9){
            i=9;
        }
        if(year1==10){
            i=10;
        }
        if(year1==11){
            i=11;
        }
        return i;
    }
}
