package com.example.myapplication.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Data.model.Session;
import com.example.myapplication.R;
import com.example.myapplication.vietnamese;


import java.util.ArrayList;

public class RecyclerView_StudentDetail extends RecyclerView.Adapter<RecyclerView_StudentDetail.ViewHolder> {

    @NonNull
    private Context mcontext;
    ArrayList<Session> list;
    ArrayList<Session> filterList;

    vietnamese vietnamese = new vietnamese();
    public RecyclerView_StudentDetail(Context mcontext, ArrayList<Session> list) {
        this.mcontext = mcontext;
        this.filterList = list;
        this.list = list;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_studentdetail, parent, false);
        return new ViewHolder(view);
    }

   /* public Filter getFilter() {
        return filter;
    }*/

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.txtCourse.setText(filterList.get(position).getIdgroup() + " - "+filterList.get(position).getIdgroupNavigation().getIdcourse());
        holder.txtClass.setText("Nhóm Môn Học: "+filterList.get(position).getClass());
        holder.txtPeriod.setText("Tiết: "+filterList.get(position).getPeriodStart()+ " - " + filterList.get(position).getPeriodEnd());





       /* holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mcontext,Session_Details_SV.class);
                intent.putExtra("IdCourse",filterList.get(position).getIdCourse());
                intent.putExtra("CourseName",filterList.get(position).getCourseName());
                intent.putExtra("IdGroupSubject",filterList.get(position).getIdGroup());
                intent.putExtra("Classroom",filterList.get(position).getClasss());
                intent.putExtra("IDTeacher",filterList.get(position).getIdTeacher());
                intent.putExtra("Semester",filterList.get(position).getSemester());
                intent.putExtra("Year",filterList.get(position).getYear());
                intent.putExtra("Day",filterList.get(position).getDay());
                intent.putExtra("PeriodStart",filterList.get(position).getPeriodStart());
                intent.putExtra("PeriodEnd",filterList.get(position).getPeriodEnd());
                intent.putExtra("NOC",filterList.get(position).getNoc());
                intent.putExtra("Date",filterList.get(position).getDate());


                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mcontext.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtCourse, txtClass, txtPeriod ;

        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCourse= itemView.findViewById(R.id.txt_Course);
            txtClass= itemView.findViewById(R.id.txt_ClassSV);
            txtPeriod= itemView.findViewById(R.id.txt_IdGroupSV);
            relativeLayout = itemView.findViewById(R.id.item_student_session);
        }
    }
    private Filter filter= new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            /// khởi tạo biến result
            FilterResults filterResults = new FilterResults();
            String keySearch = constraint.toString();
            /// khi keysearch co gia tri
            if (keySearch != null && keySearch.toString().length() > 0) {
                /// thì mình khởi tạo list trống để add dữ liệu sao khi check vào
                ArrayList<Session> filteredList = new ArrayList<>();
                String pattrn = keySearch.toLowerCase().trim();
                for (Session item : list) {
                    /// check dử liệu để add
                    if (vietnamese.ConvertString(item.getClassroom().toLowerCase())
                            .contains(vietnamese.ConvertString(pattrn))) {
                        filteredList.add(item);
                    }
                }
                /// gán vào giá trị sao khi check xong
                filterResults.values = filteredList;
                filterResults.count = filteredList.size();
            } else {
                /// gán lại giá trị all
                filterResults.values = list;
                filterResults.count = list.size();
                synchronized (list) {
                    filterResults.values = list;
                    filterResults.count = list.size();
               }
            }
            return filterResults;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filterList = (ArrayList<Session>) results.values;
            notifyDataSetChanged();
        }
    };
}
