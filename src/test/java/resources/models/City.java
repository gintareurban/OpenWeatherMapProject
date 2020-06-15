package resources.models;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("City")
public class City {

    @XStreamAlias("id")
    private String id;

    @XStreamAlias("name")
    private String name;

    @XStreamAlias("countryCode")
    private String countryCode;

    @XStreamAlias("zipCode")
    private String zipCode;

    @XStreamAlias("longitude")
    private String longitude;

    @XStreamAlias("latitude")
    private String latitude;

    @XStreamAlias("category")
    private String category;

    @Override
    public String toString() {
        return "City{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
