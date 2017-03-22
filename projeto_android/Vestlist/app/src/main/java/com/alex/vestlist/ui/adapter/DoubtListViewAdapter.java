package com.alex.vestlist.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.alex.vestlist.R;
import com.alex.vestlist.model.Doubt;
import com.alex.vestlist.ui.presenter.BasePresenter;

import java.util.List;

/**
 * Created by Alex on 16/03/2017.
 */

public class DoubtListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Doubt> doubts;
    private int resourceLayout;
    private BasePresenter mPresenter;

    public DoubtListViewAdapter(Context context, List doubts, int resourceLayout, BasePresenter mPresenter) {
        this.context = context;
        this.doubts = doubts;
        this.resourceLayout = resourceLayout;
        this.mPresenter = mPresenter;
    }

    @Override
    public int getCount() {
        return doubts.size();
    }

    @Override
    public Object getItem(int position) {
        return doubts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return doubts.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            convertView = inflater.inflate(resourceLayout, parent, false);
            convertView.setTag(convertView);
        }else
            convertView = (View) convertView.getTag();

        TextView nameTV = (TextView) convertView.findViewById(R.id.nameTV);
        Button deleteBT = (Button) convertView.findViewById(R.id.deleteBT);

        final Doubt doubt = doubts.get(position);
        deleteBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.startRemoveDataInSource(doubt);
            }
        });
        nameTV.setText(doubt.getQuestion());

        return convertView;
    }

    public void addAll(List<Doubt> result) {
        doubts.addAll(result);
        notifyDataSetChanged();
    }
}
