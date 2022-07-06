package Model;

/**
 * Models a division.
 *
 * @author Zachary Deacon
 *
 */

public class division {

    /**
     * The ID for the division
     */
    private int divisionID;
    /**
     * The countryID for the division
     */
    private int countryID;
    /**
     * The name for the division
     */
    private String division;

    /**
     * Constructor for a new instance of a division
     *
     * @param division the division name of the division
     * @param countryID the country ID attached to the division
     * @param divisionID the division ID attached to the division
     */
    public division(int divisionID, int countryID, String division){
        this.divisionID = divisionID;
        this.countryID = countryID;
        this.division = division;

    }

    /**
     * The getter for the division ID
     *
     * @return divisionID of the division
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * The setter for the division id
     *
     * @param divisionID The id of the division
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * The getter for the country ID
     *
     * @return countryID of the division
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * The setter for the country id
     *
     * @param countryID The id of the division
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * The getter for the division name
     *
     * @return division of the division
     */
    public String getDivision() {
        return division;
    }

    /**
     * The setter for the division name
     *
     * @param division The division name of the division
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * Override method to override the toString method to allow the combo box to populate with division names
     *
     * @return name of the division
     */
    @Override
    public String toString(){
        return (division);
    }

}
