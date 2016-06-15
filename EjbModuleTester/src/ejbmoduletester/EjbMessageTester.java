/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbmoduletester;

import com.eduLibrary.entities.Books;
import com.eduLibrary.persistence.LibraryPersistentBeanRemote;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author derick
 */
public class EjbMessageTester {

    /**
     * @param args the command line arguments
     */
    BufferedReader brConsoleReader = null;
    Properties props;
    InitialContext ctx;

    {
        props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
        props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        props.put(Context.PROVIDER_URL, "localhost");
//        try {
//            props.load(new FileInputStream("jndi.properties"));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
        
        try {
            ctx = new InitialContext(props);
        } catch (NamingException ex) {
            Logger.getLogger(EjbMessageTester.class.getName()).log(Level.SEVERE, null, ex);
        }
        brConsoleReader
                = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) {
        EjbMessageTester ejbTester = new EjbMessageTester();
        ejbTester.testMessageBeanEjb();
    }

    private void showGUI() {
        System.out.println("**********************");
        System.out.println("Welcome to Book Store");
        System.out.println("**********************");
        System.out.print("Options \n1. Add Book\n2. Exit \nEnter Choice: ");
    }

    private void testMessageBeanEjb() {
        try {
            int choice = 1;
            Queue queue = (Queue) ctx.lookup("/queue/BookQueue");
            QueueConnectionFactory factory
                    = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
            QueueConnection connection
                    = factory.createQueueConnection();
            QueueSession session
                    = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
            QueueSender sender = session.createSender(queue);
            while (choice != 2) {
                String bookName;
                showGUI();
                String strChoice = brConsoleReader.readLine();
                choice = Integer.parseInt(strChoice);
                if (choice == 1) {
                    System.out.print("Enter book name: ");
                    bookName = brConsoleReader.readLine();
                    Books book = new Books();
                    book.setName(bookName);
                    ObjectMessage objectMessage
                            = session.createObjectMessage(book);
                    sender.send(objectMessage);
                } else if (choice == 2) {
                    break;
                }
            }
            LibraryPersistentBeanRemote libraryBean
                    = (LibraryPersistentBeanRemote) ctx.lookup("LibraryPersistentBean/remote");
            List<Books> booksList = libraryBean.getBooks();
            System.out.println("Book(s) entered so far: " + booksList.size());
            int i = 0;
            for (Books book : booksList) {
                System.out.println((i + 1) + ". " + book.getName());
                i++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (brConsoleReader != null) {
                try {
                    brConsoleReader.close();
                } catch (IOException ex) {
                    Logger.getLogger(EjbMessageTester.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
