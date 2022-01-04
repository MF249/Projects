import java.io.FileNotFoundException;
import java.sql.*;
import java.lang.*;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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

public class IndvInvite extends HttpServlet{
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

        String to = request.getParameter("mail");
        String from = "superadmin@cop4710.com";
        String host = "localhost";

        //Get the session object
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);


        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setSubject("Textbook Order Request Invitation");
            message.setText("Hello professors, textbook order requests for the Spring 2022 semester have opened up. " +
                    "Please join our site here: http://localhost:8080/accountCreate.jsp");
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));

            Transport.send(message);
        } catch (Exception e) {
            message = e.toString();
        }

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("message", message);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Admin/indvInvite.jsp");
        dispatcher.forward(request, response);
    }
}
