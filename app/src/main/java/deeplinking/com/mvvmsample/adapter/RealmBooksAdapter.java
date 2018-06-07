package deeplinking.com.mvvmsample.adapter;

import android.content.Context;

import deeplinking.com.mvvmsample.model.Mobiles;
import io.realm.RealmResults;

/**
 * Created by Rajesh Kumar on 07-06-2018.
 */


public class RealmBooksAdapter extends RealmModelAdapter<Mobiles> {

    public RealmBooksAdapter(Context context, RealmResults<Mobiles> realmResults, boolean automaticUpdate) {

        super(context, realmResults, automaticUpdate);
    }
}