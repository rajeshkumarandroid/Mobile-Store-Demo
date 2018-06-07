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
public class Common_dialog {

    private Realm realm;
    public static Common_dialog intance;
    public  updateItem updateItem;
    private LayoutInflater inflater;


    public static Common_dialog getIntance(){
        if(intance ==null){
            intance = new Common_dialog();
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


    public void editItem(Context context,final Realm realm,Mobiles mobiles,final int position,final updateItem updateitem){
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View content = inflater.inflate(R.layout.edit_item, null);
        final EditText edttxt_name = (EditText) content.findViewById(R.id.edttxt_name);
        final EditText edttxt_model = (EditText) content.findViewById(R.id.edttxt_model);
        final EditText edttxt_color = (EditText) content.findViewById(R.id.edttxt_color);
        final EditText edttxt_cost = (EditText) content.findViewById(R.id.edttxt_cost);
        final EditText edttxt_battery = (EditText) content.findViewById(R.id.edttxt_battery);
        final EditText edttxt_primary_cam = (EditText) content.findViewById(R.id.edttxt_primary_cam);
        final EditText edttxt_secondary_cam = (EditText) content.findViewById(R.id.edttxt_secondary_cam);
        final EditText edttxt_memory = (EditText) content.findViewById(R.id.edttxt_memory);

        edttxt_name.setText(mobiles.getName());
        edttxt_model.setText(mobiles.getModel());
        edttxt_color.setText(mobiles.getColor());
        edttxt_cost.setText(mobiles.getPrice());
        edttxt_battery.setText(mobiles.getBattery());
        edttxt_primary_cam.setText(mobiles.getPrimary_camera());
        edttxt_secondary_cam.setText(mobiles.getSecondry_camera());
        edttxt_memory.setText(mobiles.getMemory());

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(content)
                .setTitle("Edit Mobile")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        RealmResults<Mobiles> results = realm.where(Mobiles.class).findAll();

                        realm.beginTransaction();
                        results.get(position).setName(edttxt_name.getText().toString());
                        results.get(position).setModel(edttxt_model.getText().toString());
                        results.get(position).setColor(edttxt_color.getText().toString());
                        results.get(position).setPrice(edttxt_cost.getText().toString());
                        results.get(position).setBattery(edttxt_battery.getText().toString());
                        results.get(position).setPrimary_camera(edttxt_primary_cam.getText().toString());
                        results.get(position).setSecondry_camera(edttxt_secondary_cam.getText().toString());
                        results.get(position).setMemory(edttxt_memory.getText().toString());
                        realm.commitTransaction();
                        updateitem.update(edttxt_name.getText().toString());
//                        notifyDataSetChanged();
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
    }

}
