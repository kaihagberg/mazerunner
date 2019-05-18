package com.kaihagberg.mazerunner;

import java.util.List;

public class MazeSolver {

    public static String solveMaze(String mazeInput) {
        Maze maze = new Maze(mazeInput);
        MazeRunner mazeRunner = new MazeRunner();
        List<Point> path = mazeRunner.findPath(maze);
        String mazeStr = maze.printPath(path);
        maze.reset();
        return mazeStr;
    }

}
