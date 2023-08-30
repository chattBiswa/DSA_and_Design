package lowLevelDesign.designPattern.strategy;

public class StrategyPattern {
    public static void main(String[] args) {
        PriceFactory pf = new PriceFactory();
        pf.getPrice(VehicleType.CAR).price();
    }
}

enum VehicleType {
    CAR,
    BIKE;
}

class PricingStrategy {
    PaymentCalc priceStrategy;

    PricingStrategy(PaymentCalc obj) {
        this.priceStrategy = obj;
    }

    void price() {
        System.out.println("Price strategy selected for: " + this.getClass());
        this.priceStrategy.price();
    }
}

class PriceFactory {
    public PricingStrategy getPrice(VehicleType VT) {
        if (VT.equals(VehicleType.CAR)) {
            return new FourWheelerPrice();
        } else {
            return VT.equals(VehicleType.BIKE) ? new TwoWheelerPrice() : null;
        }
    }
}
class FourWheelerPrice extends PricingStrategy {
    FourWheelerPrice() {
        super(new HourlyCalc());
    }
}
class TwoWheelerPrice extends PricingStrategy {
    TwoWheelerPrice() {
        super(new MinCalc());
    }
}

interface PaymentCalc {
    void price();
}
class HourlyCalc implements PaymentCalc {
    public void price() {
        System.out.println("Prices are per hr basis");
    }
}
class MinCalc implements PaymentCalc {
    public void price() {
        System.out.println("Prices are per min basis");
    }
}