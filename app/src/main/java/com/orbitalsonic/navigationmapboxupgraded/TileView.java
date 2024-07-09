package com.orbitalsonic.navigationmapboxupgraded;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ScaleGestureDetector;
import android.view.ViewGroup;
import android.widget.GridLayout;

import androidx.appcompat.widget.AppCompatImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TileView extends AppCompatImageView {

    private boolean walkable;
    private boolean isPath;
    private int row;
    private int col;
    private double gCost;
    private double hCost;
    private TileView parentTile;
    private int tileType; // Added tileType variable
    private int screenWidth;
    private int screenHeight;


    public TileView(Context context) {
        super(context);
        init(context);
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        layoutParams.width = getResources().getDimensionPixelSize(R.dimen.grid_width); // Define width
        layoutParams.height = getResources().getDimensionPixelSize(R.dimen.grid_height); // Define height
        setLayoutParams(layoutParams);

    }

    public TileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public TileView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public int getTileType() {
        return tileType;
    }
    private void init(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;

        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        layoutParams.width = screenWidth * 2;
        layoutParams.height = screenHeight * 2;
        setLayoutParams(layoutParams);
    }

    public void setTileType(int type, int row, int col) {
        this.tileType = type;
        this.row = row;
        this.col = col;

        switch (type) {
            case 0: // Floor
                setImageResource(R.drawable.graytile);
                walkable = true;
                setClickable(true);
                break;
            case 1: // grass
                setImageResource(R.drawable.grasstile);
                walkable = false;
                setClickable(false);
                break;

            case 2: // white tile
                setImageResource(R.drawable.whitetile);
                walkable = false;
                setClickable(false);
                break;


            case 3: // Wall
                setImageResource(R.drawable.walk1);
                setClickable(true);
                walkable = true;
                break;


            case 4: // Wall
                setImageResource(R.drawable.walk2);
                setClickable(true);
                walkable = true;
                break;

            case 5: // Wall
                setImageResource(R.drawable.walk3);
                setClickable(true);
                walkable = true;
                break;

            case 6: // Wall
                setImageResource(R.drawable.walk4);
                walkable = true;
                setClickable(true);
                break;

            case 7: // Wall
                setImageResource(R.drawable.walk5);
                walkable = true;
                setClickable(true);
                break;

            case 8: // Wall
                setImageResource(R.drawable.walk6);
                walkable = true;
                setClickable(true);
                break;

            case 9: // Wall
                setImageResource(R.drawable.walk7);
                walkable = true;
                setClickable(true);
                break;
            case 10: // Wall
                setImageResource(R.drawable.walk8);
                walkable = true;
                setClickable(true);
                break;


            case 11: // Wall
                setImageResource(R.drawable.walk9);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 12: // Wall
                setImageResource(R.drawable.walk10);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 13: // Wall
                setImageResource(R.drawable.walk11);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not walk12.");
                break;

            case 14: // Wall
                setImageResource(R.drawable.walk12);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 15: // Wall
                setImageResource(R.drawable.walk13);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 16: // Wall
                setImageResource(R.drawable.walk14);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 17: // Wall
                setImageResource(R.drawable.walk15);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 18: // Wall
                setImageResource(R.drawable.walk16);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 19: // Wall
                setImageResource(R.drawable.walk17);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;


            case 20: // Wall
                setImageResource(R.drawable.walk18);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 21: // Wall
                setImageResource(R.drawable.walk19);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;


            case 22: // Wall
                setImageResource(R.drawable.walk20);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 23: // Wall
                setImageResource(R.drawable.walk21);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 24: // Wall
                setImageResource(R.drawable.walk22);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;


            case 25: // Wall
                setImageResource(R.drawable.walk23);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 26: // Wall
                setImageResource(R.drawable.walk24);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 27: // Wall
                setImageResource(R.drawable.walk25);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;


            case 28: // Wall
                setImageResource(R.drawable.walk26);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;


            case 29: // Wall
                setImageResource(R.drawable.walk27);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 30: // Wall
                setImageResource(R.drawable.walk28);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 31: // Wall
                setImageResource(R.drawable.walk29);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 32: // Wall
                setImageResource(R.drawable.walk30);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;


            case 33: // Wall
                setImageResource(R.drawable.walk31);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 34: // Wall
                setImageResource(R.drawable.walk32);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;


            case 35: // Wall
                setImageResource(R.drawable.walk33);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;


            case 36: // Wall
                setImageResource(R.drawable.walk34);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 37: // Wall
                setImageResource(R.drawable.walk35);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 38: // Wall
                setImageResource(R.drawable.walk36);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 39: // Wall
                setImageResource(R.drawable.walk37);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 40: // Wall
                setImageResource(R.drawable.walk38);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 41: // Wall
                setImageResource(R.drawable.walk39);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 42: // Wall
                setImageResource(R.drawable.walk40);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 43: // Wall
                setImageResource(R.drawable.walk41);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 44: // Wall
                setImageResource(R.drawable.walk42);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 45: // Wall
                setImageResource(R.drawable.walk43);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 46: // Wall
                setImageResource(R.drawable.walk44);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 47: // Wall
                setImageResource(R.drawable.walk45);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 48: // Wall
                setImageResource(R.drawable.walk46);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 49: // Wall
                setImageResource(R.drawable.walk47);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 50: // Wall
                setImageResource(R.drawable.walk48);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 51: // Wall
                setImageResource(R.drawable.walk49);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 52: // Wall
                setImageResource(R.drawable.walk50);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 53: // Wall
                setImageResource(R.drawable.walk51);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 54: // Wall
                setImageResource(R.drawable.walk52);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 55: // Wall
                setImageResource(R.drawable.walk53);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 56: // Wall
                setImageResource(R.drawable.walk54);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 57: // Wall
                setImageResource(R.drawable.walk55);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 58: // Wall
                setImageResource(R.drawable.walk56);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 59: // Wall
                setImageResource(R.drawable.walk57);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 60: // Wall
                setImageResource(R.drawable.walk58);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 61: // Wall
                setImageResource(R.drawable.walk59);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 62: // Wall
                setImageResource(R.drawable.walk60);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;


            case 63: // Wall
                setImageResource(R.drawable.walk61);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
                case 64: // Wall
                setImageResource(R.drawable.walk62);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 65: // Wall
                setImageResource(R.drawable.walk63);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 66: // Wall
                setImageResource(R.drawable.walk64);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 67: // Wall
                setImageResource(R.drawable.walk65);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 68: // Wall
                setImageResource(R.drawable.walk66);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 69: // Wall
                setImageResource(R.drawable.walk67);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 70: // Wall
                setImageResource(R.drawable.walk68);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 71: // Wall
                setImageResource(R.drawable.walk69);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 72: // Wall
                setImageResource(R.drawable.walk70);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 73: // Wall
                setImageResource(R.drawable.walk71);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 74: // Wall
                setImageResource(R.drawable.walk72);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 75: // Wall
                setImageResource(R.drawable.walk73);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 76: // Wall
                setImageResource(R.drawable.walk74);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 77: // Wall
                setImageResource(R.drawable.walk75);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 78: // Wall
                setImageResource(R.drawable.walk76);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 79: // Wall
                setImageResource(R.drawable.walk77);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 80: // Wall
                setImageResource(R.drawable.walk78);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 81: // Wall
                setImageResource(R.drawable.walk79);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 82: // Wall
                setImageResource(R.drawable.walk80);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 83: // Wall
                setImageResource(R.drawable.walk81);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 84: // Wall
                setImageResource(R.drawable.walk82);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 85: // Wall
                setImageResource(R.drawable.walk83);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 86: // Wall
                setImageResource(R.drawable.walk84);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 87: // Wall
                setImageResource(R.drawable.walk85);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 88: // Wall
                setImageResource(R.drawable.walk86);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 89: // Wall
                setImageResource(R.drawable.walk87);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 90: // Wall
                setImageResource(R.drawable.walk88);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 91: // Wall
                setImageResource(R.drawable.walk89);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 92: // Wall
                setImageResource(R.drawable.walk90);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 93: // Wall
                setImageResource(R.drawable.walk91);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 94: // Wall
                setImageResource(R.drawable.walk92);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 95: // Wall
                setImageResource(R.drawable.walk93);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 96: // Wall
                setImageResource(R.drawable.walk94);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 97: // Wall
                setImageResource(R.drawable.walk95);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 98: // Wall
                setImageResource(R.drawable.walk96);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 99: // Wall
                setImageResource(R.drawable.walk97);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 100: // Wall
                setImageResource(R.drawable.walk98);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 101: // Wall
                setImageResource(R.drawable.walk99);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 102: // Wall
                setImageResource(R.drawable.floor1);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 103: // Wall
                setImageResource(R.drawable.floor2);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 104: // Wall
                setImageResource(R.drawable.floor3);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 105: // Wall
                setImageResource(R.drawable.floor4);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 106: // Wall
                setImageResource(R.drawable.floor5);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 107: // Wall
                setImageResource(R.drawable.floor6);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 108: // Wall
                setImageResource(R.drawable.floor7);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 109: // Wall
                setImageResource(R.drawable.floor8);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 110: // Wall
                setImageResource(R.drawable.floor9);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 111: // Wall
                setImageResource(R.drawable.floor10);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 112: // Wall
                setImageResource(R.drawable.floor11);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 113: // Wall
                setImageResource(R.drawable.floor12);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 114: // Wall
                setImageResource(R.drawable.floor13);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 115: // Wall
                setImageResource(R.drawable.floor14);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 116: // Wall
                setImageResource(R.drawable.floor15);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 117: // Wall
                setImageResource(R.drawable.floor16);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 118: // Wall
                setImageResource(R.drawable.floor17);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 119: // Wall
                setImageResource(R.drawable.floor18);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 120: // Wall
                setImageResource(R.drawable.floor19);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 121: // Wall
                setImageResource(R.drawable.floor20);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 122: // Wall
                setImageResource(R.drawable.floor21);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 123: // Wall
                setImageResource(R.drawable.floor22);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 124: // Wall
                setImageResource(R.drawable.floor23);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 125: // Wall
                setImageResource(R.drawable.floor24);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 126: // Wall
                setImageResource(R.drawable.floor25);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 127: // Wall
                setImageResource(R.drawable.floor26);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 128: // Wall
                setImageResource(R.drawable.floor27);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 129: // Wall
                setImageResource(R.drawable.floor28);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 130: // Wall
                setImageResource(R.drawable.floor29);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 131: // Wall
                setImageResource(R.drawable.floor30);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 132: // Wall
                setImageResource(R.drawable.floor31);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 133: // Wall
                setImageResource(R.drawable.floor32);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 134: // Wall
                setImageResource(R.drawable.floor33);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 135: // Wall
                setImageResource(R.drawable.floor34);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 136: // Wall
                setImageResource(R.drawable.floor35);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 137: // Wall
                setImageResource(R.drawable.water1);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 138: // Wall
                setImageResource(R.drawable.water2);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 139: // Wall
                setImageResource(R.drawable.mid);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 140: // Wall
                setImageResource(R.drawable.mid1);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 141: // Wall
                setImageResource(R.drawable.mid2);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 142: // Wall
                setImageResource(R.drawable.mid3);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 143: // Wall
                setImageResource(R.drawable.mid4);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 144: // Wall
                setImageResource(R.drawable.mid5);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 145: // Wall
                setImageResource(R.drawable.mid6);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 146: // Wall
                setImageResource(R.drawable.mid7);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 147: // Wall
                setImageResource(R.drawable.mid8);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 148: // Wall
                setImageResource(R.drawable.tree);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 149: // Wall
                setImageResource(R.drawable.tree1);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 150: // Wall
                setImageResource(R.drawable.tree2);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 151: // Wall
                setImageResource(R.drawable.tree3);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 152: // Wall
                setImageResource(R.drawable.stage);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 153: // Wall
                setImageResource(R.drawable.stage1);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 154: // Wall
                setImageResource(R.drawable.stage2);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 155: // Wall
                setImageResource(R.drawable.ch);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 156: // Wall
                setImageResource(R.drawable.ch1);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 157: // Wall
                setImageResource(R.drawable.ch2);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 158: // Wall
                setImageResource(R.drawable.ch3);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 159: // Wall
                setImageResource(R.drawable.ch4);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 160: // Wall
                setImageResource(R.drawable.ch5);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 161: // Wall
                setImageResource(R.drawable.ch6);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 162: // Wall
                setImageResource(R.drawable.ch7);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 163: // Wall
                setImageResource(R.drawable.ch8);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 164: // Wall
                setImageResource(R.drawable.ch9);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 165: // Wall
                setImageResource(R.drawable.ch10);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 167: // Wall
                setImageResource(R.drawable.ch11);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 168: // Wall
                setImageResource(R.drawable.ch12);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 169: // Wall
                setImageResource(R.drawable.ch13);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 170: // Wall
                setImageResource(R.drawable.ch14);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 171: // Wall
                setImageResource(R.drawable.ch15);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 172: // Wall
                setImageResource(R.drawable.ch16);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 173: // Wall
                setImageResource(R.drawable.ch17);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 174: // Wall
                setImageResource(R.drawable.ch18);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 175: // Wall
                setImageResource(R.drawable.ch19);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 176: // Wall
                setImageResource(R.drawable.ch20);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 177: // Wall
                setImageResource(R.drawable.ch21);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 178: // Wall
                setImageResource(R.drawable.ch22);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 179: // Wall
                setImageResource(R.drawable.ch23);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 180: // Wall
                setImageResource(R.drawable.ch24);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 181: // Wall
                setImageResource(R.drawable.ch25);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 182: // Wall
                setImageResource(R.drawable.ch26);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 183: // Wall
                setImageResource(R.drawable.ch27);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 184: // Wall
                setImageResource(R.drawable.ch28);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 185: // Wall
                setImageResource(R.drawable.ch29);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 186: // Wall
                setImageResource(R.drawable.ch30);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 187: // Wall
                setImageResource(R.drawable.ch31);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 188: // Wall
                setImageResource(R.drawable.ch32);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 189: // Wall
                setImageResource(R.drawable.ch33);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 190: // Wall
                setImageResource(R.drawable.ch34);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 191: // Wall
                setImageResource(R.drawable.ch35);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 192: // Wall
                setImageResource(R.drawable.ch36);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 193: // Wall
                setImageResource(R.drawable.ch37);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 194: // Wall
                setImageResource(R.drawable.ch38);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 195: // Wall
                setImageResource(R.drawable.ch39);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 196: // Wall
                setImageResource(R.drawable.ch40);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 197: // Wall
                setImageResource(R.drawable.ch41);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 198: // Wall
                setImageResource(R.drawable.ch42);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 199: // Wall
                setImageResource(R.drawable.ch43);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 200: // Wall
                setImageResource(R.drawable.ch44);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 201: // Wall
                setImageResource(R.drawable.ch45);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 202: // Wall
                setImageResource(R.drawable.ch46);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 203: // Wall
                setImageResource(R.drawable.ch47);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 204: // Wall
                setImageResource(R.drawable.ch48);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 205: // Wall
                setImageResource(R.drawable.ch49);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 206: // Wall
                setImageResource(R.drawable.ch50);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 207: // Wall
                setImageResource(R.drawable.ch51);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 208: // Wall
                setImageResource(R.drawable.ch52);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 209: // Wall
                setImageResource(R.drawable.ch53);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 210: // Wall
                setImageResource(R.drawable.ch54);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 211: // Wall
                setImageResource(R.drawable.ch55);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 212: // Wall
                setImageResource(R.drawable.ch56);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 213: // Wall
                setImageResource(R.drawable.ch57);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 214: // Wall
                setImageResource(R.drawable.ch58);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 215: // Wall
                setImageResource(R.drawable.ch59);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 216: // Wall
                setImageResource(R.drawable.ch60);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 217: // Wall
                setImageResource(R.drawable.ch61);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 218: // Wall
                setImageResource(R.drawable.ch62);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 219: // Wall
                setImageResource(R.drawable.ch63);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 220: // Wall
                setImageResource(R.drawable.ch64);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 221: // Wall
                setImageResource(R.drawable.ch65);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 222: // Wall
                setImageResource(R.drawable.ch66);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 223: // Wall
                setImageResource(R.drawable.ch67);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 224: // Wall
                setImageResource(R.drawable.ch68);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 225: // Wall
                setImageResource(R.drawable.ch69);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 226: // Wall
                setImageResource(R.drawable.ch70);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 227: // Wall
                setImageResource(R.drawable.ch71);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 228: // Wall
                setImageResource(R.drawable.ch72);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 229: // Wall
                setImageResource(R.drawable.ch73);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 230: // Wall
                setImageResource(R.drawable.ch74);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 231: // Wall
                setImageResource(R.drawable.ch75);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 232: // Wall
                setImageResource(R.drawable.ch76);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 233: // Wall
                setImageResource(R.drawable.ch77);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 234: // Wall
                setImageResource(R.drawable.ch78);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 235: // Wall
                setImageResource(R.drawable.ch79);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 236: // Wall
                setImageResource(R.drawable.ch80);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 237: // Wall
                setImageResource(R.drawable.ch81);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 238: // Wall
                setImageResource(R.drawable.ch82);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 239: // Wall
                setImageResource(R.drawable.ch83);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 240: // Wall
                setImageResource(R.drawable.ch85);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 241: // Wall
                setImageResource(R.drawable.ch86);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 242: // Wall
                setImageResource(R.drawable.ch87);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 243: // Wall
                setImageResource(R.drawable.ch88);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 244: // Wall
                setImageResource(R.drawable.ch89);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 245: // Wall
                setImageResource(R.drawable.ch90);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 246: // Wall
                setImageResource(R.drawable.ch91);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 247: // Wall
                setImageResource(R.drawable.ch92);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 248: // Wall
                setImageResource(R.drawable.ch93);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 249: // Wall
                setImageResource(R.drawable.ch96);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 250: // Wall
                setImageResource(R.drawable.ch97);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 251: // Wall
                setImageResource(R.drawable.ch98);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 252: // Wall
                setImageResource(R.drawable.ct1);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 253: // Wall
                setImageResource(R.drawable.ct2);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 254: // Wall
                setImageResource(R.drawable.ct3);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 255: // Wall
                setImageResource(R.drawable.ct4);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 256: // Wall
                setImageResource(R.drawable.ct5);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 257: // Wall
                setImageResource(R.drawable.ct6);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 258: // Wall
                setImageResource(R.drawable.ct7);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 259: // Wall
                setImageResource(R.drawable.ct8);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 260: // Wall
                setImageResource(R.drawable.ct10);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 261: // Wall
                setImageResource(R.drawable.ct11);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 262: // Wall
                setImageResource(R.drawable.ct12);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 263: // Wall
                setImageResource(R.drawable.ct13);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 264: // Wall
                setImageResource(R.drawable.ct14);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 265: // Wall
                setImageResource(R.drawable.ct15);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 266: // Wall
                setImageResource(R.drawable.ct16);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 267: // Wall
                setImageResource(R.drawable.ct17);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 268: // Wall
                setImageResource(R.drawable.ct18);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 269: // Wall
                setImageResource(R.drawable.ct19);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 270: // Wall
                setImageResource(R.drawable.ct20);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 271: // Wall
                setImageResource(R.drawable.ct21);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 272: // Wall
                setImageResource(R.drawable.ct22);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 273: // Wall
                setImageResource(R.drawable.ct23);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 274: // Wall
                setImageResource(R.drawable.ct24);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 275: // Wall
                setImageResource(R.drawable.ct26);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 276: // Wall
                setImageResource(R.drawable.ct25);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 277: // Wall
                setImageResource(R.drawable.ct27);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 278: // Wall
                setImageResource(R.drawable.ct28);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 279: // Wall
                setImageResource(R.drawable.ct29);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 280: // Wall
                setImageResource(R.drawable.ct30);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 281: // Wall
                setImageResource(R.drawable.ct31);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 282: // Wall
                setImageResource(R.drawable.ct32);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 283: // Wall
                setImageResource(R.drawable.ct33);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 284: // Wall
                setImageResource(R.drawable.ct34);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 285: // Wall
                setImageResource(R.drawable.ct35);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 286: // Wall
                setImageResource(R.drawable.ct36);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 287: // Wall
                setImageResource(R.drawable.ct37);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 288: // Wall
                setImageResource(R.drawable.ct38);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 289: // Wall
                setImageResource(R.drawable.ct39);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 290: // Wall
                setImageResource(R.drawable.ct40);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 291: // Wall
                setImageResource(R.drawable.ct41);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 292: // Wall
                setImageResource(R.drawable.ct42);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 293: // Wall
                setImageResource(R.drawable.ct43);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 294: // Wall
                setImageResource(R.drawable.ct44);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 295: // Wall
                setImageResource(R.drawable.ct45);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 296: // Wall
                setImageResource(R.drawable.ct46);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 297: // Wall
                setImageResource(R.drawable.ct47);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;


            case 298: // Wall
                setImageResource(R.drawable.ct48);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;


            case 299: // Wall
                setImageResource(R.drawable.ct49);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;


            case 300: // Wall
                setImageResource(R.drawable.ct50);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;


            case 301: // Wall
                setImageResource(R.drawable.ct51);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;


            case 302: // Wall
                setImageResource(R.drawable.ct52);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;


            case 303: // Wall
                setImageResource(R.drawable.ct53);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;


            case 304: // Wall
                setImageResource(R.drawable.ct54);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 305: // Wall
                setImageResource(R.drawable.ct55);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 306: // Wall
                setImageResource(R.drawable.ct56);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 307: // Wall
                setImageResource(R.drawable.ct57);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 308: // Wall
                setImageResource(R.drawable.ct58);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 309: // Wall
                setImageResource(R.drawable.ct59);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 310: // Wall
                setImageResource(R.drawable.ct60);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 311: // Wall
                setImageResource(R.drawable.ct61);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 312: // Wall
                setImageResource(R.drawable.ct62);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 313: // Wall
                setImageResource(R.drawable.ct63);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 314: // Wall
                setImageResource(R.drawable.ct64);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 315: // Wall
                setImageResource(R.drawable.ct65);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 316: // Wall
                setImageResource(R.drawable.ct66);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 317: // Wall
                setImageResource(R.drawable.ct67);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 318: // Wall
                setImageResource(R.drawable.ct68);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 319: // Wall
                setImageResource(R.drawable.ct69);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 320: // Wall
                setImageResource(R.drawable.ct70);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 321: // Wall
                setImageResource(R.drawable.ct71);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 322: // Wall
                setImageResource(R.drawable.ct72);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 323: // Wall
                setImageResource(R.drawable.ct73);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 324: // Wall
                setImageResource(R.drawable.ct74);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 325: // Wall
                setImageResource(R.drawable.ct75);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 326: // Wall
                setImageResource(R.drawable.ct76);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 327: // Wall
                setImageResource(R.drawable.ct77);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 328: // Wall
                setImageResource(R.drawable.ct78);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 329: // Wall
                setImageResource(R.drawable.ct79);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 330: // Wall
                setImageResource(R.drawable.ct80);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 331: // Wall
                setImageResource(R.drawable.ct81);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 332: // Wall
                setImageResource(R.drawable.ct82);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 333: // Wall
                setImageResource(R.drawable.ct83);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 334: // Wall
                setImageResource(R.drawable.ct84);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 335: // Wall
                setImageResource(R.drawable.ct85);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 336: // Wall
                setImageResource(R.drawable.ct86);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 337: // Wall
                setImageResource(R.drawable.ct87);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 338: // Wall
                setImageResource(R.drawable.ct88);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 339: // Wall
                setImageResource(R.drawable.ct89);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 340: // Wall
                setImageResource(R.drawable.ct90);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 341: // Wall
                setImageResource(R.drawable.ct91);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 342: // Wall
                setImageResource(R.drawable.ct92);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 343: // Wall
                setImageResource(R.drawable.ct93);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 344: // Wall
                setImageResource(R.drawable.ct94);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 345: // Wall
                setImageResource(R.drawable.ct95);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 346: // Wall
                setImageResource(R.drawable.ct96);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 347: // Wall
                setImageResource(R.drawable.cr1);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 348: // Wall
                setImageResource(R.drawable.cr2);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 349: // Wall
                setImageResource(R.drawable.cr3);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 350: // Wall
                setImageResource(R.drawable.cr4);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 351: // Wall
                setImageResource(R.drawable.cr5);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 352: // Wall
                setImageResource(R.drawable.cr7);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 353: // Wall
                setImageResource(R.drawable.cr6);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 354: // Wall
                setImageResource(R.drawable.cr8);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 355: // Wall
                setImageResource(R.drawable.cr9);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 356: // Wall
                setImageResource(R.drawable.cr10);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 357: // Wall
                setImageResource(R.drawable.cr11);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 358: // Wall
                setImageResource(R.drawable.cr12);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 359: // Wall
                setImageResource(R.drawable.cr13);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 360: // Wall
                setImageResource(R.drawable.cr14);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 361: // Wall
                setImageResource(R.drawable.cr15);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 362: // Wall
                setImageResource(R.drawable.cr16);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 363: // Wall
                setImageResource(R.drawable.cr17);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 364: // Wall
                setImageResource(R.drawable.cr18);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 365: // Wall
                setImageResource(R.drawable.cr19);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 366: // Wall
                setImageResource(R.drawable.cr20);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 367: // Wall
                setImageResource(R.drawable.cr21);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 368: // Wall
                setImageResource(R.drawable.cr22);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 369: // Wall
                setImageResource(R.drawable.cr23);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 370: // Wall
                setImageResource(R.drawable.cr24);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 371: // Wall
                setImageResource(R.drawable.cr25);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 372: // Wall
                setImageResource(R.drawable.cr26);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 373: // Wall
                setImageResource(R.drawable.cr27);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 374: // Wall
                setImageResource(R.drawable.cr28);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 375: // Wall
                setImageResource(R.drawable.cr29);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 376: // Wall
                setImageResource(R.drawable.cr30);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 377: // Wall
                setImageResource(R.drawable.cr31);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 378: // Wall
                setImageResource(R.drawable.cr32);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 379: // Wall
                setImageResource(R.drawable.cr33);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 380: // Wall
                setImageResource(R.drawable.cr34);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 381: // Wall
                setImageResource(R.drawable.cr35);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 382: // Wall
                setImageResource(R.drawable.cr36);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 383: // Wall
                setImageResource(R.drawable.cr37);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 384: // Wall
                setImageResource(R.drawable.cr38);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 385: // Wall
                setImageResource(R.drawable.cr39);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 386: // Wall
                setImageResource(R.drawable.cr40);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 387: // Wall
                setImageResource(R.drawable.cr41);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 388: // Wall
                setImageResource(R.drawable.cr42);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 389: // Wall
                setImageResource(R.drawable.cr43);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 390: // Wall
                setImageResource(R.drawable.cr44);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 391: // Wall
                setImageResource(R.drawable.cr45);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 392: // Wall
                setImageResource(R.drawable.cr46);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 393: // Wall
                setImageResource(R.drawable.cr47);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 394: // Wall
                setImageResource(R.drawable.cr48);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 395: // Wall
                setImageResource(R.drawable.cr49);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 396: // Wall
                setImageResource(R.drawable.cr50);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 397: // Wall
                setImageResource(R.drawable.cr51);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 398: // Wall
                setImageResource(R.drawable.cr52);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 399: // Wall
                setImageResource(R.drawable.cr53);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 400: // Wall
                setImageResource(R.drawable.cr54);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 401: // Wall
                setImageResource(R.drawable.cr55);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 402: // Wall
                setImageResource(R.drawable.cr56);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 403: // Wall
                setImageResource(R.drawable.cr57);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 404: // Wall
                setImageResource(R.drawable.cr58);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 405: // Wall
                setImageResource(R.drawable.cr59);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 406: // Wall
                setImageResource(R.drawable.cr59);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 407: // Wall
                setImageResource(R.drawable.cr60);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 408: // Wall
                setImageResource(R.drawable.cr61);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 409: // Wall
                setImageResource(R.drawable.cr62);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 410: // Wall
                setImageResource(R.drawable.cr63);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 411: // Wall
                setImageResource(R.drawable.cr64);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 412: // Wall
                setImageResource(R.drawable.cr66);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 413: // Wall
                setImageResource(R.drawable.cr65);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 414: // Wall
                setImageResource(R.drawable.cr67);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 415: // Wall
                setImageResource(R.drawable.cr68);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 416: // Wall
                setImageResource(R.drawable.ce1);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 417: // Wall
                setImageResource(R.drawable.ce2);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 418: // Wall
                setImageResource(R.drawable.ce3);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 419: // Wall
                setImageResource(R.drawable.ce4);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 420: // Wall
                setImageResource(R.drawable.ce5);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 421: // Wall
                setImageResource(R.drawable.ce6);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 422: // Wall
                setImageResource(R.drawable.ce7);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 423: // Wall
                setImageResource(R.drawable.ce8);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 424: // Wall
                setImageResource(R.drawable.ce9);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 425: // Wall
                setImageResource(R.drawable.ce10);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 426: // Wall
                setImageResource(R.drawable.ce11);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 427: // Wall
                setImageResource(R.drawable.ce12);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 428: // Wall
                setImageResource(R.drawable.ce13);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 429: // Wall
                setImageResource(R.drawable.ce14);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 430: // Wall
                setImageResource(R.drawable.ce15);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 431: // Wall
                setImageResource(R.drawable.ce16);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 432: // Wall
                setImageResource(R.drawable.ce17);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 433: // Wall
                setImageResource(R.drawable.ce18);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 434: // Wall
                setImageResource(R.drawable.ce19);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 435: // Wall
                setImageResource(R.drawable.ce20);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 436: // Wall
                setImageResource(R.drawable.ce21);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 437: // Wall
                setImageResource(R.drawable.ce22);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 438: // Wall
                setImageResource(R.drawable.ce23);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 439: // Wall
                setImageResource(R.drawable.ce24);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 440: // Wall
                setImageResource(R.drawable.ce25);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 441: // Wall
                setImageResource(R.drawable.ce26);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 442: // Wall
                setImageResource(R.drawable.ce27);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 443: // Wall
                setImageResource(R.drawable.ce28);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 444: // Wall
                setImageResource(R.drawable.ce29);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 455: // Wall
                setImageResource(R.drawable.ce30);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 445: // Wall
                setImageResource(R.drawable.ce31);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 446: // Wall
                setImageResource(R.drawable.ce32);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 447: // Wall
                setImageResource(R.drawable.ce33);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 448: // Wall
                setImageResource(R.drawable.ce34);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 449: // Wall
                setImageResource(R.drawable.ce35);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 450: // Wall
                setImageResource(R.drawable.ce36);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 451: // Wall
                setImageResource(R.drawable.ce37);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 452: // Wall
                setImageResource(R.drawable.ce38);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 453: // Wall
                setImageResource(R.drawable.ce39);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 454: // Wall
                setImageResource(R.drawable.ce40);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 456: // Wall
                setImageResource(R.drawable.ce41);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 457: // Wall
                setImageResource(R.drawable.ce42);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 458: // Wall
                setImageResource(R.drawable.ce43);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 459: // Wall
                setImageResource(R.drawable.ce44);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 460: // Wall
                setImageResource(R.drawable.ce45);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 461: // Wall
                setImageResource(R.drawable.ce46);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 462: // Wall
                setImageResource(R.drawable.ce47);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 463: // Wall
                setImageResource(R.drawable.ce48);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 464: // Wall
                setImageResource(R.drawable.ce49);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 465: // Wall
                setImageResource(R.drawable.ce50);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 466: // Wall
                setImageResource(R.drawable.ce51);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 467: // Wall
                setImageResource(R.drawable.ce52);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 468: // Wall
                setImageResource(R.drawable.ce53);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 469: // Wall
                setImageResource(R.drawable.ce57);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 470: // Wall
                setImageResource(R.drawable.ce54);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 471: // Wall
                setImageResource(R.drawable.ce55);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 472: // Wall
                setImageResource(R.drawable.ce56);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 473: // Wall
                setImageResource(R.drawable.ce58);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 474: // Wall
                setImageResource(R.drawable.ce59);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 475: // Wall
                setImageResource(R.drawable.m1);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 476: // Wall
                setImageResource(R.drawable.m2);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 477: // Wall
                setImageResource(R.drawable.m3);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 478: // Wall
                setImageResource(R.drawable.m4);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 479: // Wall
                setImageResource(R.drawable.m5);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 480: // Wall
                setImageResource(R.drawable.m6);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 481: // Wall
                setImageResource(R.drawable.m7);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 482: // Wall
                setImageResource(R.drawable.m8);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 483: // Wall
                setImageResource(R.drawable.m9);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 484: // Wall
                setImageResource(R.drawable.m10);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 485: // Wall
                setImageResource(R.drawable.m11);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 486: // Wall
                setImageResource(R.drawable.m12);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 487: // Wall
                setImageResource(R.drawable.m13);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 488: // Wall
                setImageResource(R.drawable.m14);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 489: // Wall
                setImageResource(R.drawable.m15);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 490: // Wall
                setImageResource(R.drawable.m16);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 491: // Wall
                setImageResource(R.drawable.m17);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 492: // Wall
                setImageResource(R.drawable.m18);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 493: // Wall
                setImageResource(R.drawable.m19);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 494: // Wall
                setImageResource(R.drawable.m20);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 495: // Wall
                setImageResource(R.drawable.m21);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 496: // Wall
                setImageResource(R.drawable.m22);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 497: // Wall
                setImageResource(R.drawable.m23);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 498: // Wall
                setImageResource(R.drawable.m24);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 499: // Wall
                setImageResource(R.drawable.m25);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 500: // Wall
                setImageResource(R.drawable.m26);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 501: // Wall
                setImageResource(R.drawable.m27);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 502: // Wall
                setImageResource(R.drawable.m28);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 503: // Wall
                setImageResource(R.drawable.m29);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 504: // Wall
                setImageResource(R.drawable.m30);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 505: // Wall
                setImageResource(R.drawable.m31);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 506: // Wall
                setImageResource(R.drawable.m32);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 507: // Wall
                setImageResource(R.drawable.m33);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 508: // Wall
                setImageResource(R.drawable.m34);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 509: // Wall
                setImageResource(R.drawable.m35);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 510: // Wall
                setImageResource(R.drawable.m36);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 511: // Wall
                setImageResource(R.drawable.m37);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 512: // Wall
                setImageResource(R.drawable.m38);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 513: // Wall
                setImageResource(R.drawable.m39);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 514: // Wall
                setImageResource(R.drawable.m40);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 515: // Wall
                setImageResource(R.drawable.m41);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 516: // Wall
                setImageResource(R.drawable.m42);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 517: // Wall
                setImageResource(R.drawable.m43);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 518: // Wall
                setImageResource(R.drawable.m44);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 519: // Wall
                setImageResource(R.drawable.m45);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 520: // Wall
                setImageResource(R.drawable.m46);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 521: // Wall
                setImageResource(R.drawable.m47);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 522: // Wall
                setImageResource(R.drawable.m48);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 523: // Wall
                setImageResource(R.drawable.m49);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 524: // Wall
                setImageResource(R.drawable.m50);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 525: // Wall
                setImageResource(R.drawable.m51);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 526: // Wall
                setImageResource(R.drawable.m52);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 527: // Wall
                setImageResource(R.drawable.m53);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 528: // Wall
                setImageResource(R.drawable.m54);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 529: // Wall
                setImageResource(R.drawable.m55);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 530: // Wall
                setImageResource(R.drawable.m56);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 531: // Wall
                setImageResource(R.drawable.m57);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 532: // Wall
                setImageResource(R.drawable.m58);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 533: // Wall
                setImageResource(R.drawable.m59);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 534: // Wall
                setImageResource(R.drawable.m60);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 535: // Wall
                setImageResource(R.drawable.m61);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 536: // Wall
                setImageResource(R.drawable.m62);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 537: // Wall
                setImageResource(R.drawable.m63);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 538: // Wall
                setImageResource(R.drawable.m64);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 539: // Wall
                setImageResource(R.drawable.m65);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 540: // Wall
                setImageResource(R.drawable.m66);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 541: // Wall
                setImageResource(R.drawable.m67);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 542: // Wall
                setImageResource(R.drawable.m68);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 543: // Wall
                setImageResource(R.drawable.m69);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 544: // Wall
                setImageResource(R.drawable.m70);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 545: // Wall
                setImageResource(R.drawable.m71);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 546: // Wall
                setImageResource(R.drawable.m72);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 547: // Wall
                setImageResource(R.drawable.m73);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 548: // Wall
                setImageResource(R.drawable.m74);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 549: // Wall
                setImageResource(R.drawable.m75);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 550: // Wall
                setImageResource(R.drawable.m76);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 551: // Wall
                setImageResource(R.drawable.m77);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 552: // Wall
                setImageResource(R.drawable.m78);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 553: // Wall
                setImageResource(R.drawable.m79);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 554: // Wall
                setImageResource(R.drawable.m80);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 555: // Wall
                setImageResource(R.drawable.m81);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 556: // Wall
                setImageResource(R.drawable.m82);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 557: // Wall
                setImageResource(R.drawable.n1);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 558: // Wall
                setImageResource(R.drawable.n2);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 559: // Wall
                setImageResource(R.drawable.n3);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 560: // Wall
                setImageResource(R.drawable.n4);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 561: // Wall
                setImageResource(R.drawable.n5);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 562: // Wall
                setImageResource(R.drawable.n6);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 563: // Wall
                setImageResource(R.drawable.n7);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 564: // Wall
                setImageResource(R.drawable.n8);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 565: // Wall
                setImageResource(R.drawable.n9);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 566: // Wall
                setImageResource(R.drawable.n10);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 567: // Wall
                setImageResource(R.drawable.n11);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 568: // Wall
                setImageResource(R.drawable.n12);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 569: // Wall
                setImageResource(R.drawable.n13);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 570: // Wall
                setImageResource(R.drawable.n14);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 571: // Wall
                setImageResource(R.drawable.n15);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 572: // Wall
                setImageResource(R.drawable.n16);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 573: // Wall
                setImageResource(R.drawable.n17);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 574: // Wall
                setImageResource(R.drawable.n18);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 575: // Wall
                setImageResource(R.drawable.n19);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 576: // Wall
                setImageResource(R.drawable.n20);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 577: // Wall
                setImageResource(R.drawable.p1);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 578: // Wall
                setImageResource(R.drawable.p2);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 579: // Wall
                setImageResource(R.drawable.p3);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 580: // Wall
                setImageResource(R.drawable.p4);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 581: // Wall
                setImageResource(R.drawable.p5);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 582: // Wall
                setImageResource(R.drawable.p6);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 583: // Wall
                setImageResource(R.drawable.p7);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 584: // Wall
                setImageResource(R.drawable.p8);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 585: // Wall
                setImageResource(R.drawable.p9);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 586: // Wall
                setImageResource(R.drawable.p10);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 587: // Wall
                setImageResource(R.drawable.p11);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 588: // Wall
                setImageResource(R.drawable.p12);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 589: // Wall
                setImageResource(R.drawable.p13);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 590: // Wall
                setImageResource(R.drawable.p14);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 591: // Wall
                setImageResource(R.drawable.p15);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 592: // Wall
                setImageResource(R.drawable.p16);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 593: // Wall
                setImageResource(R.drawable.p17);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 594: // Wall
                setImageResource(R.drawable.p18);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 595: // Wall
                setImageResource(R.drawable.p19);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 596: // Wall
                setImageResource(R.drawable.p20);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 597: // Wall
                setImageResource(R.drawable.p21);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 598: // Wall
                setImageResource(R.drawable.p22);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 599: // Wall
                setImageResource(R.drawable.p23);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 600: // Wall
                setImageResource(R.drawable.p24);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 601: // Wall
                setImageResource(R.drawable.p25);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 602: // Wall
                setImageResource(R.drawable.p26);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 603: // Wall
                setImageResource(R.drawable.p27);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 604: // Wall
                setImageResource(R.drawable.p28);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 605: // Wall
                setImageResource(R.drawable.p29);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 606: // Wall
                setImageResource(R.drawable.p30);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 607: // Wall
                setImageResource(R.drawable.p31);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 608: // Wall
                setImageResource(R.drawable.p32);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 609: // Wall
                setImageResource(R.drawable.p33);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 610: // Wall
                setImageResource(R.drawable.p34);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 611: // Wall
                setImageResource(R.drawable.p35);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 612: // Wall
                setImageResource(R.drawable.p36);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 613: // Wall
                setImageResource(R.drawable.p37);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 614: // Wall
                setImageResource(R.drawable.p38);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 615: // Wall
                setImageResource(R.drawable.p39);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 616: // Wall
                setImageResource(R.drawable.w1);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 617: // Wall
                setImageResource(R.drawable.w2);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 618: // Wall
                setImageResource(R.drawable.w3);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 619: // Wall
                setImageResource(R.drawable.w4);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 620: // Wall
                setImageResource(R.drawable.w5);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 621: // Wall
                setImageResource(R.drawable.w6);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 622: // Wall
                setImageResource(R.drawable.w7);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 623: // Wall
                setImageResource(R.drawable.w8);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 624: // Wall
                setImageResource(R.drawable.w9);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 625: // Wall
                setImageResource(R.drawable.w10);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 626: // Wall
                setImageResource(R.drawable.w11);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 627: // Wall
                setImageResource(R.drawable.w12);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 628: // Wall
                setImageResource(R.drawable.w13);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 629: // Wall
                setImageResource(R.drawable.w14);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 630: // Wall
                setImageResource(R.drawable.w15);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 631: // Wall
                setImageResource(R.drawable.w16);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 632: // Wall
                setImageResource(R.drawable.w17);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 633: // Wall
                setImageResource(R.drawable.w18);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 634: // Wall
                setImageResource(R.drawable.w19);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 635: // Wall
                setImageResource(R.drawable.w20);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 636: // Wall
                setImageResource(R.drawable.w21);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 637: // Wall
                setImageResource(R.drawable.w22);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 638: // Wall
                setImageResource(R.drawable.w23);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 639: // Wall
                setImageResource(R.drawable.w24);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 640: // Wall
                setImageResource(R.drawable.w25);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 641: // Wall
                setImageResource(R.drawable.w26);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 642: // Wall
                setImageResource(R.drawable.w27);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 643: // Wall
                setImageResource(R.drawable.w28);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 644: // Wall
                setImageResource(R.drawable.w29);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 645: // Wall
                setImageResource(R.drawable.w30);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 646: // Wall
                setImageResource(R.drawable.w31);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 647: // Wall
                setImageResource(R.drawable.w32);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 648: // Wall
                setImageResource(R.drawable.w33);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 649: // Wall
                setImageResource(R.drawable.w34);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 650: // Wall
                setImageResource(R.drawable.w35);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 651: // Wall
                setImageResource(R.drawable.w36);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 652: // Wall
                setImageResource(R.drawable.w37);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 653: // Wall
                setImageResource(R.drawable.w38);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 654: // Wall
                setImageResource(R.drawable.w39);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 655: // Wall
                setImageResource(R.drawable.w40);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 656: // Wall
                setImageResource(R.drawable.w41);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 657: // Wall
                setImageResource(R.drawable.w42);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 658: // Wall
                setImageResource(R.drawable.e1);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 659: // Wall
                setImageResource(R.drawable.e2);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 660: // Wall
                setImageResource(R.drawable.e3);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 661: // Wall
                setImageResource(R.drawable.e4);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 662: // Wall
                setImageResource(R.drawable.e5);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 663: // Wall
                setImageResource(R.drawable.e6);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 664: // Wall
                setImageResource(R.drawable.e7);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 665: // Wall
                setImageResource(R.drawable.e8);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 666: // Wall
                setImageResource(R.drawable.e9);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 667: // Wall
                setImageResource(R.drawable.e10);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 668: // Wall
                setImageResource(R.drawable.e11);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 669: // Wall
                setImageResource(R.drawable.e12);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 670: // Wall
                setImageResource(R.drawable.e13);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 671: // Wall
                setImageResource(R.drawable.e14);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 672: // Wall
                setImageResource(R.drawable.e15);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 673: // Wall
                setImageResource(R.drawable.e16);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 674: // Wall
                setImageResource(R.drawable.e17);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 675: // Wall
                setImageResource(R.drawable.r1);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 676: // Wall
                setImageResource(R.drawable.h1);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 677: // Wall
                setImageResource(R.drawable.h11);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 678: // Wall
                setImageResource(R.drawable.h13);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 679: // Wall
                setImageResource(R.drawable.h12);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 680: // Wall
                setImageResource(R.drawable.h14);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 681: // Wall
                setImageResource(R.drawable.h6);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 682: // Wall
                setImageResource(R.drawable.h15);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 683: // Wall
                setImageResource(R.drawable.h16);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 684: // Wall
                setImageResource(R.drawable.h17);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 685: // Wall
                setImageResource(R.drawable.h18);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 686: // Wall
                setImageResource(R.drawable.h19);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 687: // Wall
                setImageResource(R.drawable.h20);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 688: // Wall
                setImageResource(R.drawable.h21);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 689: // Wall
                setImageResource(R.drawable.h22);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 690: // Wall
                setImageResource(R.drawable.h23);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 691: // Wall
                setImageResource(R.drawable.h24);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 692: // Wall
                setImageResource(R.drawable.h25);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 693: // Wall
                setImageResource(R.drawable.h26);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 694: // Wall
                setImageResource(R.drawable.h27);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 695: // Wall
                setImageResource(R.drawable.h28);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 696: // Wall
                setImageResource(R.drawable.h29);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 697: // Wall
                setImageResource(R.drawable.h30);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 698: // Wall
                setImageResource(R.drawable.h31);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 699: // Wall
                setImageResource(R.drawable.h32);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 700: // Wall
                setImageResource(R.drawable.h33);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 701: // Wall
                setImageResource(R.drawable.h34);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 702: // Wall
                setImageResource(R.drawable.h35);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 703: // Wall
                setImageResource(R.drawable.h36);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 704: // Wall
                setImageResource(R.drawable.h37);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 705: // Wall
                setImageResource(R.drawable.h38);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 706: // Wall
                setImageResource(R.drawable.h39);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 707: // Wall
                setImageResource(R.drawable.h40);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 708: // Wall
                setImageResource(R.drawable.h41);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 709: // Wall
                setImageResource(R.drawable.h42);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 710: // Wall
                setImageResource(R.drawable.h43);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 711: // Wall
                setImageResource(R.drawable.h44);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 712: // Wall
                setImageResource(R.drawable.h45);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 713: // Wall
                setImageResource(R.drawable.h46);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 714: // Wall
                setImageResource(R.drawable.h47);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 715: // Wall
                setImageResource(R.drawable.b1);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 716: // Wall
                setImageResource(R.drawable.b2);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 717: // Wall
                setImageResource(R.drawable.b3);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 718: // Wall
                setImageResource(R.drawable.b4);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 719: // Wall
                setImageResource(R.drawable.b5);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 720: // Wall
                setImageResource(R.drawable.b6);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 721: // Wall
                setImageResource(R.drawable.b7);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 722: // Wall
                setImageResource(R.drawable.b8);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 723: // Wall
                setImageResource(R.drawable.b9);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 724: // Wall
                setImageResource(R.drawable.b10);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 725: // Wall
                setImageResource(R.drawable.b11);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 726: // Wall
                setImageResource(R.drawable.b12);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 727: // Wall
                setImageResource(R.drawable.b13);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 728: // Wall
                setImageResource(R.drawable.b15);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 729: // Wall
                setImageResource(R.drawable.b16);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 730: // Wall
                setImageResource(R.drawable.fl1);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 731: // Wall
                setImageResource(R.drawable.fl2);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 732: // Wall
                setImageResource(R.drawable.fl3);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 733: // Wall
                setImageResource(R.drawable.fl4);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 734: // Wall
                setImageResource(R.drawable.fl5);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 735: // Wall
                setImageResource(R.drawable.fl6);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 736: // Wall
                setImageResource(R.drawable.fl7);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 737: // Wall
                setImageResource(R.drawable.fl8);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 738: // Wall
                setImageResource(R.drawable.fl8);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 739: // Wall
                setImageResource(R.drawable.x1);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 740: // Wall
                setImageResource(R.drawable.x2);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 741: // Wall
                setImageResource(R.drawable.x3);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 742: // Wall
                setImageResource(R.drawable.x4);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 743: // Wall
                setImageResource(R.drawable.x5);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 744: // Wall
                setImageResource(R.drawable.x6);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 745: // Wall
                setImageResource(R.drawable.x7);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 746: // Wall
                setImageResource(R.drawable.x8);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 747: // Wall
                setImageResource(R.drawable.x9);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 748: // Wall
                setImageResource(R.drawable.x10);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 749: // Wall
                setImageResource(R.drawable.x11);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 750: // Wall
                setImageResource(R.drawable.x12);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 751: // Wall
                setImageResource(R.drawable.x13);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 752: // Wall
                setImageResource(R.drawable.x14);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 753: // Wall
                setImageResource(R.drawable.x15);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 754: // Wall
                setImageResource(R.drawable.x16);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 755: // Wall
                setImageResource(R.drawable.x17);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 756: // Wall
                setImageResource(R.drawable.x18);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 757: // Wall
                setImageResource(R.drawable.x19);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 758: // Wall
                setImageResource(R.drawable.x20);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 759: // Wall
                setImageResource(R.drawable.x21);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 760: // Wall
                setImageResource(R.drawable.x22);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 761: // Wall
                setImageResource(R.drawable.x23);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 762: // Wall
                setImageResource(R.drawable.x24);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 763: // Wall
                setImageResource(R.drawable.x25);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 764: // Wall
                setImageResource(R.drawable.x26);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 765: // Wall
                setImageResource(R.drawable.x27);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 766: // Wall
                setImageResource(R.drawable.x28);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 767: // Wall
                setImageResource(R.drawable.x29);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 768: // Wall
                setImageResource(R.drawable.x30);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 769: // Wall
                setImageResource(R.drawable.x31);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 770: // Wall
                setImageResource(R.drawable.x32);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 771: // Wall
                setImageResource(R.drawable.x33);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 772: // Wall
                setImageResource(R.drawable.x34);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 773: // Wall
                setImageResource(R.drawable.x35);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 774: // Wall
                setImageResource(R.drawable.x36);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 775: // Wall
                setImageResource(R.drawable.x37);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 776: // Wall
                setImageResource(R.drawable.x38);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 777: // Wall
                setImageResource(R.drawable.x39);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 778: // Wall
                setImageResource(R.drawable.x40);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 779: // Wall
                setImageResource(R.drawable.x41);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 780: // Wall
                setImageResource(R.drawable.x42);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 781: // Wall
                setImageResource(R.drawable.x43);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 782: // Wall
                setImageResource(R.drawable.x44);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 783: // Wall
                setImageResource(R.drawable.x45);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 784: // Wall
                setImageResource(R.drawable.x46);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 785: // Wall
                setImageResource(R.drawable.x47);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 786: // Wall
                setImageResource(R.drawable.x48);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 787: // Wall
                setImageResource(R.drawable.x50);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 788: // Wall
                setImageResource(R.drawable.x51);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 789: // Wall
                setImageResource(R.drawable.x52);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 790: // Wall
                setImageResource(R.drawable.x53);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 791: // Wall
                setImageResource(R.drawable.x54);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 792: // Wall
                setImageResource(R.drawable.x55);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 793: // Wall
                setImageResource(R.drawable.x56);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 794: // Wall
                setImageResource(R.drawable.x57);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 795: // Wall
                setImageResource(R.drawable.x58);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 796: // Wall
                setImageResource(R.drawable.x59);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 797: // Wall
                setImageResource(R.drawable.x60);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 798: // Wall
                setImageResource(R.drawable.x61);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 799: // Wall
                setImageResource(R.drawable.x62);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 800: // Wall
                setImageResource(R.drawable.x63);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 801: // Wall
                setImageResource(R.drawable.x64);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 802: // Wall
                setImageResource(R.drawable.x65);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 803: // Wall
                setImageResource(R.drawable.x66);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 804: // Wall
                setImageResource(R.drawable.x67);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 805: // Wall
                setImageResource(R.drawable.x68);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 806: // Wall
                setImageResource(R.drawable.x69);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 807: // Wall
                setImageResource(R.drawable.x70);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 808: // Wall
                setImageResource(R.drawable.x71);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 809: // Wall
                setImageResource(R.drawable.x72);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 810: // Wall
                setImageResource(R.drawable.x73);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 811: // Wall
                setImageResource(R.drawable.x74);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 812: // Wall
                setImageResource(R.drawable.x75);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 813: // Wall
                setImageResource(R.drawable.x76);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 814: // Wall
                setImageResource(R.drawable.x77);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 815: // Wall
                setImageResource(R.drawable.x78);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 816: // Wall
                setImageResource(R.drawable.x79);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 817: // Wall
                setImageResource(R.drawable.x80);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 818: // Wall
                setImageResource(R.drawable.x81);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 819: // Wall
                setImageResource(R.drawable.x82);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 820: // Wall
                setImageResource(R.drawable.x83);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 821: // Wall
                setImageResource(R.drawable.x84);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 822: // Wall
                setImageResource(R.drawable.x85);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 823: // Wall
                setImageResource(R.drawable.x86);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 824: // Wall
                setImageResource(R.drawable.x87);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 825: // Wall
                setImageResource(R.drawable.z1);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 826: // Wall
                setImageResource(R.drawable.z2);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 827: // Wall
                setImageResource(R.drawable.z3);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 828: // Wall
                setImageResource(R.drawable.z4);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 829: // Wall
                setImageResource(R.drawable.z5);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 830: // Wall
                setImageResource(R.drawable.z6);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 831: // Wall
                setImageResource(R.drawable.z7);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;

            case 832: // Wall
                setImageResource(R.drawable.z8);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 833: // Wall
                setImageResource(R.drawable.z9);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 834: // Wall
                setImageResource(R.drawable.z10);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 835: // Wall
                setImageResource(R.drawable.z11);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 836: // Wall
                setImageResource(R.drawable.z12);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 837: // Wall
                setImageResource(R.drawable.z13);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 838: // Wall
                setImageResource(R.drawable.z14);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 839: // Wall
                setImageResource(R.drawable.z15);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 840: // Wall
                setImageResource(R.drawable.z16);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 841: // Wall
                setImageResource(R.drawable.z17);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 842: // Wall
                setImageResource(R.drawable.z18);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 843: // Wall
                setImageResource(R.drawable.z19);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 844: // Wall
                setImageResource(R.drawable.z20);
                walkable = false;
                setClickable(false);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 845: // Wall
                setImageResource(R.drawable.z21);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 846: // Wall
                setImageResource(R.drawable.z22);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 847: // Wall
                setImageResource(R.drawable.z23);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 848: // Wall
                setImageResource(R.drawable.z24);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 849: // Wall
                setImageResource(R.drawable.z25);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 850: // Wall
                setImageResource(R.drawable.z26);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 851: // Wall
                setImageResource(R.drawable.z27);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 852: // Wall
                setImageResource(R.drawable.z28);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 853: // Wall
                setImageResource(R.drawable.z29);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 854: // Wall
                setImageResource(R.drawable.z30);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 855: // Wall
                setImageResource(R.drawable.z31);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 856: // Wall
                setImageResource(R.drawable.z32);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 857: // Wall
                setImageResource(R.drawable.z33);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 858: // Wall
                setImageResource(R.drawable.z34);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 859: // Wall
                setImageResource(R.drawable.z38);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 860: // Wall
                setImageResource(R.drawable.z39);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 861: // Wall
                setImageResource(R.drawable.z40);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 862: // Wall
                setImageResource(R.drawable.z41);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 863: // Wall
                setImageResource(R.drawable.z42);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 864: // Wall
                setImageResource(R.drawable.z35);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 865: // Wall
                setImageResource(R.drawable.z36);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 866: // Wall
                setImageResource(R.drawable.z37);
                walkable = false;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 867: // Wall
                setImageResource(R.drawable.z43);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 868: // Wall
                setImageResource(R.drawable.z44);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 869: // Wall
                setImageResource(R.drawable.z45);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 870: // Wall
                setImageResource(R.drawable.z46);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
            case 871: // Wall
                setImageResource(R.drawable.z47);
                walkable = true;
                setClickable(true);
                Log.d("TileView", "This tile is not clickable.");
                break;
        }
    }
    public boolean isWalkable() {
        return walkable;
    }
    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
        setClickable(walkable);
    }


    public void setGCost(double gCost) {
        this.gCost = gCost;
    }
    public void setHCost(double hCost) {
        this.hCost = hCost;
    }
    public void setPath(boolean isPath) {
        this.isPath = isPath;
        invalidate();
    }
    public int getType() {
        return tileType;
    }





    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (isPath) {
            // Draw blue dot for final path
            Paint pathPaint = new Paint();
            pathPaint.setColor(Color.BLUE);
            pathPaint.setAlpha(200);
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int radius = Math.min(getWidth(), getHeight()) / 8;
            canvas.drawCircle(centerX, centerY, radius, pathPaint);
        } else if (showDot) {
            // Draw dot if showDot is true
            Paint dotPaint = new Paint();
            dotPaint.setColor(dotColor);
            dotPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(getWidth() / 2f, getHeight() / 2f, getWidth() / 4f, dotPaint);
        }
    }

    private boolean showDot;
    private int dotColor;

    // Constructor and other methods...

    public void setDot(boolean showDot, int color) {
        this.showDot = showDot;
        this.dotColor = color;
        invalidate(); // Trigger a redraw
    }

    public static ArrayList<TileView> findPath(TileView[][] tiles, TileView start, TileView end) {
        ArrayList<TileView> openList = new ArrayList<>();
        ArrayList<TileView> closedList = new ArrayList<>();
        openList.add(start);

        while (!openList.isEmpty()) {
            TileView currentTile = openList.remove(0);
            closedList.add(currentTile);

            if (currentTile == end) {
                return reconstructPath(start, end);
            }

            ArrayList<TileView> neighbors = getNeighbors(tiles, currentTile);

            for (TileView neighbor : neighbors) {
                if (!closedList.contains(neighbor) && neighbor.isWalkable()) {
                    double tentativeGCost = currentTile.getGCost() + distance(currentTile, neighbor);
                    if (!openList.contains(neighbor) || tentativeGCost < neighbor.getGCost()) {
                        neighbor.setParent(currentTile);
                        neighbor.setGCost(tentativeGCost);
                        neighbor.setHCost(distance(neighbor, end));
                        if (!openList.contains(neighbor)) {
                            openList.add(neighbor);
                        }
                    }
                }
            }
            Collections.sort(openList, new Comparator<TileView>() {
                @Override
                public int compare(TileView t1, TileView t2) {
                    return Double.compare(t1.getFCost(), t2.getFCost());
                }
            });
        }
        return new ArrayList<>();
    }

    private static ArrayList<TileView> reconstructPath(TileView start, TileView end) {
        ArrayList<TileView> path = new ArrayList<>();
        TileView currentTile = end;
        while (currentTile != start) {
            path.add(currentTile);
            currentTile.setPath(true);
            currentTile = currentTile.getParentTile();
        }
        path.add(start);
        Collections.reverse(path);
        return path;
    }

    private static ArrayList<TileView> getNeighbors(TileView[][] tiles, TileView tile) {
        ArrayList<TileView> neighbors = new ArrayList<>();
        int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
        int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
        int rows = tiles.length;
        int cols = tiles[0].length;

        for (int i = 0; i < 8; i++) {
            int newRow = tile.row + dx[i];
            int newCol = tile.col + dy[i];
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                neighbors.add(tiles[newRow][newCol]);
            }
        }
        return neighbors;
    }

    private static double distance(TileView tile1, TileView tile2) {
        int dx = Math.abs(tile1.row - tile2.row);
        int dy = Math.abs(tile1.col - tile2.col);
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double getFCost() {
        return getGCost() + getHCost();
    }

    public double getGCost() {
        return gCost;
    }

    private double getHCost() {
        return 0;
    }

    public TileView getParentTile() {
        return parentTile;
    }

    public void setParentTile(TileView parent) {
        parentTile = parent;
    }

    protected void setParent(TileView parent) {
        parentTile = parent;
    }
}
