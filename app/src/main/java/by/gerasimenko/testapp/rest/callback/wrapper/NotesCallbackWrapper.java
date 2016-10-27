package by.gerasimenko.testapp.rest.callback.wrapper;

import java.util.List;

import by.gerasimenko.testapp.rest.callback.NotesCallback;
import by.gerasimenko.testapp.rest.objects.Note;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created 27.10.2016.
 */

public class NotesCallbackWrapper implements Callback<List<Note>> {

    private final NotesCallback notesCallback;

    public NotesCallbackWrapper(NotesCallback notesCallback) {
        this.notesCallback = notesCallback;
    }

    @Override
    public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {

        if (response.body()!= null) {
            notesCallback.onNotesDownload(response.body());
        }
    }

    @Override
    public void onFailure(Call<List<Note>> call, Throwable t) {

        notesCallback.onErrorDownload(t.getMessage());
    }
}
