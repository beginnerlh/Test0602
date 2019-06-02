package lianxi0517;

import java.util.*;

class Goods{
    private String name ;
    private int count;

    public synchronized void set(String name){
        this.name = name;
        this.count = count+1;
        System.out.println(toString());
    }
    public synchronized void get(){
        this.count = count -1;
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}

class producer implements Runnable{

    private Goods goods;

    public producer(Goods goods) {
        super();
        this.goods = goods;
    }

    @Override
    public void run() {
        this.goods.set("offer");
    }

}

class customer implements Runnable{

    private Goods goods;

    public customer(Goods goods) {
        super();
        this.goods = goods;
    }

    @Override
    public void run() {
        this.goods.get();
    }
}

public class Main1 {
    public static void main(String[] args) throws InterruptedException {
        Goods goods = new Goods();
        Thread produceThread = new Thread(new producer(goods),"生产offer");
        Thread customerThread = new Thread(new customer(goods),"消费offer");
        produceThread.start();
        Thread.sleep(2000);
        customerThread.start();
    }
}
