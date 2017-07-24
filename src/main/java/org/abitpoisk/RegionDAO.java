package org.abitpoisk;

import javax.persistence.*;

/**
 * Created by Роман on 03.07.2017.
 */

@Entity
@Table(name = "regions")
public class RegionDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "region_id")
    private int regionId;
    @Column(name = "region_href")
    private String regionHref;

    @Override
    public String toString() {
        return "RegionDAO{" +
                "regionId=" + regionId +
                ", regionHref='" + regionHref + '\'' +
                ", regionName='" + regionName + '\'' +
                ", maxBudgetPlaces=" + maxBudgetPlaces +
                ", allPlaces=" + allPlaces +
                ", applies=" + applies +
                ", originals=" + originals +
                '}';
    }

    @Column(name = "region_name")
    private String regionName;
    @Column(name = "max_budget_places")
    private int maxBudgetPlaces;
    @Column(name = "all_places")
    private int allPlaces;
    @Column(name = "applies")
    private int applies;
    @Column(name = "originals")
    private int originals;

    public RegionDAO() {
    }

    public RegionDAO(String regionHref, String regionName, int maxBudgetPlaces, int allPlaces, int applies, int originals) {
        this.regionHref = regionHref;
        this.regionName = regionName;
        this.maxBudgetPlaces = maxBudgetPlaces;
        this.allPlaces = allPlaces;
        this.applies = applies;
        this.originals = originals;
    }

    public int getRegionId() {

        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
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
    public String getRegionHref() {
        return regionHref;
    }

    public void setRegionHref(String regionHref) {
        this.regionHref = regionHref;
    }
}
