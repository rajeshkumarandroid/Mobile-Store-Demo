package deeplinking.com.mvvmsample.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import deeplinking.com.mvvmsample.R;
import deeplinking.com.mvvmsample.databinding.ListRowBinding;
import deeplinking.com.mvvmsample.viewmodel.NewsModel;

/**
 * Created by Rajesh Kumar on 10-05-2018.
 */
public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder> {


    Context context;
    ArrayList<NewsModel> arrayList;

    public NewsRecyclerAdapter(Context context, ArrayList<NewsModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private final ListRowBinding rowBinding;

        public ViewHolder(ListRowBinding itemView) {
            super(itemView.getRoot());
            this.rowBinding = itemView;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListRowBinding itemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_row, parent, false);
        return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.rowBinding.setNewslist(arrayList.get(position));

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
