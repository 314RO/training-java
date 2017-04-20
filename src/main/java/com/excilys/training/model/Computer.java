package com.excilys.training.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.excilys.training.exceptions.ChronologicalException;
import com.excilys.training.exceptions.CustomDateException;
import com.excilys.training.exceptions.NoNameException;

public class Computer {
    private long id;
    private String name;
    private LocalDate introduced;
    private LocalDate discontinued;
    private Company company;

    /**
     * Constructeur avec Builder.
     * @param builder (Builder)
     */
    private Computer(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.introduced = builder.introduced;
        this.discontinued = builder.discontinued;
        this.company = builder.company;
    }

    /**
     * Classe Builder pour la classe Computer.
     */
    public static class Builder {
        private long id;
        private String name;
        private LocalDate introduced;
        private LocalDate discontinued;
        private Company company;

        /**
         * Constructeur du Builder.
         * @param name (argument obligatoire)
         */
        public Builder(String name) throws NoNameException {
            if (name==null || name.length()<2){
                throw new NoNameException();
            }
            else {
                this.name = name;
            }
        }

        /**
         * Ajout d'un id au Builder.
         * @param  id (long)
         * @return this
         */
        public Builder id(long id) {
            this.id = id;
            return this;
        }

        /**
         * Ajout de introduced au Builder.
         * @param  introduced (LocalDate)
         * @return this
         * @throws CustomDateException 
         */
        public Builder introduced(String introduced) throws CustomDateException {
            LocalDate date = null ;
            try {
                if (introduced!=null){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                date = LocalDate.parse(introduced, formatter);
                }
                
                this.introduced = date;
                return this;
            }
            catch (DateTimeParseException exc) {
                throw new CustomDateException();      // Pour renvoyer un message personnalisé.
            }
        }

        /**
         * Ajout de introduced au Builder.
         * @param  discontinued (LocalDate)
         * @return this
         * @throws ChronologicalException 
         * @throws CustomDateException 
         */
        public Builder discontinued(String discontinued) throws ChronologicalException, CustomDateException {
            LocalDate date = null ;
            try {
                if (discontinued!=null){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                date = LocalDate.parse(discontinued, formatter);
                }
            }
                catch (DateTimeParseException exc) {
                    throw new CustomDateException();      // Pour renvoyer un message personnalisé.
                }
                
            if (this.introduced!=null && date!=null) {
                if (this.introduced.isBefore(date)) {
                    this.discontinued = date;
                }
                else {
                    throw new ChronologicalException();
                }
            }
            else {
                this.discontinued = date;
            }
            return this;

        }

        /**
         * Ajout de company au Builder.
         * @param company (Company)
         * @return this
         */
        public Builder company(Company company) {
            this.company = company;
            return this;
        }

        /**
         * Passage du Builder à l'objet.
         * @return Computer
         */
        public Computer build() {
            return new Computer(this);
        }
    }

    /**
     * getter.
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * getter.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * getter.
     * @return introduced
     */
    public LocalDate getIntroduced() {
        return introduced;
    }

    /**
     * getter.
     * @return discontinued
     */
    public LocalDate getDiscontinued() {
        return discontinued;
    }

    /**
     * getter.
     * @return company
     */
    public Company getCompany() {
        return company;
    }

    /**
     * hashCode.
     * @return int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((company == null) ? 0 : company.hashCode());
        result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /**
     * equals().
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Builder other = (Builder) obj;
        if (company == null) {
            if (other.company != null) {
                return false;
            }
        } else if (!company.equals(other.company)) {
            return false;
        }
        if (discontinued == null) {
            if (other.discontinued != null) {
                return false;
            }
        } else if (!discontinued.equals(other.discontinued)) {
            return false;
        }
        if (id != other.id) {
            return false;
        }
        if (introduced == null) {
            if (other.introduced != null) {
                return false;
            }
        } else if (!introduced.equals(other.introduced)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    /**
     * toString().
     * @return String
     */
    @Override
    public String toString() {
        return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
                + ", company=" + company + "]";
    }

}
