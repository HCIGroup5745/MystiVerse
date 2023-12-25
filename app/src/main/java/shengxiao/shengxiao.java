package shengxiao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.luck.R;

public class shengxiao extends AppCompatActivity {
    Button button;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shengxiao);
        this.datePicker=(DatePicker)findViewById(R.id.date_Picker);
        this.button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(shengxiao.this, shengxiaosecond.class);
                intent.putExtra("year",datePicker.getYear());
                startActivity(intent);
            }
        });
    }
}
