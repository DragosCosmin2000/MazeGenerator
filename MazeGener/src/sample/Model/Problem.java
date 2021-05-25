package sample.Model;
import java.util.ArrayList;
import java.util.List;

public class Problem {
    private Maze maze;

    private List<Cell> stack;

    public Problem(Maze maze) {
        this.maze = maze;
        this.stack = new ArrayList<>();
    }

    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public void push(Cell cell) {
        if(cell != null) {
            stack.add(cell);
        }
    }

    public Cell pop() {
        Cell cell = null;
        if(stack.size() > 0) {
            cell = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);
        }

        return cell;
    }

    public int getStackLength() {
        return stack.size();
    }

    @Override
    public String toString() {
        return "Problem{" +
                "maze=" + maze +
                '}';
    }
}