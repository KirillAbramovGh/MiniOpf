package com.netcracker.students.o3.model.users;

import java.math.BigInteger;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "employee")
@XmlRootElement
public class EmployerImpl implements Employee
{
    private BigInteger id;
    private String name;
    private String login;
    private String password;

    public EmployerImpl(final String name, final String login, final String password)
    {
        this.name = name;
        this.login = login;
        this.password = password;
    }


    @Override
    public String toString()
    {
        return "Employer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public EmployerImpl(){}

    public EmployerImpl(final BigInteger id, final String name, final String login, final String password)
    {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    @Override
    public BigInteger getId()
    {
        return id;
    }


    @Override
    public void setId(final BigInteger id)
    {
        this.id = id;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void setName(final String name)
    {
        if(name!=null && !name.replaceAll(" ","").isEmpty())
        {
            this.name = name;
        }
    }

    @Override
    public String getLogin()
    {
        return login;
    }

    @Override
    public void setLogin(final String login)
    {
        if(login!=null && !login.replaceAll(" ","").isEmpty())
        {
            this.login = login;
        }
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public void setPassword(final String password)
    {
        if(password!=null && !password.replaceAll(" ","").isEmpty())
        {
            this.password = password;
        }
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
        final EmployerImpl employer = (EmployerImpl) o;
        return Objects.equals(id, employer.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
