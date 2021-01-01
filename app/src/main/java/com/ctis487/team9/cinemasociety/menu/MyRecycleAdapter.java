package com.ctis487.team9.cinemasociety.menu;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ctis487.team9.cinemasociety.DatabaseHelper;
import com.ctis487.team9.cinemasociety.Event;
import com.ctis487.team9.cinemasociety.R;
import com.squareup.picasso.Picasso;
import android.os.Bundle;
import java.util.ArrayList;

public class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.MyRecyclerViewItemHolder>{

    private Context context;
    private ArrayList<Event> recyclerItemValues;

    DatabaseHelper dbHelper;

    public MyRecycleAdapter(Context context, ArrayList<Event> recyclerItemValues) {
        this.context = context;
        this.recyclerItemValues = recyclerItemValues;

       dbHelper = new DatabaseHelper(context);
    }




    @NonNull
    @Override
    public MyRecycleAdapter.MyRecyclerViewItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(context);

        View itemView = inflator.inflate(R.layout.recycle_item, parent, false);

        MyRecyclerViewItemHolder mViewHolder = new MyRecyclerViewItemHolder(itemView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewItemHolder holder, int i) {

        final Event eventItem = recyclerItemValues.get(i);

        holder.titleTxt.setText(eventItem.getTitle()+" ("+eventItem.getYear()+")");
        holder.directorTxt.setText(eventItem.getDirector());
        holder.dateTxt.setText(eventItem.getDate()+ " | " +eventItem.getTime());

        String imgAdress = eventItem.getImage();

        Picasso.with(context)
                .load(imgAdress)
                .resize(400,400)
                .error(R.mipmap.ic_launcher)
                .into(holder.imgEvent);

        System.out.println(eventItem.getImage());
        Log.d("IMAGE", eventItem.getImage());
    }

    @Override
    public int getItemCount() {
       return recyclerItemValues.size();
    }

    static class MyRecyclerViewItemHolder extends RecyclerView.ViewHolder {
        TextView titleTxt;
        TextView directorTxt;
        TextView dateTxt;
        ImageView imgEvent;
        ConstraintLayout parentLayout;
        public MyRecyclerViewItemHolder(@NonNull View itemView)
        {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.textTitle);
            directorTxt = itemView.findViewById(R.id.textDirector);
            dateTxt = itemView.findViewById(R.id.textDateTime);
            imgEvent = itemView.findViewById(R.id.imgEvent);
            parentLayout = itemView.findViewById(R.id.itemConstLayout);
        }
    }
}
