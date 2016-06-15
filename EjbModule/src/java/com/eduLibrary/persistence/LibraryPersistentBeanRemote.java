/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eduLibrary.persistence;

import com.eduLibrary.entities.Bookblob;
import com.eduLibrary.entities.BookEmbeddable;
import javax.ejb.Remote;
import com.eduLibrary.entities.Books;
import java.util.List;

/**
 *
 * @author derick
 */
@Remote
public interface LibraryPersistentBeanRemote {

    void addBook(Books bookName);

    List<Books> getBooks();
    
    void addBookEmbeddable(BookEmbeddable bookName);

    List<BookEmbeddable> getBookEmbeddable();

    void addBookBlob(Bookblob book);

    List<Bookblob> getBookBlob();
    
//    void addBookQuery(Bookuery bookName);
//    List<BookQuery> getBookQuery();
}
