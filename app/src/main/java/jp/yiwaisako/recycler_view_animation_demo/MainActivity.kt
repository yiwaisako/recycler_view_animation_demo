package jp.yiwaisako.recycler_view_animation_demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import jp.yiwaisako.recycler_view_animation_demo.diffUtil.DiffUtilActivity
import jp.yiwaisako.recycler_view_animation_demo.layoutAnimation.ListActivity
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_list.setOnClickListener(this)
        btn_list2.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        Timber.d("" + view.id)
        when (view.id) {
            R.id.btn_list -> startActivity(ListActivity.createIntent(this))
            R.id.btn_list2 -> startActivity(DiffUtilActivity.createIntent(this))
            else -> {
            }
        }
    }
}
