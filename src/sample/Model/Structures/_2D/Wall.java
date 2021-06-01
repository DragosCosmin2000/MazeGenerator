package sample.Model.Structures._2D;

public class Wall {
    private Cell cell1;
    private Cell cell2;

    public Cell getCell1() {
        return cell1;
    }

    public void setCell1(Cell cell1) {
        this.cell1 = cell1;
    }

    public Cell getCell2() {
        return cell2;
    }

    public void setCell2(Cell cell2) {
        this.cell2 = cell2;
    }

    public Wall(Cell cell1, Cell cell2) {
        this.cell1 = cell1;
        this.cell2 = cell2;
    }

    @Override
    public String toString() {
        return "Wall{" +
                "cell1=" + cell1 +
                ", cell2=" + cell2 +
                '}';
    }
}
