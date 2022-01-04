import java.io.FileNotFoundException;
import java.sql.*;
import java.lang.*;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.MysqlDataSource;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class RequestCreation extends HttpServlet{
    private Connection connection;
    private PreparedStatement preparedStatement;
    String message = " ";

    private void getDBConnection() {

        try {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser("root");
            dataSource.setPassword("root");
            dataSource.setURL("jdbc:mysql://localhost:3306/bookRequest");

            connection = dataSource.getConnection();
        } catch (Exception e) {
            message = e.toString();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String bookTitle = request.getParameter("bookTitle");
        String authorName = request.getParameter("authorName");
        String edition = request.getParameter("edition");
        String publisher = request.getParameter("publisher");
        String isbn = request.getParameter("isbn");

        try {
            getDBConnection();

            String query = "insert into book values (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, bookTitle);
            preparedStatement.setString(2, authorName);
            preparedStatement.setString(3, edition);
            preparedStatement.setString(4, publisher);
            preparedStatement.setString(5, isbn);

            int validate = preparedStatement.executeUpdate();

            if (validate > 0) {
                message = "Request successfully created.";
            } else {
                message = "The request you have entered is already made. Please enter another.";
            }

            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            message = e.toString();
        }

        HttpSession session = request.getSession();
        session.setAttribute("message", message);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/bookRequest.jsp");
        dispatcher.forward(request, response);
    }
}
