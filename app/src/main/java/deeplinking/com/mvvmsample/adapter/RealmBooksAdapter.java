package deeplinking.com.mvvmsample.adapter;

import android.content.Context;

import deeplinking.com.mvvmsample.model.Book;
import io.realm.RealmResults;

public class RealmBooksAdapter extends RealmModelAdapter<Book> {

    public RealmBooksAdapter(Context context, RealmResults<Book> realmResults, boolean automaticUpdate) {

        super(context, realmResults, automaticUpdate);
    }
}