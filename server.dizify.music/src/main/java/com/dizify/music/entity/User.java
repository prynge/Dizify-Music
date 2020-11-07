package com.dizify.music.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entité User persistente en base de données.
 * 
 * @author Yann KOTTO
 * @author Maxime GRAND
 * @since 2020-11
 * @version 1.0
 */
@Entity
@Table(name = "user")
public class User {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Id
    private String email;

    private String pwd;
    
    private String fname;
    
    private String lname;

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param pwd the pwd to set
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

}