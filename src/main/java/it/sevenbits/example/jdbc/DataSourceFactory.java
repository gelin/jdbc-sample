package it.sevenbits.example.jdbc;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

public class DataSourceFactory {

    private DataSourceFactory() {
        //avoid instantiation
    }

    static DataSource getDataSource() throws ServletException {
        Context context = null;
        DataSource dataSource;
        try {
            context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/db");
        } catch (NamingException e) {
            throw new ServletException(e);
        } finally {
            try {
                context.close();
            } catch (NamingException e) {
                throw new ServletException(e);
            }
        }
        return dataSource;
    }

}
