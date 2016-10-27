package by.gerasimenko.testapp.ui.mvp;

import java.util.List;

import by.gerasimenko.testapp.rest.objects.Note;

/**
 * Created 27.10.2016.
 */

public interface MainView {
    void initRecyclerView(List<Note> objects);
    void updateList();
    void showMessasge(String text);
    void closeRefresh();
    void showActionDialog();
    void showEditNoteDialog(String title,String description);
}
