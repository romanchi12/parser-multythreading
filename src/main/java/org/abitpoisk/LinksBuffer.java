package org.abitpoisk;

import java.util.LinkedList;

/**
 * Created by Роман on 23.07.2017.
 */
public class LinksBuffer<String> extends LinkedList<String> {
    int MAX_LINKS;
    public LinksBuffer(){
        this(1000);
    }
    public LinksBuffer(int MAX_LINKS){
        this.MAX_LINKS = MAX_LINKS;
    }
    @Override
    public synchronized String removeFirst() {
        while(this.size() == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " has been interrupted");
                return null;
            }
        }
        String o =  super.removeFirst();
        System.out.println("Link consumed " + o);
        this.notifyAll();
        return o;
    }

    @Override
    public synchronized void addLast(String o) {
        while(this.size() + 1 > MAX_LINKS){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Link produced " + o);
        super.addLast(o);
        this.notifyAll();
    }
}
