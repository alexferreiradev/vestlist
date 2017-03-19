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
    public List<Teacher> loadDataFromSource(int offset, int loadItemsLimit) {
        return mSource.loadTeachers(mView.getSubject(), offset, loadItemsLimit);
    }

    @Override
    protected void setEmptyView() {
        mView.setEmptyView("Não há professores");
    }

    @Override
    protected void showSuccessMsg() {

    }

    @Override
    protected void showErrorMsg() {

    }

    @Override
    public void populateFilteredList(String filterType) {

    }

    @Override
    public void showAddOrEditView(Teacher data) {
        ViewUtil.showNotImplementedMsg(context);
    }

    @Override
    public int updateModelInSource(Teacher data) {
        return 0;
    }

    @Override
    public void openDataDetails(Teacher item) {
        Intent intent = new Intent(context, ListActivity.class);
        intent.putExtra(ListActivity.TEACHER_EXTRA_KEY, item);
        context.startActivity(intent);
    }

    public interface View extends BaseListContract.View<Teacher>{
        public Subject getSubject();
    }
}