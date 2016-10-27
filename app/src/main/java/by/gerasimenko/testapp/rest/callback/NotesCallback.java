package by.gerasimenko.testapp.rest.callback;

import java.util.List;

import by.gerasimenko.testapp.rest.objects.Note;

/**
 * Created 27.10.2016.
 */

public interface NotesCallback {

    void onNotesDownload(List<Note> object);

    void onErrorDownload(String message);
}
