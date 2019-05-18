package com.kaihagberg.mazerunner;

import java.util.*;

public class MazeRunner {

    private static final int[][] VECTORS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public List<Point> findPath(Maze maze) {

        LinkedList<Point> nextPoints = new LinkedList<>();
        Point start = maze.getStart();
        nextPoints.add(start);

        while (!nextPoints.isEmpty()) {

            Point currentPoint = nextPoints.remove();

            if (!maze.isValidPoint(currentPoint.getX(), currentPoint.getY()) || maze.isExplored(currentPoint.getX(), currentPoint.getY())) {
                continue;
            }
            if (maze.isBlocked(currentPoint.getX(), currentPoint.getY())) {
                maze.setVisited(currentPoint.getX(), currentPoint.getY(), true);
                continue;
            }
            if (maze.isEnd(currentPoint.getX(), currentPoint.getY())) {
                return tracePath(currentPoint);
            }
            for (int[] vector : VECTORS) {
                Point nextPoint = new Point(currentPoint.getX() + vector[0], currentPoint.getY() + vector[1], currentPoint);
                nextPoints.add(nextPoint);
                maze.setVisited(currentPoint.getX(), currentPoint.getY(), true);
            }
        }
        return Collections.emptyList();
    }
    private List<Point> tracePath(Point currentPoint) {

        List<Point> path = new ArrayList<>();
        Point point = currentPoint;

        while (point != null) {
            path.add(point);
            point = point.getParent();
        }
        return path;
    }
}
