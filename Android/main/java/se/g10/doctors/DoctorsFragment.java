package se.g10.doctors;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import se.g10.DataManager;
import se.g10.R;

public class DoctorsFragment extends Fragment {
    private ListView mListView;
    public DoctorAdapter myAdapter;

    public void refreshList(){
        myAdapter = new DoctorAdapter(DataManager.doctors, getContext());
        myAdapter.notifyDataSetChanged();
        mListView.setAdapter(myAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.general_list, container, false);

        Button addButton=view.findViewById(R.id.add);
        addButton.setText("add a new doctor");
        addButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
            }
        });

        Button refreshButton=view.findViewById(R.id.refresh);
        refreshButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                DataManager dm=new DataManager();
                if(dm.running==1)
                    return;//prevent dead lock
                dm.running=1;
                dm.start();
                refreshList();
            }
        });

        mListView = view.findViewById(R.id.listview);
        refreshList();

        return view;
    }
}
