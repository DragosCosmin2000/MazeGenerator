package sample.Model;

public class Problem {
    private Maze maze;

    private Integer startingCellI;
    private Integer startingCellJ;

    public Problem(Maze maze) {
        this.maze = maze;

        this.startingCellI = maze.getCurrentCell().getI();
        this.startingCellJ = maze.getCurrentCell().getJ();
    }

    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public Integer getStartingCellXPos() {
        return startingCellI;
    }

    public void setStartingCellXPos(Integer startingCellXPos) {
        this.startingCellI = startingCellXPos;
    }

    public Integer getStartingCellYPos() {
        return startingCellJ;
    }

    public void setStartingCellYPos(Integer startingCellYPos) {
        this.startingCellJ = startingCellYPos;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "maze=" + maze +
                ", startingCellXPos=" + startingCellI +
                ", startingCellYPos=" + startingCellJ +
                '}';
    }
}
