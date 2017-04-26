package com.excilys.training.validators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class ValidatorWeb {

    public ValidatorWeb() {
    }

    public static boolean validName(String name) {
        if (name == null || name.length() < 2) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean validIntroduced(String introduced) {
        LocalDate date = null;
        try {
            if (introduced.equals("")|| introduced==null) {
                return true;
            }
            if (!introduced.equals("")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                date = LocalDate.parse(introduced, formatter);
            }
            return true;
        } catch (DateTimeParseException exc) {
            return false;
        }

    }

    public static boolean validDiscontinued(String discontinued, String introduced) {
        LocalDate date = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            if (discontinued.equals("") || discontinued==null) {
                return true;
            }

            if (!discontinued.equals("") && discontinued!=null) {
                date = LocalDate.parse(discontinued, formatter);
            }
        } catch (DateTimeParseException exc) {
            return false;
        }

        if (introduced != null && !introduced.equals("") && date != null && !LocalDate.parse(introduced, formatter).isBefore(date)) {
            return false;
        } else {
            return true;
        }

    }
    
    
    
    // pas de validatorWeb sur l'id company, puisque l'utilisateur ne peut choisir que dans une liste.
}
