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

import com.ctis487.team9.cinemasociety.Event;
import com.ctis487.team9.cinemasociety.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UpcomingRecycle  extends RecyclerView.Adapter<UpcomingRecycle.MyRecyclerViewItemHolder> {

    private Context context;
    private ArrayList<Event> upcomingList;

    public UpcomingRecycle(Context context, ArrayList<Event> upcomingList) {
        this.context = context;
        this.upcomingList = upcomingList;
    }

    @NonNull
    @Override
    public UpcomingRecycle.MyRecyclerViewItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycle_item, parent, false);
        return new MyRecyclerViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingRecycle.MyRecyclerViewItemHolder holder, int i) {

        final Event eventItem = upcomingList.get(i);

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
        return upcomingList.size();
    }

    public class MyRecyclerViewItemHolder extends RecyclerView.ViewHolder {

        TextView titleTxt;
        TextView directorTxt;
        TextView dateTxt;
        ImageView imgEvent;
        ConstraintLayout parentLayout;


        public MyRecyclerViewItemHolder(@NonNull View itemView) {
            super(itemView);


            titleTxt = itemView.findViewById(R.id.textTitle);
            directorTxt = itemView.findViewById(R.id.textDirector);
            dateTxt = itemView.findViewById(R.id.textDateTime);
            imgEvent = itemView.findViewById(R.id.imgEvent);
            parentLayout = itemView.findViewById(R.id.itemConstLayout);


        }
    }
}
