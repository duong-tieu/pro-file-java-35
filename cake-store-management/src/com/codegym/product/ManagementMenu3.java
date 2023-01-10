package com.codegym.product;
import java.util.List;
import java.util.Scanner;
public class ManagementMenu3 {
     ManagementCake managementCake = ManagementCake.getManagementCake();
    public void DisplayMenu() {
        System.out.println("--- Danh sách các loại trên hóa đơn---");
        System.out.println("    1.Chọn loại bánh vầ thêm bánh vào danh sách:");
        System.out.println("    2. Xóa bánh ra khỏi danh sách bằng tên:");
        System.out.println("    3. Xóa toàn bộ danh sách.           ");
        System.out.println("    3. Tìm kiếm bánh bằng tên:         ");
        System.out.println("    4. Tìm kiếm bánh bằng id:          ");
        System.out.println("    5. Loại bánh:                      ");
        System.out.println("    6. Cập nhập thêm bánh:             ");
        System.out.println("    7. Loại bánh còn:                  ");
        System.out.println("    8. loại bánh không còn :           ");
        System.out.println("    9. hiển thị danh sách :             ");
        System.out.println("    ---****Kết thúc***---              ");
        System.out.println("***************************************");
    }
    public  void menu() {
        Scanner sc = new Scanner(System.in);
        int choice =  -1;
        while (choice != 0){
            DisplayMenu();
            System.out.println("nhập yêu cầu bạn muốn:");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1 -> ChooseTypeCake();
                case 2 -> remove();
                case 3 -> SearchName();
                case 4 -> SearchId();
                case 5 -> SearchType();
                case 6 -> UpDate();
                case 7 -> outOfStock();
                case 8 -> inStock();
                case 9 -> show();
                default -> {
                }
            }
        }
    }

    private void ChooseTypeCake() {
        do {
            System.out.println("|----- Bạn muốn chọn loại bánh nào-----|");
            System.out.println("1. Bánh ngọt.");
            System.out.println("2. Bánh mặn.");
            Scanner sc = new Scanner(System.in);
            int choose = sc.nextInt();
            sc.nextLine();
            switch (choose){
                case 1:
                    System.out.println("Nhập tên bánh :");String nameS = sc.nextLine();
                    System.out.println("Nhập mã số của bánh:"); String idS = sc.nextLine();
                    System.out.println("Nhập kiểu bánh:");String typeS = sc.nextLine();
                    System.out.println("Nhập kích cỡ của bánh:"); String sizeS = sc.nextLine();
                    System.out.println("Nhập số lượng bánh được làm ra:"); int quantity1 = sc.nextInt();
                    System.out.println("Nhập giá tiền của loại bánh đó:");Double priceS = sc.nextDouble();
                    Cake sweetCake = new SweetCake(idS,nameS,typeS,sizeS,priceS,quantity1);
                    managementCake.Add(sweetCake);
                    break;
                case 2:
                    System.out.println("Nhập tên bánh :");String name = sc.nextLine();
                    System.out.println("Nhập mã số của bánh:"); String id = sc.nextLine();
                    System.out.println("Nhập kiểu bánh:");String type = sc.nextLine();
                    System.out.println("Nhập kích cỡ của bánh:"); String size = sc.nextLine();
                    System.out.println("Nhập số lượng bánh được làm ra:");int quantity2 = sc.nextInt();
                    System.out.println("Nhập giá tiền của loại: ");Double price = sc.nextDouble();
                    Cake sadCake = new SadCake(id, name, type,size,price, quantity2);
                    managementCake.Add(sadCake);
                    break;
            }
        } while(true);
    }
    private void remove(){
            Scanner sc = new Scanner(System.in);
            System.out.println("Nhập tên loại bánh bạn muốn tìm:");
            String name = sc.nextLine();
            if(managementCake.Remove(name)){
                System.out.println("Đã xóa loại bánh đó ");
            } else {
                System.out.println("Chưa xóa loại bánh đó");
            }
    }
    private void SearchName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("nhập tên loại bánh bạn muốn tìm");
        String name = sc.nextLine();
        List<Cake> cakeList = managementCake.SearchCakeName(name);
        if (cakeList.size() != 0) {
            for (Cake cake : cakeList) {
                System.out.println(cake);
            }
        } else {
            System.out.println("Không tìm thấy bánh");
        }
    }
    private void SearchId(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập id của loại bánh muốn tìm:");
        String id = sc.nextLine();
        Cake searchCakeById = managementCake.SearchById(id);
            if(searchCakeById != null){
                System.out.println(id);
            }else {
                System.out.println("Không tìm thấy loại bánh có mã đó :");
            }
    }
    private void SearchType(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập kiểu bánh bạn muốn tìm :");
        String type = sc.nextLine();
        List<Cake> typeList = managementCake.SearchByType(type);
        if(typeList.size() != 0){
            for(Cake p : typeList){
                System.out.println(p);
            }
        } else {
            System.out.println("Kiểu bánh đó đã hết.");
        }
    }

    private void UpDate(){
        Scanner sc = new Scanner(System.in);
        System.out.println("nhập tên bánh mới :");
        String newName = sc.nextLine();
        System.out.println("nhập id mới của bánh:");
        String newId = sc.nextLine();
        System.out.println("nhập kiểu bánh mới:");
        String newType = sc.nextLine();
        System.out.println("nhập kích cỡ của bánh:");
        String newSize = sc.nextLine();
        System.out.println("nhập gíá mới cho bánh:");
        Double newPrice = sc.nextDouble();
        System.out.println("Nhập số lượng bánh loại mới:");
        int newQuantity = sc.nextInt();
        Cake newCake = new Cake(newName,newId, newType, newSize, newPrice, newQuantity);
        managementCake.UpdateNewCake(newCake.getNameCake(),newCake);
    }
    
    private  void inStock(){
        List<Cake> typeOfCake = managementCake.inStock();
        for(Cake oldStock : typeOfCake){
            System.out.println(oldStock);
        }
    }

    private  void outOfStock(){
        List<Cake> theCakeIsOut = managementCake.OutOfStock();
        for (Cake stock: theCakeIsOut) {
            System.out.println(stock);
        }
    }

    private void show(){
        List<Cake> pieList = managementCake.DisplayCake();
        for(Cake pie : pieList){
            System.out.println(pie);
        }
        System.out.println("-------------------");
    }
}



