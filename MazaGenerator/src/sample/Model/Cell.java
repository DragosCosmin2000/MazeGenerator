package sample.Model;

public class Cell {
    private Integer i;
    private Integer j;

    public Cell(Integer i, Integer j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "i=" + i +
                ", j=" + j +
                '}';
    }
}
