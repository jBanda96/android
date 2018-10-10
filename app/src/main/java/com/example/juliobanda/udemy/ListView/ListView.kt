package com.example.juliobanda.udemy.ListView

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import com.example.juliobanda.udemy.R
import kotlinx.android.synthetic.main.activity_list_view.*

class ListView : AppCompatActivity() {

    private var namesAdapter: ItemListAdapter? = null
    private var name = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        name.apply {
            add("Julio")
            add("Krystell")
            add("Ericka")
            add("Mario Banda Avila")
            add("Mario Banda Castillo")
            add("Jaime")

        }
        namesAdapter = ItemListAdapter(name, this)

        listView.apply {
            adapter = namesAdapter
        }

        registerForContextMenu(listView)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =
            when (item?.itemId) {
                R.id.menu_add_new -> {
                    name.add("Otro")
                    namesAdapter?.notifyDataSetChanged()
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val adapterContextMenu = menuInfo as? AdapterView.AdapterContextMenuInfo
        menu?.setHeaderTitle(name[adapterContextMenu?.position ?: 0])
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {

        val adapterContextMenuInfo = item?.menuInfo as? AdapterView.AdapterContextMenuInfo

        when (item?.itemId) {
            R.id.delete_item -> {
                name.removeAt(adapterContextMenuInfo?.position ?: 0)
                namesAdapter?.notifyDataSetChanged()
                return true
            }

            else -> return super.onContextItemSelected(item)
        }
    }

}
