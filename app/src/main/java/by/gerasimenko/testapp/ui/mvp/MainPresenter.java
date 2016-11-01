package by.gerasimenko.testapp.ui.mvp;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import by.gerasimenko.testapp.rest.RestClient;
import by.gerasimenko.testapp.rest.callback.NotesCallback;
import by.gerasimenko.testapp.rest.callback.SaveNotesCallback;
import by.gerasimenko.testapp.rest.callback.wrapper.NotesCallbackWrapper;
import by.gerasimenko.testapp.rest.callback.wrapper.SaveNotesWrapper;
import by.gerasimenko.testapp.rest.objects.Note;
import by.gerasimenko.testapp.ui.ActionDialog;

/**
 * Created 27.10.2016.
 */

public class MainPresenter implements NotesCallback, SaveNotesCallback{

    private final WeakReference<MainView> weakReference;
    private final List<Note> notes = new ArrayList<>();

    private final static long MAX_SIZE = 1024*1024+512*1024;

    private Note editable;
    private int pos;

    public MainPresenter(MainView view) {
        this.weakReference = new WeakReference<>(view);

        view.initRecyclerView(notes);
    }

    public void onStart(){

        onRefresh();
    }

    public void onStop() {


    }

    public void onRefresh() {

        RestClient.INSTANCE.getApiService().getNotes().enqueue(new NotesCallbackWrapper(this));
    }

    public void onClick(Note note) {
        if (note == null) return;

        if (weakReference.get() != null) {
            weakReference.get().showActionDialog(ActionDialog.Mode.EDIT);
            editable = note;
        }
    }

    public void onClickAddNote() {

        pos = notes.indexOf(editable);
        editable = null;

        if (weakReference.get() != null) {

            weakReference.get().showEditNoteDialog("","");
        }
    }

    public void onClickRemoveNote() {
        if (weakReference.get() != null) {

            if (notes.contains(editable)) {
                notes.remove(editable);
                weakReference.get().updateList();
                saveToServer();
            }
        }
    }

    public void onClickEditNote() {
        if (editable == null) return;

        MainView view = weakReference.get();
        if (view!= null) {
            view.showEditNoteDialog(editable.getTitle(),editable.getDescription());
        }
    }

    public void onSaveNote(String title, String description) {
        MainView view = weakReference.get();
        if (view != null) {
            if (editable != null){
                editable.setTitle(title);
                editable.setDescription(description);
            } else {
                notes.add(pos+1,new Note(title,description));
                view.updateList();
            }
            view.updateList();
            saveToServer();
        }
    }

    public void onClickEmptyList() {
        if (weakReference.get() != null) {
            weakReference.get().showActionDialog(ActionDialog.Mode.ADD);
        }
    }

    @Override
    public void onNotesDownload(List<Note> object) {
        MainView view = weakReference.get();
        if (object != null && object.isEmpty()) {
            onClickEmptyList();
        } else {
            notes.clear();
            notes.addAll(object);
            if (view!= null) {
                view.updateList();
            }
        }
        if (view!= null) {
            view.closeRefresh();
        }
    }

    @Override
    public void onErrorDownload(String message) {
        MainView view = weakReference.get();
        if (view!= null) {
            view.showMessasge(message);

            //notes.add(new Note("Where can I get some?","There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc."));

            view.updateList();
            view.closeRefresh();
        }

        onClickEmptyList();
    }

    @Override
    public void onNotesSaveComplete() {

    }

    @Override
    public void onNotesSaveError(String message) {
        if (weakReference.get() != null) {
            weakReference.get().showMessasge(message);
        }
    }

    private void saveToServer() {
        MainView view = weakReference.get();
        if (view == null) return;

        if (calcSize(notes)) {
            RestClient.INSTANCE.getApiService().saveNotes(notes).enqueue(new SaveNotesWrapper(this));
        } else {
            view.showMessasge("Too many notes");
        }
    }

    private static boolean calcSize(List<Note> notes) {
        long total = 0;
        for (Note note:notes) {
            total += note.size();
        }

        return total < MAX_SIZE;
    }
}
