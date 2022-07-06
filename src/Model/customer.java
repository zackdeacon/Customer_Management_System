package Model;

/**
 * Models a user.
 *
 * @author Zachary Deacon
 *
 */

public class customer {
    /**
     * The ID for the customer
     */
    protected int customer_ID;
    /**
     * The name for the customer
     */
    protected String customer_Name;
    /**
     * The address for the customer
     */
    protected String address;
    /**
     * The postal code for the customer
     */
    protected String postal_Code;
    /**
     * The phone number for the customer
     */
    protected String phone;
    /**
     * The division name for the customer
     */
    protected String division;
    /**
     * The country name for the customer
     */
    protected String country;
    /**
     * The country ID for the customer
     */
    protected int countryID;
    /**
     * The division ID for the customer
     */
    protected int divisionID;

    /**
     * Constructor for a new instance of a customer
     *
     * @param customer_ID the ID for the customer
     * @param customer_Name the name of the customer
     * @param address the address of the customer
     * @param postal_Code the postal code of the customer
     * @param phone the phone number of customer
     * @param division the division name of the customer
     * @param country the country name for the customer
     * @param countryID the country ID attached to the customer
     * @param divisionID the division ID attached to the customer
     */
    public customer(int customer_ID, String customer_Name, String address, String postal_Code, String phone, String division, String country, int countryID, int divisionID){
        this.customer_ID = customer_ID;
        this.customer_Name = customer_Name;
        this.address = address;
        this.postal_Code = postal_Code;
        this.phone = phone;
        this.division = division;
        this.country = country;
        this.countryID = countryID;
        this.divisionID = divisionID;
    }


    /**
     * The getter for the country id
     *
     * @return countryID of the customer
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * The setter for the country id
     *
     * @param countryID The id of the customer
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * The getter for the division id
     *
     * @return divisionID of the customer
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * The setter for the division id
     *
     * @param divisionID The division id of the customer
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * The getter for the customer id
     *
     * @return customerID of the customer
     */
    public int getCustomer_ID(){return customer_ID;}

    /**
     * The setter for the customer id
     *
     * @param id The id of the customer
     */
    public void setCustomer_ID(int id){
        this.customer_ID = id;
    }

    /**
     * The getter for the address
     *
     * @return address of the customer
     */
    public String getAddress() {
        return address;
    }

    /**
     * The setter for the address
     *
     * @param address The address of the customer
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * The getter for the customer name
     *
     * @return customer_name of the customer
     */
    public String getCustomer_Name() {
        return customer_Name;
    }

    /**
     * The getter for the division name
     *
     * @return division of the customer
     */
    public String getDivision() {
        return division;
    }

    /**
     * The setter for the division
     *
     * @param division The division of the customer
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * The getter for the phone number
     *
     * @return phone of the customer
     */
    public String getPhone() {
        return phone;
    }

    /**
     * The setter for the phone
     *
     * @param phone The phone of the customer
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * The getter for the postal code
     *
     * @return postal_code of the customer
     */
    public String getPostal_Code() {
        return postal_Code;
    }

    /**
     * The setter for the postal code
     *
     * @param postal_Code The postal code of the customer
     */
    public void setPostal_Code(String postal_Code) {
        this.postal_Code = postal_Code;
    }

    /**
     * The setter for the customer name
     *
     * @param customer_Name The name of the customer
     */
    public void setCustomer_Name(String customer_Name) {
        this.customer_Name = customer_Name;
    }

    /**
     * The getter for the country name
     *
     * @return country of the customer
     */
    public String getCountry() {
        return country;
    }

    /**
     * The setter for the country name
     *
     * @param country The country name of the customer
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Override method to override the toString method to allow the combo box to populate with customer names
     *
     * @return name of the customer
     */
    @Override
    public String toString(){
        return (customer_Name);
    }

}


