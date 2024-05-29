package carSimulation.service;

import carSimulation.enums.Direction;
import carSimulation.model.Car;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SimulatorTest {

    Simulator simulator;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Before
    public void setUp() throws Exception {
        simulator = new Simulator(10,10);
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void addCar() {
        simulator.addCar(Mockito.mock(Car.class));
        assertEquals(1, simulator.getAllCars().size());
    }

    @Test
    public void execute1() {
        simulator.execute();
        assertEquals("Your current list of cars are:", outContent.toString().trim());
    }

    @Test
    public void executeSuccess() {
        Car car = new Car("Name",1,1,Direction.W,"R");
        simulator.addCar(car);
        simulator.execute();
        assertEquals("Your current list of cars are:\n- Name, (1,1) W, R", outContent.toString().trim());
        assertEquals(car.toString(), "- Name, (1,1) N");
        assertEquals(car.isCollided(), false);
    }

    @Test
    public void execute3() {
        Car mockedCar = Mockito.mock(Car.class);
        when(mockedCar.toString()).thenReturn("This is mocked response");
        when(mockedCar.isCollided()).thenReturn(true);
        simulator.addCar(mockedCar);
        simulator.execute();
        assertEquals("Your current list of cars are:\nThis is mocked response", outContent.toString().trim());
        verify(mockedCar, times(0)).setSuccess();
    }

    @Test
    public void executeCollision() {
        Car car1 = new Car("One",0,0,Direction.E,"FF");
        Car car2 = new Car("Two", 1,1,Direction.S, "FR");
        simulator.addCar(car1);
        simulator.addCar(car2);
        simulator.execute();
        assertEquals(car1.toString(), "- One, collides with Two at (1,0) at step 1");
        assertEquals(car1.isCollided(), true);
        assertEquals(car2.toString(), "- Two, collides with One at (1,0) at step 1");
        assertEquals(car2.isCollided(), true);
    }

}