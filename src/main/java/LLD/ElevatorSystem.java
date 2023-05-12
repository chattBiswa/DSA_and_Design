package LLD;

import java.util.*;

public class ElevatorSystem {
    public static void main(String[] args) {
        Dispatcher ds = Dispatcher.getInstance();
        Building bl = new Building(10);

        bl.floorList.get(1).requestLift(4);
        bl.floorList.get(1).requestLift(2);
        bl.floorList.get(3).requestLift(2);

        while(true){
            ds.processUpRequest();
            ds.processDownRequest();
        }

    }
}

class Dispatcher{
    static ElevatorCar carObj = new ElevatorCar(1);
    static State state;
    static int floor;
    static PriorityQueue<Integer> upQueue = new PriorityQueue<>();
    static PriorityQueue<Integer> downQueue = new PriorityQueue<>(Collections.reverseOrder());
    private static Dispatcher obj = null;

    private Dispatcher(){}
    public static synchronized Dispatcher getInstance(){
        if(obj == null)
            return new Dispatcher();
        return obj;
    }

    void submitRequest(State dir, int fl){
        state = dir;
        floor = fl;
        controlElevator();
    }

    void controlElevator(){
        if(state == State.MOVING_UP){
            upQueue.offer(floor);
        } else if (state == State.MOVING_DOWN ) {
            downQueue.offer(floor);
        } else {
            System.out.println(" NOT moving ");
        }
    }
    void processUpRequest(){
        while(!upQueue.isEmpty()) {
            int destFloor = upQueue.poll();
            carObj.moveUp(destFloor);
        }
    }
    void processDownRequest(){
        while(!downQueue.isEmpty()) {
            int destFloor = downQueue.poll();
            carObj.moveDown(destFloor);
        }
    }
}


class ElevatorCar{
    int id;
    Display display;
    int currentFloor;
    State state;
    InternalButton btnObj;
    ElevatorCar(int id){
        this.id = id;
        this.state = State.IDLE;
        this.currentFloor = 0;
        this.display = new Display();
        this.btnObj = new InternalButton();
    }
    void moveUp(int floor){
        state = State.MOVING_UP;
        currentFloor=floor;
        display.setDisplay(currentFloor, state);
        display.showDisplay();
    }
    void moveDown(int floor){
        state = State.MOVING_DOWN;
        currentFloor=floor;
        display.setDisplay(currentFloor, state);
        display.showDisplay();
    }

    void pressButton(int floor){
        btnObj.pressButton(this.currentFloor, floor);
    }
}

abstract class Button{
    Dispatcher disObj = Dispatcher.getInstance();
    abstract void pressButton(int src, int dest);
}
class InternalButton extends Button{

    void pressButton(int src, int dest){
        State dir = (src-dest) == 0 ? State.IDLE : (src-dest) > 0 ? State.MOVING_DOWN : State.MOVING_UP;
        disObj.submitRequest(dir, dest);
    }
}

class ExternalButton extends Button{
    void pressButton(int src, int dest){
        State dir = (src-dest) == 0 ? State.IDLE : (src-dest) > 0 ? State.MOVING_DOWN : State.MOVING_UP;
        disObj.submitRequest(dir, dest);
    }
}


enum State{
    MOVING_UP,
    MOVING_DOWN,
    IDLE;
}

class Floor{
    int floorId;
    ExternalButton btnObj;
    Floor(int id){
        this.floorId = id;
        btnObj = new ExternalButton();
    }
    void requestLift(int destFloor){
        System.out.println("Lift requested at floor : " + floorId + " and destination is : " + destFloor);
        btnObj.pressButton(floorId, destFloor);
    }
}

class Building{
    List<Floor> floorList;
    Building(int floorCount){
        this.floorList = new ArrayList<>(floorCount);
        for(int i=0;i<floorCount;i++)
            floorList.add(new Floor(i));
    }
}

class Display{
    int floor;
    State state;
    void setDisplay(int floor, State state){
        this.floor = floor;
        this.state = state;
    }
    void showDisplay(){
        System.out.println("State : " + this.state.name() + " to floor : " + this.floor);
    }
}