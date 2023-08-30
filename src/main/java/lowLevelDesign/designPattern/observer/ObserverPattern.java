package lowLevelDesign.designPattern.observer;

import java.util.ArrayList;
import java.util.List;


public class ObserverPattern {
    public static void main(String[] args) {
        MobileStockObservable mobileObservable = new MobileStockObservable();

        SMSObserver ob1 = new SMSObserver();
        EmailObserver ob2 = new EmailObserver();

        mobileObservable.addObserver(ob1);
        mobileObservable.addObserver(ob2);

        mobileObservable.setData(10);
    }
}

interface Observable {
    void addObserver(Observer var1);
    void removeObserver(Observer var1);
    void notifyObservers();
    void setData(int var1);
}

class MobileStockObservable implements Observable {
    List<Observer> obList = new ArrayList<>();
    int mobileStock = 0;

    public void addObserver(Observer obj) {
        this.obList.add(obj);
    }

    public void removeObserver(Observer obj) {
        this.obList.remove(obj);
    }

    public void notifyObservers() {
        for (Observer ob : this.obList) {
            ob.updateData();
        }
    }

    public void setData(int newStock) {
        if (this.mobileStock == 0) {
            this.notifyObservers();
        }
        this.mobileStock += newStock;
    }
}



interface Observer {
    void updateData();
}
class SMSObserver implements Observer {
    public void updateData() {
        System.out.println("SMS sent!");
    }
}
class EmailObserver implements Observer {
    public void updateData() {
        System.out.println("Email sent!");
    }
}

