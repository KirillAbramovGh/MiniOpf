package com.netcracker.students.o3.model.serializer.XMLLog;

import com.netcracker.students.o3.model.area.AreaImpl;
import com.netcracker.students.o3.model.orders.OrderImpl;
import com.netcracker.students.o3.model.services.ServiceImpl;
import com.netcracker.students.o3.model.templates.TemplateImpl;
import com.netcracker.students.o3.model.users.CustomerImpl;
import com.netcracker.students.o3.model.users.EmployerImpl;

import org.xml.sax.SAXException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class XMLSerializer
{
    private static final Class[] classes = new Class[]{
            XMLRequestsWrapper.class, XMLRequest.class,
            HashSet.class, HashMap.class,
            CustomerImpl.class, EmployerImpl.class, AreaImpl.class,
            OrderImpl.class, TemplateImpl.class, ServiceImpl.class
    };

    public void serializeObjectToXML(XMLRequestsWrapper o, String fileName)
    {
        try
        {
            Writer writer = new FileWriter(fileName);


            JAXBContext context = JAXBContext.newInstance(classes);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty("jaxb.formatted.output", true);
            marshaller.marshal(o, writer);

            schemeValidation(fileName,"C:\\Users\\Kirill\\IdeaProjects\\MiniOPF\\logs\\shema.xsd");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean schemeValidation(String pathXml, String pathXsd) throws IOException
    {
        try
        {
            SchemaFactory factory = SchemaFactory
                    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(pathXsd));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(pathXml));
            return true;
        }
        catch (SAXException e)
        {
            e.printStackTrace();
            return false;
        }
    }


}
