package com.example.internassignkavan;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.*;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    private Context context;
    private List<Note> notesList;
    // Add this interface
    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    private OnItemClickListener listener;

    // Modify your constructor to accept the listener
    public NotesAdapter(Context context, List<Note> notesList, OnItemClickListener listener) {
        this.context = context;
        this.notesList = notesList;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, content;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            content = view.findViewById(R.id.content);
            view.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(notesList.get(position));
                }
                Log.d("NotesAdapter", "Clicked on note with ID: " + notesList.get(position).getId());

            });
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Note note = notesList.get(position);
        holder.title.setText(note.getTitle());
        holder.content.setText(note.getContent());

        Log.d("NotesAdapter", "Binding note with ID: " + note.getId());

    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }
}