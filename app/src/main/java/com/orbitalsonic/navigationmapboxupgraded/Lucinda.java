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
import android.widget.CheckBox;
import android.widget.CompoundButton;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class Lucinda extends AppCompatActivity {
    private CheckBox walkableCheckbox;

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
        setContentView(R.layout.lucinda);

        scrollViewVertical = findViewById(R.id.scrollViewVertical);
        scrollViewHorizontal = findViewById(R.id.scrollViewHorizontal);

        gridLayout = findViewById(R.id.gridLayout);
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        createTiles();

        walkableCheckbox = findViewById(R.id.checkBox_tile_walkable);
        walkableCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Update the clickability of specific tile type based on the checkbox state
                updateTileClickabilityForSpecificType(isChecked);
            }
        });


        Button findPathButton = findViewById(R.id.findPathButton);
        findPathButton.setOnClickListener(new View.OnClickListener() {
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
                {1, 1, 135, 136, 243, 242, 227, 241, 239, 238, 237, 1, 462, 463, 464, 465, 466, 467, 443, 1, 1, 1},
                {1, 133, 134, 137, 215, 227, 227, 227, 227, 227, 240, 461, 468, 460, 456, 457, 458, 459, 443, 1, 1, 2},
                {1, 132, 138, 137, 215, 227, 220, 221, 222, 223, 227, 235, 1, 322, 451, 452, 453, 454, 443, 1, 1, 2},
                {1, 131, 138, 137, 215, 227, 216, 217, 218, 219, 227, 234, 1, 322, 447, 448, 449, 450, 443, 1, 1, 2},
                {1, 128, 129, 130, 215, 227, 227, 227, 227, 227, 227, 233, 1, 322, 444, 455, 445, 446, 443, 1, 1, 2},
                {1, 1, 1, 127, 213, 227, 227, 227, 227, 227, 231, 232, 1, 373, 438, 439, 440, 441, 442, 1, 1, 2},
                {123, 124, 125, 126, 208, 209, 210, 227, 212, 229, 230, 1, 1, 322, 434, 435, 436, 437, 1, 1, 1, 2},
                {119, 120, 193, 122, 203, 204, 205, 206, 207, 228, 1, 1, 1, 372, 429, 430, 431, 432, 433, 1, 2},
                {1, 115, 116, 117, 118, 198, 199, 200, 201, 202, 249, 1, 1, 370, 371, 424, 425, 426, 427, 428, 1, 2},
                {1, 1, 112, 113, 114, 192, 194, 195, 196, 197, 249, 366, 367, 368, 369, 420, 421, 422, 423, 1, 1, 2},
                //10
                {1, 108, 109, 110, 111, 178, 179, 175, 180, 181, 365, 358, 359, 360, 361, 416, 417, 418, 419, 1, 1, 2},
                {1, 472, 473, 474, 1, 1, 171, 170, 172, 173, 364, 354, 355, 356, 357, 1, 1, 1, 1, 1, 1, 2},
                {1, 470, 469, 471, 1, 1, 0, 165, 167, 168, 249, 351, 352, 353, 322, 1, 1, 1, 1, 1, 1, 2},
                {1, 104, 105, 106, 107, 1, 0, 313, 313, 313, 363, 313, 313, 313, 337, 1, 412, 413, 414, 1, 1, 2},
                {1, 100, 101, 102, 103, 1, 0, 1, 1, 1, 249, 340, 339, 338, 322, 407, 408, 409, 410, 411, 1, 2},
                {1, 96, 97, 98, 99, 1, 0, 1, 1, 1, 350, 347, 348, 349, 335, 401, 402, 403, 404, 405, 1, 2},
                {1, 92, 93, 94, 95, 145, 146, 163, 164, 248, 249, 344, 345, 346, 336, 1, 399, 400, 1, 1, 1, 2},
                {1, 88, 89, 90, 91, 143, 144, 161, 162, 247, 249, 341, 342, 343, 334, 393, 394, 1, 1, 1, 1, 2},
                {1, 84, 85, 86, 87, 141, 142, 159, 160, 246, 362, 313, 313, 313, 333, 388, 389, 395, 396, 397, 1, 2},
                {1, 80, 81, 82, 83, 139, 140, 157, 158, 244, 249, 329, 330, 331, 322, 1, 398, 390, 391, 392, 1, 2},
                {1, 76, 77, 78, 79, 148, 150, 155, 156, 245, 249, 326, 327, 328, 332, 383, 384, 385, 386, 387, 1, 2},
                {1, 72, 73, 74, 75, 149, 151, 1, 1, 1, 249, 323, 324, 325, 321, 378, 379, 380, 381, 382, 1, 2},
                //10
                {1, 68, 69, 70, 71, 1, 1, 1, 1, 1, 249, 317, 318, 319, 320, 374, 375, 376, 377, 1, 1, 2},
                {1, 59, 60, 61, 62, 63, 64, 65, 66, 67, 260, 262, 313, 314, 315, 313, 313, 316, 313, 1, 1, 2},
                {1, 56, 57, 58, 9, 10, 11, 12, 13, 14, 261, 1, 1, 264, 1, 1, 1, 310, 307, 308, 1, 2},
                {1, 53, 54, 55, 8, 1, 1, 147, 1, 154, 249, 1, 1, 264, 1, 1, 1, 311, 305, 306, 1, 2},
                {1, 50, 51, 52, 7, 1, 1, 1, 153, 152, 249, 259, 268, 271, 274, 277, 280, 312, 303, 304, 1, 2},
                {1, 47, 48, 49, 6, 1, 1, 1, 1, 1, 256, 258, 267, 270, 273, 276, 279, 282, 301, 302, 1, 2},
                {1, 44, 45, 46, 5, 1, 1, 1, 1, 1, 249, 257, 266, 269, 272, 275, 278, 281, 299, 300, 1, 2},
                {1, 41, 40, 43, 4, 148, 150, 1, 148, 150, 250, 251, 251, 255, 293, 294, 295, 296, 297, 298, 1, 2},
                {1, 38, 39, 42, 3, 149, 151, 1, 149, 151, 252, 286, 288, 284, 289, 290, 291, 292, 1, 1, 1, 2},
                {1, 1, 1, 1, 254, 1, 1, 1, 1, 1, 253, 285, 287, 1, 1, 1, 1, 1, 1, 1, 1, 1},


        };
        CheckBox walkableCheckbox = findViewById(R.id.checkBox_tile_walkable); // Assuming you have a checkbox with this ID in your layout XML

        gridLayout.setRowCount(mapLayout.length);
        gridLayout.setColumnCount(mapLayout[0].length);
        tiles = new TileView[mapLayout.length][mapLayout[0].length];

        for (int i = 0; i < mapLayout.length; i++) {
            for (int j = 0; j < mapLayout[i].length; j++) {
                TileView tile = new TileView(this);
                tile.setTileType(mapLayout[i][j], i, j);


                // Check if the tile should be clickable or not based on the tile type
                if (mapLayout[i][j] == 1 || mapLayout[i][j] == 0 || mapLayout[i][j] == 260 || mapLayout[i][j] == 311 || mapLayout[i][j] == 262 || mapLayout[i][j] == 263 || mapLayout[i][j] == 256 || mapLayout[i][j] == 249 || mapLayout[i][j] == 3 || mapLayout[i][j] == 4 || mapLayout[i][j] == 44 || mapLayout[i][j] == 5 || mapLayout[i][j] == 6 || mapLayout[i][j] == 7 || mapLayout[i][j] == 8 || mapLayout[i][j] == 9 || mapLayout[i][j] == 10 || mapLayout[i][j] == 11 || mapLayout[i][j] == 12 || mapLayout[i][j] == 13 || mapLayout[i][j] == 14 || mapLayout[i][j] == 15 || mapLayout[i][j] == 16 || mapLayout[i][j] == 17 || mapLayout[i][j] == 18 || mapLayout[i][j] == 19 || mapLayout[i][j] == 20 || mapLayout[i][j] == 21 || mapLayout[i][j] == 22 || mapLayout[i][j] == 25 || mapLayout[i][j] == 26 || mapLayout[i][j] == 27 || mapLayout[i][j] == 28 || mapLayout[i][j] == 29 || mapLayout[i][j] == 32 || mapLayout[i][j] == 33 || mapLayout[i][j] == 35 || mapLayout[i][j] == 36 || mapLayout[i][j] == 37 || mapLayout[i][j] == 62 || mapLayout[i][j] == 71 || mapLayout[i][j] == 75 || mapLayout[i][j] == 79 || mapLayout[i][j] == 83 || mapLayout[i][j] == 87 || mapLayout[i][j] == 91 || mapLayout[i][j] == 95 || mapLayout[i][j] == 141 || mapLayout[i][j] == 144 || mapLayout[i][j] == 146 || mapLayout[i][j] == 165 || mapLayout[i][j] == 167 || mapLayout[i][j] == 168 || mapLayout[i][j] == 171
                        || mapLayout[i][j] == 63 || mapLayout[i][j] == 64 || mapLayout[i][j] == 65 || mapLayout[i][j] == 66 || mapLayout[i][j] == 67 || mapLayout[i][j] == 112 || mapLayout[i][j] == 113 || mapLayout[i][j] == 272 || mapLayout[i][j] == 273 || mapLayout[i][j] == 274 || mapLayout[i][j] == 275 || mapLayout[i][j] == 276 || mapLayout[i][j] == 277 || mapLayout[i][j] == 278 || mapLayout[i][j] == 279 || mapLayout[i][j] == 280
                        || mapLayout[i][j] == 354 || mapLayout[i][j] == 356
                        || mapLayout[i][j] == 389 || mapLayout[i][j] == 391 || mapLayout[i][j] == 392
                        || mapLayout[i][j] == 395 || mapLayout[i][j] == 396 || mapLayout[i][j] == 397 || mapLayout[i][j] == 398

                        || mapLayout[i][j] == 385 || mapLayout[i][j] == 386 || mapLayout[i][j] == 387 || mapLayout[i][j] == 388 || mapLayout[i][j] == 390
                        || mapLayout[i][j] == 378 || mapLayout[i][j] == 379 || mapLayout[i][j] == 380 || mapLayout[i][j] == 381 || mapLayout[i][j] == 382
                        || mapLayout[i][j] == 289 || mapLayout[i][j] == 290 || mapLayout[i][j] == 291 || mapLayout[i][j] == 292 || mapLayout[i][j] == 293 || mapLayout[i][j] == 294
                        || mapLayout[i][j] == 183
                        || mapLayout[i][j] == 178 || mapLayout[i][j] == 179 || mapLayout[i][j] == 184 || mapLayout[i][j] == 252 || mapLayout[i][j] == 250 || mapLayout[i][j] == 251 || mapLayout[i][j] == 253 || mapLayout[i][j] == 254 || mapLayout[i][j] == 255 || mapLayout[i][j] == 261 || mapLayout[i][j] == 258 || mapLayout[i][j] == 264 || mapLayout[i][j] == 267 || mapLayout[i][j] == 269 || mapLayout[i][j] == 270 || mapLayout[i][j] == 271 || mapLayout[i][j] == 310 || mapLayout[i][j] == 313 || mapLayout[i][j] == 314 || mapLayout[i][j] == 315 || mapLayout[i][j] == 316 || mapLayout[i][j] == 320 || mapLayout[i][j] == 321 || mapLayout[i][j] == 322 || mapLayout[i][j] == 332 || mapLayout[i][j] == 333 || mapLayout[i][j] == 334 || mapLayout[i][j] == 335 || mapLayout[i][j] == 336 || mapLayout[i][j] == 337 || mapLayout[i][j] == 350 || mapLayout[i][j] == 351 || mapLayout[i][j] == 357 || mapLayout[i][j] == 361 || mapLayout[i][j] == 362 || mapLayout[i][j] == 363 || mapLayout[i][j] == 365 || mapLayout[i][j] == 369 || mapLayout[i][j] == 372 || mapLayout[i][j] == 373 || mapLayout[i][j] == 438 || mapLayout[i][j] == 439 || mapLayout[i][j] == 440 || mapLayout[i][j] == 441 || mapLayout[i][j] == 442 || mapLayout[i][j] == 443 || mapLayout[i][j] == 460 || mapLayout[i][j] == 463 || mapLayout[i][j] == 394 || mapLayout[i][j] == 393 || mapLayout[i][j] == 364 || mapLayout[i][j] == 309 || mapLayout[i][j] == 283) {
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
    private void updateTileClickabilityForSpecificType(boolean isChecked) {
        int[] specificTileTypes = {1, 30, 31,32,34,35,38,39,40,41,149,151,148,150}; // Change this to the specific tile types you want to control

        for (int specificTileType : specificTileTypes) {
            for (int i = 0; i < mapLayout.length; i++) {
                for (int j = 0; j < mapLayout[i].length; j++) {
                    if (mapLayout[i][j] == specificTileType) {
                        TileView tile = tiles[i][j];
                        if (tile != null) {
                            tile.setWalkable(isChecked); // Set walkability based on checkbox state
                        }
                    }
                }
            }
        }
    }



    private void onTileClicked(TileView tile) {


        if (tile.getType() == 63) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, CCJE.class);
            startActivity(intent);
        } else if (tile.getType() == 64) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, CCJE.class);
            startActivity(intent);
        } else if (tile.getType() == 65) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, CCJE.class);
            startActivity(intent);
        } else if (tile.getType() == 66) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, CCJE.class);
            startActivity(intent);
        } else if (tile.getType() == 67) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, CCJE.class);
            startActivity(intent);
        } else if (tile.getType() == 112) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 113) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 114) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 272) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 273) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 274) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 275) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 276) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 277) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 278) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 279) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 280) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 289) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 290) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 291) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 292) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 293) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 294) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 301) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 302) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 303) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 304) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);

        } else if (tile.getType() == 305) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);

        } else if (tile.getType() == 306) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 307) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 308) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);


        } else if (tile.getType() == 378) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, COS.class);
            startActivity(intent);

        } else if (tile.getType() == 379) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, COS.class);
            startActivity(intent);

        } else if (tile.getType() == 380) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, COS.class);
            startActivity(intent);
        } else if (tile.getType() == 381) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, COS.class);
            startActivity(intent);
        } else if (tile.getType() == 382) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, COS.class);
            startActivity(intent);
        } else if (tile.getType() == 385) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, COS.class);
            startActivity(intent);
        } else if (tile.getType() == 386) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, COS.class);
            startActivity(intent);
        } else if (tile.getType() == 387) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, COS.class);
            startActivity(intent);
        } else if (tile.getType() == 388) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, COS.class);
            startActivity(intent);
        } else if (tile.getType() == 389) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, COS.class);
            startActivity(intent);
        } else if (tile.getType() == 391) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, COS.class);
            startActivity(intent);
        } else if (tile.getType() == 392) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, COS.class);
            startActivity(intent);
        } else if (tile.getType() == 393) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, COS.class);
            startActivity(intent);
        } else if (tile.getType() == 396) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 397) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 398) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, COS.class);
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

    public void evacluc1(View view) {
        Intent intent = new Intent(this, evaclucinda.class);
        startActivity(intent);
    }
}



