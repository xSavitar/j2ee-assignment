/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eduLibrary.webservice;

import com.eduLibrary.entities.Books;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author derick
 */
@Stateless
@WebService(serviceName = "LibraryService")

public class LibraryBooksWebserviceBean implements LibraryBooksWebserviceBeanRemote {

    public LibraryBooksWebserviceBean() {
    }
    @PersistenceContext(unitName = "EjbModulePU")
    private EntityManager entityManager;

    public void addBook(Books book) {
        entityManager.persist(book);
    }

    @WebMethod(operationName = "getBooks")
    public List<Books> getBooks() {
        return entityManager.createQuery("From Books").getResultList();
    }

}
