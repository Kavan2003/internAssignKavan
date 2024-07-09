package com.example.internassignkavan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class AddNoteFragment extends Fragment {
    private EditText title, content;
    private Button save;
    private DatabaseHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_note, container, false);

        title = view.findViewById(R.id.title);
        content = view.findViewById(R.id.content);
        save = view.findViewById(R.id.save);

        db = new DatabaseHelper(getActivity());

        save.setOnClickListener(v -> {
            String noteTitle = title.getText().toString();
            String noteContent = content.getText().toString();
            long currentTimeMillis = System.currentTimeMillis();
            int uniqueId = (int) currentTimeMillis;

            Note note = new Note(uniqueId, "","","");
            note.setTitle(noteTitle);
            note.setContent(noteContent);
//(String title, String content, String username)
            db.insertNote(note.getTitle(), note.getContent(), note.getUsername());

            navigateToNotesFragment();
        });

        return view;
    }

    private void navigateToNotesFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_view, new NotesFragment());
        transaction.commit();
    }
}