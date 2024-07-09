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


public class CCJE extends AppCompatActivity {

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
        setContentView(R.layout.activity_ccje);

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
                {729,  729,  729,  729,  729,  729,  729,  729,  729,  729, 729,  729,  729,  729,  729,  729,  729,  729,  729,  729, 729,  729,  729,  729,  729,  729,  729,  729,  729,  729,},
                {729,  729,  729,  729,  729,  729,  729,  729,  729,  729, 729,  729,  729,  729,  729,  729,  729,  729,  729,  729, 729,  729,  729,  729,  729,  729,  729,  729,  729,  729,},
                {729,  729,  729,  729,  729,  729,  729,  729,  729,  729, 729,  729,  729,  729,  729,  729,  729,  729,  729,  729, 729,  729,  729,  729,  729,  729,  729,  729,  729,  729,},
                {729,  729,  729,  729,  729,  729,  729,  729,  729,  729, 729,  729,  729,  729,  729,  729,  729,  729,  729,  729, 729,  729,  729,  729,  729,  729,  729,  729,  729,  729,},

                {684,  694,  685,  698,  694,  704,  698,  694,  704,  698, 694,  704,  708,  709, 709,   713,  698,  694,  704,  698, 694,   704,  698,  694,  704,  698,  694,  694,704,  684,},
                {691,  692,  693,  697,  728,  703,  697,  722,  703,  697,723,    703,  707,  710, 710,   711,  697,  724,    703,  697, 725,    703,  697,  726,    703,  697,  727, 676,   703,  682,},
                {689,  676,  690,  696,  676,  702,  696,  676,  702,  696, 676,  702,  706,  712, 712,   706,  696,  676,  702,  696,676,   702,  696,  676,  702,  696,  676,676, 702,  678,},
                {686,  687,  688,  695,  699,  701,  695,  699,  701,  695, 699,  701,  714,  676,  676,  676,  695,  699,  701,  695, 699,  701,  695,  699,  701,  695,  699,700, 700,  695,},
                {676,  676,  676,  676,  676,  676,  676,  676,  676,  676, 676,  676,  734,  735,  736,  737,  676,  676, 676,  676, 676,  676,  676,  676,  676,  676,  676,  676,  676,  676,},
                {681,  681,  681,  681,  681,  681,  681,  681,  681,  681, 681,  681,  681,  681,  681,  681,  681,  681, 681,  681, 681,  681,  681,  681,  681,  681,  681,  681,  681,  681,},

                {786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786,},
                {786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786,},
                {786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786,},
                {786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786,},

                {729,  729,  729,  729,  729,  729,  729,  729,  729,  729, 729,  729,  729,  729,  729,  729,  729,  729,  729,  729, 729,  729,  729,  729,  729,  729,  729,  729,  729,  729,},

                {684,  685,  698,  694,  694,  694,  694,  694,  704,  698, 694,  704,  708,  709,  709,  713,  698,  694,  704,  698, 694,704,  698,  694,  704,  698,  694,  704,  684,  1,},
                {682,  683,  697,  676,  715,  716,  676,676,  703,  697, 717,    703,  707,  710,  710,  711,  697,  718,    703,  697, 719,  703,  697,  720,    703,  697,  721,    703,  682,  1,},
                {678,  680,  696,  676,  676,  676,  676, 676,  702,  696, 676,  702,  706,  712,  712,  706,  696,  676,  702,  696,676, 702,  696,  676,  702,  696,  676,  702,  678,  1,},
                {677,  679,  695,  699,  700,  700,  700,  700,  701,  695, 699,  701,  714,  676,  676,  676,  695,  699,  701,  695, 699,701,  695,  699,  701,  695,  699,  701,  695,  1,},
                {676,  676,  676,  676,  676,  676,  676,  676,  676,  676, 676,  676,  730,  731,  732,  733,  676,  676, 676,  676, 676,  676,  676,  676,  676,  676,  676,  676,  676,  676,},
                {681,  681,  681,  681,  681,  681,  681,  681,  681,  681, 681,  681,  681,  681,  681,  681,  681,  681, 681,  681, 681,  681,  681,  681,  681,  681,  681,  681,  681,  681,},
                {786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786,},
                {786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786,},
                {786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786,},
                {786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786, 786,  786,  786,  786,  786,  786,  786,  786,  786,  786,},

        };
        gridLayout.setRowCount(mapLayout.length);
        gridLayout.setColumnCount(mapLayout[0].length);
        tiles = new TileView[mapLayout.length][mapLayout[0].length];

        for (int i = 0; i < mapLayout.length; i++) {
            for (int j = 0; j < mapLayout[i].length; j++) {
                TileView tile = new TileView(this);
                tile.setTileType(mapLayout[i][j], i, j);

                // Check if the tile should be clickable or not based on the tile type
                if (mapLayout[i][j] == 0 ||mapLayout[i][j] == 678
                        ||mapLayout[i][j] == 676||mapLayout[i][j] == 682||mapLayout[i][j] == 677
                        ||mapLayout[i][j] == 686||mapLayout[i][j] == 688||mapLayout[i][j] == 681
                        ||mapLayout[i][j] == 701||mapLayout[i][j] == 695||mapLayout[i][j] == 705
                        ||mapLayout[i][j] == 706||mapLayout[i][j] == 707||mapLayout[i][j] == 708
                        ||mapLayout[i][j] == 7013||mapLayout[i][j] == 710||mapLayout[i][j] == 714
                        ||mapLayout[i][j] == 715||mapLayout[i][j] == 716||mapLayout[i][j] == 717
                        ||mapLayout[i][j] == 718||mapLayout[i][j] == 719||mapLayout[i][j] == 720
                        ||mapLayout[i][j] == 721||mapLayout[i][j] == 722||mapLayout[i][j] == 723
                        ||mapLayout[i][j] == 724||mapLayout[i][j] == 725||mapLayout[i][j] == 726
                        ||mapLayout[i][j] == 730||mapLayout[i][j] == 731||mapLayout[i][j] == 732
                        ||mapLayout[i][j] == 733||mapLayout[i][j] == 734||mapLayout[i][j] == 735
                        ||mapLayout[i][j] == 736||mapLayout[i][j] == 737)
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

        if (tile.getType() == 717 ) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 718 ) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity10.class);
            startActivity(intent);
        } else if (tile.getType() == 719) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, ls10.class);
            startActivity(intent);
        } else if (tile.getType() == 720 ) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, ls11.class);
            startActivity(intent);
        } else if (tile.getType() == 721 ) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, ls12.class);
            startActivity(intent);
        } else if (tile.getType() == 722) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, rc1.class);
            startActivity(intent);
        } else if (tile.getType() == 723) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, rc2.class);
            startActivity(intent);
        } else if (tile.getType() == 724) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, rc4.class);
            startActivity(intent);
        } else if (tile.getType() == 725) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, rc5.class);
            startActivity(intent);
        } else if (tile.getType() == 726) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, rc6.class);
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




