
package models;

import java.net.MalformedURLException;
import java.net.URL;

public class InstrumentModel
{
    /**
     * The InstrumentModel Class creates the properties of the Instruments to be sent to
     * the database.
     *
     * functions():
     * toString()- returns a string that includes all the current values of the model.
     * getters- getUPC(), getSale(), getCat(), getCatName(), getImageURL(), getAvailable(), getPrice()
     * setters- setId(), setInstrumentName(), setUsed(), setPrice()
     * @params getCat(): integer value
     * @params getUPC(), getSale(), getCatName(): String values
     * @params getPrice(): Float value
     * @params getAvailable(): Boolean value
     * @params getImage_URL(): Type URL
     *
     * NOTE: The used property is set to an int to allow flexibility with determining if the instrument
     * is new, used, or needs to be repaired.
     * [0 new, 1 used, Any other number is an assigned number indicating it needs to be repaired]
     */
    private String UPC; //UPC provided by eBay
    private String sale; //sale name from eBay seller
    private Integer cat;
    private String catName;
    private Float price;
    private Boolean available;
    private URL image_url;

    public InstrumentModel(String UPC,String sale ,
                            Integer cat,String catName,Float price,
                            Boolean available, String url)
    {
        this.UPC = UPC;
        this.sale = sale;
        this.cat = cat;
        this.catName = catName;
        this.price = price;
        this.available = available;
        try {
            this. image_url = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public InstrumentModel(String UPC,String sale ,
                           Integer cat,String catName,Float price,
                           Boolean available, URL url)
    {
        this.UPC = UPC;
        this.sale = sale;
        this.cat = cat;
        this.catName = catName;
        this.price = price;
        this.available = available;
        this. image_url = url;
    }
    public Integer getCat() {
        return cat;
    }

    public void setCat(Integer cat) {
        this.cat = cat;
    }

    public URL getImage_url() {
        return image_url;
    }

    public void setImage_url(URL image_url) {
        this.image_url = image_url;
    }

    public String getSale()
    {
        return sale;
    }

    public void setSale(String sale)
    {
        this.sale = sale;
    }

    public void setPrice(Float price)
    {
        this.price = price;
    }

    public Boolean getAvailable()
    {
        return available;
    }

    public void setAvailable(Boolean available)
    {
        this.available = available;
    }

    public String getUPC()
    {
        return UPC;
    }

    public void setUPC(String UPC)
    {
        this.UPC = UPC;
    }

    public String getCatName()
    {
        return catName;
    }

    public void setCatName(String catName)
    {
        this.catName = catName;
    }

    // price getter
    public float getPrice()
    {
        return this.price;
    }


    // price setter
    public void setPrice(float price)
    {
        this.price = price;
    }
}
