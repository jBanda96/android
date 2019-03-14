package com.example.juliobanda.udemy.WorkingWithTables

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.example.juliobanda.udemy.R
import kotlinx.android.synthetic.main.main_item.view.*
import java.util.*
import kotlin.properties.Delegates

class WTVAdapter(private var items: ArrayList<String>,
                 private val longClickListener: LongClick,
                 private val itemDragListener: ItemDragListener): RecyclerView.Adapter<WTVAdapter.WTVViewHolder>(), ItemTouchHelperListener {

    var scrollDirection = ScrollDirection.DOWN

    var orderButton by Delegates.observable(false){ _, _, _ ->
        this.notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WTVViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent,false)
        return WTVViewHolder(view)
    }
    override fun onBindViewHolder(holder: WTVViewHolder, position: Int) {
        holder.bind(items[position], longClickListener)
    }

    override fun onItemMoved(recyclerView: RecyclerView, fromPosition: Int, toPosition: Int): Boolean {

        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(items, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(items, i, i - 1)
            }
        }

        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(viewHolder: RecyclerView.ViewHolder, position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }



    inner class WTVViewHolder(val view: View): RecyclerView.ViewHolder(view), ItemSelectedListener {

        fun bind(title: String, longClickListener: LongClick){
            view.main_text_title.text = title
            view.main_reorder.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    itemDragListener.onItemDrag(this)
                }
                false
            }

            if (orderButton) {
                view.main_reorder.visibility = View.VISIBLE
            } else {
                view.main_reorder.visibility = View.GONE
            }

            view.setOnClickListener {
                longClickListener.longClick(title)
            }

            animateView(view)
        }

        override fun onItemSelected() {
            view.main_view.setBackgroundColor(ContextCompat.getColor(view.context, R.color.selectedItem))
        }

        override fun onItemClear() {
            view.main_view.setBackgroundColor(0)
        }

        private fun animateView(viewToAnimate: View) {
            if (viewToAnimate.animation == null) {

                val animation =
                        if (scrollDirection == ScrollDirection.DOWN) AnimationUtils.loadAnimation(viewToAnimate.context, R.anim.slide_from_bottom)
                        else AnimationUtils.loadAnimation(viewToAnimate.context, R.anim.slide_from_top)

                viewToAnimate.animation = animation
            }
        }

    }

    enum class ScrollDirection {
        UP, DOWN
    }

}