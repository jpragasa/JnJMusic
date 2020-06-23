package models;

public class Users
{
    /**
     * The Users class is a template for creating new users. The class basically gets the user's
     * email, password, phone#, primaryIn, and determines if the user is an admin.
     *
     * functions():
     * getters- getEmail(), getPassword(), getPhone(), getPrimaryIn(), getAdmin()
     * setters- setEmail(), setPassword(), setPhone(), setPrimaryIn(), setAdmin()
     *
     * @params getEmail(), getPassword(), getPhone(), getPrimaryIn(): returns String
     * @params getAdmin(): returns a Boolean
     */
    private String email;
    private String password;
    private String phone;
    private String primaryIn;
    private Boolean admin;

    public Users(String email, String password,
                 String phone, String primaryIn, Boolean admin)
    {
        this.primaryIn = primaryIn;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.admin = admin;
    }


    public String getPrimaryIn() {
        return primaryIn;
    }

    public void setPrimaryIn(String primaryIn) {
        this.primaryIn = primaryIn;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public Boolean getAdmin()
    {
        return admin;
    }

    public void setAdmin(Boolean admin)
    {
        this.admin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
