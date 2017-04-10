import java.sql.*;
import java.util.*;
import com.excilys.training.model.*;
import com.excilys.training.persistence.*;


public class Test {
	public static void main(String[] args) {


		SQLConnection.getInstance();
		SQLConnection.print("computer");
		SQLConnection.printComputer(19);
		SQLConnection.addComputer("name","2012-05-05","2013-10-10",10);


	}
}