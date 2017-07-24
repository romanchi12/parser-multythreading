package org.abitpoisk;

/**
 * Created by Роман on 23.07.2017.
 */
public class AppMultithreading {
    public static void main(String...args){
        System.setProperty("javax.net.ssl.trustStore", "C:/Users/Roman/IdeaProjects/db/abit-poiskorgua.jks");
        /*PagesBuffer pagesBuffer = new PagesBuffer();
        PagesProducersPool pagesProducersPool = new PagesProducersPool(pagesBuffer,40);
        PagesConsumerPool pagesConsumerPool = new PagesConsumerPool(pagesBuffer, 2);
        pagesProducersPool.poolStart();
        pagesConsumerPool.poolStart();*/
        LinksBuffer linksBuffer = new LinksBuffer();
        PagesBuffer pagesBuffer = new PagesBuffer();
        PagesProducer pagesProducer = new PagesProducer(pagesBuffer,linksBuffer);
        LinksProducer linksProducer = new LinksProducer(linksBuffer);
        linksProducer.start();
        pagesProducer.start();
        System.out.println(pagesBuffer);
    }
}
