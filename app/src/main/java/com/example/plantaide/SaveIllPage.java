package com.example.plantaide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.plantaide.model.Added;
import com.example.plantaide.model.Course;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SaveIllPage extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://plantaideapp-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference ref = database.getReference();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String userId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
    private int needtodelete;


    private ListView diseasesList;
    private ArrayAdapter<String> arrayAdapter;
    private List<String> courseListIllTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_ill_page);

        diseasesList = findViewById(R.id.diseases_list);

        courseListIllTitle = new ArrayList<>();
        for (Integer c: Added.disease_id) {

            courseListIllTitle.add(MapActivity.fullCoursesList.get(c - 1).getTitle());

        }
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courseListIllTitle);
        diseasesList.setAdapter(arrayAdapter);

        registerForContextMenu(diseasesList);

        loadDiseases();
    }

    private void loadDiseases() {
//        DatabaseReference tmp = FirebaseDatabase.getInstance().getReference("Users");
        //



        int id = getIntent().getIntExtra("courseId", 0);
        //
        DatabaseReference databaseReference = ref.child("Users").child(userId).child("courseId");
//        DatabaseReference databaseReference = ref.child("Users").child(userId);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Added.disease_id.clear();
                courseListIllTitle.clear();
                //
//                for (int i = 1; i <= 13; ++i) {
//                    int amount = dataSnapshot.getValue(i)
//                }

                //
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    int diseaseId = Integer.parseInt(snapshot.getKey());
                    Added.disease_id.add(diseaseId);
//                    System.out.println(diseaseId);


                }
//                System.out.println(Added.disease_id);
                if (!Added.disease_id.isEmpty()) {
                    arrayAdapter.notifyDataSetChanged();
                }
                for (Integer c: Added.disease_id) {

                    courseListIllTitle.add(MapActivity.fullCoursesList.get(c - 1).getTitle());

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    private void deleteItem(int id){
        String name = diseasesList.getAdapter().getItem(id).toString();
        System.out.println(name);

        for (int i = 0; i < MapActivity.fullCoursesList.size(); ++i) {
            if (MapActivity.fullCoursesList.get(i).getTitle().equals(name)) {
                System.out.println("find " + MapActivity.fullCoursesList.get(i).getTitle());
                needtodelete = i;
                DatabaseReference databaseReference = ref.child("Users").child(userId).child("courseId").child(Integer.toString(i));
//                databaseReference.removeValue();
                ref.child("Users").child(userId).child("courseId").child(Integer.toString(id)).child(Integer.toString(id)).setValue(0);
                loadDiseases();
//                databaseReference.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        Added.disease_id.clear();
//                        //
////                for (int i = 1; i <= 13; ++i) {
////                    int amount = dataSnapshot.getValue(i)
////                }
//
//                        //
//                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                            int diseaseId = Integer.parseInt(snapshot.getKey());
////                            Added.disease_id.add(diseaseId);
////                    System.out.println(diseaseId);
//                            if (diseaseId == needtodelete) {
//                                snapshot.
//                            }
//
//
//                        }
////                System.out.println(Added.disease_id);
//                        if (!Added.disease_id.isEmpty()) {
//                            arrayAdapter.notifyDataSetChanged();
//                        }
//                        for (Integer c: Added.disease_id) {
//
//                            courseListIllTitle.add(MapActivity.fullCoursesList.get(c - 1).getTitle());
//
//                        }
//                    }
//
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                    }
//                });
            }
        }

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextmenu, menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delete:
                deleteItem(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }


    }

}