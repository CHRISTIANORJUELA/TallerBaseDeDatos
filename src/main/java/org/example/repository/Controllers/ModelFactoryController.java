package org.example.repository.Controllers;

import org.example.repository.Services.Instances;
import org.example.repository.Services.ModelFactoryService;
import org.example.repository.models.Product;

import java.util.List;

public class ModelFactoryController implements ModelFactoryService {
    Instances instances;
    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }
    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() {
        instances = new Instances();
    }


    //----------------------------******************************-------------------------------------

    @Override
    public List<Product> setList(List<Product> listForAdd) {
      return instances.getImplements1().setList(listForAdd);
    }
}
