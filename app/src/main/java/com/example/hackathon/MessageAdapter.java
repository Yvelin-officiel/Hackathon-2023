package com.example.hackathon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    Context context;
    ArrayList<Message> list;

    public MessageAdapter(Context context, ArrayList<Message> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");

        Message m = list.get(position);

        usersRef.child(m.getUserId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users user = snapshot.getValue(Users.class);
                String email = user.getEmail();
                String nomPrenom = "(Vous avez posté ce message en anonyme)";

                Date date = new Date(m.getTimestamp());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
                String formattedDate = sdf.format(date);

                if (!m.isAnonyme()) {
                    String[] parts = email.split("@");
                    String[] nameParts = parts[0].split("\\.");

                    String prenom = nameParts[0];
                    String nom = nameParts[1];

                    // Formatez le nom et le prénom
                    nomPrenom = nom.substring(0, 1).toUpperCase() + nom.substring(1).toLowerCase() + " " +
                            prenom.substring(0, 1).toUpperCase() + prenom.substring(1).toLowerCase();
                } else {
                    holder.ano.setTextSize(13);
                }

                holder.ano.setText(nomPrenom);
                holder.msg.setText(m.getMessageText());
                holder.date.setText(formattedDate);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void sortMessagesByTimestamp() {
        // Trie la liste par timestamp (du plus récent au plus ancien)
        list.sort(new Comparator<Message>() {
            @Override
            public int compare(Message message1, Message message2) {
                return Long.compare(message2.getTimestamp(), message1.getTimestamp());
            }
        });
        notifyDataSetChanged(); // Actualise l'adaptateur après le tri
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {

        TextView ano, msg, date;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            ano = itemView.findViewById(R.id.ano);
            msg = itemView.findViewById(R.id.msg);
            date = itemView.findViewById(R.id.date);

        }
    }
}
