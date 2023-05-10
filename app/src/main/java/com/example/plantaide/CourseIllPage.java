package com.example.plantaide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plantaide.model.Added;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class CourseIllPage extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://plantaideapp-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference ref = database.getReference();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String userId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_ill_page);

        ConstraintLayout courseBg = findViewById(R.id.courseIllPageBg);
        TextView courseTitle = findViewById(R.id.coursePageTitle);
        TextView courseExciter = findViewById(R.id.coursePageExciter);
        TextView courseSign = findViewById(R.id.coursePageSign);
        TextView courseText = findViewById(R.id.coursePageText);

        courseBg.setBackgroundColor(getIntent().getIntExtra("courseBg", 0));
        courseTitle.setText(getIntent().getStringExtra("courseTitle"));
        courseExciter.setText(getIntent().getStringExtra("courseExciter"));
        courseSign.setText(getIntent().getStringExtra("courseSign"));
        courseText.setText(getIntent().getStringExtra("courseText"));
    }

    public void addToCard(View view) {

        int id = getIntent().getIntExtra("courseId", 0);
        ref.child("Users").child(userId).child("courseId").child(Integer.toString(id)).child(Integer.toString(id)).setValue(1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CourseIllPage.this, "Болезнь сохранена", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(CourseIllPage.this, "Error", Toast.LENGTH_LONG).show();
                }
            }
        });

        Added.disease_id.add(id);
        //Toast.makeText(this, "Добавлена болезнь", Toast.LENGTH_LONG).show();

    }
}