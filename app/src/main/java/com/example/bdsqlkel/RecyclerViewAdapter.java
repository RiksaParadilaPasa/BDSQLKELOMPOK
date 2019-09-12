package com.example.bdsqlkel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.UserViewHolder> {

    Context ctx;
    List<Entity_Mahasiswa> mahasiswa;
    OnUserClickListener listener;

    public RecyclerViewAdapter(Context ctx, List<Entity_Mahasiswa> mahasiswa , OnUserClickListener listener) {
        this.ctx = ctx;
        this.mahasiswa = mahasiswa;
        this.listener = listener;
    }



    public interface OnUserClickListener{
        void onUserClick(Entity_Mahasiswa current , String action);
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list,parent,false);
        UserViewHolder userViewHolder = new UserViewHolder(v);

        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.UserViewHolder holder, final int position) {
        final Entity_Mahasiswa person = mahasiswa.get(position);
        holder.txtNama.setText(person.getNama());
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(view.getContext());
                LayoutInflater inflater = LayoutInflater.from(view.getContext());
                View dialogView = inflater.inflate(R.layout.layout_what_to_do,null);
                dialogBuilder.setView(dialogView);

                Button btnDoUpdate = (Button) dialogView.findViewById(R.id.doUpdate);
                Button btnDoDelete = (Button) dialogView.findViewById(R.id.doDelete);
                Button btnDoDetail = (Button) dialogView.findViewById(R.id.doDetail);

                final AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();

                btnDoDetail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), DetailMahasiswa.class);
                        String id = person.getId()+"";
                        intent.putExtra("id",id);
                        intent.putExtra("nama",person.getNama());
                        intent.putExtra("tgl",person.getTgl_lahir());
                        intent.putExtra("jenkel",person.getJenkel());
                        intent.putExtra("alamat",person.getAlamat());
                        ctx.startActivity(intent);
                    }
                });

                btnDoUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onUserClick(person,"update");
                    }
                });

                btnDoDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onUserClick(person,"delete");
                        alertDialog.cancel();
                    }
                });
            }
        });





    }

    @Override
    public int getItemCount() {
        return mahasiswa.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        TextView txtNama;
        ImageView imageDelete;
        CardView cv;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txtNama);
            cv = itemView.findViewById(R.id.card);
        }
    }


}
