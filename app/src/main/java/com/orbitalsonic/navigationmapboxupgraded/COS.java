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


public class COS extends AppCompatActivity {

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
        setContentView(R.layout.activity_cos);

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
                {748,  749,  748,  749,  749,  748,  749,  749,  748,  749, 748,  749,  754,  755,  758,   748,  749,  749, 748,  749, 749,  748,  749,  749,  748, 749,   749,  1,  1,  1,},
                {747,  793,  747,  794,  729,  747,  795,  796,  797,  798, 799,  800,  753,  756,  757,   747,  801,  676, 747,  802, 676,  747,  803,  676,  747,    793,  676,  1,  1,  1,},
                {747,  750,  751,  676,  752,  751,  676,  752,  747,  752, 747,  752,  762,  676,  676,   751,  676,  752, 751,  676, 752,  751,  676,  752,  747,    676,  752,  1,  1,  1,},
                {739,  740,  741,  742,  743,  741,  742,  743,  744,  740, 744,  740,  745,  676,  676,   741,  742,  743, 741,  742, 743,  741,  742,  743,  744,  746,  740,  1,  1,  1,},
                {770,  676,  676,  676,  676,  676,  676,  676,  676,  676, 676,  676,  759,  760,  761,   676,  676,  676, 676,  676, 676,  676,  676,  676,  676,  676,  783,  1,  1,  1,},
                {770,  785,  785,  676,  676,  676,  676,  676,  676,  676, 676,  676,  676,  676,  676,   676,  676,  676, 676,  676, 676,  676,  676,  676,  785,  785,  783,  1,  1,  1,},

                {769,  767,  763,  676,  676,  676,  676,  676,  676,  676, 676,  676,  676,  676,  676,   676,  676,  676, 676,  676, 676,  676,  676,  676,  778,  781,  784,  1,  1,  1,},
                {770,  804,  764,  676,  676,  775,  771,  771,  771,  771, 771,  771,  771,  771,  771,   771,  771,  771, 771,  771, 771,  774,  676,  676,  779,  801,  783,  1,  1,  1,},
                {770,  768,  765,  676,  676,  772,  786,  786,  786,  786, 786,  786,  786,  786,  786,   786,  786,  786, 786,  786, 786,  773,  676,  676,  780,  782,  783,  1,  1,  1,},
                {769,  767,  763,  676,  676,  772,  786,  729,  729,  729, 729,  729,  729,  729,  729,   729,  729,  729, 729,  729, 786,  773,  676,  676,  778,  781,  784,  1,  1,  1,},
                {770,  805,  764,  676,  676,  772,  786,  729,  729,  729, 729,  729,  729,  729,  729,   729,  729,  729, 729,  729, 786,  773,  676,  676,  779,  802,  783,  1,  1,  1,},

                {770,  768,  765,  676,  676,  772,  786,  729,  729,  729, 729,  729,  729,  729,  729,   729,  729,  729, 729,  729, 786,  773,  676,  676,  780,  782,  783,  1,  1,  1,},
                {766,  766,  766,  766,  766,  777,  786,  729,  729,  729, 729,  729,  729,  729,  729,   729,  729,  729, 729,  729, 786,  776,  766,  766,  766,  766,  766,  1,  1,  1,},
                {786,  786,  786,  786,  786,  786,  786,  729,  729,  729, 729,  729,  729,  729,  729,   729,  729,  729, 729,  729, 786,  786,  786,  786,  786,  786,  786,  1,  1,  1,},
                {729,  729,  729,  729,  729,  729,  729,  729,  729,  729, 729,  729,  729,  729,  729,   729,  729,  729, 729,  729, 729,  729,  729,  729,  729,  729,  729,  1,  1,  1,},
                {729,  729,  729,  729,  729,  729,  729,  729,  729,  729, 729,  729,  729,  729,  729,   729,  729,  729, 729,  729, 729,  729,  729,  729,  729,  729,  729,  1,  1,  1,},

//space
                {748,  749,  748,  749,  749,  749,  749,  748,  749,  749, 749,  749,  754,  755,  758,   748,  749,  749, 749, 749,  749,  748,  749,  749, 749,   748,  749,  1,  1,  1,},
                {747,  793,  747,  729,  821,  822,  729, 747,  729,  815, 816,  729,  753,  756,  757,   747,  729,  676, 817, 818,  729,  747,  729,  819,  820,  747,  793,  1,  1,  1,},
                {747,  750,  751,  676,  676,  676,  752,  751,  676,  676, 676,  752,  762,  676,  676,   751,  676,  676, 676, 676,  752,  751,  676,  676,  752,  747,  752,  1,  1,  1,},
                {739,  740,  741,  742,  746,  746,  743,  741,  742,  746, 746,  743,  745,  676,  676,   741,  742,  746, 746, 746,  743,  741,  742,  746,  743,  744,  740,  1,  1,  1,},
                {770,  676,  676,  676,  676,  676,  676,  676,  676,  676, 676,  676,  787,  788,  789,   676,  676,  676, 676,  676, 676,  676,  676,  676,  676,  676,  783,  1,  1,  1,},
                {770,  785,  785,  676,  676,  676,  676,  676,  676,  676, 676,  676,  676,  676,  676,   676,  676,  676, 676,  676, 676,  676,  676,  676,  785,  785,  783,  1,  1,  1,},

