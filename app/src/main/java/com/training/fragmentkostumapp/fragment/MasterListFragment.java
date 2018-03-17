package com.training.fragmentkostumapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.training.fragmentkostumapp.R;
import com.training.fragmentkostumapp.adapter.MasterListAdapter;
import com.training.fragmentkostumapp.data.AndroidImageAssets;

/**
 * Created by Blackswan on 8/17/2017.
 */

public class MasterListFragment extends Fragment {

    OnimageClickListener callback;

    public interface OnimageClickListener{
        void onImageSelected(int position);
    }

    public MasterListFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            callback=(OnimageClickListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() +
            "must implement OnImageClickListener");

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
   View v =inflater.inflate(R.layout.fragment_master_list,container,false);
        GridView gridkostum = (GridView)v.findViewById(R.id.images_grid_view);

        MasterListAdapter adapter =new MasterListAdapter
                (getContext(), AndroidImageAssets.getAll());
        gridkostum.setAdapter(adapter);
        gridkostum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callback.onImageSelected(position);
            }
        });

        return v;
    }
}
