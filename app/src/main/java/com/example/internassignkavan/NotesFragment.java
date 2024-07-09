package com.example.internassignkavan;

import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.internassignkavan.Note;


import java.util.*;

public class NotesFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Note> notesList = new ArrayList<>();
    private NotesAdapter mAdapter;
    private DatabaseHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("NotesFragment", "Creating NotesFragment view");

        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        db = new DatabaseHelper(getActivity());

        Button addNoteButton = view.findViewById(R.id.add_note_button);
        addNoteButton.setOnClickListener(v -> navigateToAddNoteFragment());

        notesList.addAll(db.getAllNotes());
        Log.d("NotesFragment", "Number of notes fetched: " + notesList.size());

        mAdapter = new NotesAdapter(getActivity(), notesList, note -> navigateToNoteDetailFragment(note));        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        return view;
    }
    private void navigateToAddNoteFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_view, new AddNoteFragment());
        transaction.addToBackStack(null); // Add this line

        transaction.commit();
    }
    private void navigateToNoteDetailFragment(Note note) {
        NoteDetailFragment noteDetailFragment = new NoteDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable("note", note);
        noteDetailFragment.setArguments(bundle);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_view, noteDetailFragment);
        transaction.addToBackStack(null); // Add this line

        transaction.commit();
    }
}