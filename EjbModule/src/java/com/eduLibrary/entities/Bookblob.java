/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eduLibrary.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author derick
 */
@Entity
@Table(name = "bookblob")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bookblob.findAll", query = "SELECT b FROM Bookblob b"),
    @NamedQuery(name = "Bookblob.findById", query = "SELECT b FROM Bookblob b WHERE b.id = :id"),
    @NamedQuery(name = "Bookblob.findByName", query = "SELECT b FROM Bookblob b WHERE b.name = :name"),
    @NamedQuery(name = "Bookblob.findByXml", query = "SELECT b FROM Bookblob b WHERE b.xml = :xml")})
public class Bookblob implements Serializable {
    private static final long serialVersionUID = 1L;
    
        private Integer id;
        private String name;
        private String xml;
        private byte[] image;

    public Bookblob() {
    }

    public Bookblob(Integer id) {
        this.id = id;
    }

@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

@Column(name = "xml")
    @Lob @Basic(fetch= FetchType.EAGER)
    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }


    @Column(name = "image")
    @Lob @Basic(fetch= FetchType.EAGER)
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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
        if (!(object instanceof Bookblob)) {
            return false;
        }
        Bookblob other = (Bookblob) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eduLibrary.entities.Bookblob[ id=" + id + " ]";
    }
    
}
