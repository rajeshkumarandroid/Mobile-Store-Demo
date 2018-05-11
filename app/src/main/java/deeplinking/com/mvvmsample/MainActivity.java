package deeplinking.com.mvvmsample;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;

import deeplinking.com.mvvmsample.adapter.NewsRecyclerAdapter;
import deeplinking.com.mvvmsample.databinding.ActivityMainBinding;
import deeplinking.com.mvvmsample.viewmodel.NewsModel;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    NewsModel newsModel;
    ArrayList<NewsModel> arrayList;
    RecyclerView recyclerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        newsModel = new NewsModel();
        arrayList = newsModel.getArrayListNews();
        binding.recyclerview.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        binding.recyclerview.setAdapter(new NewsRecyclerAdapter(this,arrayList));

    }
}
