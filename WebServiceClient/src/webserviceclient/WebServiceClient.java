/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webserviceclient;

/**
 *
 * @author derick
 */
public class WebServiceClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        for(com.webservice.client.Books book:getBooks()){
        System.out.println(book.getName());
}
        
    }

    private static java.util.List<com.webservice.client.Books> getBooks() {
        com.webservice.client.LibraryService service = new com.webservice.client.LibraryService();
        com.webservice.client.LibraryBooksWebserviceBean port = service.getLibraryBooksWebserviceBeanPort();
        return port.getBooks();
    }
    
    
    
}
