package com.example.myapplication.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Data.model.GroupSubject;
import com.example.myapplication.R;
import com.example.myapplication.SessionDetailsGV;
import com.example.myapplication.vietnamese;

import java.util.ArrayList;

public class RecyclerView_GroupSubjectSV extends RecyclerView.Adapter<RecyclerView_GroupSubjectSV.ViewHolder> {

    @NonNull
    private Context mcontext;
    ArrayList<GroupSubject> list;
    ArrayList<GroupSubject> filterList;

    vietnamese xuLyChuoiTiengViet = new vietnamese();
    public RecyclerView_GroupSubjectSV(Context mcontext, ArrayList<GroupSubject> list) {
        this.mcontext = mcontext;
        this.filterList = list;
        this.list = list;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khung_listview_session, parent, false);
        return new ViewHolder(view);
    }

    public Filter getFilter() {
        return filter;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        holder.txtCourse.setText(filterList.get(position).getIdcourse() + " : "+filterList.get(position).getIdcourseNavigation().getCoursetName());
        holder.txtGroupSuject.setText("Nhóm Môn Học: "+filterList.get(position).getIdgroup());
        holder.txtClass.setText("Lớp: "+filterList.get(position).getClass_());





        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mcontext, SessionDetailsGV.class);//qua một cái view moi hien thị tg tung buoi hc
                intent.putExtra("IdCourse",filterList.get(position).getIdcourse());
                intent.putExtra("CourseName",filterList.get(position).getIdcourseNavigation().getCoursetName());
                intent.putExtra("IdGroupSubject",filterList.get(position).getIdgroup());
                intent.putExtra("Class",filterList.get(position).getClass_());



                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvHSP;
        TextView  txtCourse, txtClass, txtGroupSuject, txtDate ;

        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCourse= itemView.findViewById(R.id.txt_Course);
            txtGroupSuject= itemView.findViewById(R.id.txt_GroupSubject);
            txtClass= itemView.findViewById(R.id.txt_Class);
            relativeLayout = itemView.findViewById(R.id.item_session);
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
                ArrayList<GroupSubject> filteredList = new ArrayList<>();
                String pattrn = keySearch.toLowerCase().trim();
                for (GroupSubject item : list) {
                    /// check dử liệu để add
                    if (xuLyChuoiTiengViet.ConvertString(item.getIdcourseNavigation().getCoursetName().toLowerCase())
                            .contains(xuLyChuoiTiengViet.ConvertString(pattrn))) {
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
            filterList = (ArrayList<GroupSubject>) results.values;
            notifyDataSetChanged();
        }
    };
}
