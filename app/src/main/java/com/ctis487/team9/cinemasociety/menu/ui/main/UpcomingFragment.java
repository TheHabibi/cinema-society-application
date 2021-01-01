package com.ctis487.team9.cinemasociety.menu.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ctis487.team9.cinemasociety.R;
import com.ctis487.team9.cinemasociety.menu.UpcomingRecycle;


import static com.ctis487.team9.cinemasociety.MainActivity.upcomingList;
import static com.ctis487.team9.cinemasociety.menu.MenuActivity.eventsListRecycle;


/**
 * A placeholder fragment containing a simple view.
 */
public class UpcomingFragment extends Fragment {

    private RecyclerView recyclerView;
    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static UpcomingFragment newInstance(int index) {
        UpcomingFragment fragment = new UpcomingFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);


    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_upcoming, container, false);


        recyclerView = root.findViewById(R.id.upcomingFragment);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        UpcomingRecycle adapter = new UpcomingRecycle(root.getContext(),upcomingList);
        recyclerView.setAdapter(adapter);

        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }
}