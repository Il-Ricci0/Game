public class Player {
    String name;
    Integer column;
    Integer row;

    public Player(String name, Integer spawncolumn, Integer spawnrow) {
        this.name = name;
        this.column = spawncolumn;
        this.row = spawnrow;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public Integer getRow() {
        return row;
    }

    public String getName() {
        return name;
    }
}
