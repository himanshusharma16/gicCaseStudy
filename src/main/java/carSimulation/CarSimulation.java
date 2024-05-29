package carSimulation;

import carSimulation.enums.Direction;
import carSimulation.service.Simulator;
import carSimulation.model.Car;

import java.util.Scanner;

public class CarSimulation {

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            new CarSimulation().start();
        } catch (Exception e){
            System.out.println("Sorry, something went wrong. Please refer below for more info on error. The program will exit now!");
            e.printStackTrace();
        }
    }

    private void start() {
        boolean startAgain = true;
        while(startAgain) {
            System.out.println("Welcome to Auto Driving Car Simulation!");
            printBlankLine();
            Simulator simulator = setUpSimulationBoard();
            printBlankLine();
            boolean chooseOptions = true;
            while (chooseOptions) {
                System.out.println("Please choose from the following options:");
                System.out.println("[1] Add a car to field");
                System.out.println("[2] Run simulation");

                int input = sc.nextInt();
                sc.nextLine();
                if (input == 1) {
                    simulator.addCar(prepareNewCar(sc));
                    simulator.printAllCars();
                    printBlankLine();
                } else if (input == 2) {
                    chooseOptions = false;
                    simulator.execute();
                    printBlankLine();

                    System.out.println("After simulation, the result is:");
                    if(simulator.getAllCars() != null ) {
                        for (Car c : simulator.getAllCars())
                            System.out.println(c);
                    }
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
            }

            printBlankLine();
            System.out.println("Please choose from the following options:");
            System.out.println("[1] Start over");
            System.out.println("[2] Exit");

            int input = sc.nextInt();
            sc.nextLine();
            if (input == 1) {
                startAgain = true;
            } else {
                printBlankLine();
                System.out.println("Thank you for running the simulation. Goodbye!");
                startAgain = false;
            }
        }
    }

    private Simulator setUpSimulationBoard() {
        System.out.println("Please enter the width and height of the simulation field in x y format:");

        String dimensions = sc.nextLine();
        String[] dimensionArray = dimensions.split(" ");
        Simulator simulator = new Simulator(Integer.parseInt(dimensionArray[1]), Integer.parseInt(dimensionArray[0]));

        System.out.println("You have created a field of " + dimensionArray[1] + " X " + dimensionArray[0] + ".");
        return simulator;
    }

    private Car prepareNewCar(Scanner sc) {
        System.out.println("Please enter the name of the car:");
        String name = sc.nextLine();

        System.out.println(String.format("Please enter initial position of the car %s in x y Direction format:",name));
        String next = sc.nextLine();
        String[] arr = next.split(" ");
        int width = Integer.parseInt(arr[0]);
        int height = Integer.parseInt(arr[1]);
        Direction d = Direction.valueOf(arr[2]);

        System.out.println("Please enter the commands for car "+name+":");
        String command = sc.nextLine();

        return new Car(name, height, width, d, command);
    }

    private static void printBlankLine(){
        System.out.println("");
    }
}
