package com.training.fragmentkostumapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.training.fragmentkostumapp.activity.AndroidActivity;
import com.training.fragmentkostumapp.data.AndroidImageAssets;
import com.training.fragmentkostumapp.fragment.BagianTubuhFragment;
import com.training.fragmentkostumapp.fragment.MasterListFragment;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnimageClickListener{
    boolean twopane;
    private AdView adView1;

    int kepalaindex,badanindex,kakiindex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adView1 = (AdView) findViewById(R.id.adView1);
        final AdRequest adRequest = new AdRequest.Builder().build();

        adView1.loadAd(adRequest);
        adView1.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.i("Ads", "onAdLoaded");
                adView1.loadAd(adRequest);

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.i("Ads", "onAdFailedToLoad");
                adView1.loadAd(adRequest);

            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                adView1.loadAd(adRequest);

                Log.i("Ads", "onAdOpened");
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                adView1.loadAd(adRequest);

                Log.i("Ads", "onAdLeftApplication");
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
                adView1.loadAd(adRequest);

                Log.i("Ads", "onAdClosed");
            }
        });
        if (findViewById(R.id.android_linear_layout)!= null){
            twopane=true;
            GridView gridkostum = (GridView)findViewById(R.id.images_grid_view);
            gridkostum.setNumColumns(2);

            Button show =(Button)findViewById(R.id.next);
            show.setVisibility(View.GONE);

            if (savedInstanceState == null) {
                //untuk kepala

                BagianTubuhFragment kepalafragment = new BagianTubuhFragment();
                //untuk menangkap bundle dari mainactivity
                int kepalaindex =getIntent().getIntExtra("kepalaIndex",0);
                kepalafragment.setIndexlistgambar(kepalaindex);
                kepalafragment.setDatagambar(AndroidImageAssets.getHeads());

                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction()
                        .add(R.id.kepala_container, kepalafragment)
                        .commit();
                //untuk badan
                BagianTubuhFragment badanfragment = new BagianTubuhFragment();
                int badanindex =getIntent().getIntExtra("badanIndex",0);
                badanfragment.setIndexlistgambar(badanindex);

                badanfragment.setDatagambar(AndroidImageAssets.getBodies());
                manager.beginTransaction()
                        .add(R.id.badan_container, badanfragment)
                        .commit();
                //untuk kaki
                BagianTubuhFragment kakifragment = new BagianTubuhFragment();
                int kakiindex =getIntent().getIntExtra("kakiIndex",0);
                kakifragment.setIndexlistgambar(kakiindex);

                kakifragment.setDatagambar(AndroidImageAssets.getLegs());
                manager.beginTransaction()
                        .add(R.id.kaki_container, kakifragment)
                        .commit();
            }
        }else{
            twopane=false;
        }

    }

    @Override
    public void onImageSelected(int position) {
        Toast.makeText(this, "pilihan anda di posisi : "+position, Toast.LENGTH_SHORT).show();

        int bodypartnumber=position/12;
        int listindex =position-12*bodypartnumber;

        if (twopane){
            BagianTubuhFragment tubuhfragment = new BagianTubuhFragment();

            switch (bodypartnumber){
                case 0:
                    tubuhfragment.setDatagambar(AndroidImageAssets.getHeads());
                    tubuhfragment.setIndexlistgambar(listindex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.kepala_container,tubuhfragment)
                            .commit();
                    break;
                case 1:
                    tubuhfragment.setDatagambar(AndroidImageAssets.getBodies());
                    tubuhfragment.setIndexlistgambar(listindex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.badan_container,tubuhfragment)
                            .commit();
                    break;
                case 2:
                    tubuhfragment.setDatagambar(AndroidImageAssets.getLegs());
                    tubuhfragment.setIndexlistgambar(listindex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.kaki_container,tubuhfragment)
                            .commit();
                    break;

            }
        }else{


        switch (bodypartnumber){
            case 0: kepalaindex=listindex;
                break;
            case 1: badanindex=listindex;
                break;
            case 2: kakiindex=listindex;
                break;
            default:break;
        }
        Bundle b = new Bundle();
        b.putInt("kepalaIndex",kepalaindex);
        b.putInt("badanIndex",badanindex);
        b.putInt("kakiIndex",kakiindex);
         final Intent intent = new Intent(this, AndroidActivity.class);
        intent.putExtras(b);

        Button show =(Button)findViewById(R.id.next);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        }
    }
}
