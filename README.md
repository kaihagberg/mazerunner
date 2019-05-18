# Maze Runner

## Requirements

* Git
* Docker
* Gradle
* Terminal (bash, zsh, etc.)

### Install

Clone repository

`git clone https://github.com/khagberg/mazerunner.git`

### Run

Ensure you are in the root directory of the project `/mazerunner` then run the following command in a terminal:

`sh start.sh`

### Utilization

The application should be running on port 5000

Given the input type I recommend using Postman or a similar utility (https://www.getpostman.com/downloads/)
		
Send a POST command to `http://localhost:5000/maze_runner/run`

Copy the maze input into the body and ensure it is set to `Text(text/plain)`

### Output

The response should include the shortest path drawn between start(S) and end(E) using an (*) along with the required steps