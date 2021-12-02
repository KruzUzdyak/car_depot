package com.epam.volodko.entity.user;

public enum DriverLicenseType {

    AM(1),
    A(2),
    A1(3),
    B(4),
    C(5),
    D(6),
    BE(7),
    CE(8),
    DE(9),
    F(10),
    I(11);

    final int id;

    DriverLicenseType(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "DriverLicenseType{"+
                "id=" + id +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(DriverLicenseType.A);
    }
}
