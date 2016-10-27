package by.gerasimenko.testapp.rest.callback;

/**
 * Created 28.10.2016.
 */

public interface SaveNotesCallback {

    void onNotesSaveComplete();

    void onNotesSaveError(String message);
}
