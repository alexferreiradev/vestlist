package com.alex.vestlist.ui.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.alex.vestlist.model.Subject;
import com.alex.vestlist.ui.view.TeacherActivity;

import java.util.List;

/**
 * Created by Alex on 16/03/2017.
 */

public class SubjectPresenter extends BaseListPresenter<SubjectPresenter.View, Subject> {

    public SubjectPresenter(View mView, Context context, Bundle savedInstanceState) {
        super(mView, context, savedInstanceState);
    }

    @Override
    protected void showDataNotEditedError() {

    }

    @Override
    protected void showDataEditedSuccess() {

    }

    @Override
    protected void showDataSavedSuccess() {

    }

    @Override
    protected void showDataRemovedError() {

    }

    @Override
    protected void showDataRemovedSuccess() {

    }

    @Override
    protected void showDataNotSavedError() {

    }

    @Override
    public void selectItemClicked(Subject subject) {
        Intent intent = new Intent(mContext, TeacherActivity.class);
        intent.putExtra(TeacherActivity.SUBJECT_EXTRA_KEY, subject);
        mContext.startActivity(intent);
    }

    @Override
    public void showAddOrEditView(Subject data) {

    }

    @Override
    public List<Subject> loadDataFromSource(int offset, int loadItemsLimit) {
        return mSource.loadSubjects(offset, loadItemsLimit);
    }

    @Override
    protected int updateDataFromSource(Subject data) {
        return 0;
    }

    @Override
    protected boolean removeDataFromSource(Subject data) {
        return false;
    }

    @Override
    protected Long saveDataFromSource(Subject data) {
        return null;
    }

    @Override
    protected List<Subject> applyFilterFromAdapter() {
        return null;
    }

    @Override
    protected List<Subject> applyFilterFromSource() {
        return null;
    }

    @Override
    protected void setEmptyView() {
        mView.setEmptyView("Não há matérias");
    }

    /**
     * Funções que somente este presenter usa da View
     */
    public interface View extends BaseListContract.View<Subject>{

    }
}
