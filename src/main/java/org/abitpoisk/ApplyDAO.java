package org.abitpoisk;

import javax.persistence.*;

/**
 * Created by Роман on 03.07.2017.
 */
@Entity
@Table(name = "applies")
public class ApplyDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "apply_id")
    private int applyId;
    @Column(name = "place")
    private int place;
    @Column(name = "pib")
    private String pib;
    @Column(name = "priority")
    private String priority;
    @Column(name = "documents")
    private boolean documents;

    @Override
    public String toString() {
        return "ApplyDAO{" +
                "applyId=" + applyId +
                ", place=" + place +
                ", pib='" + pib + '\'' +
                ", priority='" + priority + '\'' +
                ", documents=" + documents +
                ", bal=" + bal +
                ", atestat=" + atestat +
                ", zno=" + zno +
                ", extraBaly=" + extraBaly +
                '}';
    }

    @Column(name = "bal")
    private String bal;
    @Column(name = "atestat")
    private String atestat;
    @Column(name = "zno")
    private String zno;
    @Column(name = "extra_baly")
    private String extraBaly;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "speciality_id")
    private SpecialityDAO speciality;

    public ApplyDAO( int place, String pib, String priority, String documents, String bal, String atestat, String zno, String extraBaly, SpecialityDAO speciality) {
        this.place = place;
        this.pib = pib;
        this.priority = priority;
        this.documents = documents.equals("+") ? true : false;
        this.bal = bal;
        this.atestat = atestat;
        this.zno = zno;
        this.extraBaly = extraBaly;
        this.speciality = speciality;
    }

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getBal() {
        return bal;
    }

    public void setBal(String bal) {
        this.bal = bal;
    }

    public String getAtestat() {
        return atestat;
    }
    public void setAtestat(String atestat) {
        this.atestat = atestat;
    }

    public String getZno() {
        return zno;
    }

    public void setZno(String zno) {
        this.zno = zno;
    }

    public String getExtraBaly() {
        return extraBaly;
    }

    public void setExtraBaly(String extraBaly) {
        this.extraBaly = extraBaly;
    }
    public boolean isDocuments() {
        return documents;
    }

    public void setDocuments(boolean documents) {
        this.documents = documents;
    }
    public SpecialityDAO getSpeciality() {
        return speciality;
    }

    public void setSpeciality(SpecialityDAO speciality) {
        this.speciality = speciality;
    }

    public ApplyDAO() {
    }


}
