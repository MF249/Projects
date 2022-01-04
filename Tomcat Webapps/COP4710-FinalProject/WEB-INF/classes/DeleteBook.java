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

public class DeleteBook extends HttpServlet{
    private Connection connection;
    private PreparedStatement preparedStatement;
    String message = " ";

    private void getDBConnection() {

        Properties properties = new Properties();
        FileInputStream filein = null;
        MysqlDataSource dataSource = null;
        try {
            filein = new FileInputStream("webapps/COP4710-FinalProject/WEB-INF/lib/db.properties");
            //filein = new FileInputStream("C:/Github/DBFinalProject/COP4710-FinalProject/WEB-INF/lib/db.properties");
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

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String semester = request.getParameter("semester");
        String isbn = request.getParameter("isbn");
        String username = request.getParameter("username");

        try
        {
            getDBConnection();

            preparedStatement = connection.prepareStatement("delete from book where isbn = ? and semester = ? and username = ?");
            preparedStatement.setString(1, isbn);
            preparedStatement.setString(2, semester);
            preparedStatement.setString(3, username);
            int validate = preparedStatement.executeUpdate();

            if (validate > 0) {
                message = "Sucessfully deleted book.";
            } else {
                message = "Issue with deleting book. " + String.valueOf(validate);
            }

            preparedStatement.close();
            connection.close();
        }
        catch (Exception e)
        {
            message = e.toString();
        }



        HttpSession session = request.getSession();
        session.setAttribute("message", message);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/bookRequest.jsp");
        dispatcher.forward(request, response);
    }
}
