package carSimulation.enums;

public enum Direction {
    N,
    S,
    E,
    W;

    public Direction rotateLeft() {
        switch (this){
            case W -> {
                return S;
            }
            case S -> {
                return E;
            }
            case N -> {
                return W;
            }
            case E -> {
                return N;
            }
            default ->
                throw new IllegalArgumentException("Invalid direction");
        }
    }

    public Direction rotateRight() {
        switch (this){
            case W -> {
                return N;
            }
            case S -> {
                return W;
            }
            case N -> {
                return E;
            }
            case E -> {
                return S;
            }
            default ->
                    throw new IllegalArgumentException("Invalid direction");
        }
    }

}
