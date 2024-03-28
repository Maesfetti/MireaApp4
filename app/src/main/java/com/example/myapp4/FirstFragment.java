package com.example.myapp4;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
public class FirstFragment extends Fragment {

    public FirstFragment() {
        super(R.layout.fragment_first);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        view.findViewById(R.id.toListFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container_view, new ListFragment1())
                        .addToBackStack(null).commit();
            }
        });

        view.findViewById(R.id.toRecyclerFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container_view, new RecyclerFragment())
                        .addToBackStack(null).commit();
            }
        });

        return view;
    }
}