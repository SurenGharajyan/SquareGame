package com.square_play.squareplay;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.square_play.squareplay.DrawingSquares.LevelActivity;

public class FragmentPlay extends Fragment implements View.OnClickListener{
    ImageView playBtn,settingBtn,closeBtn,musicBtn,voiceBtn;
    boolean openSetting,soundOn;
    boolean musicPlay;
    static MediaPlayer musicMenu;

    AudioManager audioManager;
    @SuppressLint("ValidFragment")
    public FragmentPlay(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play,container,false);

        playBtn = view.findViewById(R.id.playbtn);
        playBtn.setOnClickListener(this);
        settingBtn = view.findViewById(R.id.settingbtn);
        settingBtn.setOnClickListener(this);
        closeBtn = view.findViewById(R.id.closebtn);
        closeBtn.setOnClickListener(this);
        musicBtn = view.findViewById(R.id.musicbtn);
        musicBtn.setOnClickListener(this);
        voiceBtn = view.findViewById(R.id.voicebtn);
        voiceBtn.setOnClickListener(this);
        openSetting = false;
        soundOn = true;
        musicPlay=SharedPrefRead();

        if (!PlaySettingActivity.musicFirstTimePlayed) {
        musicMenu = MediaPlayer.create(getActivity(), R.raw.music_menu);
        musicMenu.setLooping(true);
            PlaySettingActivity.musicFirstTimePlayed=true;
        }

            audioManager = (AudioManager) getActivity().getSystemService(getActivity().AUDIO_SERVICE);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        imageViewAnimation(settingBtn);
        imageViewAnimation(playBtn);
        imageViewAnimation(closeBtn);

    }

    private void imageViewAnimation(ImageView im) {
        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(im, "scaleX", 0, 1);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(im, "scaleY", 0, 1);
        scaleDownX.setDuration(1000);
        scaleDownY.setDuration(1000);

        AnimatorSet scaleDown = new AnimatorSet();
        scaleDown.play(scaleDownX).with(scaleDownY);
        scaleDown.start();
    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.voicebtn ||view.getId()==R.id.musicbtn
                || view.getId()==R.id.closebtn || view.getId()==R.id.settingbtn || view.getId()==R.id.playbtn) {
            Animation animScale = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_scale_change);
            view.startAnimation(animScale);

        }
        switch (view.getId()) {
            case R.id.playbtn:
                LevelFragment fragmentlev = new LevelFragment();
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.anim_fragment_left,R.anim.anim_fragment_right)
                        .replace(R.id.some_container, fragmentlev)
                        .commit();

                SharedPrefAdd(musicPlay);
                break;
            case R.id.settingbtn:
                if(!openSetting) {
                    if (!musicPlay) {
                        musicBtn.setImageResource(R.drawable.musicoffbtn);
                    }else  {
                        musicBtn.setImageResource(R.drawable.musiconbtn);
                    }
                    musicBtn.setVisibility(View.VISIBLE);
                        voiceBtn.setVisibility(View.VISIBLE);
                    ObjectAnimatorForSettingsOpen(-160,172);
                }else {
                    ObjectAnimatorForSettingsClose(-160,172);
                }
                openSetting = !openSetting;
                break;
            case R.id.closebtn:
                getActivity().finish();
                break;
            case R.id.musicbtn:
                if (musicPlay) {
                    musicBtn.setImageResource(R.drawable.musicoffbtn);
                    musicMenu.pause();
                }else {
                    musicBtn.setImageResource(R.drawable.musiconbtn);
                    musicMenu.start();
                }
                musicPlay = !musicPlay;
                SharedPrefAdd(musicPlay);
                break;
            case R.id.voicebtn:
                if(soundOn) {
//                    TODO This place must be some code of sound on
                    voiceBtn.setAlpha(0.5f);
                    audioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, 0, 0);
                }else {
                    //TODO This place must be some code of sound off
                    voiceBtn.setAlpha(1f);
                    audioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, 10, 0);
                }
                soundOn = !soundOn;
                break;
        }
    }

    private void ObjectAnimatorForSettingsOpen(int movetoY,int movetoX) {
        ObjectAnimator tranparentYM = ObjectAnimator.ofFloat(musicBtn, "translationY", 0, movetoY);
        ObjectAnimator tranparentXV = ObjectAnimator.ofFloat(voiceBtn, "translationX", 0, movetoX);
        tranparentYM.setDuration(500);
        tranparentXV.setDuration(500);
        tranparentYM.start();
        tranparentXV.start();
    }

    private void ObjectAnimatorForSettingsClose(int movetoY,int movetoX){

        ObjectAnimator tranparentYM = ObjectAnimator.ofFloat(musicBtn, "translationY", movetoY, 0);
        ObjectAnimator tranparentXV = ObjectAnimator.ofFloat(voiceBtn, "translationX", movetoX, 0);
        tranparentYM.setDuration(500);
        tranparentXV.setDuration(500);
        tranparentYM.start();
        tranparentXV.start();
        tranparentXV.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                musicBtn.setVisibility(View.GONE);
                voiceBtn.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }


    private void SharedPrefAdd(boolean t) {
        SharedPreferences sharedP = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedP.edit();
        editor.putBoolean("KeyBoolean",t);
        editor.apply();
    }

    private boolean SharedPrefRead() {
        SharedPreferences sharedP = getActivity().getPreferences(Context.MODE_PRIVATE);
        return sharedP.getBoolean("KeyBoolean",false);
    }
}
