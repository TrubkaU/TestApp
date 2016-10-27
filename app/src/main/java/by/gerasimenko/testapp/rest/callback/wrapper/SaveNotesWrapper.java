package by.gerasimenko.testapp.rest.callback.wrapper;

import by.gerasimenko.testapp.rest.callback.SaveNotesCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created 28.10.2016.
 */

public class SaveNotesWrapper implements Callback<Void> {
    private final SaveNotesCallback saveNotesCallback;

    public SaveNotesWrapper(SaveNotesCallback saveNotesCallback) {
        this.saveNotesCallback = saveNotesCallback;
    }

    @Override
    public void onResponse(Call<Void> call, Response<Void> response) {

        saveNotesCallback.onNotesSaveComplete();
    }

    @Override
    public void onFailure(Call<Void> call, Throwable t) {
        saveNotesCallback.onNotesSaveError(t.getMessage());
    }
}
