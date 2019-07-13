package myapp.dell.example.android.canteenmanagementapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView t1,t2,t3,t4;
    View mView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        mView=itemView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v,getAdapterPosition());
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mClickListener.onItemLongClick(v,getAdapterPosition());
                return true;
            }
        });
        t1=itemView.findViewById(R.id.tvItems);
        t2=itemView.findViewById(R.id.tvPrice);
    }
    private ViewHolder.ClickListener mClickListener;
    public interface ClickListener {
        void onItemClick(View v,int position);
        void onItemLongClick(View v,int position);
    }
    public void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClickListener=clickListener;
    }

}
