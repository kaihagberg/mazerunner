package com.kaihagberg.mazerunner;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/maze_runner")
public class MazeController {

    @GetMapping()
    public String index() {
        return "MAZE RUNNER";
    }


    @PostMapping(path = "/run", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.TEXT_PLAIN_VALUE)
    public HttpEntity<Object> run(@RequestBody String maze) {
        String solution;
        try {
            solution = MazeSolver.solveMaze(maze);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(solution);
    }
}
