package designPattern.decorator;

public class DecoratorPattern {
    public static void main(String[] args) {
        BasePizza myPizza = new Topping1(new Topping2(new VegPizza()));
        System.out.println("Price = " + myPizza.cost());
    }
}

abstract class BasePizza {
    abstract int cost();
}
class VegPizza extends BasePizza {
    int cost() {
        return 100;
    }
}
class NonVegPizza extends BasePizza {
    int cost() {
        return 200;
    }
}


abstract class PizzaDecorator extends BasePizza {
    PizzaDecorator() {
    }
}
class Topping1 extends PizzaDecorator {
    BasePizza base1;

    Topping1(BasePizza obj) {
        this.base1 = obj;
    }

    int cost() {
        return this.base1.cost() + 50;
    }
}
class Topping2 extends PizzaDecorator {
    BasePizza base2;

    Topping2(BasePizza obj) {
        this.base2 = obj;
    }

    int cost() {
        return this.base2.cost() + 60;
    }
}
