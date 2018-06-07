package deeplinking.com.mvvmsample.realm;


import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import deeplinking.com.mvvmsample.model.Mobiles;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Rajesh Kumar on 07-06-2018.
 */



public class RealmController {

    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {

        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {

        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {

        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {

        return instance;
    }

    public Realm getRealm() {

        return realm;
    }

    //Refresh the realm istance
    public void refresh() {

        realm.refresh();
    }

    //clear all objects from Book.class
    public void clearAll() {

        realm.beginTransaction();
        realm.clear(Mobiles.class);
        realm.commitTransaction();
    }




    public RealmResults<Mobiles> getMobiles(){
        return realm.where(Mobiles.class).findAll();
    }

}
