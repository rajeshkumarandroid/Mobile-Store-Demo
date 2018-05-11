package deeplinking.com.mvvmsample.viewmodel;

import java.util.ArrayList;

import deeplinking.com.mvvmsample.model.News;

/**
 * Created by Rajesh Kumar on 10-05-2018.
 */
public class NewsModel {

    public String header;
    public String desc;
    public NewsModel(News news){
        this.header = news.header;
        this.desc = news.desc;
    }

    public NewsModel() {
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public ArrayList<NewsModel> getArrayListNews(){
        ArrayList<NewsModel> arrayList = new ArrayList<>();
          NewsModel newsModel = new NewsModel(new News("Header1 ","Description 1"));
          NewsModel newsMode2 = new NewsModel(new News("Header2 ","Description 2"));
          NewsModel newsMode3 = new NewsModel(new News("Header3 ","Description 3"));
          NewsModel newsMode4 = new NewsModel(new News("Header4 ","Description 4"));
          NewsModel newsMode5 = new NewsModel(new News("Header5 ","Description 5"));
          arrayList.add(newsModel);
          arrayList.add(newsMode2);
          arrayList.add(newsMode3);
          arrayList.add(newsMode4);
          arrayList.add(newsMode5);
        return arrayList;




    }


}
