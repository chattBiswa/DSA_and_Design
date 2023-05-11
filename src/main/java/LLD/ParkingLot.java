package LLD;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class ParkingLot {
    public static void main(String[] args) throws InterruptedException {
        
        EntranceGate start = new EntranceGate(13);
        ExitGate end = new ExitGate();
        
        Vehicle car1 = new Vehicle(100, SlotType.CAR);
        ParkingSpotManager psm = start.getParkingSpotManager(car1.vType);
        ParkingSlot freeSlot = start.findSlot();
        int slotId = start.bookSlot(freeSlot, car1);
        Ticket tc1 = start.generateTicket(slotId, freeSlot.getSlotType());
        System.out.println(tc1.toString());
        Thread.sleep(1000L);
        end.setParkingSpotManager(psm);
        end.freeSlot(freeSlot);
        float ticketAmt = end.ComputePrice(tc1);
        System.out.println("Total amount to pay = " + ticketAmt);
        System.out.println();
        
        Vehicle car2 = new Vehicle(101, SlotType.CAR);
        psm = start.getParkingSpotManager(car2.vType);
        freeSlot = start.findSlot();
        slotId = start.bookSlot(freeSlot, car2);
        Ticket tc2 = start.generateTicket(slotId, freeSlot.getSlotType());
        System.out.println(tc2.toString());
        Thread.sleep(1000L);
        end.setParkingSpotManager(psm);
        end.freeSlot(freeSlot);
        ticketAmt = end.ComputePrice(tc2);
        System.out.println("Total amount to pay = " + ticketAmt);
        System.out.println();

        Vehicle bike1 = new Vehicle(200, SlotType.BIKE);
        psm = start.getParkingSpotManager(bike1.vType);
        freeSlot = start.findSlot();
        slotId = start.bookSlot(freeSlot, bike1);
        Ticket tc3 = start.generateTicket(slotId, freeSlot.getSlotType());
        System.out.println(tc3.toString());
        Thread.sleep(1000L);
        end.setParkingSpotManager(psm);
        end.freeSlot(freeSlot);
        ticketAmt = end.ComputePrice(tc3);
        System.out.println("Total amount to pay = " + ticketAmt);
        System.out.println();
        
        Vehicle bike2 = new Vehicle(202, SlotType.BIKE);
        psm = start.getParkingSpotManager(bike2.vType);
        freeSlot = start.findSlot();
        slotId = start.bookSlot(freeSlot, bike2);
        Ticket tc4 = start.generateTicket(slotId, freeSlot.getSlotType());
        System.out.println(tc4.toString());
        Thread.sleep(1000L);
        end.setParkingSpotManager(psm);
        end.freeSlot(freeSlot);
        ticketAmt = end.ComputePrice(tc4);
        System.out.println("Total amount to pay = " + ticketAmt);
        System.out.println();
    }
}


class EntranceGate {
    ParkingSpotManager psm;
    ParkingSpotManagerFactory psmf = new ParkingSpotManagerFactory();
    int parkingCap;

    EntranceGate(int parkingCap) {
        this.parkingCap = parkingCap;
    }

    public ParkingSpotManager getParkingSpotManager(SlotType s) {
        return this.psm = this.psmf.getSpotManagerObj(s, this.parkingCap);
    }

    public ParkingSlot findSlot() {
        return this.psm.findParkingSlot();
    }

    public int bookSlot(ParkingSlot slot, Vehicle v) {
        return this.psm.parkVehicle(v, slot);
    }

    public Ticket generateTicket(int slotId, SlotType st) {
        return new Ticket(slotId, st);
    }
}


class ExitGate {
    ParkingSpotManager psm;

    public void setParkingSpotManager(ParkingSpotManager psm) {
        this.psm = psm;
    }

    public void freeSlot(ParkingSlot s) {
        this.psm.removeVehicle(s);
    }

    public float ComputePrice(Ticket t) {
        LocalDateTime startTime = t.entryTime;
        LocalDateTime endTime = LocalDateTime.now();
        long secs = ChronoUnit.SECONDS.between(startTime, endTime);
        System.out.println(" " + startTime + " " + endTime);
        return (float)(10L * secs);
    }
}


class ParkingSpotManagerFactory {
    public ParkingSpotManager getSpotManagerObj(SlotType s, int parkingCap) {
        ParkingSpotManager psm;
        switch (s) {
            case CAR:
                psm = FourWheelerParkingSpotManager.getInstance(parkingCap);
                break;
            case BIKE:
                psm = TwoWheelerParkingSpotManager.getInstance(parkingCap);
                break;
            default:
                psm = null;
        }

        return psm;
    }
}

class ParkingSpotManager {
    List<ParkingSlot> plist;
    ParkingStrategy ps;
    ParkingSlot pSlot;
    int cap;

    ParkingSpotManager(ParkingStrategy ps, int slotCapacity) {
        this.ps = ps;
        this.cap = slotCapacity;
    }

