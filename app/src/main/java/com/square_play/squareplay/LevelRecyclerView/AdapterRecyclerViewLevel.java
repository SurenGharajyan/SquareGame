package com.square_play.squareplay.LevelRecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.square_play.squareplay.FragmentPlay;
import com.square_play.squareplay.LevelFragment;
import com.square_play.squareplay.R;

import java.util.List;
import java.util.Observable;

/**
 * Created by USER on 19.10.2017.
 */

public class AdapterRecyclerViewLevel extends RecyclerView.Adapter<AdapterRecyclerViewLevel.Viewholder123> {
    private List<LevelModel> levelModels;
    private Context context;
    private RecyclerViewItemClickListener itemClickListener;

    public AdapterRecyclerViewLevel(List<LevelModel> levelModels,Context co,RecyclerViewItemClickListener itemClickListener) {
        this.levelModels = levelModels;
        context=co;
        this.itemClickListener = itemClickListener;
    }



    @Override
    public Viewholder123 onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Viewholder123(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item,parent,false));
    }

    @Override
    public void onBindViewHolder(final Viewholder123 holder, final int position) {
        holder.buttonsLevel.setText(levelModels.get(position).getNameNumber());

        holder.buttonsLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        itemClickListener.onItemClick(levelModels,position);

                Animation animScale = AnimationUtils.loadAnimation(context, R.anim.anim_scale_change);
                holder.buttonsLevel.startAnimation(animScale);
                    }
                });
            }


    @Override
    public int getItemCount() {
        return levelModels.size();
    }


    class Viewholder123 extends RecyclerView.ViewHolder {
        Button buttonsLevel;
        public Viewholder123(View itemView) {
            super(itemView);
            buttonsLevel = itemView.findViewById(R.id.btnlvl);
        }

    }


}
