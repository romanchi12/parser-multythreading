package org.abitpoisk;

/**
 * Created by Роман on 23.07.2017.
 */
//parsing page to Document and saving to database
public class PagesConsumer extends Thread{
    private PagesBuffer pagesBuffer;
    public PagesConsumer(PagesBuffer pagesBuffer){
        this(pagesBuffer, "PagesConsumer_" + Thread.activeCount());
    }
    public PagesConsumer(PagesBuffer pagesBuffer, String name){
        this.pagesBuffer = pagesBuffer;
        this.setName(name);
    }
    @Override
    public void run() {
        //parsing data from pagesBuffer and saving to database
        while(true){
            try{
                Object consumedValue = this.pagesBuffer.removeFirst();
            }catch (Exception ex){
                System.out.println(Thread.currentThread().getName() + " has been interrupted");
                break;
            }
        }
    }
}
