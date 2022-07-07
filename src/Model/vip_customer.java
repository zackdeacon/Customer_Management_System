package Model;

public class vip_customer extends Model.customer{
    private String company;
    private int VIP_customer_ID;

    public vip_customer(int VIP_customer_ID, int customer_ID, String customer_Name, String address, String postal_Code, String phone, String division, String country, int countryID, int divisionID, String company){
        super(customer_ID, customer_Name, address, postal_Code, phone, division, country, countryID, divisionID);
        this.company = company;
        this.VIP_customer_ID = VIP_customer_ID;
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getVIP_ID() {
        return VIP_customer_ID;
    }

    public void setVIP_ID(int id) {this.VIP_customer_ID = id;}


    //Polymorphism
    @Override
    public String toString(){
        return (company);
    }
}
