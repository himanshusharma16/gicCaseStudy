package carSimulation.service;

import carSimulation.model.Car;
import carSimulation.model.Position;

import java.util.*;

public class Simulator {
    private List<Car> cars;
    private final Car[][] board;
    private static String errorMessage = "- %s, collides with %s at %s at step %d";

    public Simulator(int width, int height){
        this.board = new Car[height][width];
    }

    public void addCar(Car car){
        if(cars == null){
            cars = new ArrayList<>();
        }
        cars.add(car);
        board[car.getX()][car.getY()] = car;
    }

    public List<Car> getAllCars(){
        return this.cars;
    }

    public void execute(){
        printAllCars();
        if(cars == null || cars.size() == 0){
            return ;
        } else {
            Set<Car> visited = new HashSet<>();
            int index = 0;
            boolean stillMoving = true;
            while(stillMoving) {
                stillMoving = false;
                for(var c : cars){
                    if(c.isCollided()){
                        continue;
                    }

                    var carAtMyLocation = board[c.getX()][c.getY()];
                    if(carAtMyLocation == c)
                        board[c.getX()][c.getY()] = null;

                    if(c.executeNext(index, board)) {
                        stillMoving = true;
                    }
                    if(board[c.getX()][c.getY()] != null){
                        var existingCar = board[c.getX()][c.getY()];
                        if(visited.contains(existingCar)){
                            //collision detected
                            addCollisionInformation(existingCar, c, index+1);
                        }
                    }
                    board[c.getX()][c.getY()] = c;
                    visited.add(c);
                }
                index++;
                visited.clear();
            }
            addSuccessMessage();
        }
    }

    private void addSuccessMessage() {
        for(Car c :cars){
            if(!c.isCollided()){
                c.setSuccess();
            }
        }
    }

    private void addCollisionInformation(Car c2, Car c1, int index) {
        c2.setMessage(String.format(errorMessage, c2.getName(), c1.getName(), c2.getPosition(), index));
        c2.setCollided(true);
        c1.setMessage(String.format(errorMessage, c1.getName(), c2.getName(), c1.getPosition(), index));
        c1.setCollided(true);
    }

    public void printAllCars() {
        System.out.println("Your current list of cars are:");
        if(cars == null)
            return;
        for(var c : cars){
            System.out.println(c);
        }
    }

    /*
    public void execute1() {
        printAllCars();
        if(cars == null || cars.size() == 0){
            return ;
        } else {
            Map< Position , List<Car>> locations = new HashMap<>();
            int index = 0;
            boolean stillMoving = true;
            while(stillMoving) {
                stillMoving = false;
                for (var c : cars) {
                    if(c.executeNext(index, board)) {
                        stillMoving = true;
                    }
                    var carsAtPosition = locations.getOrDefault(c.getPosition(), new ArrayList<>());
                    carsAtPosition.add(c);
                    locations.put(c.getPosition(), carsAtPosition);
                }
                index++;
                if(checkCollision(locations)){
                    addCollisionMessage(locations, index);
                    return;
                } else
                    locations.clear();
            }
            addSuccessMessage();
        }
    }

    private boolean checkCollision(Map<Position, List<Car>> locations) {
        return locations.size() != cars.size();
    }

    private void addCollisionMessage(Map<Position, List<Car>> locations, int i) {
        for(var entry : locations.entrySet()){
            if(entry.getValue() != null && entry.getValue().size() > 1){
                var position = entry.getKey();
                var cars = entry.getValue();
                StringBuilder sb = new StringBuilder("- ");
                sb.append(cars.get(0).getName());
                sb.append(", collides with ").append(cars.get(1).getName())
                        .append(" at (").append(position.getY()).append(",")
                        .append(position.getX()).append(") at step ").append(i);
                cars.get(0).setMessage(sb.toString());
                sb = new StringBuilder("- ");
                sb.append(cars.get(1).getName());
                sb.append(", collides with ").append(cars.get(0).getName())
                        .append(" at (").append(position.getY()).append(",")
                        .append(position.getX()).append(") at step ").append(i);

                cars.get(1).setMessage(sb.toString());
            }
        }
    }
*/
}
