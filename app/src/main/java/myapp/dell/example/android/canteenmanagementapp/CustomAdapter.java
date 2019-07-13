package myapp.dell.example.android.canteenmanagementapp;


import android.app.Notification;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import static myapp.dell.example.android.canteenmanagementapp.AppNotification.CHANNEL_1_ID;

public class CustomAdapter extends RecyclerView.Adapter<ViewHolder> {
    MainActivity listActivity;
    List<Model> modelList;
    Context context;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;


    public CustomAdapter(MainActivity listActivity, List<Model> modelList) {
        this.listActivity = listActivity;
        this.modelList = modelList;


    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.model_layout,viewGroup,false);


        ViewHolder viewHolder=new ViewHolder(itemView);
        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
            @Override
            public void onItemClick(View v, int position) {
               String items=modelList.get(position).getItems();
               String price=modelList.get(position).getPrice();
               Toast.makeText(listActivity,items+"\n" +price,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View v, int position) {

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
         viewHolder.t1.setText(modelList.get(i).getItems());
         viewHolder.t2.setText(modelList.get(i).getPrice());

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }


}
