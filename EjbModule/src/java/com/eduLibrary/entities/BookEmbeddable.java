/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eduLibrary.entities;

import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
@Table(name = "book_embeddable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BookEmbeddable.findAll", query = "SELECT b FROM BookEmbeddable b"),
    @NamedQuery(name = "BookEmbeddable.findById", query = "SELECT b FROM BookEmbeddable b WHERE b.id = :id"),
    @NamedQuery(name = "BookEmbeddable.findByName", query = "SELECT b FROM BookEmbeddable b WHERE b.name = :name")})
public class BookEmbeddable implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private Publisher publisher;

    public BookEmbeddable() {
    }

    public BookEmbeddable(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "name",
                column = @Column(name = "PUBLISHER")),
        @AttributeOverride(name = "address",
                column = @Column(name = "PUBLISHER_ADDRESS"))
    })
    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
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
        if (!(object instanceof BookEmbeddable)) {
            return false;
        }
        BookEmbeddable other = (BookEmbeddable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eduLibrary.entities.BookEmbeddable[ id=" + id + " ]";
    }

}
