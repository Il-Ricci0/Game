public class Player {
    String name;
    Integer column;
    Integer row;

    public Player(String name, Integer spawncolumn, Integer spawnrow) {
        this.name = name;
        this.column = spawncolumn;
        this.row = spawnrow;
    }

    public void setcolumn(Integer column) {
        this.column = column;
    }

    public void setrow(Integer row) {
        this.row = row;
    }

    public Integer getcolumn() {
        return column;
    }

    public Integer getrow() {
        return column;
    }

    public String getName() {
        return name;
    }
}
