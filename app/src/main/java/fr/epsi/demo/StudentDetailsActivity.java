package fr.epsi.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.epsi.demo.model.Student;

public class StudentDetailsActivity extends DemoActivity {
    private Student student;

    public static void display(DemoActivity activity, Student student){
        Intent intent=new Intent(activity,StudentDetailsActivity.class);
        intent.putExtra("student",student);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        showBackBtn();
        student = (Student) getIntent().getExtras().get("student");
        TextView textName = findViewById(R.id.textViewNameDetails);
        TextView textEmail = findViewById(R.id.textViewEmailDetails);
        ImageView imageViewDetails= findViewById(R.id.imageViewStudentDetails);

        textName.setText(student.getName());
        textEmail.setText(student.getEmail());
        Picasso.get().load(student.getPictureUrl()).into(imageViewDetails);

        setTitle(student.getName());

    }
}
