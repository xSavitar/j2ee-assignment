/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbmoduletester;

import com.eduLibrary.entities.Bookblob;
import com.eduLibrary.persistence.LibraryPersistentBeanRemote;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author derick
 */
public class BlobClobTester {

    BufferedReader brConsoleReader = null;
    Properties props;
    InitialContext ctx;

    {

        props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
        props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        props.put(Context.PROVIDER_URL, "localhost");
//        try {
//        try {
//            props.load(new FileInputStream("jndi.properties"));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
        try {
            ctx = new InitialContext(props);
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        brConsoleReader
                = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) {
        BlobClobTester ejbTester = new BlobClobTester();
        ejbTester.testBlobClob();
    }

    private void showGUI() {
        System.out.println("**********************");
        System.out.println("Welcome to Book Store");
        System.out.println("**********************");
        System.out.print("Options \n1. Add Book\n2. Exit \nEnter Choice: ");
    }

    private void testBlobClob() {
        try {
            int choice = 1;
            LibraryPersistentBeanRemote libraryBean = (LibraryPersistentBeanRemote) ctx.lookup("LibraryPersistentBean/remote");
            while (choice != 2) {
                String bookName;
                String publisherName;
                String publisherAddress;
                showGUI();
                String strChoice = brConsoleReader.readLine();
                choice = Integer.parseInt(strChoice);
                if (choice == 1) {
                    System.out.print("Enter book name: ");
                    bookName = brConsoleReader.readLine();
                    String xml = "<book><name>" + bookName + "</name></book>";
                    Bookblob book = new Bookblob();
                    byte[] imageBytes = {0x32, 0x32, 0x32, 0x32, 0x32, 0x32, 0x32, 0x32,
                                         0x32, 0x32, 0x32, 0x32, 0x32, 0x32, 0x32, 0x32,
                                         0x32, 0x32, 0x32, 0x32, 0x32, 0x32, 0x32, 0x32
                                         };
                    
                    book.setName(bookName);
                    book.setImage(imageBytes);
                    book.setXml(xml);
                    libraryBean.addBookBlob(book);
                } else if (choice == 2) {
                    break;
                }
            }
            List<Bookblob> booksList = libraryBean.getBookBlob();
            System.out.println("Book(s) entered so far: " + booksList.size());
            int i = 0;
            for (Bookblob book : booksList) {
                System.out.println((i + 1) + ". " + book.getName());
                byte[] imageByts = book.getImage();
                if (imageByts != null) {
                    System.out.print("image bytes: [");
                    for (int j = 0; j < imageByts.length; j++) {
                        System.out.print("0x"
                                + String.format("%x", imageByts[j]) + " ");
                    }
                    System.out.println("]");
                }
                System.out.println(book.getXml());
                i++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (brConsoleReader != null) {
                    brConsoleReader.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
