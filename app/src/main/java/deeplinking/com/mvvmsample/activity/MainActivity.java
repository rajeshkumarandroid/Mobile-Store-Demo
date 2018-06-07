package deeplinking.com.mvvmsample.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import deeplinking.com.mvvmsample.R;
import deeplinking.com.mvvmsample.adapter.MobileAdapter;
import deeplinking.com.mvvmsample.adapter.RealmBooksAdapter;
import deeplinking.com.mvvmsample.app.Prefs;
import deeplinking.com.mvvmsample.model.Mobiles;
import deeplinking.com.mvvmsample.realm.RealmController;
import deeplinking.com.mvvmsample.viewmodel.MobileModel;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Rajesh Kumar on 07-06-2018.
 */


public class MainActivity extends AppCompatActivity {

    private MobileAdapter adapter;
    private Realm realm;
    private LayoutInflater inflater;
    private FloatingActionButton fab;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        recycler = findViewById(R.id.recycler);

        //get realm instance
        this.realm = RealmController.with(this).getRealm();

        setupRecycler();
        MobileModel mobileModel = new MobileModel();
        if (!Prefs.with(this).getPreLoad()) {
            mobileModel.setRealmDataModel(realm,this);
        }

        RealmController.with(this).refresh();
        setRealmAdapter(RealmController.with(this).getMobiles());

        //add new item
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inflater = MainActivity.this.getLayoutInflater();
                View content = inflater.inflate(R.layout.edit_item, null);
                final EditText edttxt_name = (EditText) content.findViewById(R.id.edttxt_name);
                final EditText edttxt_model = (EditText) content.findViewById(R.id.edttxt_model);
                final EditText edttxt_color = (EditText) content.findViewById(R.id.edttxt_color);
                final EditText edttxt_cost = (EditText) content.findViewById(R.id.edttxt_cost);
                final EditText edttxt_battery = (EditText) content.findViewById(R.id.edttxt_battery);
                final EditText edttxt_primary_cam = (EditText) content.findViewById(R.id.edttxt_primary_cam);
                final EditText edttxt_secondary_cam = (EditText) content.findViewById(R.id.edttxt_secondary_cam);
                final EditText edttxt_memory = (EditText) content.findViewById(R.id.edttxt_memory);


                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(content)
                        .setTitle("Add Mobile")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Mobiles mobiles = new Mobiles();
                                mobiles.setId(RealmController.getInstance().getMobiles().size() + System.currentTimeMillis());
                                mobiles.setName(edttxt_name.getText().toString());
                                mobiles.setModel(edttxt_model.getText().toString());
                                mobiles.setColor(edttxt_color.getText().toString());
                                mobiles.setPrice(edttxt_cost.getText().toString());
                                mobiles.setBattery(edttxt_battery.getText().toString());
                                mobiles.setPrimary_camera(edttxt_primary_cam.getText().toString());
                                mobiles.setSecondry_camera(edttxt_secondary_cam.getText().toString());
                                mobiles.setMemory(edttxt_memory.getText().toString());

                                if(edttxt_name.getText().length()==0||edttxt_model.getText().length()==0||edttxt_color.getText().length()==0||edttxt_cost.getText().length()==0||edttxt_battery.getText().length()==0
                                        ||edttxt_primary_cam.getText().length()==0||edttxt_memory.getText().length()==0){

                                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    realm.beginTransaction();
                                    realm.copyToRealm(mobiles);
                                    realm.commitTransaction();
                                    adapter.notifyDataSetChanged();
                                    recycler.scrollToPosition(RealmController.getInstance().getMobiles().size()-1);
                                }
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
        });
    }

    public void setRealmAdapter(RealmResults<Mobiles> mobilesRealmResults) {

        RealmBooksAdapter realmAdapter = new RealmBooksAdapter(this.getApplicationContext(), mobilesRealmResults, true);
        adapter.setRealmAdapter(realmAdapter);
        adapter.notifyDataSetChanged();
    }

    private void setupRecycler() {
        recycler.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        adapter = new MobileAdapter(this);
        recycler.setAdapter(adapter);
    }


}