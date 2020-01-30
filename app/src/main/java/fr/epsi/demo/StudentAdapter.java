package fr.epsi.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

import fr.epsi.demo.model.Student;

public class StudentAdapter extends ArrayAdapter<Student> {

    public StudentAdapter(@NonNull Context context, int resource, @NonNull List<Student> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater li = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = li.inflate(R.layout.c_student, null);

        TextView textViewName=convertView.findViewById(R.id.textViewName);
        TextView textViewEmail=convertView.findViewById(R.id.textViewEmail);
        ImageView imageView=convertView.findViewById(R.id.imageViewStudent);

        Student student=getItem(position);

        textViewName.setText(student.getName());
        textViewEmail.setText(student.getEmail());
        Picasso.get().load(student.getPictureUrl()).into(imageView);
        return convertView;
    }
}
