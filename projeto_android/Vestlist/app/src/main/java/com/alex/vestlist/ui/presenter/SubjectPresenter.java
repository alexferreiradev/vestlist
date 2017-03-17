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

public class SubjectPresenter extends BaseListInfoPresenter<SubjectPresenter.View, Subject> {

    public SubjectPresenter(View mView, Context context, Bundle savedInstanceState) {
        super(mView, context, savedInstanceState);
    }

    @Override
    public void onAddActionSelected() {
    }

    @Override
    public void onItemSelected(Context context, Subject subject) {
        Intent intent = new Intent(context, TeacherActivity.class);
        intent.putExtra(TeacherActivity.SUBJECT_EXTRA_KEY, subject);
        context.startActivity(intent);
    }

    @Override
    public void onAddedListViewItem() {

    }

    @Override
    public List<Subject> requestLoadInfo(int offset, int loadItemsLimit) {
        return mBussiness.loadSubjects(offset, loadItemsLimit);
    }

    /**
     * Funções que somente este presenter usa da View
     */
    public interface View extends BaseListInfoPresenter.View{

    }
}
