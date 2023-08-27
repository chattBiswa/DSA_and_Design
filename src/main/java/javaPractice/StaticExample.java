package javaPractice;

public class StaticExample {
    public static void main(String[] args) {
        Base b = new Base();
        Base s = new SubClass();
        b.func1();
        s.func1();

        b.func2();
        s.func2();
    }
}

class Base {
    public static void func1(){
        System.out.println("func1 of Base class");
    }
    public void func2(){
        System.out.println("func2 of Base class");
    }
}

class SubClass extends Base {
    public static void func1(){
        System.out.println("func1 of SubClass class");
    }
    public void func2(){
        System.out.println("func2 of SubClass class");
    }
}


