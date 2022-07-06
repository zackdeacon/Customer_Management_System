package Model;

/**
 * Models a user.
 *
 * @author Zachary Deacon
 *
 */

public class user {

    /**
     * The ID for the user
     */
    private int User_ID;
    /**
     * The name for the user
     */
    private String User_Name;
    /**
     * The password for the user
     */
    private String Password;

    /**
     * Constructor for a new instance of a user
     *
     * @param User_ID the user id of the user
     * @param User_Name the user name attached to the user
     * @param Password the password attached to the user
     */
    public user(int User_ID, String User_Name, String Password) {
        this.User_ID = User_ID;
        this.User_Name = User_Name;
        this.Password = Password;
    }

    /**
     * The getter for the User ID
     *
     * @return User_ID of the user
     */
    public int getUserID() {
        return User_ID;
    }

    /**
     * The setter for the user id
     *
     * @param User_ID The id of the user
     */
    public void setUserID(int User_ID) {
        this.User_ID = User_ID;
    }

    /**
     * The getter for the user name
     *
     * @return User_Name of the user
     */
    public String getUserName() {
        return User_Name;
    }

    /**
     * The setter for the user name
     *
     * @param User_Name The name of the user
     */
    public void setUserName(String User_Name){
        this.User_Name = User_Name;
    }

    /**
     * The getter for the password
     *
     * @return password of the user
     */
    public String getPassword(){
        return Password;
    }

    /**
     * The setter for the password
     *
     * @param Password The password of the user
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

    /**
     * Override method to override the toString method to allow the combo box to populate with user names
     *
     * @return name of the user
     */
    @Override
    public String toString(){
        return (User_Name);
    }
}
