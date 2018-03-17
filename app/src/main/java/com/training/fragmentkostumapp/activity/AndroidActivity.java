package com.training.fragmentkostumapp.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.training.fragmentkostumapp.R;
import com.training.fragmentkostumapp.data.AndroidImageAssets;
import com.training.fragmentkostumapp.fragment.BagianTubuhFragment;

public class AndroidActivity extends AppCompatActivity {
    private AdView mAdView,adView1;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android);
        MobileAds.initialize(this, "ca-app-pub-9803172382468425~3760079323");
        mInterstitialAd = new InterstitialAd(this);

        mInterstitialAd.setAdUnitId("ca-app-pub-9803172382468425/7562635106");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                Log.i("Ads", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.i("Ads", "onAdFailedToLoad");
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
                Log.i("Ads", "onAdOpened");
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Log.i("Ads", "onAdLeftApplication");
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                Log.i("Ads", "onAdClosed");
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });

        //myToast("tunggu berapa saat");
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
          //  myToast("The interstitial wasn't loaded yet");
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }

        mAdView = (AdView) findViewById(R.id.adView);
        adView1 = (AdView) findViewById(R.id.adView1);
        final AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.i("Ads", "onAdLoaded");
                mAdView.loadAd(adRequest);

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.i("Ads", "onAdFailedToLoad");
                mAdView.loadAd(adRequest);

            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                mAdView.loadAd(adRequest);

                Log.i("Ads", "onAdOpened");
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                mAdView.loadAd(adRequest);

                Log.i("Ads", "onAdLeftApplication");
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
                mAdView.loadAd(adRequest);

                Log.i("Ads", "onAdClosed");
            }
        });
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
    }
}
