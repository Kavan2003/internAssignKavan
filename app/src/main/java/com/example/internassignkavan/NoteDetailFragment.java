package com.example.internassignkavan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class NoteDetailFragment extends Fragment {
    private Note note;
    private EditText title, content;
    private Button update, delete;
    private DatabaseHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_detail, container, false);

        title = view.findViewById(R.id.title);
        content = view.findViewById(R.id.content);
        update = view.findViewById(R.id.update);
        delete = view.findViewById(R.id.delete);

        db = new DatabaseHelper(getActivity());

         note = getArguments().getParcelable("note");


        update.setOnClickListener(v -> {
            note.setTitle(title.getText().toString());
            note.setContent(content.getText().toString());
            db.updateNote(note);
            getFragmentManager().popBackStack();

        });

        delete.setOnClickListener(v -> {
            db.deleteNote(note);
            getFragmentManager().popBackStack();

        });
        if (note != null) {
            title.setText(note.getTitle());
            content.setText(note.getContent());
        }
        return view;
    }


}