                {769,  767,  763,  676,  676,  676,  676,  676,  676,  676, 676,  676,  676,  676,  676,   676,  676,  676, 676,  676, 676,  676,  676,  676,  778,  781,  784,  1,  1,  1,},
                {770,  807,  764,  676,  676,  775,  771,  771,  771,  771, 771,  771,  771,  771,  771,   771,  771,  771, 771,  771, 771,  774,  676,  676,  779,  809,  783,  1,  1,  1,},
                {770,  768,  765,  676,  676,  772,  786,  786,  786,  786, 786,  786,  786,  786,  786,   786,  786,  786, 786,  786, 786,  773,  676,  676,  780,  782,  783,  1,  1,  1,},
                {769,  767,  763,  676,  676,  772,  786,  729,  729,  729, 729,  729,  729,  729,  729,   729,  729,  729, 729,  729, 786,  773,  676,  676,  778,  781,  784,  1,  1,  1,},
                {770,  806,  764,  676,  676,  772,  786,  729,  729,  729, 729,  729,  729,  729,  729,   729,  729,  729, 729,  729, 786,  773,  676,  676,  779,  808,  783,  1,  1,  1,},

                {770,  768,  765,  676,  676,  772,  786,  729,  729,  729, 729,  729,  729,  729,  729,   729,  729,  729, 729,  729, 786,  773,  676,  676,  780,  782,  783,  1,  1,  1,},
                {766,  766,  766,  766,  766,  777,  786,  729,  729,  729, 729,  729,  729,  729,  729,   729,  729,  729, 729,  729, 786,  776,  766,  766,  766,  766,  766,  1,  1,  1,},
                {786,  786,  786,  786,  786,  786,  786,  729,  729,  729, 729,  729,  729,  729,  729,   729,  729,  729, 729,  729, 786,  786,  786,  786,  786,  786,  786,  1,  1,  1,},
                {729,  729,  729,  729,  729,  729,  729,  729,  729,  729, 729,  729,  729,  729,  729,   729,  729,  729, 729,  729, 729,  729,  729,  729,  729,  729,  729,  1,  1,  1,},
                {729,  729,  729,  729,  729,  729,  729,  729,  729,  729, 729,  729,  729,  729,  729,   729,  729,  729, 729,  729, 729,  729,  729,  729,  729,  729,  729,  1,  1,  1,},


                //space
                {748,  749,  748,  749,  749,  749,  749,  748,  749,  749, 749,  749,  754,  755,  758,   748,  749,  749, 749, 749,  749,  749,  749,  749, 749,   748,  749,  1,  1,  1,},
                {747,  793,  747,  729,  823,  824,  729,  747,  729,  813, 729,  676,  753,  756,  757,   747,  729,  729, 729, 814,  729,  729,  729,  729, 676,  747,    793,  1,  1,  1,},
                {747,  750,  751,  676,  676,  676,  752,  751,  676,  676, 676,  752,  762,  676,  676,   751,  676,  676, 676, 676,  676,  676,  676,  676,  752,  747,  752,  1,  1,  1,},
                {739,  740,  741,  742,  746,  746,  743,  741,  742,  746, 746,  743,  745,  676,  676,   741,  742,  746, 746, 746,  746,  746,  746,  746,  743,  744,  740,  1,  1,  1,},
                {770,  676,  676,  676,  676,  676,  676,  676,  676,  676, 676,  676,  790,  791,  792,   676,  676,  676, 676,  676, 676,  676,  676,  676,  676,  676,  783,  1,  1,  1,},
                {770,  785,  785,  676,  676,  676,  676,  676,  676,  676, 676,  676,  676,  676,  676,   676,  676,  676, 676,  676, 676,  676,  676,  676,  785,  785,  783,  1,  1,  1,},

