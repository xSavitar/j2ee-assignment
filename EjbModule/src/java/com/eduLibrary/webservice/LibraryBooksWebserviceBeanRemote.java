/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eduLibrary.webservice;

import com.eduLibrary.entities.Books;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author derick
 */
@Remote
public interface LibraryBooksWebserviceBeanRemote {
    
    void addBook(Books bookName);

    List<Books> getBooks();
    
}
