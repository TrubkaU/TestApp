package by.gerasimenko.testapp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.gerasimenko.testapp.R;
import by.gerasimenko.testapp.rest.objects.Note;
import by.gerasimenko.testapp.ui.interfaces.OnNoteListener;

/**
 * Created 27.10.2016.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.Holder> {

    private final List<Note> objects;
    private final OnNoteListener onNoteListener;

    public NoteAdapter(List<Note> objects, OnNoteListener onNoteListener) {
        this.objects = objects;
        this.onNoteListener = onNoteListener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder( LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder,int position) {
        final Note note = objects.get(position);

        holder.tvTitle.setText(note.getTitle());
        holder.tvDescription.setText(note.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNoteListener.onClick(note);
            }
        });
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    static class Holder extends  RecyclerView.ViewHolder {

        @BindView(R.id.item_note_title)
        TextView tvTitle;
        @BindView(R.id.item_note_description)
        TextView tvDescription;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
