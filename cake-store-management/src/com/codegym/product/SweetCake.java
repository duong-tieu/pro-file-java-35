package com.codegym.product;

import java.util.Scanner;

public class SweetCake extends Cake implements Content{
    public SweetCake(String idCake, String nameCake, String type, String size, double price, int stock) {
        super(idCake, nameCake, type, size, price, stock);
    }

    public SweetCake(String name, String id, String type, String size, Double price, int newQuantity) {
        super(name, id, type, size, price, newQuantity);
    }

    @Override
    public void content(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chọn bánh có hàm lượng trong bánh:");
        String choose;
        choose = scanner.nextLine();
        if(choose == "0"){
            System.out.println("Bánh có hàm lượng đường là 0% ");
        } else if(choose == "1"){
            System.out.println("Bánh có hàm lượng đường là 20%");
        } else if(choose == "2"){
            System.out.println("Bánh có hàm lượng đường là 50%");
        } else if(choose == "3"){
            System.out.println("Bánh có hàm lượng đường là 70%");
        } else if(choose == "4"){
            System.out.println("Banhs có hàm lượng đường là 100%");
        }
    }
}
