package com.example.gb_2_07_fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainFragment extends Fragment {

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true); // пункты из фрагмента добавятся в главное меню
        initPopupMenu(view);
        return view;
    }

    private void initPopupMenu(View view) {
        // Повесим меню на текст. Один пункт меню скроем и программно добавим другой пункт меню
        TextView text = view.findViewById(R.id.textView);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = requireActivity();
                PopupMenu popupMenu = new PopupMenu(activity, v);
                activity.getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());
                Menu menu = popupMenu.getMenu();
                menu.findItem(R.id.item2_popup).setVisible(false); // скрываем
                menu.add(0, 123456, 12, R.string.new_menu_item_added); // добавляем новый
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        switch (id) {
                            case R.id.item1_popup:
                                Toast.makeText(getContext(), "Chosen popup item 1", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.item2_popup:
                                Toast.makeText(getContext(), "Chosen popup item 2", Toast.LENGTH_SHORT).show();
                                return true;
                            case 123456:
                                Toast.makeText(getContext(), "Chosen new item added", Toast.LENGTH_SHORT).show();
                                return true;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_fragment, menu);
        //super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            Toast.makeText(getContext(), "Chosen add", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}