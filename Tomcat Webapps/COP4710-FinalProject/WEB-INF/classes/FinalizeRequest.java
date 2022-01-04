import java.io.FileNotFoundException;
import java.sql.*;
import java.lang.*;
import java.io.FileWriter;
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

public class FinalizeRequest extends HttpServlet{
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
        FileWriter fw = new FileWriter("c:\\csv\\export.csv");
        fw.append("Title");
        fw.append(",");
        fw.append("Author");
        fw.append(",");
        fw.append("Edition");
        fw.append(",");
        fw.append("Publisher");
        fw.append(",");
        fw.append("ISBN");
        fw.append(",");
        fw.append("Username");
        fw.append("\n");
        out.println("<div>");
        try
        {
            getDBConnection();
            String semester = request.getParameter("sem");
            preparedStatement = connection.prepareStatement("select * from book where semester = ? group by username");
            preparedStatement.setString(1, semester);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                fw.append(rs.getString("title"));
                fw.append(",");
                fw.append(rs.getString("author"));
                fw.append(",");
                fw.append(rs.getString("edition"));
                fw.append(",");
                fw.append(rs.getString("publisher"));
                fw.append(",");
                fw.append(rs.getString("isbn"));
                fw.append("\n");
            }
            fw.flush();
            fw.close();
            preparedStatement.close();
            connection.close();
            message = "<b>successfully created CSV file at C:/csv/export.csv</b>";
           }
            catch (Exception e)
           {
            out.println("error");
        }

        HttpSession session = request.getSession();
        session.setAttribute("message", message);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Admin/finalizeRequest.jsp");
        dispatcher.forward(request, response);
    }
}
