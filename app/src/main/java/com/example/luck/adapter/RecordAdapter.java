package com.example.luck.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luck.R;
import com.example.luck.db.Diary;

import java.util.ArrayList;
import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.Holder> {
    private Context context;
    private List<Diary> diaries = new ArrayList<>();

    public RecordAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<Diary> diaries) {
        this.diaries = diaries;
        notifyDataSetChanged();
    }

    public void addData(Diary diary) {
        diaries.add(0, diary);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecordAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sentens, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordAdapter.Holder holder, int position) {
        Diary diary = diaries.get(position);
        holder.time.setText(diary.getTime());
        holder.time1.setText(diary.getTime1());
        holder.tip.setText(diary.getTip());
        holder.content.setText(diary.getContent());
    }

    @Override
    public int getItemCount() {
        return diaries.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView time, time1, content, tip;

        public Holder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
            time1 = itemView.findViewById(R.id.time2);
            content = itemView.findViewById(R.id.content);
            tip = itemView.findViewById(R.id.tip);
        }
    }
}
