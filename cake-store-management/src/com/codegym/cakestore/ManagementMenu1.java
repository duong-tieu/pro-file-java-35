package com.codegym.cakestore;

import com.codegym.customer.ManagementCustomer;
import com.codegym.product.Cake;
import com.codegym.product.ManagementCake;


import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ManagementMenu1 {
    ManagementReceipt managementReceipt = ManagementReceipt.getManagementReceipt;
    ManagementCake managementCake = ManagementCake.getManagementCake();
    ManagementCustomer managerCustomer = ManagementCustomer.getManagerCustomer();
    public void DisplayMenu() {
        System.out.println("|----- Hóa đơn cửa hàng -----|");
        System.out.println("|      1. Thêm hóa đơn       |");
        System.out.println("|      2. Tìm tên người mua  |");
        System.out.println("|      3. Xóa hóa đơn theo id|");
        System.out.println("|      4. Tìm hóa đơn bằng id|");
        System.out.println("|      5. Tìm hóa đơn được xuất vào ngày bất kì.|");
        System.out.println("|      6. hiện ra hóa đơn.    |");
        System.out.println("|      0. Thoát hóa đơn.      |");
    }
    public void menu()  {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while(choice != 0) {
            DisplayMenu();
            System.out.println("Nhập số :");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addReceipt();
                case 2 -> SearchCustomer();
                case 3 -> RemoveById();
                case 4 -> SearchByIdBill();
                case 5 -> SearchDay();
                case 6 -> printReceipt();
                default -> {
                }
            }
        }
    }
    private void addReceipt()  {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Danh sách loại bánh cửa hàng vẫn còn:");
        List<Cake> cakeList = ManagementCake.getManagementCake().inStock();
        for (Cake cake : cakeList) {
            System.out.println(cake);
        }
        System.out.println("Nhập tên của khách mua :");
        String nameCustomer = scanner.nextLine();
        System.out.println("Nhập mã số khách hàng:");
        String idCustomer = scanner.nextLine();
        System.out.println("Nhập số điện thoại của khách mua");
        String phoneCustomer = scanner.nextLine();
        Receipt newReceipt = new Receipt(nameCustomer, idCustomer, phoneCustomer);
        do {
            System.out.println("1. Nhập thêm bánh vào hóa đơn");
            System.out.println("0. Không thêm loại bánh nào vào danh sách");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if(choice == 1) {
                System.out.println("Nhập id của bánh");
                String idCake = scanner.nextLine();
                do {
                    System.out.println("Nhập số lương bánh người mua muốn mua: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();
                    Cake cake = managementCake.SearchById(idCake);
                    if (quantity > cake.getStock()) {
                        System.out.println("Số bánh còn lại trong của hàng không đủ");
                        System.out.println("bánh :" + cake.getNameCake() + " có " + cake.getType() + " và có " +
                                cake.getSize() + " còn lại " + cake.getStock());
                        managementReceipt.RemoveByIdReceipt(newReceipt.getIdReceipt());
                    } else {
                        newReceipt.add(idCake, quantity);
                    }
                } while (true);
            } else {
                System.out.println(" Không nhập thêm bánh vào hóa đơn ");
                break;
            }
        } while (true);
        managementReceipt.add(newReceipt);
    }
    private void RemoveById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("nhap id cua hoa don ");
        UUID idReceipt = UUID.fromString(scanner.nextLine());
        if (managementReceipt.RemoveByIdReceipt(idReceipt)) {
            System.out.println("Đã xóa khỏi hóa đơn");
        } else {
            System.out.println("van con trong danh sach");
        }
    }
    private void SearchByIdBill() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhâpj vào id của hóa đơn");
        UUID idReceipt = UUID.fromString(scanner.nextLine());
        Receipt searchByIdReceipt = managementReceipt.SearchByIdReceipt(idReceipt);
        if (searchByIdReceipt != null) {
            System.out.println(searchByIdReceipt);
        } else {
            System.out.println("khong tim thay id hoa don");
        }
    }

    private void SearchCustomer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("nhap ten cua khach hang");
        String nameCustomer = sc.nextLine();
        List<Receipt> SearchCustomer = managementReceipt.SearchNameCustomer(nameCustomer);
        if (SearchCustomer.size() != 0) {
            for (Receipt r : SearchCustomer) {
                System.out.println(r);
            }
        } else {
            System.out.println("Id khong ton tai");
        }
    }

    private void SearchDay(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập ngày muốn tìm hoá đơn.");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        List<LocalDate> dayList = managementReceipt.SearchReceipt(date);
        if (dayList != null){
           for (LocalDate localDate : dayList) {
               System.out.println(localDate);
           }
        } else {
            System.out.println("hoa don trong ngay nay khong co");
        }
    }
    private void printReceipt() {
        System.out.println(managementReceipt.Display());
    }
}