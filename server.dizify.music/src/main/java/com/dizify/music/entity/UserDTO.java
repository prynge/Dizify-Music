package com.dizify.music.entity;


public class UserDTO {
    private Integer id;
    
    private String email;

    private String password;
    
    private String fname;
    
    private String lname;
    
    private String avatar;
    
    private String role;

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
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
     * @param password the password to set
     */
    public void setpassword(String password) {
        this.password = password;
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
    public String getRole() {
		return role;
	}

    /**
     * @param role the role to set
     */
	public void setRole(String role) {
		this.role = role;
	}

}