package org.abitpoisk;

import java.util.ArrayList;

/**
 * Created by Роман on 23.07.2017.
 */
public class PagesConsumerPool {
    PagesBuffer pagesBuffer;
    ArrayList<PagesConsumer> pagesConsumers;
    int MAX_PAGES_CONSUMERS;
    public PagesConsumerPool(PagesBuffer pagesBuffer){
        this(pagesBuffer, 1);
    }
    public PagesConsumerPool(PagesBuffer pagesBuffer, int MAX_PAGES_CONSUMERS){
        this.pagesBuffer = pagesBuffer;
        this.MAX_PAGES_CONSUMERS = MAX_PAGES_CONSUMERS;
        this.pagesConsumers = new ArrayList<PagesConsumer>(this.MAX_PAGES_CONSUMERS);
        for(int i = 0;i < this.MAX_PAGES_CONSUMERS; i++){
            this.pagesConsumers.add(new PagesConsumer(pagesBuffer));
        }
    }
    public boolean poolStart(){
        try{
            for(int i = 0; i < this.MAX_PAGES_CONSUMERS; i++){
                this.pagesConsumers.get(i).start();
            }
            return true;
        }catch (Exception ex){
            //trying to interrupt already running threads
            return false;
        }
    }
    public void poolInterrupt(){
        for(int i = 0; i < this.MAX_PAGES_CONSUMERS; i++){
            this.pagesConsumers.get(i).interrupt();
        }
    }
}
