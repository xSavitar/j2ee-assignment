/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eduLibrary.persistence;

import com.eduLibrary.entities.Bookblob;
import com.eduLibrary.entities.BookEmbeddable;
import com.eduLibrary.entities.Books;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
//import javax.persistence.TypedQuery;

/**
 *
 * @author derick
 */
@Stateless
public class LibraryPersistentBean implements LibraryPersistentBeanRemote {

    public LibraryPersistentBean() {
    }

    @PersistenceContext(unitName = "EjbModulePU")
    private EntityManager entityManager;

    public void addBook(Books book) {
        entityManager.persist(book);
    }

    public List<Books> getBooks() {

        Query q2 = entityManager.createQuery("SELECT b FROM Books b");
        return q2.getResultList();

    }

    public void addBookEmbeddable(BookEmbeddable book) {
        entityManager.persist(book);

    }

    public List<BookEmbeddable> getBookEmbeddable() {
        Query q2 = entityManager.createQuery("SELECT b FROM BookEmbeddable b");
        return q2.getResultList();
    }

    public void addBookBlob(Bookblob book) {

        entityManager.persist(book);
    }

    public List<Bookblob> getBookBlob() {
        Query q2 = entityManager.createQuery("SELECT b FROM Bookblob b");
        return q2.getResultList();

    }

    /**
     * Providing support for CallBack Annotations
     */
    @PostConstruct
    public void postConstruct() {
        System.out.println("postConstruct:: LibraryPersistentBean session bean"
                + " created with entity Manager object: ");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("preDestroy: LibraryPersistentBean session"
                + " bean is removed ");
    }

//    public void addBookQuery(BookQuery book) {
//
//        entityManager.persist(book);
//
//    }
//
//    public List<BookQuery> getBookQuery() {
//        
//        
//        Query q2 = entityManager.createQuery("BookQuery.findAll");
//        return q2.getResultList();
////        //create an ejbql expression
////        String ejbQL = "SELECT b From BookQuery b where b.name like ?1";
////        //create query
////        Query query = entityManager.createQuery(ejbQL);
////        //substitute parameter.
////        query.setParameter(1, "%test%");
////        //execute the query
////        return query.getResultList();
//
//    }

}
