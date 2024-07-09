package com.orbitalsonic.navigationmapboxupgraded;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class debuggermap extends AppCompatActivity {
    private GridLayout gridLayout;
    private TileView[][] tiles;
    private TileView startTile, endTile;
    private int[][] mapLayout;
    private ScrollView scrollViewVertical;
    private HorizontalScrollView scrollViewHorizontal;
    private HPA hpa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debuggermap);
        scrollViewVertical = findViewById(R.id.scrollViewVertical);
        scrollViewHorizontal = findViewById(R.id.scrollViewHorizontal);
        gridLayout = findViewById(R.id.gridLayout);
        createTiles();
        hpa = new HPA(mapLayout, new Handler(Looper.getMainLooper()));

        Button findPathButton1 = findViewById(R.id.findPathButton);
        findPathButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startTile != null && endTile != null) {
                    ArrayList<TileView> path = hpa.findPath(tiles, startTile, endTile);
                    if (path != null && !path.isEmpty()) {
                        visualizePath(path);
                    } else {
                        Log.d("Pathfinding", "Path not found");
                    }
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scrollViewVertical.onTouchEvent(event);
        scrollViewHorizontal.onTouchEvent(event);
        return true;
    }

    public void createTiles() {
        mapLayout = new int[][]{
                //10
                {1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 1,  1,  1,  1,  1,  1,  1,  1,  1,  1,},
                {1,  0,  0,  0,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  0,  0,  0,  1,  0, 0,  0,  0,  0,  1,  0,  0,  0,  0,  1,},
                {1,  0,  1,  1,  1,  0,  1,  0,  0,  0,  0,  0,  1,  0,  0,  0,  0,  0,  1,  0, 0,  0,  0,  0,  1,  0,  0,  0,  0,  1,},
                {1,  0,  1,  0,  1,  1,  1,  0,  0,  1,  0,  0,  1,  0,  0,  0,  0,  0,  1,  0, 0,  0,  0,  0,  1,  0,  0,  0,  0,  1,},
                {1,  0,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,  1,  0,  0,  0,  0,  0,  1,  0, 0,  0,  0,  0,  1,  0,  0,  0,  0,  1,},
                {1,  0,  1,  0,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  0,  0,  0,  1,  0, 0,  0,  0,  0,  1,  0,  0,  0,  0,  1,},
                {1,  0,  1,  0,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  0,  0,  0,  1,  0, 0,  0,  0,  0,  1,  0,  0,  0,  0,  1,},
                {1,  0,  0,  0,  0,  0,  1,  0,  0,  0,  0,  0,  1,  0,  0,  0,  0,  0,  1,  0, 0,  0,  0,  0,  1,  0,  0,  0,  0,  1,},
                {1,  1,  1,  0,  1,  1,  1,  1,  1,  0,  1,  1,  1,  0,  0,  0,  0,  0,  1,  0, 0,  0,  0,  0,  1,  0,  0,  0,  0,  1,},
                {1,  0,  0,  0,  0,  0,  1,  0,  0,  0,  0,  0,  1,  0,  0,  0,  0,  0,  1,  0, 0,  0,  0,  0,  1,  0,  0,  0,  0,  1,},
                {1,  0,  1,  1,  1,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  0,  0,  0,  1,  0, 0,  0,  0,  0,  1,  0,  0,  0,  0,  1,},
                {1,  0,  1,  0,  1,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  0,  0,  0,  1,  0, 0,  0,  0,  0,  1,  0,  0,  0,  0,  1,},
                {1,  0,  0,  0,  1,  0,  0,  0,  1,  1,  1,  0,  1,  0,  0,  0,  0,  0,  1,  0, 0,  0,  0,  0,  1,  0,  0,  0,  0,  1,},
                {1,  0,  1,  1,  1,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  0,  0,  0,  1,  0, 0,  0,  0,  0,  1,  0,  0,  0,  0,  1,},
                {1,  0,  1,  0,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  0,  0,  0,  1,  0, 0,  0,  0,  0,  1,  0,  0,  0,  0,  1,},
                {1,  0,  0,  0,  0,  0,  1,  0,  0,  0,  0,  0,  1,  0,  0,  0,  0,  0,  1,  0, 0,  0,  0,  0,  1,  0,  0,  0,  0,  1,},
                {1,  0,  1,  0,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  0,  0,  0,  1,  0, 0,  0,  0,  0,  1,  0,  0,  0,  0,  1,},
                {1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 1,  1,  1,  1,  1,  1,  1,  1,  1,  1,},

        };


        gridLayout.setRowCount(mapLayout.length);
        gridLayout.setColumnCount(mapLayout[0].length);
        tiles = new TileView[mapLayout.length][mapLayout[0].length];
        for (int i = 0; i < mapLayout.length; i++) {
            for (int j = 0; j < mapLayout[i].length; j++) {
                TileView tile = new TileView(this);
                tile.setTileType(mapLayout[i][j], i, j);

                if (mapLayout[i][j] == 7 || mapLayout[i][j] == 0) {
                    tile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onTileClicked((TileView) v);
                        }
                    });
                } else {
                    Log.d("TileView", "This tile is not clickable.");
                    tile.setClickable(false);
                }
                gridLayout.addView(tile);
                tiles[i][j] = tile;
            }
        }
    }

    private void onTileClicked(TileView tile) {
        if (startTile == null) {
            startTile = tile;
            startTile.setColorFilter(Color.argb(200, 0, 255, 0));
        } else if (endTile == null) {
            endTile = tile;
            endTile.setColorFilter(Color.argb(200, 255, 0, 0));
        } else {
            if (!tile.isWalkable()) {
                tile.setWalkable(true);
            } else {
                setAsBlockTile(tile);
            }
        }
    }

    private void setAsBlockTile(TileView tile) {
        tile.setWalkable(false);
        tile.setColorFilter(Color.BLACK);
        tile.setTag("blockTile");
    }

    private void clearPreviousSelections() {
        if (startTile != null) {
            startTile.clearColorFilter();
        }
        if (endTile != null) {
            endTile.clearColorFilter();
        }
    }

    private void visualizePath(ArrayList<TileView> path) {
        for (TileView tile : path) {
            tile.setPath(true); // Mark the tile as part of the path
        }
    }

    private void resetPathfinding() {
        clearPreviousSelections();
        startTile = null;
        endTile = null;

        // Clear any existing path visualization including dots
        if (tiles != null) {
            for (TileView[] row : tiles) {
                if (row != null) {
                    for (TileView tile : row) {
                        if (tile != null) {
                            tile.setPath(false); // Reset path state
                            tile.clearColorFilter(); // Clear color filter
                            tile.setDot(false, 0); // Clear dot
                            if ("blockTile".equals(tile.getTag())) {
                                tile.setWalkable(true); // Reset walkable state for block tiles
                            }
                        }
                    }
                }
            }
        }
    }
}
