package com.alex.vestlist.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alex.vestlist.R;
import com.alex.vestlist.model.Subject;

import java.util.List;

/**
 * Created by Alex on 16/03/2017.
 */

public class SubjectListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Subject> subjects;
    private int resourceLayout;

    public SubjectListViewAdapter(Context context, List subjects, int resourceLayout) {
        super();
        this.context = context;
        this.subjects = subjects;
        this.resourceLayout = resourceLayout;
    }

    @Override
    public int getCount() {
        return subjects.size();
    }

    @Override
    public Object getItem(int position) {
        return subjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return subjects.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            convertView = inflater.inflate(resourceLayout, parent, false);
            convertView.setTag(convertView);
        }else
            convertView = (View) convertView.getTag();

        TextView tvAcronomy = (TextView) convertView.findViewById(R.id.tvAcronomy);
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);

        Subject subject = subjects.get(position);
        tvAcronomy.setText(subject.getName().substring(0,3));
        tvName.setText(subject.getName());

        return convertView;
    }

    public void addAll(List result) {
        this.subjects.addAll(result);
    }
}
