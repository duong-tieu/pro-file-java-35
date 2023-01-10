package com.codegym.product;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class ManagementCake {
    private static final ManagementCake managementCake = new ManagementCake();
    private final List<Cake> CakeList;
    private static final String File_Cake = "cake.csv";
    public static ManagementCake getManagementCake() {
        return managementCake;
    }
    private ManagementCake() {
        CakeList = new ArrayList<>();
        ReadFill();
    }




    public void Add(Cake newcake) {
        CakeList.add(newcake);
        SaveFill();
    }

    public boolean Remove(String nameCake) {
        Cake cake = (Cake) SearchCakeName(nameCake);
        if (cake != null) {
            CakeList.remove(cake);
            SaveFill();
            return true;
        }
        return false;
    }

    public List<Cake> SearchCakeName(String name) {
        List<Cake> nameCakelist = new ArrayList<>();
        for (Cake cake : nameCakelist) {
            if (cake.getNameCake().contains(name)) {
                nameCakelist.add(cake);
            }
        }
        return nameCakelist;
    }

    public Cake SearchById(String id) {
        for (Cake cake : CakeList) {
            if (cake.getIdCake().equals(id)) {
                return cake;
            }
        }
        return null;
    }

    public List<Cake> SearchByType(String type) {
        List<Cake> typeList = new ArrayList<>();
        for (Cake cake : typeList) {
            if (cake.getType().contains(type)) {
                typeList.add(cake);
            }
        }
        return typeList;
    }

    public List<Cake> DisplayCake() {
        return new ArrayList<>(CakeList);
    }

    public void UpdateNewCake(String name, Cake newCake) {
        Cake cake = (Cake) SearchCakeName(name);
        if (cake != null) {
            cake.setType(newCake.getType());
            cake.setPrice(newCake.getPrice());
            cake.setSize(newCake.getSize());
        }
        SaveFill();
    }

    public List<Cake> OutOfStock() {
        List<Cake> outOfstock = new ArrayList<>();
        for (Cake cake : CakeList) {
            if (!cake.isStock) {
                outOfstock.add(cake);
            }
        }
        ReadFill();
        return outOfstock;
    }

    public List<Cake> inStock() {
        List<Cake> inStock = new ArrayList<>();
        for (Cake cake : CakeList) {
            if (cake.isStock()) {
                inStock.add(cake);
            }
        }
        ReadFill();
        return inStock;
    }

    public Cake handleLine(String line){
        String[] strings = line.split(",");
        return new Cake(strings[0], strings[1], strings[2], strings[3],
                Double.parseDouble(strings[4]), Boolean.parseBoolean(strings[5]), Integer.parseInt(strings[6]));
    }
    private void ReadFill() throws RuntimeException {
        CakeList.clear();
        try {
            FileReader fileReader = new FileReader(File_Cake);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line  ;
            while ((line = bufferedReader.readLine()) != null) {
                Cake cake = handleLine(line);
                CakeList.add(cake);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void SaveFill() {
        try {
            FileWriter fileWriter = new FileWriter(File_Cake);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Cake c : CakeList) {
                bufferedWriter.write(c.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


