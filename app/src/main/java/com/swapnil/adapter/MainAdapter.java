package com.swapnil.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.swapnil.R;
import com.swapnil.db.RoomDB;
import com.swapnil.model.MainData;

import java.util.List;


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private List<MainData> dataList;
    private Activity context;
    private RoomDB database;

    public MainAdapter(Activity context, List<MainData> dataList) {
        this.context = context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        //Initialized main data
        MainData data = dataList.get(position);
        database = RoomDB.getInstance(context);
        holder.textView.setText(data.getText());
        holder.tvLastName.setText(data.getLastName());
        holder.tvEmail.setText(data.getEmail());

        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainData d = dataList.get(holder.getAdapterPosition());
                database.mainDao().delete(d);
                int position = holder.getAdapterPosition();
                dataList.remove(position);
                notifyDataSetChanged();

            }
        });

        holder.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainData d = dataList.get(holder.getAdapterPosition());
                final int sId = d.getID();
                String sText = d.getText();
                String lastNa = d.getLastName();
                String email = d.getEmail();

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_update);
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int hight = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setLayout(width, hight);
                dialog.show();

                final EditText editText = dialog.findViewById(R.id.edit_text);
                final EditText lastName = dialog.findViewById(R.id.edit_last_name);
                final EditText editEmail = dialog.findViewById(R.id.edit_email);

                Button btUpdate = dialog.findViewById(R.id.bt_update);
                editText.setText(sText);
                lastName.setText(lastNa);
                editEmail.setText(email);
                btUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        String uText = editText.getText().toString().trim();
                        String lastNam = lastName.getText().toString().trim();
                        String email = editEmail.getText().toString().trim();
                        database.mainDao().update(sId, uText, lastNam, email);
                        dataList.clear();
                        dataList.addAll(database.mainDao().getAll());
                        notifyDataSetChanged();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView, tvEmail, tvLastName;
        ImageView btEdit, btDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
            btEdit = itemView.findViewById(R.id.bt_edit);
            btDelete = itemView.findViewById(R.id.bt_delete);
            tvEmail = itemView.findViewById(R.id.tv_email);
            tvLastName = itemView.findViewById(R.id.tv_last_name);

        }
    }
}
