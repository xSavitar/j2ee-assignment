/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eduLibrary.rawdatabase;

import com.eduLibrary.model.Bookss;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author derick
 */
@Remote
public interface LibraryBookssPersistentBeanRemote {

    void addBook(Bookss bookName);

    List<Bookss> getBooks();
}
