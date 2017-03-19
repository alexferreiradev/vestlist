package com.alex.vestlist.ui.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alex.vestlist.R;
import com.alex.vestlist.model.Doubt;
import com.alex.vestlist.ui.presenter.AddOrEditDoubtPresenter;

public class AddOrEditDoubtActivity extends BaseActivity implements AddOrEditDoubtPresenter.View {

    public static final String ARGUMENT_DOUBT_KEY = "doubt argument";
    public static final String ARGUMENT_LIST_ID_KEY = "list id argument";

    private AddOrEditDoubtPresenter mPresenter;
    private EditText questionETV;
    private EditText detailsETV;
    private ProgressBar progressBar;
    private Doubt mDoubt;
    private long mListId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_doubt);
        mPresenter = new AddOrEditDoubtPresenter(this, this, savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_doubt_activity, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.saveAction:

                Doubt doubt = new Doubt();
                doubt.setQuestion(questionETV.getText().toString());
                doubt.setDetails(detailsETV.getText().toString());
                doubt.setListId(mListId);
                mPresenter.validateData(doubt);
                break;
        }
        return super.onOptionsItemSelected(item);
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

        mListId = getIntent().getExtras().getLong(ARGUMENT_LIST_ID_KEY);
        // todo verificar se tem doubt para editar e setar dados com presenter
    }

    @Override
    public void showErrorMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSuccessMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startAddOrUpdateThread(Doubt data) {
        mDoubt = data;
        new SaveThread().execute();
    }

    @Override
    public void showInvalidInputError(String msg) {
        showErrorMsg(msg);
    }

    @Override
    public void returnResultToActivity() {
        setResult(RESULT_OK, null);// Dado foi salvo no Bd, somente precisa de refresh list
        finish();
    }

    public class SaveThread extends AsyncTask<String, Integer, Object>{

        @Override
        protected Object doInBackground(String... params) {
            return mPresenter.saveOrEditDataInSource(mDoubt);
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            mPresenter.analiseResultFromThread(o);
        }
    }
}
