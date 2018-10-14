package jp.yiwaisako.recycler_view_animation_demo

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.view.ContextThemeWrapper
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.AppCompatSpinner
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.patrickiv.demo.enteranimationdemo.model.AnimationItem
import java.util.ArrayList

class ListActivity : AppCompatActivity(), View.OnClickListener {

    private val mHandler = Handler()
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mSpinner: AppCompatSpinner
    private var mAnimationItems = arrayOf(
            AnimationItem("Fall down", R.anim.layout_animation_fall_down),
            AnimationItem("Slide from right", R.anim.layout_animation_from_right),
            AnimationItem("Slide from bottom", R.anim.layout_animation_from_bottom))
    private var mSelectedItem = AnimationItem("Fall down", R.anim.layout_animation_fall_down)

    companion object {
        @JvmStatic
        fun createIntent(context: Context): Intent {
            return Intent(context, ListActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        mRecyclerView = findViewById(R.id.recycler_view)
        mSpinner = findViewById(R.id.spinner)
        findViewById<View>(R.id.btn_reload).setOnClickListener(this)

        setupRecyclerView()
        setupSpinner()
        runLayoutAnimation(mRecyclerView, mSelectedItem)
    }

    override fun onDestroy() {
        mHandler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_reload) {
            runLayoutAnimation(mRecyclerView, mSelectedItem)
        }
    }

    private fun runLayoutAnimation(recyclerView: RecyclerView, item: AnimationItem) {
        val context = recyclerView.context
        val controller = AnimationUtils.loadLayoutAnimation(context, item.resourceId)
        recyclerView.layoutAnimation = controller
        recyclerView.adapter!!.notifyDataSetChanged()
        recyclerView.scheduleLayoutAnimation()
    }

    private fun setupRecyclerView() {
        val context = mRecyclerView.context
        val spacing = resources.getDimensionPixelOffset(R.dimen.default_spacing_small)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.adapter = CardAdapter()
        mRecyclerView.addItemDecoration(ItemOffsetDecoration(spacing))
    }

    private fun setupSpinner() {
        val itemNames = ArrayList<String>()
        for (item in mAnimationItems) {
            itemNames.add(item.name)
        }

        val ctx = mRecyclerView.context
        // Apply another theme to make the spinner text the right color
        val themedCtx = ContextThemeWrapper(ctx, R.style.Theme_AppCompat)
        mSpinner.adapter = ArrayAdapter(themedCtx, R.layout.row_spinner_item, itemNames)
        mSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                mSelectedItem = mAnimationItems[i]
                runLayoutAnimation(mRecyclerView, mSelectedItem)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {}
        }
    }
}
