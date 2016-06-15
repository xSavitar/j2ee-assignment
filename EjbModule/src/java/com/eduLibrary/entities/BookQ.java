/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eduLibrary.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author derick
 */
@Entity
@Table(name = "book_query")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BookQ.findAll", query = "SELECT b FROM BookQ b"),
    @NamedQuery(name = "BookQ.findById", query = "SELECT b FROM BookQ b WHERE b.id = :id"),
    @NamedQuery(name = "BookQ.findByName", query = "SELECT b FROM BookQ b WHERE b.name = :name")})
public class BookQ implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;

    public BookQ() {
        
    }

    public BookQ(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookQ)) {
            return false;
        }
        BookQ other = (BookQ) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eduLibrary.entities.BookQ[ id=" + id + " ]";
    }
    
}
