package com.square_play.squareplay.DrawingSquares;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.square_play.squareplay.R;

public class FORDELETELevelChoosedFragment extends Fragment {
        SquaresDraw DSquare;
        @SuppressLint("ValidFragment")
        FORDELETELevelChoosedFragment() {

        }
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fordelete_fragment_level_choosed,container,false);
          //  DSquare = view.findViewById(R.id.drawsq);
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:

                            Toast.makeText(getActivity(), "Okay finder is down" , Toast.LENGTH_SHORT).show();
                            break;
                        case MotionEvent.ACTION_UP:
                            Toast.makeText(getActivity(), "Okay finder is up", Toast.LENGTH_SHORT).show();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            Toast.makeText(getActivity(), "Okay finder is move", Toast.LENGTH_SHORT).show();
                            break;
                        case MotionEvent.ACTION_BUTTON_PRESS:
                            Toast.makeText(getActivity(), "Okay finder is button Pressed", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    return true;
                }
            });

            return view;
        }



    }
