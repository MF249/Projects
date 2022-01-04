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

public class ViewRequest extends HttpServlet{
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

        PrintWriter out = response.getWriter();
        message = "<div>";
        try
        {
            getDBConnection();
            String semester = request.getParameter("sem");
            preparedStatement = connection.prepareStatement("select * from book where semester = ? group by username", ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
            preparedStatement.setString(1, semester);
            ResultSet rs = preparedStatement.executeQuery();
            message += "<table border=1 width=50% height=50%>";
            message += "<tr><th>Title</th><th>Author</th><th>Edition</th><th>Publisher</th><th>ISBN</th><th>Request By</th><tr>";
            while (rs.next())
            {
                String title = rs.getString("title");
                String auth = rs.getString("author");
                String ed = rs.getString("edition");
                String pub = rs.getString("publisher");
                String isbn = rs.getString("isbn");
                String by = rs.getString("username");
                message += "<tr><td>" + title + "</td><td>" + auth + "</td><td>" + ed + "</td><td>"
                              + pub + "</td><td>" + isbn + "</td><td>" + by + "</td></tr>";
            }
            message += "</table>";
            message += "</div>";
            preparedStatement.close();
            connection.close();
        }
        catch (Exception e)
        {
            out.println("error2");
        }

        HttpSession session = request.getSession();
        session.setAttribute("message", message);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Admin/viewRequest.jsp");
        dispatcher.forward(request, response);
    }
}
