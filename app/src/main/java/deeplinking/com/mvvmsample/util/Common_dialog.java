package deeplinking.com.mvvmsample.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import deeplinking.com.mvvmsample.R;
import deeplinking.com.mvvmsample.app.Prefs;
import deeplinking.com.mvvmsample.model.Mobiles;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Rajesh Kumar on 07-06-2018.
 */
public class Delete_dialog {

    private Realm realm;
    public static Delete_dialog intance;
    public  updateItem updateItem;
    private LayoutInflater inflater;


    public static Delete_dialog getIntance(){
        if(intance ==null){
            intance = new Delete_dialog();
        }
        return intance;
    }


    public void deleteItem(final Realm realm,final int position,final Context context,final updateItem updateitem){


        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View content = inflater.inflate(R.layout.confirm_delete, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(content)
                .setTitle("Alert!!")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RealmResults<Mobiles> results = realm.where(Mobiles.class).findAll();

                        Mobiles b = results.get(position);
                        String title = b.getName();

                        realm.beginTransaction();

                        results.remove(position);
                        if (results.size() == 0) {
                            Prefs.with(context).setPreLoad(false);
                        }
                        updateitem.update(title);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();

        realm.commitTransaction();
    }

    public interface updateItem{
        void update(String itemname);
    }

}
