package com.dizify.music.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
    
    private String avatar;
    
    @ManyToOne
    private Role role;

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
     * @return the avatar
     */
    public String getAvatar() {
    	return avatar;
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

    /**
     * @param avatar the avatar to set
     */
    public void setAvatar(String avatar) {
    	this.avatar = avatar;
    }

    /**
     * @return the role
     */
    public Role getRole() {
		return role;
	}

    /**
     * @param role the role to set
     */
	public void setRole(Role role) {
		this.role = role;
	}
    
}