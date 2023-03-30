package org.example.repository.Services;

import org.example.repository.models.Product;

import java.util.ArrayList;
import java.util.List;

public class PrincipalList {
    List<Product> list = new ArrayList<>();

    public List<Product> setList(List<Product> listForAdd){
        list.clear();
        list.addAll(listForAdd);
        return list;
    }
}
