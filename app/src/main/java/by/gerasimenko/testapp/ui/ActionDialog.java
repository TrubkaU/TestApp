package by.gerasimenko.testapp.ui;

import android.app.Activity;

import butterknife.OnClick;
import by.gerasimenko.testapp.R;
import by.gerasimenko.testapp.ui.interfaces.OnActionListener;

/**
 * Created 27.10.2016.
 */

public class ActionDialog extends AbstractDialog {

    private OnActionListener listener;

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        listener = (OnActionListener) context;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.dialog_actions;
    }

    @OnClick(R.id.action_edit)
    public void onClickEdit() {
        listener.onClickEditNote();

        getDialog().dismiss();
    }

    @OnClick(R.id.action_remove)
    public void onClickRemove() {
        listener.onClickRemoveNote();
        getDialog().dismiss();
    }

    @OnClick(R.id.action_add)
    public void onClickAdd() {
        listener.onClickAddNote();
        getDialog().dismiss();
    }


}
