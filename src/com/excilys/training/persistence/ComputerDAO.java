package com.excilys.training.persistence;

import java.util.*;
import com.excilys.training.model.*;

public interface ComputerDAO {
	
	public boolean add(Computer obj);
	public Computer getById(long id);
	public ArrayList<Computer> getByName(String name);
	public boolean delete(long id);
	public boolean update(Computer obj);
	public ArrayList<Computer> fetchPage();

}
