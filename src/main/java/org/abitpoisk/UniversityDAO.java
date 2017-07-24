package org.abitpoisk;

import javax.persistence.*;

/**
 * Created by Роман on 02.07.2017.
 */
@Entity
@Table(name="universities")
public class UniversityDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="university_id")
    private int universityId;
    @Column(name = "university_href")
    private String universityHref;
    @Column(name="university_name")
    private String universityName;
    @Column(name="max_budget_places")
    private int maxBudgetPlaces;

    @Override
    public String toString() {
        return "UniversityDAO{" +
                "universityId=" + universityId +
                ", universityHref='" + universityHref + '\'' +
                ", universityName='" + universityName + '\'' +
                ", maxBudgetPlaces=" + maxBudgetPlaces +
                ", allPlaces=" + allPlaces +
                ", applies=" + applies +
                ", originals=" + originals +
                '}';
    }

    public UniversityDAO(String universityHref, String universityName, int maxBudgetPlaces, int allPlaces, int applies, int originals, RegionDAO region) {
        this.universityHref = universityHref;
        this.universityName = universityName;
        this.maxBudgetPlaces = maxBudgetPlaces;
        this.allPlaces = allPlaces;
        this.applies = applies;
        this.originals = originals;
        this.region = region;
    }

    @Column(name="all_places")
    private int allPlaces;
    @Column(name="applies")
    private int applies;
    @Column(name="originals")
    private int originals;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "region_id")
    private RegionDAO region;

    public UniversityDAO() {
    }

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public int getMaxBudgetPlaces() {
        return maxBudgetPlaces;
    }

    public void setMaxBudgetPlaces(int maxBudgetPlaces) {
        this.maxBudgetPlaces = maxBudgetPlaces;
    }

    public int getAllPlaces() {
        return allPlaces;
    }

    public void setAllPlaces(int allPlaces) {
        this.allPlaces = allPlaces;
    }

    public int getApplies() {
        return applies;
    }

    public void setApplies(int applies) {
        this.applies = applies;
    }

    public int getOriginals() {
        return originals;
    }

    public void setOriginals(int originals) {
        this.originals = originals;
    }

    public RegionDAO getRegion() {
        return region;
    }

    public void setRegion(RegionDAO region) {
        this.region = region;
    }
    public String getUniversityHref() {
        return universityHref;
    }

    public void setUniversityHref(String universityHref) {
        this.universityHref = universityHref;
    }
}
