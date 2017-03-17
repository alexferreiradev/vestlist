package com.alex.vestlist.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alex.vestlist.R;
import com.alex.vestlist.model.Teacher;

import java.util.List;

/**
 * Created by Alex on 16/03/2017.
 */

public class TeacherListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Teacher> teachers;
    private int resourceLayout;

    public TeacherListViewAdapter(Context context, List teachers, int resourceLayout) {
        this.context = context;
        this.teachers = teachers;
        this.resourceLayout = resourceLayout;
    }

    @Override
    public int getCount() {
        return teachers.size();
    }

    @Override
    public Object getItem(int position) {
        return teachers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return teachers.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            convertView = inflater.inflate(resourceLayout, parent, false);
            convertView.setTag(convertView);
        }else
            convertView = (View) convertView.getTag();

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);

        Teacher teacher = teachers.get(position);
        tvName.setText(teacher.getName());

        return convertView;
    }

    public void addAll(List result){
        teachers.addAll(result);
    }
}
