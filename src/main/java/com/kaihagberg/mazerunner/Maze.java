package com.kaihagberg.mazerunner;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Maze {

    private static final int OPEN = 0;
    private static final int BLOCKED = 1;
    private static final int START = 2;
    private static final int END = 3;
    private static final int PATH = 4;

    private int[][] maze;
    private boolean[][] visited;
    private Point start;
    private Point end;

    public Maze(String mazeInput) {

        String mazeString = "";
        try (Scanner input = new Scanner(mazeInput)) {
            while (input.hasNextLine()) {
                mazeString += input.nextLine() + "\n";
            }
        }
        buildMaze(mazeString);
    }

    private void buildMaze(String mazeString) {

        if (mazeString == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        String[] lines = mazeString.split("[\r]?\n");
        maze = new int[lines.length][lines[0].length()];
        visited = new boolean[lines.length][lines[0].length()];

        for (int row = 0; row < getHeight(); row++) {
            if (lines[row].length() != getWidth()) {
                throw new IllegalArgumentException("invalid maze: row " + row + ": is not valid length");
            }

            for (int col = 0; col < getWidth(); col++) {
                if (lines[row].charAt(col) == '#')
                    maze[row][col] = BLOCKED;
                else if (lines[row].charAt(col) == 'A') {
                    maze[row][col] = START;
                    start = new Point(row, col);
                } else if (lines[row].charAt(col) == 'B') {
                    maze[row][col] = END;
                    end = new Point(row, col);
                } else if (lines[row].charAt(col) == '.') {
                    maze[row][col] = OPEN;
                } else {
                    throw new IllegalArgumentException("maze contains an invalid charachter: " +
                            lines[row].charAt(col) + " found at " + row + ", " + col);
                }
            }
        }
    }

    private int getHeight() {
        return maze.length;
    }

    private int getWidth() {
        return maze[0].length;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public boolean isEnd(int x, int y) {
        return x == end.getX() && y == end.getY();
    }

    public boolean isStart(int x, int y) {
        return x == start.getX() && y == start.getY();
    }

    public boolean isExplored(int row, int col) {
        return visited[row][col];
    }

    public boolean isBlocked(int row, int col) {
        return maze[row][col] == BLOCKED;
    }

    public void setVisited(int row, int col, boolean bool) {
        visited[row][col] = bool;
    }

    public boolean isValidPoint(int row, int col) {
        return !(row < 0 || row >= getHeight() || col < 0 || col >= getWidth());
    }

    public String printPath(List<Point> path) {
        int[][] tempMaze = Arrays.stream(maze)
                .map(int[]::clone)
                .toArray(int[][]::new);
        for (Point point : path) {
            if (isStart(point.getX(), point.getY()) || isEnd(point.getX(), point.getY())) {
                continue;
            }
            tempMaze[point.getX()][point.getY()] = PATH;
        }
        String strObj = mazePrinter(tempMaze);
        return strObj;
    }

    public String mazePrinter(int[][] maze) {

        StringBuilder result = new StringBuilder(getWidth() * (getHeight() + 1));
        int steps = 1;

        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                if (maze[row][col] == OPEN) {
                    result.append(' ');
                } else if (maze[row][col] == BLOCKED) {
                    result.append('#');
                } else if (maze[row][col] == START) {
                    result.append('S');
                } else if (maze[row][col] == END) {
                    result.append('E');
                } else {
                    result.append('*');
                    steps++;
                }
            }
            result.append('\n');
        }
        result
                .append('\n')
                .append("Steps to complete: ")
                .append(steps);

        return result.toString();
    }

    public void reset() {
        for (int i = 0; i < visited.length; i++)
            Arrays.fill(visited[i], false);
    }
}