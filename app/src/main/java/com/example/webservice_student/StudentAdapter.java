package com.example.webservice_student;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder>{

    private List<Student> mListStudent= new ArrayList<>();

    public void setStudents(List<Student> students) {
        this.mListStudent = students;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = mListStudent.get(position);
        if (student == null){
            return;
        }
        holder.tvName.setText(String.valueOf(student.getHoten()));
        holder.tvGmail.setText(String.valueOf(student.getGmail()));
        holder.tvSdt.setText(String.valueOf(student.getSdt()));
    }

    @Override
    public int getItemCount() {
        if(mListStudent != null){
            return mListStudent.size();
        }
        return 0;
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder{

        private TextView tvName, tvGmail, tvSdt;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName  = itemView.findViewById(R.id.tv_hoten);
            tvGmail = itemView.findViewById(R.id.tv_gmail);
            tvSdt   = itemView.findViewById(R.id.tv_sdt);
        }
    }
}
