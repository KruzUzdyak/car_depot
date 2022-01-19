package com.epam.volodko.service.validator;

import com.epam.volodko.entity.user.DriverLicense;

public class DriverLicenseValidator extends AbstractValidator{

    public boolean validate(DriverLicense license){
        return license.getLicenseType() != null &&
                license.getObtainingDate() != null &&
                notEmptyString(license.getLicenseNumber());
    }
}
