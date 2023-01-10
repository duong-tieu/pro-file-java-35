package com.codegym.product;

import java.util.Scanner;

public class SadCake extends Cake implements Content {
    public SadCake(String idCake, String nameCake, String type, String size, double price, int stock) {
        super(idCake, nameCake, type, size, price, stock);
    }

    public SadCake(String name, String id, String type, String size, Double price,int newQuantity) {
        super(name, id, type, size, price, newQuantity);
    }

    @Override
    public void content() {
        Scanner sc =  new Scanner(System.in);
        System.out.println("Lựa chọn loại bánh mặn bạn muốn mua:");
        int choose = sc.nextInt();
        switch (choose){
            case 1 :
                System.out.println(" Bạn muốn bánh có nhân gì : ");
                int choice = sc.nextInt();
                switch (choice){
                    case 1 :
                        System.out.println("nhân thịt, cùng rau và đồ chua.");
                        break;
                    case 2 :
                        System.out.println("nhân thịt nguội, cùng rau ăn kèm.");
                        break;
                    case 3:
                        System.out.println("Các loại nhân khác.");
                        break;
                    default:
                        System.out.println("hoàn thành cái bánh.");
                        break;
                }
            }
        }
    }
