package Model;

public class vip_customer extends Model.customer{
    private String company;

    public vip_customer(int customer_ID, String customer_Name, String address, String postal_Code, String phone, String division, String country, int countryID, int divisionID, String company){
        super( customer_ID, customer_Name, address, postal_Code, phone, division, country, countryID, divisionID);
        this.company = company;
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


    //Polymorphism
    @Override
    public String toString(){
        return (company);
    }
}
