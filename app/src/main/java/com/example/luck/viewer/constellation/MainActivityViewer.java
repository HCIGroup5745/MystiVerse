package com.example.luck.viewer.constellation;

import android.os.Bundle;
import android.widget.RadioGroup;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.luck.MainActivity;
import com.example.luck.R;

import com.google.gson.Gson;
import com.example.luck.viewer.constellation.starfrag.StarFragment;
import com.example.luck.viewer.constellation.luckfrag.LuckFragment;
import com.example.luck.viewer.constellation.parnterfrag.PartnerFragment;
import com.example.luck.viewer.constellation.mefrag.MeFragment;
import com.example.luck.viewer.constellation.utils.AssetsUtils;
import com.example.luck.viewer.constellation.bean.StarBean;

// 示例导入语句，具体路径根据你的包名和类名而定

/**
 * （1）创建整个布局
 */
public class MainActivityViewer extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    RadioGroup mainRg;
//    声明四个按钮对应的Fragment对象
    Fragment starFrag,luckFrag,meFrag;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_01);
        mainRg = findViewById(R.id.main_rg);
//        设置监听点击了哪个单选按钮
        mainRg.setOnCheckedChangeListener(this);
//        加载星座相关数据  /assets/xzcontent/xzcontent.json
        StarBean infoBean = loadData();
        Bundle bundle = new Bundle();
        bundle.putSerializable("info",infoBean);
//        创建碎片对象
        starFrag = new StarFragment();
        starFrag.setArguments(bundle);
        luckFrag = new LuckFragment();
        luckFrag.setArguments(bundle);
        meFrag = new MeFragment();
        meFrag.setArguments(bundle);
//        将四个Fragment进行动态加载，一起加载到布局当中。replace       add/hide/show
        addFragmentPage();
    }
    /* 读取assets文件夹下的xzcontent.json文件*/
    private StarBean loadData() {
        String json = AssetsUtils.getJsonFromAssets(this, "xzcontent/xzcontent.json");
        Gson gson = new Gson();
        StarBean infoBean = gson.fromJson(json, StarBean.class);
        AssetsUtils.saveBitmapFromAssets(this,infoBean);
        return infoBean;
    }

    /**
     * （1）
     * @des 将主页当中的碎片一起加载进入布局，有用的显示，暂时无用的隐藏
     * */
    private void addFragmentPage() {
//        1.创建碎片管理者对象
        manager = getSupportFragmentManager();
//        2.创建碎片处理事务的对象
        FragmentTransaction transaction = manager.beginTransaction();
//        3.将四个Fragment统一的添加到布局当中
        transaction.add(R.id.main_layout_center,starFrag);
        transaction.add(R.id.main_layout_center,luckFrag);
        transaction.add(R.id.main_layout_center,meFrag);
//        4.隐藏后面的三个

        transaction.hide(luckFrag);
        transaction.hide(meFrag);
//        5.提交碎片改变后的事务
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = manager.beginTransaction();

        if (checkedId == R.id.main_rb_star) {

            transaction.hide(luckFrag);
            transaction.hide(meFrag);
            transaction.show(starFrag);
        }
         else if (checkedId == R.id.main_rb_luck) {
            transaction.hide(starFrag);

            transaction.hide(meFrag);
            transaction.show(luckFrag);
        } else if (checkedId == R.id.main_rb_me) {
            transaction.hide(starFrag);
            transaction.hide(luckFrag);

            transaction.show(meFrag);
        }

        transaction.commit();
    }

}
