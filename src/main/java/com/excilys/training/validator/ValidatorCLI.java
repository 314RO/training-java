package com.excilys.training.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.excilys.training.exception.ChronologicalException;
import com.excilys.training.exception.CustomDateException;
import com.excilys.training.exception.NotNameException;

public class ValidatorCLI {

    public ValidatorCLI() {}

    public static String validName(String name) throws NotNameException{
        if (name==null || name.length()<2){
            throw new NotNameException();
        }
        else {
            return name;
        }
    }
    
    
    public static LocalDate validIntroduced(String introduced) throws CustomDateException{
        LocalDate date = null;
        try {
            if (!introduced.equals("")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(introduced, formatter);
            }
        return date;
        }
        catch (DateTimeParseException exc) {
            throw new CustomDateException(); 
    }

}
    public static LocalDate validDiscontinued(String discontinued, LocalDate introduced) throws ChronologicalException, CustomDateException {
        LocalDate date = null;
        try {
            if (!discontinued.equals("")){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(discontinued, formatter);
            }
        }
            catch (DateTimeParseException exc) {
                throw new CustomDateException();      // Pour renvoyer un message personnalisé.
            }
            
        if (introduced!=null && date!=null) {
            if (!introduced.isBefore(date)) {
                throw new ChronologicalException();
            }
        }
        return date;
    } 
   
}
