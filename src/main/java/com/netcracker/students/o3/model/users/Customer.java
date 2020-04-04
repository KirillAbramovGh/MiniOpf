package com.netcracker.students.o3.model.users;

import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.area.AreaImpl;
import com.netcracker.students.o3.model.services.Service;

import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public interface Customer extends User
{

     @Id
     @Override
     BigInteger getId();

     @Column(name = "login")
     @Override
     String getLogin();

     @Column(name = "name")
     @Override
     String getName();

     @Column(name = "password")
     @Override
     String getPassword();

     /**
      * @return customer money balance
      * */
     @Column(name = "moneybalance")
     BigDecimal getMoneyBalance();

     /**
      * set customer money balance
      * */
     void setMoneyBalance(final BigDecimal moneyBalance);

     /**
      * @return set of connected customer services ids
      */
     @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "customer")
     Set<Service> getConnectedServices();

     /***
      * set customer connected services
      * @param connectedServices
      */
     void setConnectedServices(final Set<Service> connectedServices);

     /**
      * @return customer area id
      */
     @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
     @JoinColumn(name = "areaid")
     Area getArea();

     /**
      * set customer area id
      * @param area
      */
     void setArea(final Area area);

     /**
      * add service to customer
      * @param service
      */
     void addConnectedService(Service service);
}
