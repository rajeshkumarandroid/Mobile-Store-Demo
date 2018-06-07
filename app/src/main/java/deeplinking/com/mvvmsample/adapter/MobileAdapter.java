package deeplinking.com.mvvmsample.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import deeplinking.com.mvvmsample.R;
import deeplinking.com.mvvmsample.model.Mobiles;
import deeplinking.com.mvvmsample.realm.RealmController;
import io.realm.Realm;

public class BooksAdapter extends RealmRecyclerViewAdapter<Mobiles> {

    final Context context;
    private Realm realm;
    private LayoutInflater inflater;

    public BooksAdapter(Context context) {

        this.context = context;
    }

    // create new views (invoked by the layout manager)
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflate a new card view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mobile, parent, false);
        return new CardViewHolder(view);
    }

    // replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        realm = RealmController.getInstance().getRealm();

        // get the article
        final Mobiles book = getItem(position);
        // cast the generic view holder to our specific one
        final CardViewHolder holder = (CardViewHolder) viewHolder;

        // set the title and the snippet
        holder.txt_name_model.setText(book.getName()+" "+book.getModel());

       holder.txt_color.setText(book.getColor());
       holder.txt_cost.setText(book.getPrice());
       holder.txt_battery.setText(book.getBattery());
       holder.txt_primary_camera.setText(book.getPrimary_camera());
       holder.txt_second_camera.setText(book.getSecondry_camera());
       holder.txt_memory.setText(book.getMemory());

        Log.e("book name is ","<><><"+book.getName());

        // load the background image
        /*if (book.getImageUrl() != null) {
            Glide.with(context)
                    .load(book.getImageUrl().replace("https", "http"))
                    .asBitmap()
                    .fitCenter()
                    .into(holder.imageBackground);
        }*/

        //remove single match from realm
        /*holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                RealmResults<Book> results = realm.where(Book.class).findAll();

                // Get the book title to show it in toast message
                Book b = results.get(position);
                String title = b.getTitle();

                // All changes to data must happen in a transaction
                realm.beginTransaction();

                // remove single match
                results.remove(position);
                realm.commitTransaction();

                if (results.size() == 0) {
                    Prefs.with(context).setPreLoad(false);
                }

                notifyDataSetChanged();

                Toast.makeText(context, title + " is removed from Realm", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //update single match from realm
        holder.card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View content = inflater.inflate(R.layout.edit_item, null);
                final EditText editTitle = (EditText) content.findViewById(R.id.title);
                final EditText editAuthor = (EditText) content.findViewById(R.id.author);
                final EditText editThumbnail = (EditText) content.findViewById(R.id.thumbnail);

                editTitle.setText(book.getTitle());
                editAuthor.setText(book.getAuthor());
                editThumbnail.setText(book.getImageUrl());

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(content)
                        .setTitle("Edit Book")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                RealmResults<Book> results = realm.where(Book.class).findAll();

                                realm.beginTransaction();
                                results.get(position).setAuthor(editAuthor.getText().toString());
                                results.get(position).setTitle(editTitle.getText().toString());
                                results.get(position).setImageUrl(editThumbnail.getText().toString());

                                realm.commitTransaction();

                                notifyDataSetChanged();
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
        });*/
    }

    // return the size of your data set (invoked by the layout manager)
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

        public CardViewHolder(View itemView) {
            // standard view holder pattern with Butterknife view injection
            super(itemView);

            card = (CardView) itemView.findViewById(R.id.card_books);
            txt_name_model = (TextView) itemView.findViewById(R.id.txt_name_model);
            txt_color = (TextView) itemView.findViewById(R.id.txt_color);
            txt_cost = (TextView) itemView.findViewById(R.id.txt_cost);
            txt_battery = (TextView) itemView.findViewById(R.id.txt_battery);
            txt_primary_camera = (TextView) itemView.findViewById(R.id.txt_primary_camera);
            txt_second_camera = (TextView) itemView.findViewById(R.id.txt_second_camera);
            txt_memory = (TextView) itemView.findViewById(R.id.txt_memory);
        }
    }
}
