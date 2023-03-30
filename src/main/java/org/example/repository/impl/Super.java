package org.example.repository.impl;
import org.example.repository.Controllers.ModelFactoryController;
import org.example.repository.ConnectionBD.ConexionBD;
import org.example.repository.models.Product;
import org.example.repository.Interfaces.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Super implements Repository {
    ModelFactoryController mfc = ModelFactoryController.getInstance();
    public Connection getConnection() throws SQLException {
        return ConexionBD.getInstance();
    }

    private Product createProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getLong("id"));
        product.setName(resultSet.getString("nombre"));
        product.setPrice(resultSet.getDouble("precio"));
        product.setDate(resultSet.getDate("fecha_registro").toLocalDate());
        return product;
    }

    public List<Product> list() {
        List<Product> productoList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * from productos")) {
            while (resultSet.next()) {
            Product product = createProduct(resultSet);
            productoList.add(product);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return productoList;
    }


    public Product byId(Long id){
        Product product = null;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM productos where id =?")){
          preparedStatement.setLong(1,id);
          ResultSet resultSet = preparedStatement.executeQuery();
          if(resultSet.next()){
              product = createProduct(resultSet);
          }
          resultSet.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public void save(Object nombre, Object precio, Object fecha) {
        String sentencia = "INSERT INTO productos(nombre,precio,fecha_registro) values(?,?,?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sentencia)){
           preparedStatement.setString(1,nombre.toString());
           preparedStatement.setDouble(2,Double.parseDouble(precio.toString()));
           preparedStatement.setDate(3, Date.valueOf(fecha.toString()));
           preparedStatement.executeUpdate();
           mfc.setList(new Super().list());
        }catch (SQLException e){
          e.printStackTrace();
        }
    }

    @Override
    public  void delete(Long id) {
      try (PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE from productos where id = ?")){
          preparedStatement.setLong(1,id);
          preparedStatement.executeUpdate();
          mfc.setList(new Super().list());
      }catch (SQLException e){
          e.printStackTrace();
      }
    }

    public void Update(Long id,String newValue){
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("UPDATE productos set nombre = ? where id = ?")){
           preparedStatement.setString(1,newValue);
           preparedStatement.setLong(2,id);
           preparedStatement.execute();
           mfc.setList(new Super().list());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Base de datos Al principio");
        List<Product> list = new Super().mfc.setList(new Super().list());
        list.forEach(System.out::println);
        System.out.println("");
        System.out.println("Eliminamos el Id 2");
        new Super().delete(2l);
        list.forEach(System.out::println);
        System.out.println("");
        System.out.println("Agregamos Dron 230 2023-09-01 ");
        new Super().save("Dron",230,"2023-09-01");
        list.forEach(System.out::println);
        System.out.println("");
        System.out.println("Obtenemos el id 1");
        Optional<Product> op1 = Optional.of(new Super().byId(1l));
        op1.ifPresentOrElse((x)-> System.out.println("Id :"+x.getId()+" nombre : "+x.getName()+"precio : "+x.getPrice()+" fecha : "+x.getDate()),
                ()-> System.out.println("No existe el elemento"));
        System.out.println("");
        System.out.println("Actualizamos el Id numero 3 con el producto  carro ");
        new Super().Update(3l,"carro");
        list.forEach(System.out::println);

    }
    }


