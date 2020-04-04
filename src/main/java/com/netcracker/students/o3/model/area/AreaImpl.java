package com.netcracker.students.o3.model.area;

import java.math.BigInteger;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "areas")
@XmlType(name = "area")
@XmlRootElement
public class AreaImpl implements Area {

    @Id
    private BigInteger id;
    @Column(name = "area_name")
    private String name;
    @Column(name = "description")
    private String description;

    public AreaImpl() {
    }


    public AreaImpl(final BigInteger id, final String name, final String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(final BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        final AreaImpl area = (AreaImpl) o;
        return Objects.equals(id, area.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
