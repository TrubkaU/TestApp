package by.gerasimenko.testapp.rest.objects;

/**
 * Created 27.10.2016.
 */

public class Note {

    String title;
    String description;

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int size() {
        int size=0;
        if (title != null) {
            size += title.getBytes().length;
        }
        if (description != null) {
            size += description.getBytes().length;
        }

        return size;
    }
}
