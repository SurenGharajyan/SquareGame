package com.square_play.squareplay.DrawingSquares;

/**
 * Created by USER on 26.10.2017.
 */

public class SquareSettings {
    int squareWidth,squareHeight,squareColor;

    public SquareSettings(int squareWidth, int squareHeight, int squareColor) {
        this.squareWidth = squareWidth;
        this.squareHeight = squareHeight;
        this.squareColor = squareColor;
    }

    public int getSquareWidth() {
        return squareWidth;
    }

    public void setSquareWidth(int squareWidth) {
        this.squareWidth = squareWidth;
    }

    public int getSquareHeight() {
        return squareHeight;
    }

    public void setSquareHeight(int squareHeight) {
        this.squareHeight = squareHeight;
    }

    public int getSquareColor() {
        return squareColor;
    }

    public void setSquareColor(int squareColor) {
        this.squareColor = squareColor;
    }
}
