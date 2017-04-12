package com.excilys.training.persistence;

import java.util.ArrayList;
import com.excilys.training.model.*;

public interface CompanyDAO {

	public ArrayList<Company> fetchPage();
	public Company getById(long id);
}