    public ParkingSlot findParkingSlot() {
        try {
            this.pSlot = this.ps.parkSlot(this.plist);
        } catch (ArrayStoreException var2) {
            System.out.println("No parking space available!");
            throw var2;
        }

        System.out.println("Parking slot found slot-id = " + this.pSlot.id);
        return this.pSlot;
    }

    public int parkVehicle(Vehicle v, ParkingSlot s) {
        System.out.println("Vehicle about to add");
        return s.parkVehicle(v);
    }

    public void removeVehicle(ParkingSlot s) {
        System.out.println("Vehicle about to remove");
        s.removeVehicle();
    }
}


class FourWheelerParkingSpotManager extends ParkingSpotManager {
    private static FourWheelerParkingSpotManager obj;

    private FourWheelerParkingSpotManager(int parkingCap) {
        super(new NearToGateParkStrategy(), parkingCap);
        this.addSlots();
    }

    public static FourWheelerParkingSpotManager getInstance(int parkingCap) {
        if (obj == null) {
            obj = new FourWheelerParkingSpotManager(parkingCap);
        }

        return obj;
    }

    public void addSlots() {
        this.plist = new ArrayList<>();

        for(int i = 0; i < this.cap; ++i) {
            ParkingSlot slot = new FourWheelerParkingSlot(i + 1);
            this.plist.add(slot);
        }

    }
}

class TwoWheelerParkingSpotManager extends ParkingSpotManager {
    private static TwoWheelerParkingSpotManager obj;

    private TwoWheelerParkingSpotManager(int parkingCap) {
        super(new DefaultParkStrategy(), parkingCap);
        this.addSlots();
    }

    public static TwoWheelerParkingSpotManager getInstance(int parkingCap) {
        if (obj == null) {
            obj = new TwoWheelerParkingSpotManager(parkingCap);
        }

        return obj;
    }

    public void addSlots() {
        this.plist = new ArrayList<>();

        for(int i = 0; i < this.cap; ++i) {
            ParkingSlot slot = new TwoWheelerParkingSlot(i + 1);
            this.plist.add(slot);
        }

    }
}


interface ParkingStrategy {
    ParkingSlot parkSlot(List<ParkingSlot> var1);
}

class NearToGateParkStrategy implements ParkingStrategy {
    public ParkingSlot parkSlot(List<ParkingSlot> plist) {
        System.out.println("Park near to gate");
        Iterator<ParkingSlot> var2 = plist.iterator();

        ParkingSlot s;
        do {
            if (!var2.hasNext()) {
                throw new ArrayStoreException();
            }

            s = (ParkingSlot)var2.next();
        } while(!s.isEmpty);

        return s;
    }
}

class DefaultParkStrategy implements ParkingStrategy {
    public ParkingSlot parkSlot(List<ParkingSlot> plist) {
        System.out.println("Park any available spot");
        Iterator<ParkingSlot> var2 = plist.iterator();

        ParkingSlot s;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            s = (ParkingSlot)var2.next();
        } while(!s.isEmpty);

        return s;
    }
}

abstract class ParkingSlot {
    int id;
    SlotType sType;
    boolean isEmpty = true;

    ParkingSlot(SlotType s, int id) {
        this.sType = s;
        this.id = id;
    }

    abstract int parkVehicle(Vehicle var1);

    abstract void removeVehicle();

    public SlotType getSlotType() {
        return this.sType;
    }
}

class FourWheelerParkingSlot extends ParkingSlot {
    FourWheelerParkingSlot(int id) {
        super(SlotType.CAR, id);
    }

    public int parkVehicle(Vehicle v) {
        System.out.println("4 wheeler Vehicle parked = " + v.vNum + " and slot type = " + this.sType);
        this.isEmpty = false;
        return this.id;
    }

    public void removeVehicle() {
        System.out.println("4 wheeler Vehicle removed and free slot-id is " + this.id);
        this.isEmpty = true;
    }
}

class TwoWheelerParkingSlot extends ParkingSlot {
    TwoWheelerParkingSlot(int id) {
        super(SlotType.BIKE, id);
    }

    public int parkVehicle(Vehicle v) {
        System.out.println("2 wheeler Vehicle parked " + v.vNum + " and slot type = " + this.sType);
        this.isEmpty = false;
        return this.id;
    }

    public void removeVehicle() {
        System.out.println("2 wheeler Vehicle removed and free slot-id is " + this.id);
        this.isEmpty = true;
    }
}

enum SlotType {
    CAR,
    BIKE;
}

class Ticket {
    LocalDateTime entryTime;
    int slotId;
    SlotType sType;

    Ticket(int slotId, SlotType st) {
        this.slotId = slotId;
        this.entryTime = LocalDateTime.now();
        this.sType = st;
    }

    public String toString() {
        String var10000 = String.valueOf(this.sType);
        return "Ticket = " + var10000 + " " + String.valueOf(this.slotId) + " " + String.valueOf(this.entryTime);
    }
}

class Vehicle {
    int vNum;
    SlotType vType;

    Vehicle(int num, SlotType type) {
        this.vNum = num;
        this.vType = type;
    }
}
