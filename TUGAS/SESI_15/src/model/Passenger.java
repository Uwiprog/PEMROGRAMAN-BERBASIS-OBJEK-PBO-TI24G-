package model;

public class Passenger extends Person {
    private String email;

    public Passenger(){}

    public Passenger(String nama, String noHp, String email) {
        super(nama, noHp);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void displayInfo(){
        System.out.println("=== Informasi Penumpang ===");
        System.out.println("Email :" + email);
    }
}
