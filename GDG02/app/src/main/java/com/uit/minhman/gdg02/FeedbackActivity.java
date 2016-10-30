package com.uit.minhman.gdg02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Minh Man on 10/29/2016.
 */

public class FeedbackActivity extends AppCompatActivity {
    EditText edtGopy;
    Button btnGui;
    DatabaseReference mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Init();

        edtGopy.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                btnGui.setEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edtGopy.getText().toString().equals("")){
                    btnGui.setEnabled(false);
                }
                else {
                    btnGui.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.child("Feedback").push().setValue(edtGopy.getText().toString());
                Toast.makeText(FeedbackActivity.this, "Phản hồi của bạn đã được ghi nhận. Xin cám ơn!", Toast.LENGTH_LONG).show();
                edtGopy.setText("");
                btnGui.setEnabled(false);
            }
        });
    }

    private void Init(){
        setContentView(R.layout.feedback_layout);
        edtGopy =(EditText) findViewById(R.id.editTextGopy);
        btnGui = (Button) findViewById(R.id.buttonGui);
        mData = FirebaseDatabase.getInstance().getReference();
    }
}
