package by.gerasimenko.testapp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import by.gerasimenko.testapp.R;
import by.gerasimenko.testapp.ui.interfaces.OnActionListener;

/**
 * Created 27.10.2016.
 */

public class ActionDialog extends AbstractDialog {

    private static final String MODE = "mode";

    private OnActionListener listener;

    @BindView(R.id.action_remove)
    TextView tvRemove;
    @BindView(R.id.action_edit)
    TextView tvEdit;


    public static ActionDialog newInstance(ActionDialog.Mode mode) {
        ActionDialog instance = new ActionDialog();

        Bundle bundle = new Bundle();
        bundle.putSerializable(MODE,mode);

        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            Mode mode = (Mode) bundle.getSerializable(MODE);
            int color;
            boolean enable = mode != null && mode == Mode.EDIT;
            if (enable) {
                color = getResources().getColor(android.R.color.black);
            } else {
                color = getResources().getColor(android.R.color.darker_gray);
            }
            tvRemove.setTextColor(color);
            tvEdit.setTextColor(color);
            tvRemove.setEnabled(enable);
            tvEdit.setEnabled(enable);
        }
    }

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

    public enum Mode {
        EDIT,
        ADD
    }
}
