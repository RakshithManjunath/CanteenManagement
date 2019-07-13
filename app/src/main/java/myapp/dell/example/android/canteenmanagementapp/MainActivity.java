package myapp.dell.example.android.canteenmanagementapp;


import android.app.Notification;
import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static myapp.dell.example.android.canteenmanagementapp.AppNotification.CHANNEL_1_ID;


public class MainActivity extends AppCompatActivity {
    List<Model> modelList=new ArrayList<>();
    RecyclerView mrecyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    CustomAdapter adapter;
    ProgressDialog p;
    private NotificationManagerCompat notificationManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=FirebaseFirestore.getInstance();
        mrecyclerView=findViewById(R.id.recycler_view);
        mrecyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        mrecyclerView.setLayoutManager(layoutManager);
        p=new ProgressDialog(this);
        notificationManager = NotificationManagerCompat.from(this);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("New Order")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);
        showData();
    }
            public void showData() {
               p.setTitle("Please Wait");
               p.show();
               db.collection("Orders")
                       .get()
                       .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                           @Override
                           public void onComplete(@NonNull Task<QuerySnapshot> task) {
                               p.dismiss();

                               for(DocumentSnapshot doc:task.getResult()) {
                                   Model model=new Model(doc.getString("id"),
                                        doc.getString("items"),
                                        doc.getString("price"));
                                   modelList.add(model);

                               }

                               adapter =new CustomAdapter(MainActivity.this,modelList);
                               mrecyclerView.setAdapter(adapter);

                           }

                       })

                       .addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                                 p.dismiss();
                                 Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();

                           }
                       });
            }






    }

