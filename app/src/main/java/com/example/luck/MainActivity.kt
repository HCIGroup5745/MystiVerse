package com.example.luck

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyLog
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.luck.databinding.ActivityMainBinding
import com.example.luck.fragment.ChatFragment
import com.example.luck.fragment.HomeFragment
import com.example.luck.fragment.MineFragment
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private val fragments: MutableList<Fragment> = ArrayList()
    var music_btn: ImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val music_btn = findViewById<ImageButton>(R.id.music_btn)
        music_btn.setOnClickListener(View.OnClickListener { v ->
            // 使用局部变量保存 music_btn 的引用
            val localMusicBtn = v as? ImageButton

            if (MyService.isplay == false) {
                startService(Intent(this@MainActivity, MyService::class.java))
                localMusicBtn?.setImageDrawable(getDrawable(R.drawable.bbb))
            } else {
                stopService(Intent(this@MainActivity, MyService::class.java))
                localMusicBtn?.setImageDrawable(resources.getDrawable(R.drawable.bbbb))
            }
        })

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        initData()
        switchFragment(fragments[0])
        binding!!.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            selectedFragment = if (item.itemId == R.id.nav_home) {
                fragments[0]
            } else if (item.itemId == R.id.nav_chat) {
                fragments[1]
            } else if (item.itemId == R.id.nav_mine) {
                fragments[2]
            } else {
                fragments[0]
            }
            // 调用封装的方法进行Fragment切换
            switchFragment(selectedFragment)
            true
        }
    }

    override fun onStart() {
        startService(Intent(this@MainActivity, MyService::class.java))
        super.onStart()
    }

    private fun initData() {
        fragments.add(HomeFragment())
        fragments.add(ChatFragment())
        fragments.add(MineFragment())
    }

    // 封装的切换Fragment的方法
    private fun switchFragment(selectedFragment: Fragment?) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, selectedFragment!!)
            .commit()
    }

}