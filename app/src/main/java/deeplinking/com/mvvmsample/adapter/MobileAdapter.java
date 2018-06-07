package deeplinking.com.mvvmsample.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import deeplinking.com.mvvmsample.R;
import deeplinking.com.mvvmsample.model.Mobiles;
import deeplinking.com.mvvmsample.realm.RealmController;
import deeplinking.com.mvvmsample.util.Common_dialog;
import io.realm.Realm;

/**
 * Created by Rajesh Kumar on 07-06-2018.
 */


public class MobileAdapter extends RealmRecyclerViewAdapter<Mobiles> {

    final Context context;
    private Realm realm;
    private LayoutInflater inflater;

    public MobileAdapter(Context context) {

        this.context = context;
    }
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mobile, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        realm = RealmController.getInstance().getRealm();


        final Mobiles mobiles = getItem(position);
        final CardViewHolder holder = (CardViewHolder) viewHolder;
        holder.txt_name_model.setText(mobiles.getName()+" "+mobiles.getModel());
       holder.txt_color.setText(mobiles.getColor());
       holder.txt_cost.setText(mobiles.getPrice());
       holder.txt_battery.setText(mobiles.getBattery()+" mAh");
       holder.txt_primary_camera.setText(mobiles.getPrimary_camera()+"MP");
       holder.txt_second_camera.setText(mobiles.getSecondry_camera()+"MP");
       holder.txt_memory.setText(mobiles.getMemory()+"GB");



        holder.card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Common_dialog.getIntance().editItem(context,realm, mobiles, position, new Common_dialog.updateItem() {
                    @Override
                    public void update(String itemname) {
                        Toast.makeText(context, itemname + " is updated", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    }
                });
            }
        });

        holder.img_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Common_dialog.getIntance().deleteItem(realm, position, context, new Common_dialog.updateItem() {
                    @Override
                    public void update(String itemname) {
                        notifyDataSetChanged();

                        Toast.makeText(context, itemname + " is removed from Data base", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        holder.frame_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common_dialog.getIntance().editItem(context,realm, mobiles, position, new Common_dialog.updateItem() {
                    @Override
                    public void update(String itemname) {
                        Toast.makeText(context, itemname + " is updated", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    }
                });
            }
        });
    }
    public int getItemCount() {

        if (getRealmAdapter() != null) {
            return getRealmAdapter().getCount();
        }
        return 0;
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        public CardView card;
        public TextView txt_name_model;
        public TextView txt_color;
        public TextView txt_cost;
        public TextView txt_battery;
        public TextView txt_primary_camera;
        public TextView txt_second_camera;
        public TextView txt_memory;
        public ImageView img_del;
        public FrameLayout frame_edit;

        public CardViewHolder(View itemView) {
            super(itemView);
            card = (CardView) itemView.findViewById(R.id.card_books);
            txt_name_model = (TextView) itemView.findViewById(R.id.txt_name_model);
            txt_color = (TextView) itemView.findViewById(R.id.txt_color);
            txt_cost = (TextView) itemView.findViewById(R.id.txt_cost);
            txt_battery = (TextView) itemView.findViewById(R.id.txt_battery);
            txt_primary_camera = (TextView) itemView.findViewById(R.id.txt_primary_camera);
            txt_second_camera = (TextView) itemView.findViewById(R.id.txt_second_camera);
            txt_memory = (TextView) itemView.findViewById(R.id.txt_memory);
            img_del = itemView.findViewById(R.id.img_del);
            frame_edit = itemView.findViewById(R.id.frame_edit);
        }
    }



}
