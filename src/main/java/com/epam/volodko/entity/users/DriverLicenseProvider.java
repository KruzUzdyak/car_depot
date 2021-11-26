package com.epam.volodko.entity.users;

import java.util.HashMap;
import java.util.Map;

public class DriverLicenseProvider {

    private static DriverLicenseProvider instance;

    private final Map<Integer, DriverLicenseType> licenseTypes;

    private DriverLicenseProvider(){
        licenseTypes = new HashMap<>();
        licenseTypes.put(1, DriverLicenseType.AM);
        licenseTypes.put(2, DriverLicenseType.A);
        licenseTypes.put(3, DriverLicenseType.A1);
        licenseTypes.put(4, DriverLicenseType.B);
        licenseTypes.put(5, DriverLicenseType.C);
        licenseTypes.put(6, DriverLicenseType.D);
        licenseTypes.put(7, DriverLicenseType.BE);
        licenseTypes.put(8, DriverLicenseType.CE);
        licenseTypes.put(9, DriverLicenseType.DE);
        licenseTypes.put(10, DriverLicenseType.F);
        licenseTypes.put(11, DriverLicenseType.I);
    }

    public static DriverLicenseType getLicenseType(int licenseTypeId){
        if (instance == null){
            instance = new DriverLicenseProvider();
        }
        return instance.licenseTypes.get(licenseTypeId);
    }


}
