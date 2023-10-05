package com.example.hackathon.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.hackathon.MainActivity;
import com.example.hackathon.MessageActivity;
import com.example.hackathon.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountFragment extends Fragment {

    public AccountFragment() {
        // require a empty public constructor

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        Button buttonLogout = view.findViewById(R.id.logoutButton);
        Button editMessageButton = view.findViewById(R.id.editMessagesButton);

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        editMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MessageActivity.class);
                startActivity(intent);
            }
        });

        // Initialize TextView
        TextView userNameTextView = view.findViewById(R.id.userNameTextView);

        // Call findUserName here to display the user's name
        findUserName(userNameTextView);

        return view;

    }

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public void findUserName(View view) {
        if (user != null) {
            String userEmail = user.getEmail();
            String userName = userEmail.split("@")[0]; // Extraction du nom d'utilisateur

            // Affichez le nom d'utilisateur dans votre activité
            TextView userNameTextView = view.findViewById(R.id.userNameTextView);
            userNameTextView.setText(userName);
        }
    }
}