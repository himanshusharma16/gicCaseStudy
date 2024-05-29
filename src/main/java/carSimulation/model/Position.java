package carSimulation.model;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode(){
        return x*3+y*2;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Position) {
            Position p = (Position) o;
            return this.x == p.x && this.y == p.y;
        }
        return false;
    }

    public void update(int x , int y){
        this.x = x;
        this.y = y;
    }

    public int getY(){
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("");
        sb.append("(").append(this.y).append(",").append(this.x).append(")");
        return sb.toString();
    }
}
