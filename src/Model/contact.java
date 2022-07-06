package Model;

/**
 * Models a contact.
 *
 * @author Zachary Deacon
 *
 */

public class contact {

    /**
     * The ID for the contact
     */
    private int contactID;
    /**
     * The name for the contact
     */
    private String name;
    /**
     * The email for the contact
     */
    private String email;

    /**
     * Constructor for a new instance of acontact
     *
     * @param contactID the contact ID attached to the contact
     * @param name the name attached to the contact
     * @param email the email attached to the contact
     */
    public contact(int contactID, String name, String email) {
        this.contactID = contactID;
        this.name = name;
        this.email = email;
    }

    /**
     * The getter for the contact ID
     *
     * @return contactID of the contact
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * The setter for the contact id
     *
     * @param contactID The id of the contact
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * The getter for the contact name
     *
     * @return name of the contact
     */
    public String getName() {
        return name;
    }

    /**
     * The setter for the contact name
     *
     * @param name The name of the contact
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The getter for the contact email
     *
     * @return email of the contact
     */
    public String getEmail() {
        return email;
    }

    /**
     * The setter for the contact email
     *
     * @param email The email of the contact
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Override method to override the toString method to allow the combo box to populate with contact names
     *
     * @return name of the contact
     */
    @Override
    public String toString(){
        return (name);
    }
}
