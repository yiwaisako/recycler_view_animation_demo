package jp.yiwaisako.recycler_view_animation_demo.diffUtil

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import jp.yiwaisako.recycler_view_animation_demo.R
import timber.log.Timber

class DiffUtilActivity : AppCompatActivity() {

    lateinit var mRecyclerView: RecyclerView
    lateinit var mRecyclerViewAdapter: EmployeeRecyclerViewAdapter

    companion object {
        @JvmStatic
        fun createIntent(context: Context): Intent {
            Timber.d("createIntent")
            return Intent(context, DiffUtilActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diff_util_animation)

        mRecyclerViewAdapter = EmployeeRecyclerViewAdapter(
                DummyEmployeeDataUtils.getEmployeeListSortedByRole())
        mRecyclerView = findViewById(R.id.recycler_view) as RecyclerView
        mRecyclerView.setLayoutManager(LinearLayoutManager(this))
        mRecyclerView.setAdapter(mRecyclerViewAdapter)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.sort_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort_by_name -> {
                mRecyclerViewAdapter.updateEmployeeListItems(
                        DummyEmployeeDataUtils.getEmployeeListSortedByName())
                return true
            }
            R.id.sort_by_role -> {
                mRecyclerViewAdapter.updateEmployeeListItems(
                        DummyEmployeeDataUtils.getEmployeeListSortedByRole())
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

}
