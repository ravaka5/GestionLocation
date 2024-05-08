package fr.example.gestionlocation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private final ArrayList location_id, location_name, location_design, location_nbrdays, location_money;

    CustomAdapter(Activity activity,
                  Context context,
                  ArrayList location_id,
                  ArrayList location_name,
                  ArrayList location_design,
                  ArrayList location_nbrdays,
                  ArrayList location_money){
        this.activity = activity ;
        this.context = context ;
        this.location_id = location_id ;
        this.location_name = location_name;
        this.location_design = location_design;
        this.location_nbrdays = location_nbrdays;
        this.location_money = location_money;

    };
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
        holder.location_id_txt.setText(String.valueOf(location_id.get(position)));
        holder.location_name_txt.setText(String.valueOf(location_name.get(position)));
        holder.location_design_txt.setText(String.valueOf(location_design.get(position)));
        holder.location_nbrdays_txt.setText(String.valueOf(location_nbrdays.get(position)));
        holder.location_money_txt.setText(String.valueOf(location_money.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,UpdateActivity.class);
                intent.putExtra("id",String.valueOf(location_id.get(position)));
                intent.putExtra("name",String.valueOf(location_name.get(position)));
                intent.putExtra("design",String.valueOf(location_design.get(position)));
                intent.putExtra("nbrdays",String.valueOf(location_nbrdays.get(position)));
                intent.putExtra("money",String.valueOf(location_money.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return location_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView location_id_txt, location_name_txt, location_design_txt, location_nbrdays_txt, location_money_txt;
        ConstraintLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            location_id_txt = itemView.findViewById(R.id.location_id_txt);
            location_name_txt = itemView.findViewById(R.id.location_name_txt);
            location_design_txt = itemView.findViewById(R.id.location_design_txt);
            location_nbrdays_txt = itemView.findViewById(R.id.location_nbrdays_txt);
            location_money_txt = itemView.findViewById(R.id.location_money_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
