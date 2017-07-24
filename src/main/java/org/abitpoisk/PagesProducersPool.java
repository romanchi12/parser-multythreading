package org.abitpoisk;

import java.util.ArrayList;

/**
 * Created by Роман on 23.07.2017.
 */
public class PagesProducersPool<T> {
    private ArrayList<PagesProducer> pagesProducers;
    int MAX_PAGES_PRODUCERS;
    public PagesProducersPool(PagesBuffer pagesBuffer, LinksBuffer linksBuffer){
        this(pagesBuffer,linksBuffer,4);
    }
    public PagesProducersPool(PagesBuffer pagesBuffer,LinksBuffer linksBuffer, int MAX_PAGES_PRODUCERS){
        this.MAX_PAGES_PRODUCERS = MAX_PAGES_PRODUCERS;
        pagesProducers = new ArrayList<PagesProducer>(this.MAX_PAGES_PRODUCERS);
        for(int i = 0; i < this.MAX_PAGES_PRODUCERS; i++){
            pagesProducers.add(new PagesProducer(pagesBuffer, linksBuffer));
        }
    }

    public boolean poolStart(){
        try{
            for(int i = 0;i < this.MAX_PAGES_PRODUCERS; i++){
                this.pagesProducers.get(i).start();
            }
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            //trying to kill already running producers
            this.poolInterrupt();
            return false;
        }
    }

    public void poolInterrupt(){
        for(int i = 0;i < this.MAX_PAGES_PRODUCERS; i++){
            this.pagesProducers.get(i).interrupt();
        }
    }
}
