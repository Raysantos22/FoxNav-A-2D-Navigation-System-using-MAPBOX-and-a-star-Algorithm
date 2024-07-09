package com.orbitalsonic.navigationmapboxupgraded;

import android.os.Handler;
import android.util.Log;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import android.graphics.Canvas;
import android.graphics.Paint;
public class HPA {
    private int[][] mapLayout;
    private int clusterSize = 5;
    private final Handler uiHandler;

    public HPA(int[][] mapLayout, Handler uiHandler) {
        this.mapLayout = mapLayout;
        this.uiHandler = uiHandler;
    }

    public ArrayList<TileView> findPath(TileView[][] tiles, TileView start, TileView end) {
        // Implement clustering logic here

        ArrayList<TileView> pathWithinClusters = findPathWithinCluster(tiles, start, end);
        if (pathWithinClusters != null && !pathWithinClusters.isEmpty()) {
            return pathWithinClusters;
        }

        // Implement inter-cluster pathfinding
        connectClusters(tiles);

        startPathfindingVisualization(tiles, start, end); // Start visualization
        return new ArrayList<>(); // Placeholder for final path
    }

    private void clusterMap(TileView[][] tiles) {
        // Clustering logic for HPA*
    }

    private ArrayList<TileView> findPathWithinCluster(TileView[][] tiles, TileView start, TileView end) {
        return AStar(tiles, start, end);
    }

    private void connectClusters(TileView[][] tiles) {
        // Implement logic to connect clusters
    }

    private ArrayList<TileView> AStar(final TileView[][] tiles, final TileView start, final TileView end) {
        final ArrayList<TileView> openList = new ArrayList<>();
        final ArrayList<TileView> closedList = new ArrayList<>();
        openList.add(start);

        while (!openList.isEmpty()) {
            final TileView currentTile = openList.remove(0);
            closedList.add(currentTile);

            if (currentTile == end) {
                return reconstructPath(start, end);
            }

            final ArrayList<TileView> neighbors = getNeighbors(tiles, currentTile);

            for (final TileView neighbor : neighbors) {
                if (!closedList.contains(neighbor) && neighbor.isWalkable()) {
                    final double tentativeGCost = currentTile.getGCost() + distance(currentTile, neighbor);
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

            // Visualize pathfinding computation step for the current tile
            uiHandler.post(new Runnable() {
                @Override
                public void run() {
                    visualizePathfinding(currentTile, openList, closedList);
                }
            });
        }
        return new ArrayList<>();
    }

    private ArrayList<TileView> reconstructPath(TileView start, TileView end) {
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

    private ArrayList<TileView> getNeighbors(TileView[][] tiles, TileView tile) {
        ArrayList<TileView> neighbors = new ArrayList<>();
        int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
        int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
        int rows = tiles.length;
        int cols = tiles[0].length;

        for (int i = 0; i < 8; i++) {
            int newRow = tile.getRow() + dx[i];
            int newCol = tile.getCol() + dy[i];
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                neighbors.add(tiles[newRow][newCol]);
            }
        }
        return neighbors;
    }

    private double distance(TileView tile1, TileView tile2) {
        int dx = Math.abs(tile1.getRow() - tile2.getRow());
        int dy = Math.abs(tile1.getCol() - tile2.getCol());
        return Math.sqrt(dx * dx + dy * dy);
    }

    public void visualizePathfinding(final TileView currentTile, final ArrayList<TileView> openList, final ArrayList<TileView> closedList) {
        // Remove color filters from all tiles
        for (TileView tile : openList) {
            tile.clearColorFilter();
        }
        for (TileView tile : closedList) {
            tile.clearColorFilter();
        }

        // Place dots instead of colors
        currentTile.setDot(true, Color.CYAN); // Cyan dot for current tile

        for (TileView tile : openList) {
            tile.setDot(true, Color.YELLOW); // Yellow dot for open list
        }

        for (TileView tile : closedList) {
            tile.setDot(true, Color.RED); // Red dot for closed list
        }

        // Log the current state
        Log.d("Pathfinding", "Current Tile: " + currentTile.getRow() + ", " + currentTile.getCol());
        Log.d("Pathfinding", "Open List: " + openList.size());
        Log.d("Pathfinding", "Closed List: " + closedList.size());
    }



    private void startPathfindingVisualization(final TileView[][] tiles, final TileView start, final TileView end) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                AStar(tiles, start, end); // Start the A* algorithm
            }
        }).start();
    }
}
