package designPattern.proxy;

public class ProxyPattern {
    public static void main(String[] args) {
        ServiceInterface obj = new Proxy();
        obj.operations();
    }
}

interface ServiceInterface {
    void operations();
}

class Proxy implements ServiceInterface {
    Service realService = new Service();
    public void operations() {
        System.out.println("Via Proxy");
        this.realService.operations();
    }
}
class Service implements ServiceInterface {
    public void operations() {
        System.out.println("Inside Service - Process complete!");
    }
}
