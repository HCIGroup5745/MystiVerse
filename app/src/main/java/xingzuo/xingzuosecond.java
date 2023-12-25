package xingzuo;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.luck.R;

public class xingzuosecond extends AppCompatActivity {
    TextView textView;
    ImageView imageView;
    int [] infoArr={
            R.string.aquarius,R.string.pisces,R.string.aries,R.string.taurus,R.string.gemini,R.string.cancer,
            R.string.leo,R.string.virgo,R.string.libra,R.string.scorpio, R.string.sagittarius,R.string.capricornus
    };
    int [] imageArr={
            R.drawable.aquarius,R.drawable.pisces,R.drawable.aries,R.drawable.taurus,R.drawable.gemini,R.drawable.cancer,
            R.drawable.leo,R.drawable.virgo,R.drawable.libra,R.drawable.scorpio, R.drawable.sagittarius,R.drawable.capricornus
    };
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xingzuosecond);
        textView = (TextView)findViewById(R.id.textInfo);
        imageView = (ImageView)findViewById(R.id.imageView);
        int month=getIntent().getIntExtra("month",0);
        int day=getIntent().getIntExtra("day",0);
        int index=searchIndex(month,day);

        imageView.setImageResource(imageArr[index]);
        String str= xingzuosecond.this.getString(infoArr[index]);
        textView.setText("您好！您的星座信息如下：\n"+str);
    }
    public int searchIndex(int month, int day) {
        int index=month;
        int []daySpilt ={20,19,21,20,21,21,23,23,23,23,22,22};
        if(day<daySpilt[month]) index--;
        if(index<0)index=11;
        return index;
    }
}
