package com.codegym.customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManagementCustomer {
    private static final ManagementCustomer managerCustomer = new ManagementCustomer();
    private final List<Customer> customersList;
    private static final String File_Customer = "customer.csv";
    public static ManagementCustomer getManagerCustomer() {
        return managerCustomer;
    }
    public ManagementCustomer() {
        customersList = new ArrayList<>();
        ReadFileC();
    }

    public void AddCustomer(Customer newCustomer)  {
        customersList.add(newCustomer);
        SaveFile();
    }
    public Customer SearchById(String id)  {
        for (Customer customer : customersList) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }
        return null;
    }

    public boolean Remove(String id) {
            for (Customer customer : customersList) {
                if (customer.getId().contains(id)) {
                    this.customersList.remove(id);
                    System.out.println(customersList);
                }
            }
            return false;
        }
    public void SetNewCustomer(String id, Customer newCustomer) {
        Customer c =  SearchById(id);
            if (c != null) {
                c.setName(newCustomer.getName());
                c.setAddress(newCustomer.getAddress());
                c.setPhone(newCustomer.getPhone());
            }
        }
   public List<Customer> SesrchByName(String name) {
        List<Customer> nameList = new ArrayList<>();
        for(Customer customer : customersList){
            if(customer.getName().contains(name)){
                nameList.add(customer);
            }
        }
        return nameList;
   }

   public Customer SearchByPhone(String phone){
       for (Customer customer: customersList) {
           if(customer.getPhone().equals(phone)){
              return customer;
           }
       }
       return null;
   }

   public boolean SearchByAddress(String address)  {
        List<Customer> addressList = new ArrayList<>();
        for(Customer customer: addressList){
            if(customer.getAddress().contains(address)){
                addressList.add(customer);
                SaveFile();
            }
        }
        return false;
   }

   public List<Customer> DisplayALl(){
     return new ArrayList<>(customersList);
   }

   public void SaveFile() {
      try {
          FileWriter fileWriter = new FileWriter(File_Customer);
          BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
          for(Customer c : customersList){
              bufferedWriter.write(c.toString());
              bufferedWriter.newLine();
          }
          bufferedWriter.close();
          fileWriter.close();
      } catch (IOException e){
          e.printStackTrace();
      }
   }

   public void ReadFileC(){
        customersList.clear();
        try{
            FileReader fileReader = new FileReader(File_Customer);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                Customer customer = handLine(line);
                customersList.add(customer);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e){
            throw new RuntimeException();
        }
   }

   public Customer handLine(String line){
       String[] strings = line.split(",");
       return new Customer(strings[0],strings[1], strings[2], strings[3]);
   }
}
