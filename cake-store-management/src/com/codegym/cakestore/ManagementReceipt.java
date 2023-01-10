package com.codegym.cakestore;

import com.codegym.customer.Customer;
import com.codegym.customer.ManagementCustomer;
import com.codegym.product.Cake;
import com.codegym.product.ManagementCake;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class ManagementReceipt {
    private static final String File_Bill="bill.csv";
    private final ManagementReceipt managementReceipt = new ManagementReceipt();
    public static ManagementReceipt getManagementReceipt;
         ManagementCustomer managementCustomer = ManagementCustomer.getManagerCustomer();
         ManagementCake managementCake = ManagementCake.getManagementCake();
    private static List<Receipt> receiptList;
    public ManagementReceipt(){
        receiptList = new ArrayList<>();
        ReadFileReceipt();
    }
    public void add(Receipt newReceipt)  {
        newReceipt.setTotal();
        receiptList.add(newReceipt);
        updateQuantity(newReceipt);
        updateCustomer(newReceipt);
        SaveFileReceipt();
    }

    public Receipt SearchByIdReceipt(UUID idReceipt){
        for(Receipt receipt : receiptList) {
            if (receipt.getIdReceipt().equals(idReceipt)) {
                return receipt;
            }
        }
        return null;
    }

    public List<LocalDate> SearchReceipt(LocalDate dateMade){
       List<LocalDate> dayList = new ArrayList<>();
       for(LocalDate date : dayList){
           if(date.isBefore(dateMade)){
               dayList.add(dateMade);
           }
       }
       return dayList;
    }
    public void updateQuantity(Receipt newReceipt){
        TreeMap<String , Integer> newQuantity = newReceipt.treeMap();
        for(Map.Entry<String , Integer> Receipt : newQuantity.entrySet()){
            Cake cake = managementCake.SearchById(newReceipt.getIdCake());
            cake.setStock(cake.getStock() - Receipt.getValue());
            if(cake.getStock() > 0) {
                managementCake.SaveFill();
            }
        }
    }

    public void updateCustomer(Receipt newReceipt) {
        Customer c = managementCustomer.SearchById(newReceipt.getIdCustomer());
        if(c != null){
            newReceipt.setNameCustomer(c.getName());
            newReceipt.setPhoneNumber(c.getPhone());
            newReceipt.setIdCustomer(c.getId());
        } else {
            Customer newCustomer = new Customer();
            newCustomer.setId(c.getId());
            newCustomer.setName(c.getName());
            newCustomer.setPhone(c.getPhone());
            managementCustomer.AddCustomer(newCustomer);
        }
        managementCustomer.SaveFile();
    }
    private void ReadFileReceipt() {
        receiptList.clear();
        try {
            FileReader fileReaderReceipt = new FileReader(File_Bill);
            BufferedReader bufferedReaderReceipt = new BufferedReader(fileReaderReceipt);
            String i = "";
            if ((i = bufferedReaderReceipt.readLine()) != null) {
                System.out.println(i);
            }
            bufferedReaderReceipt.close();
            fileReaderReceipt.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void SaveFileReceipt() {
        try {
            FileWriter fileWriterReceipt = new FileWriter(File_Bill);
            BufferedWriter bufferedWriterReceipt = new BufferedWriter(fileWriterReceipt);
            for (Receipt r : receiptList){
                bufferedWriterReceipt.write(r.toString());
                bufferedWriterReceipt.newLine();
            }
            bufferedWriterReceipt.close();
            fileWriterReceipt.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Cake> inStockList(){
        return managementCake.inStock();
    }

    public boolean RemoveByIdReceipt(UUID idReceipt) {
        Receipt receipt = SearchByIdReceipt(idReceipt);
        if (receipt != null) {
            receiptList.remove(receipt);
            SaveFileReceipt();
            System.out.println(receipt);
            return true;
        }
        return false;
    }
    public String Display(){
        String string = "";
        for(Receipt receipt : receiptList){
            string += receipt.toString();
        }
        ReadFileReceipt();
        return string;
    }
    public List<Receipt> SearchNameCustomer(String name) {
        List<Receipt> receipts = new ArrayList<>();
        for (Receipt r : receipts){
            if (r.getNameCustomer().equals(name)){
                receiptList.add(r);
            }
        }
        return receiptList;
    }
}