                {769,  767,  763,  676,  676,  676,  676,  676,  676,  676, 676,  676,  676,  676,  676,   676,  676,  676, 676,  676, 676,  676,  676,  676,  778,  781,  784,  1,  1,  1,},
                {770,  813,  764,  676,  676,  775,  771,  771,  771,  771, 771,  771,  771,  771,  771,   771,  771,  771, 771,  771, 771,  774,  676,  676,  779,  811,  783,  1,  1,  1,},
                {770,  768,  765,  676,  676,  772,  786,  786,  786,  786, 786,  786,  786,  786,  786,   786,  786,  786, 786,  786, 786,  773,  676,  676,  780,  782,  783,  1,  1,  1,},
                {769,  767,  763,  676,  676,  772,  786,  729,  729,  729, 729,  729,  729,  729,  729,   729,  729,  729, 729,  729, 786,  773,  676,  676,  778,  781,  784,  1,  1,  1,},
                {770,  812,  764,  676,  676,  772,  786,  729,  729,  729, 729,  729,  729,  729,  729,   729,  729,  729, 729,  729, 786,  773,  676,  676,  779,  810,  783,  1,  1,  1,},

                {770,  768,  765,  676,  676,  772,  786,  729,  729,  729, 729,  729,  729,  729,  729,   729,  729,  729, 729,  729, 786,  773,  676,  676,  780,  782,  783,  1,  1,  1,},
                {766,  766,  766,  766,  766,  777,  786,  729,  729,  729, 729,  729,  729,  729,  729,   729,  729,  729, 729,  729, 786,  776,  766,  766,  766,  766,  766,  1,  1,  1,},
                {786,  786,  786,  786,  786,  786,  786,  729,  729,  729, 729,  729,  729,  729,  729,   729,  729,  729, 729,  729, 786,  786,  786,  786,  786,  786,  786,  1,  1,  1,},
                {729,  729,  729,  729,  729,  729,  729,  729,  729,  729, 729,  729,  729,  729,  729,   729,  729,  729, 729,  729, 729,  729,  729,  729,  729,  729,  729,  1,  1,  1,},
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
                if (mapLayout[i][j] == 676 ||mapLayout[i][j] == 740
                        ||mapLayout[i][j] == 741||mapLayout[i][j] == 743||mapLayout[i][j] == 745
                        ||mapLayout[i][j] == 753||mapLayout[i][j] == 754||mapLayout[i][j] == 755
                        ||mapLayout[i][j] == 758||mapLayout[i][j] == 757||mapLayout[i][j] == 762
                        ||mapLayout[i][j] == 759||mapLayout[i][j] == 760||mapLayout[i][j] == 761
                        ||mapLayout[i][j] == 763||mapLayout[i][j] == 765||mapLayout[i][j] == 778
                        ||mapLayout[i][j] == 780||mapLayout[i][j] == 785||mapLayout[i][j] == 787
                        ||mapLayout[i][j] == 788||mapLayout[i][j] == 789||mapLayout[i][j] == 790
                        ||mapLayout[i][j] == 791||mapLayout[i][j] == 792||mapLayout[i][j] == 801
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

        if (tile.getType() == 801 ) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, cossched.class);
            startActivity(intent);
        } else if (tile.getType() == 802 ) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, cossched.class);
            startActivity(intent);
        } else if (tile.getType() == 803) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, cossched.class);
            startActivity(intent);
        } else if (tile.getType() == 804 ) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, cossched.class);
            startActivity(intent);
        } else if (tile.getType() == 805 ) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, cossched.class);
            startActivity(intent);
        } else if (tile.getType() == 806) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, cossched.class);
            startActivity(intent);
        } else if (tile.getType() == 807) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, cossched.class);
            startActivity(intent);
        } else if (tile.getType() == 808) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, cossched.class);
            startActivity(intent);
        } else if (tile.getType() == 809) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, cossched.class);
            startActivity(intent);
        } else if (tile.getType() == 810) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, cossched.class);
            startActivity(intent);
        } else if (tile.getType() == 811) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, cossched.class);
            startActivity(intent);
        } else if (tile.getType() == 812) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, cossched.class);
            startActivity(intent);
        } else if (tile.getType() == 813) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, cossched.class);
            startActivity(intent);
        } else if (tile.getType() == 814) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, cossched.class);
            startActivity(intent);
        } else if (tile.getType() == 815) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, cossched.class);
            startActivity(intent);
        } else if (tile.getType() == 816) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, cossched.class);
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




