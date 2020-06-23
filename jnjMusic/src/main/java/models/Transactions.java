package models;

public class Transactions
{
    /**
     * The Transactions class is a model used to set the different aspects of
     * transactions for an instrument by setting the upc codes, user email, date and time of transaction, and
     * the price of the instruments.
     *
     * functions:
     * getters- getTrans_id(), getUpc(), getEmaill(), getDate(), getTime(), getPrice()
     * setters- setTrans_id(), setUpc(), setEmaill(), setDate(), setTime(), setPrice()
     *
     * @params getTrans_id(), getUpc: returns Integer
     * @params getEmaill(), getDate(), getTime(): return String
     * @params getPrice() returns a float value
     */
    private Integer trans_id;
    private String upc;
    private String email;
    private String date;
    private String time;
    private Float price;



    public Transactions( Integer id, String upc, String email, String date,
                         String time, Float price)
    {
        this.date = date;
        this.time = time;
        this.email = email;
        this.price = price;
        this.trans_id = id;
        this.upc = upc;
    }

    public Transactions(String upc, String email,Float price)
    {
        this.email = email;
        this.price = price;
        this.upc = upc;
    }

    public Integer getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(Integer trans_id) {
        this.trans_id = trans_id;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getEmaill() {
        return email;
    }

    public void setEmaill(String emaill) {
        this.email = emaill;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) { this.price = price;
    }
}
