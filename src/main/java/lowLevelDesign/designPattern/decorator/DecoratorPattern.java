package lowLevelDesign.designPattern.decorator;

// We use decorator pattern to avoid Class explosion -
// means, creation of multiple classes based on permutation and combination
//
// NOTE: It follows both IS-A (inheritance) and HAS-A (composition) relation between base class and decorator class
//

public class DecoratorPattern {
    public static void main(String[] args) {
        BasePizza myPizza = new ExtraCheese(new Mashroom(new VegPizza()));
        System.out.println("Price = " + myPizza.cost());
        
        BasePizza myNonVegPizza = new ExtraCheese(new Chicken(new NonVegPizza()));
        System.out.println("Prize of NonVeg Pizza = " + myNonVegPizza.cost());
    }
}

// Base class
abstract class BasePizza {
    abstract int cost();
}

// Base type - 1
class VegPizza extends BasePizza {
    int cost() {
        return 100;
    }
}

// Base type - 2
class NonVegPizza extends BasePizza {
    int cost() {
        return 200;
    }
}


// Decorator class - which IS-A base class and HAS-A base class obj
abstract class PizzaToppingDecorator extends BasePizza {
    PizzaToppingDecorator() {}
}

// Topping  - 1
class ExtraCheese extends PizzaToppingDecorator {
    BasePizza base;

    ExtraCheese(BasePizza obj) {
        this.base = obj;
    }

    int cost() {
        return this.base.cost() + 50;
    }
}

// Topping  - 2
class Mashroom extends PizzaToppingDecorator {
    BasePizza base;

    Mashroom(BasePizza obj) {
        this.base = obj;
    }

    int cost() {
        return this.base.cost() + 60;
    }
}

// Topping  - 3
class Chicken extends PizzaToppingDecorator {
    BasePizza base;
    Chicken(BasePizza obj){
        this.base = obj;
    }

    int cost() {
        return this.base.cost() + 100;
    }
}
