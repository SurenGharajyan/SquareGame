package com.square_play.squareplay;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.ImageView;
//import android.widget.Toast;
//import android.animation.Animator;
//import android.animation.AnimatorSet;
//import android.animation.ObjectAnimator;
//import android.app.FragmentManager;
//import android.app.FragmentTransaction;
//import android.media.MediaPlayer;
//import android.os.PersistableBundle;
//import android.support.v4.app.Fragment;
public class PlaySettingActivity extends AppCompatActivity {
    FragmentPlay fragmentPlay;
    static boolean musicFirstTimePlayed = false;
    SharedPreferences prefs = null;
    static int LevelChooseNumber;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_setting);
        fragmentPlay = new FragmentPlay();
        getFragmentManager().beginTransaction()
                .replace(R.id.some_container, fragmentPlay)
                .commit();
        fragmentPlay.musicPlay=false;
        prefs = getSharedPreferences("com.mycompany.myAppName", MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (prefs.getBoolean("firstrun", true)) {
            prefs.edit().putBoolean("firstrun", false).apply();
        }
//        FragmentPlay.musicMenu.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        FragmentPlay.musicMenu.stop();
    }
}