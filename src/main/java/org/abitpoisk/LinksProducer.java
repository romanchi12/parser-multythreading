package org.abitpoisk;

import org.hibernate.Session;
import sun.awt.image.ImageWatched;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Роман on 23.07.2017.
 */
public class LinksProducer extends Thread {
    LinksBuffer linksBuffer;
    public LinksProducer(LinksBuffer linksBuffer){
        this.linksBuffer = linksBuffer;
    }
    @Override
    public void run() {
        //selecting links from database and pushing them to linksbuffer
        //need hibernate, gu gu
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<SpecialityDAO> criteriaQuery = criteriaBuilder.createQuery(SpecialityDAO.class);
        Root<SpecialityDAO> root = criteriaQuery.from(SpecialityDAO.class);
        criteriaQuery.select(root);

        TypedQuery<SpecialityDAO> typedQuery = session.createQuery(criteriaQuery);
        List<SpecialityDAO> specialitiesBuff = typedQuery.setFirstResult(0).setMaxResults(1000).getResultList();
        for(int i = 1000; specialitiesBuff.size() != 0 || specialitiesBuff != null; i+=1000) {
            specialitiesBuff = typedQuery.setFirstResult(i).setMaxResults(1000).getResultList();
            for(int j = 0; j < specialitiesBuff.size(); j++){
                linksBuffer.addLast(specialitiesBuff.get(j).getApplyHref());
                System.out.println(specialitiesBuff.get(j).getApplyHref());
            }
        }
    }
}
