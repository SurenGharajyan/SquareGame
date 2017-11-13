package com.square_play.squareplay;

import android.annotation.SuppressLint;
//import android.content.Context;
//import android.net.Uri;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import com.square_play.squareplay.DrawingSquares.LevelActivity;
import com.square_play.squareplay.LevelRecyclerView.AdapterRecyclerViewLevel;
import com.square_play.squareplay.LevelRecyclerView.LevelModel;
import com.square_play.squareplay.LevelRecyclerView.RecyclerViewItemClickListener;

import java.util.ArrayList;
import java.util.List;


public class LevelFragment extends Fragment implements RecyclerViewItemClickListener{
    
    RecyclerView recyclerViewLevel;
    RecyclerView.LayoutManager layoutManager;
    List<LevelModel> levelModel;
    ImageButton Returnbtn;
    @SuppressLint("ValidFragment")
    LevelFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_level, container, false);
        Returnbtn = (ImageButton) view.findViewById(R.id.return_btn);
        Returnbtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                Animation animScale = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_scale_change);
                Returnbtn.startAnimation(animScale);
                FragmentPlay fragmplay = new FragmentPlay();
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.anim_fragment_left,R.anim.anim_fragment_right)
                        .replace(R.id.some_container, fragmplay)
                        .commit();
            }
        });
        recyclerViewLevel = (RecyclerView) view.findViewById(R.id.recycler_view_button);
        recyclerViewLevel.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getActivity(), 5);
        recyclerViewLevel.setLayoutManager(layoutManager);
        AdapterRecyclerViewLevel adapterRec = new AdapterRecyclerViewLevel(AdapterAdd(),getActivity(),this);
        recyclerViewLevel.setAdapter(adapterRec);
        return view;
    }

    private List<LevelModel> AdapterAdd() {
        levelModel = new ArrayList<>();
        for (int i = 1; i <=10 ; i++) {
            levelModel.add(new LevelModel(i + ""));
        }
        return levelModel;
    }





    @SuppressLint("ResourceType")
    @Override
    public void onItemClick(List<LevelModel> levelModels,int position) {
        PlaySettingActivity.LevelChooseNumber=position+1;
//        FORDELETELevelChoosedFragment lF=new FORDELETELevelChoosedFragment();
//        getFragmentManager().beginTransaction()
//                .setCustomAnimations(R.anim.anim_fragment_left,R.anim.anim_fragment_right)
//                .replace(R.id.some_container, lF)
//                .commit();
        Intent intent = new Intent(getActivity(), LevelActivity.class);
        intent.putExtra("Level",PlaySettingActivity.LevelChooseNumber);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}
