package com.alex.vestlist.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.alex.vestlist.R;
import com.alex.vestlist.model.ExerciseList;
import com.alex.vestlist.ui.presenter.BasePresenter;

import java.util.List;

/**
 * Created by Alex on 16/03/2017.
 */

public class ExerciseListListViewAdapter extends BaseAdapter {
    private Context context;
    private List<ExerciseList> exerciseLists;
    private int resourceLayout;
    private BasePresenter basePresenter;

    public ExerciseListListViewAdapter(Context context, List list, int resourceLayout, BasePresenter basePresenter) {
        this.context = context;
        this.exerciseLists = list;
        this.resourceLayout = resourceLayout;
        this.basePresenter = basePresenter;
    }

    @Override
    public int getCount() {
        return exerciseLists.size();
    }

    @Override
    public Object getItem(int position) {
        return exerciseLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return exerciseLists.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            convertView = inflater.inflate(resourceLayout, parent, false);
            convertView.setTag(convertView);
        }else
            convertView = (View) convertView.getTag();

        TextView tvName = (TextView) convertView.findViewById(R.id.nameTV);
        CheckBox statusCB = (CheckBox) convertView.findViewById(R.id.statusCB);
        Button deleteBT = (Button) convertView.findViewById(R.id.deleteBT);

        final ExerciseList exerciseList = exerciseLists.get(position);
        tvName.setText(exerciseList.getName());
        if (!exerciseList.isHasDoubt())
            tvName.setTextColor(Color.RED);

        statusCB.setChecked(exerciseList.isCompleted());

        statusCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exerciseList.setCompleted(!exerciseList.isCompleted());
                basePresenter.startSaveOrEditDataInSource(exerciseList);
            }
        });
        deleteBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basePresenter.startRemoveDataInSource(exerciseList);
            }
        });

        return convertView;
    }

    public void addAll(List<ExerciseList> result) {
        exerciseLists.addAll(result);
        notifyDataSetChanged();
    }

    public void removeAll(List<ExerciseList> result) {
        exerciseLists.removeAll(result);
        notifyDataSetChanged();
    }
}
