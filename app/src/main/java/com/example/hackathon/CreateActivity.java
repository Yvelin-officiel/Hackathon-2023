package com.example.hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CreateActivity extends AppCompatActivity {
    private DatabaseReference rootNode;
    private EditText message;
    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        message = findViewById(R.id.edtMessage);
        rootNode = FirebaseDatabase.getInstance().getReference("messages");
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
    }

    public void sendData(View view) {
        writeMessage();
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void writeMessage() {
        String userId = user.getUid();
        Message msg = new Message(userId, message.getText().toString(), System.currentTimeMillis());
        DatabaseReference newMessage = rootNode.push();
        String messageId = newMessage.getKey();
        rootNode.child(messageId).setValue(msg);
    }
}