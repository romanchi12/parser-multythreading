package org.abitpoisk;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Роман on 23.07.2017.
 */
//it connects to https://abitpoisk.org.ua, downloads data and saves it to PagesBuffer
public class PagesProducer extends Thread {
    private PagesBuffer pagesBuffer;
    private LinksBuffer linksBuffer;
    @Override
    public void run() {
        //goig to the internet, downloading data and saving to buffer
        //we need to be synchronized by pagesBuffer, moreover by !exactly! !one! pageBuffer!
        for(int i = 0; i < 5; i++){
            try {
                // producing value e. g. going to the internet and downloading data
                String link = (String) linksBuffer.removeFirst();
                String producedValue = "";
                for(int pageNumber = 1; true; pageNumber++){
                    try {
                        URL url = new URL(link+"/?page=" + pageNumber);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        InputStream is = connection.getInputStream();
                        byte[] buff = new byte[1048576];
                        String temp = "";
                        while(is.read(buff) > 0){
                            temp += new String(buff,"UTF-8");
                        }
                        producedValue += temp;
                        /*Document doc = Jsoup.connect(linksBuffer.removeFirst()+"/?page=" + pageNumber).get();
                        Elements rows = doc.select("tbody tr");
                        producedValue += rows.html();*/
                        //System.out.println("produced value: " + producedValue);
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
                System.out.println("Producer produce: " + producedValue);
                this.pagesBuffer.addLast(producedValue);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " has been interrupted");
                break;
            }
        }
    }
    public PagesProducer(PagesBuffer pagesBuffer, LinksBuffer linksBuffer){
        this(pagesBuffer,linksBuffer, "PageProducer_" + Thread.activeCount());
    }
    public PagesProducer(PagesBuffer pagesBuffer,LinksBuffer linksBuffer, String name){
        this.pagesBuffer = pagesBuffer;
        this.linksBuffer = linksBuffer;
        this.setName(name);
    }
}
