package com.example.luck.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.luck.MineActivity;
import com.example.luck.R;
import com.example.luck.RecordActivity;
import com.example.luck.SettingActivity;

public class GridViewAdapter extends BaseAdapter {
    private Context context;

    // 示例数据，实际使用时根据需要替换
    private String[] itemNames = {"档案", "我的发布", "赞与收藏", "浏览记录", "提问", "心情日记", "设置"};
    private Integer[] itemImgs = {R.drawable.icon_record, R.drawable.icon_issue, R.drawable.icon_like,
            R.drawable.icon_browse, R.drawable.icon_quiz, R.drawable.icon_diary, R.drawable.icon_setting};

    public GridViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return itemNames.length;
    }

    @Override
    public Object getItem(int position) {
        return itemNames[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_function, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView textView = convertView.findViewById(R.id.textView);
        LinearLayout ll = convertView.findViewById(R.id.ll);
        // 示例中直接使用了数组数据，实际使用时应根据数据源设置ImageView和TextView的内容
        imageView.setImageResource(itemImgs[position]);
        textView.setText(itemNames[position]);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                if (position == 0) {
                    intent = new Intent(context, MineActivity.class);
                    context.startActivity(intent);
                }
                if (position == 3) {
                    intent = new Intent(context, RecordActivity.class);
                    context.startActivity(intent);
                }
                if (position == 6) {
                    intent = new Intent(context, SettingActivity.class);
                    context.startActivity(intent);
                }

            }
        });
        return convertView;
    }
}