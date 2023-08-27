package javaPractice;

import java.util.*;

public class SingleObjOfEachClass {
    public static void main(String[] args) {
        A a1 = new A();     // Object created of class javaPractice.A
        B b1 = new B();     // Object created of class javaPractice.B
//        B b2 = new B();     // will throw exception as it's 2nd obj and there is a check in super constructor
    }
}

class A {
    private static final Set<Object> createdObj = new HashSet<>();
    public A(){
        if(!createdObj.add(this.getClass())){
            throw new RuntimeException("Cannot create 2nd object");
        } else {
            System.out.println("Object created of "+ this.getClass());
        }
    }
}
class B extends A{}
class C extends A{}

