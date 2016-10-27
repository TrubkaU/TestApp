package by.gerasimenko.testapp.rest;

import java.util.List;

import by.gerasimenko.testapp.rest.objects.Note;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created 27.10.2016.
 */

public interface ApiService {

    String KEY = "mvfH344";

    @GET(KEY)
    Call<List<Note>> getNotes();

    @POST(KEY)
    Call<Void> saveNotes(@Body List<Note> notes);
}
