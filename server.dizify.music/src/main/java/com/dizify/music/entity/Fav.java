package com.dizify.music.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entité Album persistente en base de données.
 * 
 * @author Yann KOTTO
 * @author Maxime GRAND
 * @since 2020-11
 * @version 1.0
 */
@Entity
@Table(name = "fav")
public class Fav {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User faved_by;

    private String type;

    private Integer faved_ids;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the faved_by
     */
    public User getFaved_by() {
        return faved_by;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the faved_ids
     */
    public Integer getFaved_ids() {
        return faved_ids;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param faved_by the faved_by to set
     */
    public void setFaved_by(User faved_by) {
        this.faved_by = faved_by;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param faved_ids the faved_ids to set
     */
    public void setFaved_ids(Integer faved_ids) {
        this.faved_ids = faved_ids;
    }

}