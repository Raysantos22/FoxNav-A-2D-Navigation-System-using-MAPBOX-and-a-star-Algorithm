package com.orbitalsonic.navigationmapboxupgraded;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class CPAG extends AppCompatActivity {

    private GridLayout gridLayout;
    private TileView[][] tiles;
    private TileView startTile, endTile;
    private int[][] mapLayout;
    private float scaleFactor = 1f;
    private boolean isClickable = true;
    private int tileType;

    private ScrollView scrollViewVertical;
    private HorizontalScrollView scrollViewHorizontal;


    private ScaleGestureDetector scaleGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpag);

        scrollViewVertical = findViewById(R.id.scrollViewVertical);
        scrollViewHorizontal = findViewById(R.id.scrollViewHorizontal);

        gridLayout = findViewById(R.id.gridLayout);
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        createTiles();

        Button findPathButton1 = findViewById(R.id.findPathButton);
        findPathButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startTile != null && endTile != null) {
                    drawPath();
                }
            }
        });

        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPathfinding();
            }
        });
    }
    private void zoomOut() {
        scaleFactor -= 0.1f; // Decrease the scale factor
        scaleFactor = Math.max(0.1f, scaleFactor); // Ensure minimum scale factor is 0.1
        gridLayout.setScaleX(scaleFactor);
        gridLayout.setScaleY(scaleFactor);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scrollViewVertical.onTouchEvent(event);
        scrollViewHorizontal.onTouchEvent(event);
        return true;
    }
    public void createTiles() {
        mapLayout = new int[][]{
                //10
                {729,  729,  729,  729,  729,  729,  729,  729,  729,  827,  826,  826,  826,  826,  826,   826,  840,  729, 729,  729, 729,  729,  729,  729,  729,  729,  729,  676,  1,  1,},
                {834,  836,  836,  836,  838,  729,  729,  729,  729,  829,  729,  867,  868,  676,  676,   729,  829,  729, 729,  729, 729,  729,  845,  843,  843,  843,  841,  676,  1,  1,},
                {835,  837,  837,  837,  676,  839,  839,  839,  839,  839,  839,  839,  839,  869,  870,   839,  839,  839, 839,  839, 839,  839,  676,  844,  844,  844,  842,  676,  1,  1,},

                {676,  676,  676,  676,  676,  676,  676,  676,  676,  676,  759,  760,  761,  676,  676,   676,  676,  676, 676,  676, 676,  676,  676,  676,  676,  676,  676,  676,  1,  1,},

                {827,  826,  825,  827,  826,  826,  826,  825,  827,  826,  826,  826,  825,  827,  826,   826,  826,  825, 832,  826, 826,  826,  826,  825,  832,  826,  826,  827,  1,  1,},
                {829,  676,  828,  829,  676,  676,  676,  828,  829,  676,  676,  676,  828,  829,  676,   676,  676,  828, 833,  676, 676,  676,  676,  828,  833,  676,  676,  829,  1,  1,},
                {829,  857,  858,  829,  854,  855,  856,  676,  829,  851,  852,  853,  676,  829,  676,   849,  850,  676, 829,  676, 847,  848,  676,  676,  829,  846,  676,  829,  1,  1,},
                {829,  729,  729,  829,  676,  676,  676,  676,  829,  676,  676,  676,  676,  829,  676,   676,  676,  676, 829,  676, 676,  676,  676,  676,  829,  676,  676,  829,  1,  1,},
                {830,  831,  831,  830,  831,  831,  831,  831,  830,  831,  831,  831,  831,  830,  831,   831,  831,  831, 830,  831, 831,  831,  831,  831,  830,  831,  831,  830,  1,  1,},


                {786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786,},
                {786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786,},
                {786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786,},
                {729,  729,  729,  729,  729,  729,  729,  729,  729,  729, 729,  729,  729,  729,  729,   729,  729,  729, 729,  729, 729,  729,  729,  729,  729,  729,  729,  676,  1,  1,},
//2nd floor
                {834,  836,  836,  836,  838,  729,  729,  729,  729,  729,  729,  729,  729,  729,  729,   729,  729,  729, 729,  729, 729,  729,  845,  843,  843,  843,  841,  676,  1,  1,},
                {835,  837,  837,  837,  676,  839,  839,  839,  839,  839,  839,  839,  839,  839,  839,   839,  839,  839, 839,  839, 839,  839,  676,  844,  844,  844,  842,  676,  1,  1,},

                {676,  676,  676,  676,  676,  676,  676,  676,  676,  676,  787,  788,  789,  676,  676,   676,  676,  676, 676,  676, 676,  676,  676,  676,  676,  676,  676,  676,  1,  1,},

                {827,  826,  825,  827,  826,  826,  826,  825,  827,  826,  826,  826,  825,  827,  826,   826,  826,  825, 827,  826, 826,  826,  826,  825,  832,  826,  826,  827,  676,  1,},
                {829,  676,  828,  829,  676,  676,  676,  828,  829,  676,  676,  676,  828,  829,  676,   676,  676,  828, 829,  676, 676,  676,  676,  828,  833,  676,  676,  829,  676,  1,},
                {829,  857,  858,  829,  676,  859,  676,  676,  829,  676,  860,  676,  676,  829,  676,   861,  676,  676, 829,  676, 676,  862,  676,  676,  829,  846,  676,  829,  676,  1,},
                {829,  729,  729,  829,  676,  676,  676,  676,  829,  676,  676,  676,  676,  829,  676,   676,  676,  676, 829,  676, 676,  676,  676,  676,  829,  676,  676,  829,  676,  1,},
                {830,  831,  831,  830,  831,  831,  831,  831,  830,  831,  831,  831,  831,  830,  831,   831,  831,  831, 830,  831, 831,  831,  831,  831,  830,  831,  831,  830,  676,  1,},

                {786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786,},
                {786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786,},
                {786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786,},
                {729,  729,  729,  729,  729,  729,  729,  729,  729,  729, 729,  729,  729,  729,  729,   729,  729,  729, 729,  729, 729,  729,  729,  729,  729,  729,  729,  676,  1,  1,},
//3nd floor
                {834,  836,  836,  836,  838,  729,  729,  729,  729,  729,  729,  729,  729,  729,  729,   729,  729,  729, 729,  729, 729,  729,  845,  843,  843,  843,  841,  676,  1,  1,},
                {835,  837,  837,  837,  676,  839,  839,  839,  839,  839,  839,  839,  839,  839,  839,   839,  839,  839, 839,  839, 839,  839,  676,  844,  844,  844,  842,  676,  1,  1,},

                {676,  676,  676,  676,  676,  676,  676,  676,  676,  676,  790,  791,  792,  676,  676,   676,  676,  676, 676,  676, 676,  676,  676,  676,  676,  676,  676,  676,  1,  1,},

                {827,  826,  825,  827,  826,  826,  826,  825,  827,  826,  826,  826,  825,  827,  826,   826,  826,  825, 827,  826, 826,  826,  826,  825,  832,  826,  826,  827,  676,  1,},
                {829,  676,  828,  829,  676,  676,  676,  828,  829,  676,  676,  676,  828,  829,  676,   676,  676,  828, 829,  676, 676,  676,  676,  828,  833,  676,  676,  829,  676,  1,},
                {829,  857,  858,  829,  676,  863,  676,  676,  829,  676,  864,  676,  676,  829,  676,   865,  676,  676, 829,  676, 676,  866,  676,  676,  829,  846,  676,  829,  676,  1,},
                {829,  676,  676,  829,  676,  676,  676,  676,  829,  676,  676,  676,  676,  829,  676,   676,  676,  676, 829,  676, 676,  676,  676,  676,  829,  676,  676,  829,  676,  1,},
                {830,  831,  831,  830,  831,  831,  831,  831,  830,  831,  831,  831,  831,  830,  831,   831,  831,  831, 830,  831, 831,  831,  831,  831,  830,  831,  831,  830,  676,  1,},

                {786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786,},
                {786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786,},
                {786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786,},
                {729,  729,  729,  729,  729,  729,  729,  729,  729,  729, 729,  729,  729,  729,  729,   729,  729,  729, 729,  729, 729,  729,  729,  729,  729,  729,  729,  1,  1,  1,},


        };
        gridLayout.setRowCount(mapLayout.length);
        gridLayout.setColumnCount(mapLayout[0].length);
        tiles = new TileView[mapLayout.length][mapLayout[0].length];

        for (int i = 0; i < mapLayout.length; i++) {
            for (int j = 0; j < mapLayout[i].length; j++) {
                TileView tile = new TileView(this);
                tile.setTileType(mapLayout[i][j], i, j);

                // Check if the tile should be clickable or not based on the tile type
                if (mapLayout[i][j] == 825 ||mapLayout[i][j] == 832
                        ||mapLayout[i][j] == 836||mapLayout[i][j] == 834||mapLayout[i][j] == 839
                        ||mapLayout[i][j] == 676||mapLayout[i][j] == 841||mapLayout[i][j] == 843
                        ||mapLayout[i][j] == 845||mapLayout[i][j] == 846||mapLayout[i][j] == 847
                        ||mapLayout[i][j] == 848||mapLayout[i][j] == 760||mapLayout[i][j] == 761
                        ||mapLayout[i][j] == 859||mapLayout[i][j] == 860||mapLayout[i][j] == 861
                        ||mapLayout[i][j] == 862||mapLayout[i][j] == 863||mapLayout[i][j] == 864
                        ||mapLayout[i][j] == 865||mapLayout[i][j] == 866||mapLayout[i][j] == 868
                        ||mapLayout[i][j] == 869||mapLayout[i][j] == 870||mapLayout[i][j] == 801
                        ||mapLayout[i][j] == 802||mapLayout[i][j] == 803||mapLayout[i][j] == 806
                        ||mapLayout[i][j] == 804||mapLayout[i][j] == 805||mapLayout[i][j] == 807
                        ||mapLayout[i][j] == 808||mapLayout[i][j] == 809||mapLayout[i][j] == 810
                        ||mapLayout[i][j] == 811||mapLayout[i][j] == 812||mapLayout[i][j] == 813
                        ||mapLayout[i][j] == 814||mapLayout[i][j] == 815)
                {
                    tile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onTileClicked((TileView) v);
                        }
                    });
                } else {
                    // Make tile not clickable
                    Log.d("TileView", "This tile is not clickable.");
                    tile.setClickable(false);
                }

                gridLayout.addView(tile);
                tiles[i][j] = tile;
            }
        }
    }
    private void onTileClicked(TileView tile) {
        int imageResource = 0;

        if (tile.getType() == 859 ) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, cpagsched.class);
            startActivity(intent);
        } else if (tile.getType() == 860 ) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, cpagsched.class);
            startActivity(intent);
        } else if (tile.getType() == 861) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, cpagsched.class);
            startActivity(intent);
        } else if (tile.getType() == 862 ) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, cpagsched.class);
            startActivity(intent);
        } else if (tile.getType() == 863 ) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, cpagsched.class);
            startActivity(intent);
        } else if (tile.getType() == 864) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, cpagsched.class);
            startActivity(intent);
        } else if (tile.getType() == 865) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, cpagsched.class);
            startActivity(intent);
        } else if (tile.getType() == 866) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, cpagsched.class);
            startActivity(intent);


        } else {
            if (startTile == null) {
                startTile = tile;
                startTile.setColorFilter(Color.argb(200, 0, 255, 0));
            } else if (endTile == null) {
                endTile = tile;
                endTile.setColorFilter(Color.argb(200, 255, 0, 0));
            } else {
                if (!tile.isWalkable()) {
                    // If the tile is already non-walkable, make it walkable again
                    tile.setWalkable(true);
                } else {
                    // Otherwise, set the tile as non-walkable
                    setAsBlockTile(tile);

                }

            }
        }

    }

    private void setAsBlockTile(TileView tile) {
        tile.setWalkable(false);
        tile.setColorFilter(Color.BLACK); // Set color to black
        tile.setTag("blockTile"); // Assign a name to identify it later
    }


    private void clearPreviousSelections() {
        if (startTile != null) {
            startTile.clearColorFilter();
        }
        if (endTile != null) {
            endTile.clearColorFilter();
        }
    }

    private void drawPath() {
        // Find path using A* algorithm
        ArrayList<TileView> path = TileView.findPath(tiles, startTile, endTile);
        if (path != null && path.size() > 1) {
            for (int i = 1; i < path.size() - 1; i++) {
                path.get(i).setPath(true); // Mark intermediate tiles as part of the path
            }
        } else {
            // Handle case when no valid path is found
        }
    }


    private void resetPathfinding() {
        clearPreviousSelections();
        startTile = null;
        endTile = null;
        // Clear any existing path visualization
        if (tiles != null) {
            for (TileView[] row : tiles) {
                if (row != null) {
                    for (TileView tile : row) {
                        if (tile != null) {
                            tile.setPath(false);
                            // Check if the tile has the tag "blockTile" and reset it
                            if ("blockTile".equals(tile.getTag())) {
                                tile.setWalkable(true);
                                // Optionally, reset the color of the tile as well
                                tile.clearColorFilter();
                            }
                        }
                    }
                }
            }
        }
    }
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= detector.getScaleFactor();
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f)); // Clamp scale factor
            gridLayout.setScaleX(scaleFactor);
            gridLayout.setScaleY(scaleFactor);
            return true;
        }
    }
}




