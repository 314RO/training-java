package com.excilys.training.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="company")
public class Company {
    
    @Id
    private long id;
    
    private String name;

    
    protected Company() {};
    /**
     * Constructeur avec Builder.
     * @param builder (Builder)
     */
    private Company(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
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
     * Classe Builder pour la classe Company.
     */
    public static class Builder {
        private long id;
        private String name;

        /**
         * Constructeur du Builder.
         * @param name (String)
         */
        public Builder(String name) {
            this.name = name;
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
         * Passage du Builder à l'objet.
         * @return Company
         */
        public Company build() {
            return new Company(this);
        }
    }

    /**
     * hashCode().
     * @return int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /**
     * equals().
     * @param  obj
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
        Company other = (Company) obj;
        if (id != other.id) {
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
        return "Company [id=" + id + ", name=" + name + "]";
    }

}
