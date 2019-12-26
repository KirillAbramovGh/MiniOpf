package com.netcracker.students.o3.model.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.students.o3.model.Model;

import java.io.File;
import java.io.IOException;

public class SerializerImpl implements Serializer
{

    public void serializeModel(Model model) throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("entities.json");
        objectMapper.writeValue(file, model);
    }

    public void deserializeModel(Model model) throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("C:\\Users\\Kirill\\IdeaProjects\\MiniOPF\\entities.json");
        Model newModel = objectMapper.readValue(file, Model.class);

        model.setCustomers(newModel.getCustomers());
        model.setEmployers(newModel.getEmployers());
        model.setOrders(newModel.getOrders());
        model.setTemplates(newModel.getTemplates());
        model.setAreas(newModel.getAreas());
        model.setServices(newModel.getServices());
    }

}
