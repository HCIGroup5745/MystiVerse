package com.example.luck.viewer.constellation.parnterfrag;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;


import com.example.luck.viewer.constellation.bean.StarBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.example.luck.R;
import com.example.luck.viewer.constellation.utils.AssetsUtils;



public class PartnerFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    ImageView manIv,womanIv;
    Spinner manSp,womanSp;
    Button prizeBtn,analysisBtn;
    private List<StarBean.StarinfoBean> infoList;
    List<String>nameList;   //存放了星座名称的集合
    private Map<String, Bitmap> logoImgMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_partner_02, container, false);
        initView(view);
//        获取activity传递的数据
        Bundle bundle = getArguments();
        StarBean starBean = (StarBean) bundle.getSerializable("info");
        infoList = starBean.getStarinfo();
        nameList = new ArrayList<>();
//      获取适配器所需要的数据源
        for (int i = 0; i < infoList.size(); i++) {
            String name = infoList.get(i).getName();
            nameList.add(name);
        }
//        创建适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.item_parnter_sp_02, R.id.item_parnter_tv, nameList);
//        设置适配器
        manSp.setAdapter(adapter);
        womanSp.setAdapter(adapter);

//        获取第一个图片资源显示在imageview上
        String logoname = infoList.get(0).getLogoname();

        logoImgMap = AssetsUtils.getContentlogoImgMap();
        Bitmap bitmap = logoImgMap.get(logoname);
        manIv.setImageBitmap(bitmap);
        womanIv.setImageBitmap(bitmap);
        Intent intent = new Intent(getActivity(), PartnerFragment.class);
        startActivity(intent);

        return view;
    }
 /*    初始化控件*/
    private void initView(View view) {
        manIv = view.findViewById(R.id.parnterfrag_iv_man);
        womanIv = view.findViewById(R.id.parnterfrag_iv_woman);
        manSp = view.findViewById(R.id.parnterfrag_sp_man);
        womanSp = view.findViewById(R.id.parnterfrag_sp_woman);
        analysisBtn = view.findViewById(R.id.parnterfrag_btn_analysis);
//        设置按钮的监听器
        prizeBtn.setOnClickListener(this);
        analysisBtn.setOnClickListener(this);
        manSp.setOnItemSelectedListener(this);
        womanSp.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.parnterfrag_btn_analysis) {
            // 获取spinner选中的位置
            int manSelPos = manSp.getSelectedItemPosition();
            int womanSelPos = womanSp.getSelectedItemPosition();

            // 跳转，传值到星座配对详情界面
            Intent intent = new Intent(getContext(), ParnterAnalysisActivity.class);
            intent.putExtra("man_name", infoList.get(manSelPos).getName());
            intent.putExtra("man_logoname", infoList.get(manSelPos).getLogoname());
            intent.putExtra("woman_name", infoList.get(womanSelPos).getName());
            intent.putExtra("woman_logoname", infoList.get(womanSelPos).getLogoname());
            startActivity(intent);
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.parnterfrag_sp_man) {
            String logoname = infoList.get(position).getLogoname();
            Bitmap bitmap = logoImgMap.get(logoname);
            manIv.setImageBitmap(bitmap);
        } else if (parent.getId() == R.id.parnterfrag_sp_woman) {
            String logoname = infoList.get(position).getLogoname();
            Bitmap bitmap = logoImgMap.get(logoname);
            womanIv.setImageBitmap(bitmap);
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
