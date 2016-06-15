/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbmoduletester;

import com.eduLibrary.timer.TimerSessionBeanRemote;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author derick
 */
public class TimerTester {

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
            ex.printStackTrace();
        }
        brConsoleReader
                = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) {
        TimerTester ejbTester = new TimerTester();
        ejbTester.testTimerService();
    }

    private void showGUI() {
        System.out.println("**********************");
        System.out.println("Welcome to Book Store");
        System.out.println("**********************");
        System.out.print("Options \n1. Add Book\n2. Exit \nEnter Choice: ");
    }

    private void testTimerService() {
        try {
            TimerSessionBeanRemote timerServiceBean
                    = (TimerSessionBeanRemote) ctx.lookup("TimerSessionBean/remote");
            System.out.println("[" + (new Date()).toString() + "]" + " timercreated.");
            timerServiceBean.createTimer(2000);
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
    }

}
