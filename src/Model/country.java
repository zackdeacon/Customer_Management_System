package Model;

/**
 * Models a country.
 *
 * @author Zachary Deacon
 *
 */

public class country {
    /**
     * The ID for the country
     */
    private int countryID;

    /**
     * The name for the country
     */
    private String countryName;


    /**
     * Constructor for a new instance of a country
     *
     * @param countryID the country ID attached to the country
     * @param countryName the name attached to the country
     */
    public country(int countryID, String countryName){
        this.countryID = countryID;
        this.countryName = countryName;
    }

    /**
     * The getter for the country name
     *
     * @return countryName of the country
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * The setter for the country name
     *
     * @param countryName The name of the contact
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * The getter for the country ID
     *
     * @return countryID of the country
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * The setter for the country ID
     *
     * @param countryID The ID of the contact
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }


    /**
     * Override method to override the toString method to allow the combo box to populate with country names
     *
     * @return name of the country
     */
    @Override
    public String toString(){
        return (countryName);
    }
}
