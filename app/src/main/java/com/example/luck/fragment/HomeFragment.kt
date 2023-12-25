package com.example.luck.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyLog
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.luck.HitoKoto
import com.example.luck.PyschTest
import com.example.luck.R
import com.google.gson.Gson
import shengxiao.shengxiao
import xingzuo.xingzuo

/*import test.PyschTest;*/ /**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    var kotoText by mutableStateOf("")

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            mParam1 = requireArguments().getString(ARG_PARAM1)
            mParam2 = requireArguments().getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val button3 = view.findViewById<Button>(R.id.button3)
        val button4 = view.findViewById<Button>(R.id.button4)
        val button5 = view.findViewById<Button>(R.id.button5)
        val koto = view?.findViewById<TextView>(R.id.textView2)
        val url = "https://v1.hitokoto.cn"
        // 创建一个请求队列
        val queue: RequestQueue = Volley.newRequestQueue(requireContext().applicationContext)

        val stringRequest = StringRequest(
            Request.Method.GET,
            url, {
                val data = it
                Log.d(VolleyLog.TAG, "onCreate: stringRequest  ${data}")
                //通过Gson转化 成上面生成的数据类
                val kotoclass = Gson().fromJson(data, HitoKoto::class.java)
                if (koto != null) {
                    koto.text = kotoclass.hitokoto
                }
            }, {
                Log.d(VolleyLog.TAG, "onCreate: stringRequest error ${it.message}")
                if (koto != null) {
                    koto.text = "error"
                }
            })
        //把请求推入 queue 会自动进行异步请求
        queue.add(stringRequest)

        // Set click listener for the button
        button3.setOnClickListener { // 当按钮被点击时执行的代码
            // 获取与该 Fragment 相关联的 Context
            val context: Context? = activity
            // 创建一个 Intent 对象，指定当前上下文（context）和目标 Activity（xingzuo.class）
            val intent = Intent(context, xingzuo::class.java)
            // 启动目标 Activity
            context!!.startActivity(intent)
        }
        button4.setOnClickListener { // 当按钮被点击时执行的代码
            // 获取与该 Fragment 相关联的 Context
            val context: Context? = activity
            // 创建一个 Intent 对象，指定当前上下文（context）和目标 Activity（Shengxiao.class）
            val intent = Intent(context, shengxiao::class.java)
            // 启动目标 Activity
            context!!.startActivity(intent)
        }
        button5.setOnClickListener { // 当按钮被点击时执行的代码
            // 获取与该 Fragment 相关联的 Context
            val context: Context? = activity
            // 创建一个 Intent 对象，指定当前上下文（context）和目标 Activity（test.class）
            val intent = Intent(context, PyschTest::class.java)
            // 启动目标 Activity
            context!!.startActivity(intent)
        }
        return view
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: String?): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}