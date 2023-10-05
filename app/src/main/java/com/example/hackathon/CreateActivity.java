package com.example.hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

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

    public int countTopics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        message = findViewById(R.id.edtMessage);
        rootNode = FirebaseDatabase.getInstance().getReference("messages");
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();


        DatePicker calendar = new DatePicker(this);

        LinearLayout flexLayout = findViewById(R.id.layoutFlex);



        // Poll section which create an edit text that create the topics on enter
        EditText topic1 = new EditText(this);
        configEditText(topic1);
        topic1.setHint("Entrez vos topics ici");

        topic1.setFocusableInTouchMode(true);

        countTopics = 0;
        topic1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (countTopics > 7){
                    Toast.makeText(CreateActivity.this, "8 topics maximum !", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER) &&
                        (topic1.getText().toString().trim().length() > 0)) {
                    EditText topic = new EditText(CreateActivity.this);
                    topic.setText(topic1.getText());
                    configEditText(topic);
                    topic.setInputType(1);

                    flexLayout.addView(topic);

                    countTopics++;
                    topic1.setText("");
                    Toast.makeText(CreateActivity.this, "Topic ajouté avec succès !", Toast.LENGTH_SHORT).show();
                    topic1.requestFocus();

                    return true;
                } else {
                    return false;
                }
            }

        });


        // RadioButtons's event listener to affect layout
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                countTopics = 0;
                flexLayout.removeAllViews();
                switch (checkedId) {
                    case R.id.radioMessage:

                        break;
                    case R.id.radioPoll:
                        flexLayout.addView(topic1);

                        break;
                    case R.id.radioEvent:
                        calendar.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {

                            @Override
                            public void onDateChanged(DatePicker view, int year, int month, int dayOfMonth) {

                            }
                        });
                        flexLayout.addView(calendar);
                        break;
                }

            }
        });

        // Cancel button, redirect to home page
        Button b = (Button) findViewById(R.id.cancel_button);
        b.setOnClickListener(view1 -> {
            Intent in = new Intent(CreateActivity.this, HomeActivity.class);
            startActivity(in);
        });

    }

    private void configEditText(EditText topic) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(10, 0, 10, 10);
        topic.setLayoutParams(params);
        topic.setWidth(1000);
        topic.setHeight(150);


        topic.setBackgroundResource(R.drawable.edit_text_border);
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