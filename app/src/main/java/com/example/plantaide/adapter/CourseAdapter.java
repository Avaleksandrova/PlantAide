package com.example.plantaide.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantaide.CourseIllPage;
import com.example.plantaide.R;
import com.example.plantaide.model.Course;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewIllHolder> {
    Context context;
    List<Course> courses;

    public CourseAdapter(Context context, List<Course> courses) {
        this.context = context;
        this.courses = courses;
    }

    @NonNull
    @Override
    public CourseViewIllHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View courseItems = LayoutInflater.from(context).inflate(R.layout.course_ill_item, parent, false);
        return new CourseAdapter.CourseViewIllHolder(courseItems);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewIllHolder holder, int position) {
        holder.courseBg.setCardBackgroundColor(Color.parseColor(courses.get(position).getColor()));

        holder.courseTitle.setText(courses.get(position).getTitle());
        holder.courseExciter.setText(courses.get(position).getExciter());
        holder.courseSign.setText(courses.get(position).getSign());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CourseIllPage.class);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, new Pair<View, String>(holder.courseTitle, "courseTitle"));

                intent.putExtra("courseBg", Color.parseColor(courses.get(holder.getAdapterPosition()).getColor()));
                intent.putExtra("courseTitle",courses.get(holder.getAdapterPosition()).getTitle());
                intent.putExtra("courseExciter", courses.get(holder.getAdapterPosition()).getExciter());
                intent.putExtra("courseSign", courses.get(holder.getAdapterPosition()).getSign());
                intent.putExtra("courseText", courses.get(holder.getAdapterPosition()).getText());
                intent.putExtra("courseId", courses.get(holder.getAdapterPosition()).getId());

                context.startActivity(intent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static final class CourseViewIllHolder extends RecyclerView.ViewHolder{
        CardView courseBg;
        TextView courseTitle, courseExciter, courseSign;
        public CourseViewIllHolder(@NonNull View itemView) {

            super(itemView);

            courseBg = itemView.findViewById(R.id.courseBg);
            courseTitle = itemView.findViewById(R.id.courseTitle);
            courseExciter = itemView.findViewById(R.id.courseExciter);
            courseSign = itemView.findViewById(R.id.courseSign);
        }
    }
}
