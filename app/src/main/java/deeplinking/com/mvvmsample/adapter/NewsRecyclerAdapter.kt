package deeplinking.com.mvvmsample.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import java.util.ArrayList

import deeplinking.com.mvvmsample.R
import deeplinking.com.mvvmsample.databinding.ListRowBinding
import deeplinking.com.mvvmsample.viewmodel.NewsModel

/**
 * Created by Rajesh Kumar on 10-05-2018.
 */
class NewsRecyclerAdapter(internal var context: Context, internal var arrayList: ArrayList<NewsModel>) : RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>() {


    inner class ViewHolder( val rowBinding: ListRowBinding) : RecyclerView.ViewHolder(rowBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = DataBindingUtil.inflate<ListRowBinding>(layoutInflater, R.layout.list_row, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.rowBinding!!.newslist = arrayList[position]

    }


    override fun getItemCount(): Int {
        return arrayList.size
    }
}
