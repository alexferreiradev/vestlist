package com.alex.vestlist.ui.presenter;

import android.content.Context;

import com.alex.vestlist.bussiness.StudentBusiness;
import com.alex.vestlist.model.Subject;

import java.util.List;

/**
 * Created by Alex on 16/03/2017.
 */

public class SubjectPresenter extends Presenter<SubjectPresenter.View> {

    public SubjectPresenter(View view, Context context) {
        super(view, context);
    }

    @Override
    public void initialize() {
        super.initialize();
        StudentBusiness business = new StudentBusiness(context);
        List<Subject> subjects = business.loadSubjects(0, -1);
        mView.showSubjects(subjects);
    }

    /**
     * Funções que somente este presenter realiza
     */
    public interface View extends Presenter.View{
        public void showSubjects(List<Subject> subjects);
    }
}
