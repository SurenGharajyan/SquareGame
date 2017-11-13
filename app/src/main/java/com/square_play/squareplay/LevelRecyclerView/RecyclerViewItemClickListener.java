package com.square_play.squareplay.LevelRecyclerView;

import android.view.View;

import java.util.List;

/**
 * Created by USER on 21.10.2017.
 */

public interface RecyclerViewItemClickListener {

    void onItemClick(List<LevelModel> levelModels,int position);
}
