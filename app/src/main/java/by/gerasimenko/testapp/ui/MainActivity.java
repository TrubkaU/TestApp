package by.gerasimenko.testapp.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.gerasimenko.testapp.ui.interfaces.OnActionListener;
import by.gerasimenko.testapp.ui.interfaces.OnNoteEditListener;
import by.gerasimenko.testapp.ui.interfaces.OnNoteListener;
import by.gerasimenko.testapp.ui.mvp.MainPresenter;
import by.gerasimenko.testapp.ui.mvp.MainView;
import by.gerasimenko.testapp.ui.adapter.NoteAdapter;
import by.gerasimenko.testapp.R;
import by.gerasimenko.testapp.rest.objects.Note;

public class MainActivity extends AppCompatActivity implements MainView,SwipeRefreshLayout.OnRefreshListener, OnNoteListener, OnActionListener, OnNoteEditListener {

    @BindView(R.id.swipe_refresh_list)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.list)
    RecyclerView recyclerView;

    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        presenter = new MainPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onStop();
    }

    @Override
    public void initRecyclerView(List<Note> objects) {

        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.Adapter adapter = new NoteAdapter(objects, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateList() {
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showMessasge(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closeRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showActionDialog() {
        ActionDialog dialog = new ActionDialog();
        dialog.show(getFragmentManager(),"");
    }

    @Override
    public void showEditNoteDialog(String title,String description) {
        NoteDialog.newInstance(title,description)
                .show(getFragmentManager(),"");
    }

    @Override
    public void onRefresh() {

        presenter.onRefresh();
    }

    @Override
    public void onClick(Note note) {
        presenter.onClick(note);
    }

    @Override
    public void onClickAddNote() {
        presenter.onClickAddNote();
    }

    @Override
    public void onClickRemoveNote() {
        presenter.onClickRemoveNote();
    }

    @Override
    public void onClickEditNote() {

        presenter.onClickEditNote();
    }

    @Override
    public void onSave(String title, String description) {
        presenter.onSaveNote(title,description);
    }
}
