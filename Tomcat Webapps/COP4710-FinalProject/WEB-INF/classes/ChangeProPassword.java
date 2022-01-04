import java.io.FileNotFoundException;
import java.sql.*;
import java.lang.*;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.MysqlDataSource;

//import javax.servlet.ServletConfig;
//import javax.servlet.ServletException;
//import javax.servlet.UnavailableException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class ChangeProPassword extends HttpServlet{
    private Connection connection;
    private PreparedStatement preparedStatement;
    String message = " ";

    private void getDBConnection() {

        Properties properties = new Properties();
        FileInputStream filein = null;
        MysqlDataSource dataSource = null;
        try {
            filein = new FileInputStream("webapps/COP4710-FinalProject/WEB-INF/lib/db.properties");
            properties.load(filein);
            dataSource = new MysqlDataSource();
            dataSource.setURL(properties.getProperty("MYSQL_DB_URL"));
            dataSource.setUser(properties.getProperty("MYSQL_DB_USERNAME"));
            dataSource.setPassword(properties.getProperty("MYSQL_DB_PASSWORD"));
            connection = dataSource.getConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String username = request.getParameter("username");
        String newPassword = request.getParameter("newPassword");
        String reenter = request.getParameter("reenter");
        int validate = -1;

        try {
            getDBConnection();

            if(newPassword.compareTo(reenter) == 0) {
                String query = "update login set password = ? where username = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, newPassword);
                preparedStatement.setString(2, username);

                validate = preparedStatement.executeUpdate();
            }
            if (validate > 0) {
                message = "Password successfully changed.";
            } else {
                message = "Entered passwords do not match. Please try again.";
            }

            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            message = "Invalid values given. Please try again.";
        }

        HttpSession session = request.getSession();
        session.setAttribute("message", message);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/professorPassword.jsp");
        dispatcher.forward(request, response);
    }
}
