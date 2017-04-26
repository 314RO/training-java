import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCP
{
        
        private static DataSource datasource;
        
        public static DataSource getDataSource()
        {
                if(datasource == null)
                {
                        HikariConfig config = new HikariConfig();
                        
                config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/computer-database-db?zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8");
                config.setUsername("root");
                config.setPassword("");

                config.setMaximumPoolSize(10);
                config.setAutoCommit(false);
                config.addDataSourceProperty("cachePrepStmts", "true");
                config.addDataSourceProperty("prepStmtCacheSize", "250");
                config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
                
                datasource = new HikariDataSource(config);
                }
                return datasource;
        }
        
        public static void main(String[] args)
        {
                      
        Connection connection = null;
                PreparedStatement pstmt = null;
                ResultSet resultSet = null;
                try
                {
                        DataSource dataSource = HikariCP.getDataSource();
                        connection = dataSource.getConnection();
                        pstmt = connection.prepareStatement("SELECT * FROM computer");
                        
                        System.out.println("The Connection Object is of Class: " + connection.getClass());
                        
                        resultSet = pstmt.executeQuery();
                        while (resultSet.next())
                        {
                                System.out.println(resultSet.getString(1) + "," + resultSet.getString(2) + "," + resultSet.getString(3));
                        }

                }
                catch (Exception e)
                {
                        try
                        {
                                connection.rollback();
                        }
                        catch (SQLException e1)
                        {
                                e1.printStackTrace();
                        }
                        e.printStackTrace();
                }
        
        }

}
