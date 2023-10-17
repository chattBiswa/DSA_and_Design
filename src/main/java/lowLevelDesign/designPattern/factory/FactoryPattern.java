package lowLevelDesign.designPattern.factory;

public class FactoryPattern {
    public static void main(String[] args) {
        ParkingSlot mySlot = ParkingSlotFactory.getParkingType(VehicleType.BIKE);
        mySlot.addVehicle();
        mySlot.removeVehicle();
    }
}

enum VehicleType {
    CAR,
    BIKE;
}
class ParkingSlotFactory {
    ParkingSlot ps;
    public static ParkingSlot getParkingType(VehicleType VT) {
        if (VT.equals(VehicleType.CAR)) {
            return new FourWheelerSLot();
        } else {
            return VT.equals(VehicleType.BIKE) ? new TwoWheelerSLot() : null;
        }
    }
}

interface ParkingSlot {
    void addVehicle();
    void removeVehicle();
}
class FourWheelerSLot implements ParkingSlot {
    public void addVehicle() {
        System.out.println("4 wheeler is added");
    }

    public void removeVehicle() {
        System.out.println("4 wheeler is removed");
    }
}
class TwoWheelerSLot implements ParkingSlot {
    public void addVehicle() {
        System.out.println("2 wheeler is added");
    }

    public void removeVehicle() {
        System.out.println("2 wheeler is removed");
    }
}
