package by.gerasimenko.testapp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import by.gerasimenko.testapp.R;
import by.gerasimenko.testapp.ui.interfaces.OnNoteEditListener;

/**
 * Created 28.10.2016.
 */

public class NoteDialog extends AbstractDialog {


    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";


    @BindView(R.id.note_title)
    EditText etTitle;
    @BindView(R.id.note_description)
    EditText etDescription;

    OnNoteEditListener listener;


    public static NoteDialog newInstance(String title,String description) {

        NoteDialog instance = new NoteDialog();

        Bundle bundle = new Bundle();
        bundle.putString(TITLE,title);
        bundle.putString(DESCRIPTION,description);

        instance.setArguments(bundle);

        return instance;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (OnNoteEditListener) activity;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_note;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            etTitle.setText(bundle.getString(TITLE));
            etDescription.setText(bundle.getString(DESCRIPTION));
        }
    }

    @OnClick(R.id.note_cancel)
    public void onClickCancel() {
        getDialog().dismiss();
    }

    @OnClick(R.id.note_save)
    public void onClickSave() {

        listener.onSave(etTitle.getText().toString(),etDescription.getText().toString());
        getDialog().dismiss();
    }
}
