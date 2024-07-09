package com.orbitalsonic.navigationmapboxupgraded;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class Main extends AppCompatActivity {
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
        setContentView(R.layout.main);

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
                {1,  1,    1,  1,  1,  1,  1,  1,  1,  1,  1,673,1,1,1,1,1,1, 1, 1, 1,},
                {1,  1,    1,  1,  1,  1,  1,  1,  1,  1,  1,672,674,1,1,1,1,1, 1, 1, 1,},
                {1,  1,    1,  1,  1,  1,669,670,671,  1,  1,  0,1,1,1,1,1,1, 1, 1, 1,},
                {1,  1,    1,  1,  1,  1,664,665,666,667,668,  0,1,1,1,1,1,1, 1, 1, 1,},
                {1,  1,    1,  1,  1,  1,658,659,660,661,662,663,1,1,1,1,1,1, 1, 1, 1,},
                {1,  1,    1,  1,  1,  1,653,654,655,656,657,  0,1,1,1,1,1,1, 1, 1, 1,},
                {1,  1,    1,  1,646,647,648,649,650,651,652,  0,1,1,1,1,1,1, 1, 1, 1,},
                {1,   1,   1,  1,  1,638,639,640,641,642,643,644,645,1,1,1,1,1, 1, 1, 1,},
                {1,   1,   1,  1,  1,627,628,629,630,631,632,633,634,635,636,637,1,1, 1, 1, 1,},
                {1,   1,   1,  1,  1,616,617,618,619,620,621,622,623,624,625,626,1,1, 1, 1, 1,},
                //10
                {1,    1,  1,  1,604,605,606,607,608,609,610,611,612,613,614,615,1,1, 1, 1, 1,},
                {1,    1,  1,  1,591,592,593,594,595,596,597,598,599,600,601,602,603,1, 1, 1, 1,},
                {1,    1,  1,  1,579,580,581,582,583,578,584,585,586,587,588,589,590,1, 1, 1, 1,},
                {1,    1,  1,  1,675,675,675,675,675,577,675,675,675,  1,1,1,1,1, 1, 1, 1,},
                {568,568,568,568,568,568,568,574,568,568,568,576,568,568,568,568,568,568,568,568,568,},
                {568,568,569,570,571,572,568,573,568,568,568,575,569,570,571,572,568,568,568,568,568,},
                {  1,557,558,559,560,561,562,563,564,565,566,567,1,1,1,1,1,1, 1, 1, 1,},
                {  1,546,547,548,549,550,551,552,553,554,555,556,1,1,1,1,1,1, 1, 1, 1,},
                {  1,535,536,537,538,539,540,541,542,543,544,545,1,1,1,1,1,1, 1, 1, 1,},
                {  1,525,526,527,528,529,530,675,531,532,533,534,1,1,1,1,1,1, 1, 1, 1,},
                //5
                {  1,516,517,518,519,520,521,675,675,522,523,524,1,1,1,1,1,1, 1, 1, 1,},
                {  1,505,506,507,508,509,510,511,512,513,514,515,1,1,1,1,1,1, 1, 1, 1,},
                {  1,494,495,496,497,498,499,500,501,502,503,504,1,1,1,1,1,1, 1, 1, 1,},
                {  1,475,476,477,478,479,480,481,482,483,484,485,1,1,1,1,1,1, 1, 1, 1,},
                {  1,  1,  1,  1,486,487,488,489,490,491,492,493,1,1,1,1,1,1, 1, 1, 1,},

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
                if (mapLayout[i][j] == 0 ||mapLayout[i][j] == 507
                        ||mapLayout[i][j] == 508||mapLayout[i][j] == 509||mapLayout[i][j] == 510
                        ||mapLayout[i][j] == 511||mapLayout[i][j] == 512||mapLayout[i][j] == 503
                        ||mapLayout[i][j] == 518||mapLayout[i][j] == 519||mapLayout[i][j] == 520
                        ||mapLayout[i][j] == 521||mapLayout[i][j] == 522||mapLayout[i][j] == 528
                        ||mapLayout[i][j] == 529||mapLayout[i][j] == 530||mapLayout[i][j] == 531
                        ||mapLayout[i][j] == 532||mapLayout[i][j] == 513||mapLayout[i][j] == 527
                        ||mapLayout[i][j] == 537||mapLayout[i][j] == 538||mapLayout[i][j] == 539
                        ||mapLayout[i][j] == 540||mapLayout[i][j] == 541||mapLayout[i][j] == 542
                        ||mapLayout[i][j] == 552||mapLayout[i][j] == 553||mapLayout[i][j] == 561
                        ||mapLayout[i][j] == 562||mapLayout[i][j] == 563||mapLayout[i][j] == 564
                        ||mapLayout[i][j] == 565||mapLayout[i][j] == 566||mapLayout[i][j] == 567
                        ||mapLayout[i][j] == 573||mapLayout[i][j] == 574||mapLayout[i][j] == 575
                        ||mapLayout[i][j] == 576||mapLayout[i][j] == 577||mapLayout[i][j] == 578
                        ||mapLayout[i][j] == 596||mapLayout[i][j] == 597||mapLayout[i][j] == 598
                        ||mapLayout[i][j] == 606||mapLayout[i][j] == 607||mapLayout[i][j] == 608
                        ||mapLayout[i][j] == 609||mapLayout[i][j] == 612||mapLayout[i][j] == 613
                        ||mapLayout[i][j] == 617||mapLayout[i][j] == 620||mapLayout[i][j] == 628
                        ||mapLayout[i][j] == 631||mapLayout[i][j] == 632||mapLayout[i][j] == 633
                        ||mapLayout[i][j] == 634||mapLayout[i][j] == 640||mapLayout[i][j] == 642
                        ||mapLayout[i][j] == 644||mapLayout[i][j] == 645||mapLayout[i][j] == 649
                        ||mapLayout[i][j] == 651||mapLayout[i][j] == 654||mapLayout[i][j] == 656
                        ||mapLayout[i][j] == 659||mapLayout[i][j] == 661||mapLayout[i][j] == 663
                        ||mapLayout[i][j] == 666||mapLayout[i][j] == 667||mapLayout[i][j] == 672
                        ||mapLayout[i][j] == 673||mapLayout[i][j] == 675||mapLayout[i][j] == 647
                        ||mapLayout[i][j] == 648||mapLayout[i][j] == 650||mapLayout[i][j] == 653
                        ||mapLayout[i][j] == 638||mapLayout[i][j] == 639||mapLayout[i][j] == 619
                        ||mapLayout[i][j] == 478||mapLayout[i][j] == 479||mapLayout[i][j] == 480
                        ||mapLayout[i][j] == 481||mapLayout[i][j] == 482||mapLayout[i][j] == 483
                        ||mapLayout[i][j] == 484||mapLayout[i][j] == 630) {
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




        if (tile.getType() == 647) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 619) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, CPAG.class);
            startActivity(intent);
        } else if (tile.getType() == 630) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, CPAG.class);
            startActivity(intent);
        } else if (tile.getType() == 479) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 480) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 481) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 482) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 483) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 484) {
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
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);

        } else if (tile.getType() == 379) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);

        } else if (tile.getType() == 380) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 381) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 382) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 385) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 386) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 387) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 388) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 389) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 391) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 392) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } else if (tile.getType() == 393) {
            // Navigate to MainActivity3
            Intent intent = new Intent(this, MainActivity9.class);
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
            Intent intent = new Intent(this, MainActivity9.class);
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

    public void evacmain1(View view) {
        Intent intent = new Intent(this, evacmain.class);
        startActivity(intent);
    }
}





