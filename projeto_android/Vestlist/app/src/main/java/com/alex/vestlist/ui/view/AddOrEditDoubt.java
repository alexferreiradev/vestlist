package com.alex.vestlist.ui.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alex.vestlist.R;
import com.alex.vestlist.ui.presenter.AddOrEditDoubtPresenter;

public class AddOrEditDoubt extends BaseActivity implements AddOrEditDoubtPresenter.View {

    private AddOrEditDoubtPresenter mPresenter;
    private EditText questionETV;
    private EditText detailsETV;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_doubt);
        mPresenter = new AddOrEditDoubtPresenter(this, this, savedInstanceState);
    }

    @Override
    public void toggleProgressBar() {
        if (progressBar.getVisibility() == View.VISIBLE)
            progressBar.setVisibility(View.GONE);
        else
            progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void initializeWidgets(Bundle savedInstanceState) {
        questionETV = (EditText) findViewById(R.id.questionETV);
        detailsETV = (EditText) findViewById(R.id.detailsETV);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public void showErrorMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSuccessMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
