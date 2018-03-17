package com.training.fragmentkostumapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.training.fragmentkostumapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Blackswan on 8/17/2017.
 */

public class BagianTubuhFragment extends Fragment {

    private static final String LOG ="BagianTubuhFragment";
    public  static final String GAMBAR_LIST="datagambar";
    public static final String INDEX_LIST_GAMBAR="indexgambar";


    List<Integer> datagambar;
    int indexlistgambar;

    public BagianTubuhFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (savedInstanceState!=null){
            datagambar=savedInstanceState.getIntegerArrayList(GAMBAR_LIST);
            indexlistgambar=savedInstanceState.getInt(INDEX_LIST_GAMBAR);
        }

        View v = inflater.inflate(R.layout.fragment_bagian_tubuh,container,false);
        final ImageView gambartubuh =(ImageView)v.findViewById(R.id.img_bagiantubuh);

        if (datagambar!=null){
            gambartubuh.setImageResource(datagambar.get(indexlistgambar));
            gambartubuh.setOnClickListener(new View.OnClickListener() {
                                               @Override
                  public void onClick(View v) {
                  if (indexlistgambar<datagambar.size()-1){
                      indexlistgambar++;
                  }else{
                      indexlistgambar=0;
                  }
                  gambartubuh.setImageResource(datagambar.get(indexlistgambar));
                  }
                  }
            );

        }else{
            Log.v(LOG,"fragment ini tidak ada data /null didalam data gambar");

        }
        return v;
    }

    public void setDatagambar(List<Integer> datagambar) {
        this.datagambar = datagambar;
    }

    public void setIndexlistgambar(int indexlistgambar) {
        this.indexlistgambar = indexlistgambar;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
   outState.putIntegerArrayList(GAMBAR_LIST,(ArrayList<Integer>) datagambar);
        outState.putInt(INDEX_LIST_GAMBAR,indexlistgambar);
    }
}
