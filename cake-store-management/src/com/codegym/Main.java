package com.codegym;

import com.codegym.cakestore.ManagementMenu1;
import com.codegym.customer.ManagementMenu2;
import com.codegym.product.ManagementMenu3;

import java.util.Calendar;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());
           ManagementMenu1 managementMenu1 = new ManagementMenu1();
           ManagementMenu2 managementMenu2 = new ManagementMenu2();
           ManagementMenu3 managementMenu3 = new ManagementMenu3();
        int choose = sc.nextInt();
        do {
            switch (choose) {
                case 1 -> managementMenu1.menu();
                case 2 -> managementMenu2.menu();
                case 3 -> managementMenu3.menu();
                default -> {
                }
            }
        }while(choose != 0);
    }
}


