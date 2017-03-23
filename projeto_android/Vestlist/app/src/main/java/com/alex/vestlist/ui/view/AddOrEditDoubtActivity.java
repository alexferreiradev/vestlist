package com.alex.vestlist.ui.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.alex.vestlist.R;
import com.alex.vestlist.model.Doubt;
import com.alex.vestlist.ui.presenter.AddOrEditDoubtPresenter;

public class AddOrEditDoubtActivity extends BaseActivity<Doubt, AddOrEditDoubtPresenter.View, AddOrEditDoubtPresenter> implements AddOrEditDoubtPresenter.View {

    public static final String ARGUMENT_DOUBT_KEY = "doubt argument";
    public static final String ARGUMENT_LIST_ID_KEY = "list id argument";

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
        getMenuInflater().inflate(R.menu.add_edit_doubt_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.saveAction:
                Doubt doubt = null;
                if (mData != null && mData.getId() > 0){
                    doubt = mData;
                }else {
                    doubt = new Doubt();
                }

                doubt.setQuestion(questionETV.getText().toString());
                doubt.setDetails(detailsETV.getText().toString());
                doubt.setListId(mListId);
                mPresenter.validateDataInputToSaveOrEdit(doubt);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initializeWidgets(Bundle savedInstanceState) {
        super.initializeWidgets(savedInstanceState);
        questionETV = (EditText) findViewById(R.id.questionETV);
        detailsETV = (EditText) findViewById(R.id.detailsETV);
    }

    @Override
    public void initializeArgumentsFromIntent() {
        mListId = getIntent().getExtras().getLong(ARGUMENT_LIST_ID_KEY, -1);
        if (mListId <= 0)
            throw new IllegalArgumentException("Id de lista está nulo ou não foi passado");

        Toolbar mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        TextView mTitleAB = (TextView) mToolbar.findViewById(R.id.toolbar_title);
        if (getIntent().hasExtra(ARGUMENT_DOUBT_KEY)) {
            mPresenter.validateDataToSetEditView((Doubt) getIntent().getExtras().getSerializable(ARGUMENT_DOUBT_KEY));
            mTitleAB.setText("Atualizando dúvida");
        }else{
            mTitleAB.setText("Nova dúvida");
        }

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
        mData = data;
        questionETV.setText(data.getQuestion());
        detailsETV.setText(data.getDetails());
    }
}
