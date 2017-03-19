package com.alex.vestlist.ui.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.alex.vestlist.R;
import com.alex.vestlist.model.Doubt;
import com.alex.vestlist.ui.presenter.AddOrEditDoubtPresenter;

public class AddOrEditDoubtActivity extends BaseActivity<Doubt, AddOrEditDoubtPresenter.View, AddOrEditDoubtPresenter> implements AddOrEditDoubtPresenter.View {

    public static final String ARGUMENT_DOUBT_KEY = "doubt argument";
    public static final String ARGUMENT_LIST_ID_KEY = "list id argument";

    private AddOrEditDoubtPresenter mPresenter;
    private EditText questionETV;
    private EditText detailsETV;
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
    public void initializeWidgets(Bundle savedInstanceState) {
        super.initializeWidgets(savedInstanceState);

        questionETV = (EditText) findViewById(R.id.questionETV);
        detailsETV = (EditText) findViewById(R.id.detailsETV);

        mListId = getIntent().getExtras().getLong(ARGUMENT_LIST_ID_KEY);
        if (mListId <= 0)
            throw new IllegalArgumentException("Id de lista está nulo ou não foi passado");

        if (getIntent().hasExtra(ARGUMENT_DOUBT_KEY))
            mPresenter.validateDataToEdit((Doubt) getIntent().getExtras().getSerializable(ARGUMENT_DOUBT_KEY));
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

    @Override
    public void setViewToEditData(Doubt data) {
        questionETV.setText(data.getQuestion());
        detailsETV.setText(data.getDetails());
    }
}
