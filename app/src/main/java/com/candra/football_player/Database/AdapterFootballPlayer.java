package com.candra.football_player.Database;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.candra.football_player.MainActivity;
import com.candra.football_player.R;

import java.util.ArrayList;

public class AdapterFootballPlayer extends RecyclerView.Adapter<AdapterFootballPlayer.ViewHolderPlayer> {

    private Context ctx;
    private ArrayList arrId, arrNama, arrNomor, arrKlub;

    public AdapterFootballPlayer(Context ctx, ArrayList arrId, ArrayList arrNama, ArrayList arrNomor, ArrayList arrKlub)
    {
        this.ctx = ctx;
        this.arrId = arrId;
        this.arrNama = arrNama;
        this.arrNomor = arrNomor;
        this.arrKlub = arrKlub;
    }

    @NonNull
    @Override
    public ViewHolderPlayer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Memompa List Item ke Recycler View
        View varView = LayoutInflater.from(ctx).inflate(R.layout.list_item_player,parent, false);
        return new ViewHolderPlayer(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPlayer holder, int position) {
        holder.tvId.setText(arrId.get(position).toString());
        holder.tvNama.setText(arrNama.get(position).toString());
        holder.tvNomor.setText(arrNomor.get(position).toString());
        holder.tvKlub.setText(arrKlub.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return arrNama.size();
    }

    public class ViewHolderPlayer extends RecyclerView.ViewHolder
    {
        private TextView tvId, tvNama, tvNomor, tvKlub;

        public ViewHolderPlayer(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvNomor = itemView.findViewById(R.id.tv_nomor);
            tvKlub = itemView.findViewById(R.id.tv_klub);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder pesan =  new AlertDialog.Builder(ctx);
                    pesan.setTitle("Perhatian !");
                    pesan.setMessage("Anda Memilih " + tvNama.getText().toString() + ". SIlahkan perintah yang anda inginkan !");
                    pesan.setCancelable(true);

                    pesan.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent();
                            intent.putExtra("varId", tvId.getText().toString());
                            intent.putExtra("varNama", tvNama.getText().toString());
                            intent.putExtra("varNomor", tvNomor.getText().toString());
                            intent.putExtra("varKlub", tvKlub.getText().toString());
                            ctx.startActivity(intent);
                        }
                    });

                    pesan.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MyDatabaseHelper myDB = new MyDatabaseHelper(ctx);
                            long execute = myDB.hapusPlayer(tvId.getText().toString());

                            if(execute == -1)
                            {
                                Toast.makeText(ctx, "Gagal Menghapus Data !", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(ctx, "Berhasil Menghapus Data !", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                ((MainActivity) ctx).onResume();
                            }
                        }
                    });

                    pesan.show();
                    return false;
                }
            });

        }
    }




}
