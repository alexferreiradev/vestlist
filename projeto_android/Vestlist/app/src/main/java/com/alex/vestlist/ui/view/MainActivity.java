package com.alex.vestlist.ui.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alex.vestlist.R;
import com.alex.vestlist.bussiness.StudentBusiness;
import com.alex.vestlist.model.Subject;
import com.alex.vestlist.ui.adapter.SubjectListViewAdapter;
import com.alex.vestlist.ui.presenter.SubjectPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SubjectPresenter.View{

    private SubjectPresenter subjectPresenter;
    private ListView listView;
    private TextView emptyView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        emptyView = (TextView) findViewById(R.id.emptyView);
        listView.setEmptyView(emptyView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        subjectPresenter = new SubjectPresenter(this, this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        subjectPresenter.initialize();
    }

    @Override
    public void loadSubjects() {
        new LoadSubjects().execute();
    }

    @Override
    public void showSubjects(List<Subject> subjects) {
        SubjectListViewAdapter adapter = new SubjectListViewAdapter(this, subjects, R.layout.adapter_subject_listview);
        listView.setAdapter(adapter);
    }

    @Override
    public void setEmptyView() {
        emptyView.setText("Nenhuma matéria encontrada");
    }

    @Override
    public void toggleProgressBar() {
        if (progressBar.getVisibility() == View.VISIBLE)
            progressBar.setVisibility(View.GONE);
        else
            progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void toggleEmptyView() {
        if (emptyView.getVisibility() == View.VISIBLE)
            emptyView.setVisibility(View.GONE);
        else
            emptyView.setVisibility(View.VISIBLE);
    }

    public class LoadSubjects extends AsyncTask<String, Integer, List> {

        @Override
        protected List doInBackground(String... params) {
            StudentBusiness business = new StudentBusiness(MainActivity.this);
            List<Subject> subjects = business.loadSubjects(0, 100);
            return subjects;
        }

        @Override
        protected void onPostExecute(List list) {
            subjectPresenter.onLoadSubjects(list);
        }
    }
}
