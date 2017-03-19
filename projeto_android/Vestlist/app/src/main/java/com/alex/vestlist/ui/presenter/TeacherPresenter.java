package com.alex.vestlist.ui.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.alex.vestlist.model.Subject;
import com.alex.vestlist.model.Teacher;
import com.alex.vestlist.ui.util.ViewUtil;
import com.alex.vestlist.ui.view.ListActivity;

import java.util.List;

/**
 * Created by Alex on 17/03/2017.
 */

public class TeacherPresenter extends BaseListPresenter<TeacherPresenter.View, Teacher> {

    public TeacherPresenter(View mView, Context context, Bundle savedInstanceState) {
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
    public List<Teacher> loadDataFromSource(int offset, int loadItemsLimit) {
        return mSource.loadTeachers(mView.getSubject(), offset, loadItemsLimit);
    }

    @Override
    protected int updateDataFromSource(Teacher data) {
        return 0;
    }

    @Override
    protected boolean removeDataFromSource(Teacher data) {
        return false;
    }

    @Override
    protected Long saveDataFromSource(Teacher data) {
        return null;
    }

    @Override
    protected List<Teacher> applyFilterFromAdapter() {
        return null;
    }

    @Override
    protected List<Teacher> applyFilterFromSource() {
        return null;
    }

    @Override
    protected void setEmptyView() {
        mView.setEmptyView("Não há professores");
    }


    @Override
    public void showAddOrEditView(Teacher data) {
        ViewUtil.showNotImplementedMsg(mContext);
    }

    @Override
    public void selectItemClicked(Teacher item) {
        Intent intent = new Intent(mContext, ListActivity.class);
        intent.putExtra(ListActivity.TEACHER_EXTRA_KEY, item);
        mContext.startActivity(intent);
    }

    public interface View extends BaseListContract.View<Teacher>{
        public Subject getSubject();
    }
}