import java.io.FileNotFoundException;
import java.sql.*;
import java.lang.*;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.*;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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

public class tempPassword extends HttpServlet{
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

        String to = "";
        String from = "superadmin@cop4710.com";
        String host = "localhost";

        //Get the session object
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);

        String username = request.getParameter("passUsername");
        int validate = -1;
        Random r = new Random();
        String pass = String.valueOf(r.nextInt(999999));

        try {
            getDBConnection();


            String query = "update login set password = ? where username = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, pass);
            preparedStatement.setString(2, username);

            validate = preparedStatement.executeUpdate();
            if (validate > 0) {
                message = "Password successfully changed and email sent.";
            } else {
                message = "There was an error updating the password. Please try again.";
            }

            query = "select * from login where username = ?";
            preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery(query);

            to = rs.getString("email");
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setSubject("Textbook Order Request Temporary Password");
            message.setText("Your temporary password is: "+ pass
                            + ", please contact an Administrator to change this as soon as possible");
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));

            Transport.send(message);


            connection.close();

        } catch (Exception e) {
            message = "Invalid values given. Please try again.";
        }

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("message", message);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
}
