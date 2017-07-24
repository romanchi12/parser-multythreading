package org.abitpoisk;

import org.hibernate.Session;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.IOException;
import java.util.List;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws IOException {
        System.setProperty("javax.net.ssl.trustStore", "C:/Users/Roman/IdeaProjects/db/abit-poiskorgua.jks");
        /*Document doc = Jsoup.connect("https://abit-poisk.org.ua/rate2016").get();
        Element table = doc.getElementsByClass("table-bordered").get(0);
        Element tbody = table.getElementsByTag("tbody").get(0);
        Elements rows = tbody.getElementsByTag("tr");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        for (Element row : rows) {
            try {
                Elements columns = row.getElementsByTag("td");
                Element a = columns.get(0).getElementsByTag("a").get(0);
                String href = "https://abit-poisk.org.ua" + a.attr("href");
                String regionName = a.text();
                Element maxBudgetPlacesElement = columns.get(1);//.text();
                Integer maxBudgetPlaces = Integer.parseInt(maxBudgetPlacesElement.text());
                Element allPlacesElement = columns.get(2);
                Integer allPlaces = Integer.parseInt(allPlacesElement.text());
                Element appliesElement = columns.get(3);
                Integer applies = Integer.parseInt(appliesElement.text());
                Element originalsElement = columns.get(4);
                Integer originals = Integer.parseInt(originalsElement.text());
                System.out.println(String.format("new Region(%s, %s, %s, %s,%s,%s);", href, regionName, maxBudgetPlaces, allPlaces, applies, originals));
                RegionDAO tempRegion = new RegionDAO(href, regionName, maxBudgetPlaces, allPlaces, applies,originals);
                System.out.println(tempRegion);
                session.save(tempRegion);
            } catch (IndexOutOfBoundsException ex) {

            }
        }
        session.flush();
        session.getTransaction().commit();

        session.close();
        App.universities();
        App.specialities();*/
        App.appies();
    }
    public static void universities() throws IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<RegionDAO> criteriaQuery = criteriaBuilder.createQuery(RegionDAO.class);
        Root<RegionDAO> root = criteriaQuery.from(RegionDAO.class);
        criteriaQuery.select(root);
        TypedQuery<RegionDAO> q = session.createQuery(criteriaQuery);
        List<RegionDAO> regions = q.getResultList();
        for(RegionDAO region: regions){
            System.out.println(region);
            Document doc = Jsoup.connect(region.getRegionHref()).get();
            Element tbody = doc.select("tbody").get(0);
            Elements rows = tbody.select("tr");
            session.beginTransaction();
            for(Element row:rows){
                try{
                    Elements columns = row.select("td");
                    Element universityNameElement = columns.get(0).select("a").get(0);
                    String universityName = universityNameElement.text();
                    String universityHref= "https://abit-poisk.org.ua" + universityNameElement.attr("href");
                    Integer maxBudgetPlaces = Integer.parseInt(columns.get(1).text());
                    Integer allPlaces = Integer.parseInt(columns.get(2).text());
                    Integer applies = Integer.parseInt(columns.get(3).text());
                    Integer originals = Integer.parseInt(columns.get(4).text());
                    UniversityDAO temp = new UniversityDAO(universityHref, universityName, maxBudgetPlaces, allPlaces, applies, originals, region);
                    session.save(temp);
                }catch (Exception ex){

                }
            }
            session.flush();
            session.getTransaction().commit();
        }
        session.close();
    }

    public static void specialities(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<UniversityDAO> criteriaQuery = criteriaBuilder.createQuery(UniversityDAO.class);
        Root<UniversityDAO> root = criteriaQuery.from(UniversityDAO.class);
        criteriaQuery.select(root);
        TypedQuery<UniversityDAO> typedQuery = session.createQuery(criteriaQuery);
        List<UniversityDAO> universities = typedQuery.getResultList();
        for(UniversityDAO university: universities){
            session.beginTransaction();
            for(int pageNumber=1; true; pageNumber++){
                try {
                    Document doc = Jsoup.connect(university.getUniversityHref()+"/?page=" + pageNumber).get();
                    //https://abit-poisk.org.ua/rate2016/univer/7648/?page=1
                    Elements rows = doc.select("tbody tr");
                    if(rows.size()==0){
                        break;
                    }
                    for(Element row: rows){
                        Elements columns = row.select("td");
                        String okr = columns.get(0).text();
                        String direction = columns.get(1).text();
                        Element appliesElement = columns.get(2).select("a").get(0);
                        String appliesHref = "https://abit-poisk.org.ua" + appliesElement.attr("href");
                        Integer applies = Integer.parseInt(appliesElement.text());
                        Integer allPlaces = Integer.parseInt(columns.get(3).text());
                        Integer maxBudgetPlaces = Integer.parseInt(columns.get(4).text());
                        Integer originals = Integer.parseInt(columns.get(5).text());
                        String subjects = columns.get(6).text();
                        SpecialityDAO speciality = new SpecialityDAO(okr, appliesHref, direction, applies, allPlaces, maxBudgetPlaces,originals,subjects, university);
                        session.save(speciality);
                    }
                    System.out.println("after rows");
                } catch (Exception e) {
                    System.out.println("Exception");
                    break;
                }

            }
            session.flush();
            session.getTransaction().commit();
        }
        session.close();
    }
    public static void appies(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<SpecialityDAO> criteriaQuery = criteriaBuilder.createQuery(SpecialityDAO.class);
        Root<SpecialityDAO> root = criteriaQuery.from(SpecialityDAO.class);
        criteriaQuery.select(root);
        TypedQuery<SpecialityDAO> typedQuery = session.createQuery(criteriaQuery);
        List<SpecialityDAO> specialities = typedQuery.getResultList();
        System.out.println(specialities.size());
        for(SpecialityDAO speciality:specialities){
            session.beginTransaction();
            for(int pageNumber=1; true; pageNumber++){
                try {
                    Document doc = Jsoup.connect(speciality.getApplyHref()+"/?page=" + pageNumber).get();
                    System.out.println(speciality.getApplyHref()+"/?page=" + pageNumber);
                    Elements rows = doc.select("tbody tr");
                    if(rows.size()==0){
                        break;
                    }
                    for(Element row: rows){
                        Elements columns = row.select("td");
                        Integer place = Integer.parseInt(columns.get(0).text());
                        String pib = columns.get(1).text();
                        String priority = columns.get(2).text();
                        String documents = columns.get(3).text();
                        String bal = (columns.get(4).text());
                        String atestat = (columns.get(5).text());
                        String zno = (columns.get(6).text());
                        String extraBaly = (columns.get(7).text());
                        ApplyDAO apply = new ApplyDAO(place, pib, priority,documents,  bal,  atestat,  zno,  extraBaly, speciality);
                        System.out.println(apply);
                        session.save(apply);
                    }
                    System.out.println("after rows");
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }

            }
            session.flush();
            session.getTransaction().commit();
        }
    }
}
