package carSimulation.model;

import carSimulation.enums.Direction;

public class Car implements Vehicle{
    private String name;
    private int x; //denotes height in a 2 D array
    private int y; //denotes width in a 2 D array
    private Direction direction;
    private String command;
    private char[] commandArray;
    private Position position;
    private static String desc = "- %s, %s %s, %s";
    private static String success = "- %s, %s %s";
    private boolean collided;
    private String message;


    public Car(String name, int x, int y, Direction d, String opt){
        this.name = name;
        this.x = x;
        this.y = y;
        this.direction = d;
        this.command = opt;
        collided = false;
        position = new Position(x,y);
        message = String.format(desc, this.name, this.position, this.direction, this.command);
    }

    public String getName(){
        return this.name;
    }

    public int getX(){
        return this.x;
    }

    public Position getPosition(){
        return position;
    }

    public boolean isCollided(){
        return this.collided;
    }

    public void setCollided(boolean b){
        this.collided = b;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public int getY(){
        return this.y;
    }

    public Direction getDirection(){
        return this.direction;
    }

    public boolean executeNext(int i, Car[][] board){
        if(commandArray == null)
            commandArray = command.toCharArray();

        if(i >= commandArray.length)
            return false;
        char c = commandArray[i];
        if(c == 'F')
            forward(board);
        else
            rotate(c);
        position.update(this.x, this.y);
        return true;
    }

    private void forward(Car[][] board){
        switch (this.direction){
            case E : {
                if (this.y < board[0].length-1)
                    this.y++;
                break;
            }
            case N: {
                if (this.x < board.length-1)
                    this.x++;
                break;
            }
            case S: {
                if (this.x > 0)
                    this.x--;
                break;
            }
            case W: {
                if (this.y > 0)
                    this.y--;
                break;
            }
        }
    }

    private void rotate(char c){
        if(c == 'L'){
            this.direction = this.direction.rotateLeft();
        } else if (c == 'R'){
            this.direction = this.direction.rotateRight();
        }
    }

    @Override
    public String toString() {
        return message;
    }

    public void setSuccess() {
        this.message = String.format(success, this.name, this.position.toString(), this.direction);
    }
}
