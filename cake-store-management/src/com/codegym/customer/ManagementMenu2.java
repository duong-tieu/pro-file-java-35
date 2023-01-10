package com.codegym.customer;
import java.util.List;
import java.util.Scanner;

public class ManagementMenu2 {
   public static ManagementCustomer managerCustomer = ManagementCustomer.getManagerCustomer();
    public static void DisplayMenu(){
        System.out.println("+++ Nhập danh sách người mua +++");
        System.out.println("1. Nhập thông tin của người mua:");
        System.out.println("2. Xóa người mua ra khỏi danh sách:");
        System.out.println("3. Xóa toàn bộ danh sách:");
        System.out.println("4. Tìm người mua bằng tên");
        System.out.println("5. Tìm người mua bằng id");
        System.out.println("6. Tìm người mua bằng số điện thoại ");
        System.out.println("7. Tìm ngưởi mua bằng địa chỉ ");
        System.out.println("8. Chỉnh sửa thông tin người mua:");
        System.out.println("9. Hiển thị danh sách:");
        System.out.println("_____ Thoát khỏi chương trình_____");
    }
    public void menu()  {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while(choice !=0){
            DisplayMenu();
            System.out.println("Lựa chọn các chức năng dưới đây:");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1 -> add();
                case 2 -> remove();
                case 3 -> SearchId();
                case 4 -> SearchName();
                case 5 -> SearchPhone();
                case 6 -> SearchAddress();
                case 7 -> SetInfomation();
                case 8 -> DisplayAll();
                default -> {
                }
            }
        }
    }
    private void add()  {
        Scanner sc =new Scanner(System.in);
        System.out.println("nhập tên của khách hàng:");
        String nameCustomer = sc.nextLine();
        System.out.println("nhâp id của khách hàng:");
        String idCustomer = sc.nextLine();
        System.out.println("nhập địa chỉ của khách hàng:");
        String addressCustomer = sc.nextLine();
        System.out.println("nhập số điện thoại của khách hàng:");
        String phoneCustomer = sc.nextLine();
        Customer newCustomer = new Customer(nameCustomer,idCustomer,addressCustomer,phoneCustomer);
        managerCustomer.AddCustomer(newCustomer);
    }
    private  void remove(){
        Scanner sc = new Scanner(System.in);
        System.out.println("nhập id của khách hàng");
        String id = sc.nextLine();
        if(managerCustomer.Remove(id)){
            System.out.println("Tìm thấy và đã xóa id đó.");
        }else {
            System.out.println("Không tìm thấy id mà bạn muốn xóa");
        }
    }

    private  void SearchName(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập tên khách hàng đã mua:");
        String name = sc.nextLine();
        List<Customer> customerNameList = managerCustomer.SesrchByName(name);
        if(customerNameList.size() != 0){
            System.out.println(name);
        } else {
            System.out.println("Khách hàng ấy không tồn tại");
        }
    }

    private  void SearchId()  {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập mã số khách hàng đã mua:");
        String id = sc.nextLine();
        Customer customerIdSearch = managerCustomer.SearchById(id);
        if(customerIdSearch != null){
            System.out.println(id);
        } else {
            System.out.println(" Mã số ấy không tồn tại.");
        }
    }

    private static void SearchPhone(){
        Scanner sc = new Scanner(System.in);
        System.out.println(" Nhập số điện của khách hàng:");
        String phone = sc.nextLine();
        Customer SearchCustomerPhone = managerCustomer.SearchByPhone(phone);
        if(SearchCustomerPhone != null){
            System.out.println(phone);
        } else {
            System.out.println("Số điện thoại ấy không tồn tại:");
        }
    }

    private static void SearchAddress() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập địa chỉ của khách hàng đã đặt hàng: ");
        String address = sc.nextLine();
        if (managerCustomer.SearchByAddress(address)) {
            System.out.println(address);
        } else {
            System.out.println("Khách hàng ấy đã nhập sai địa chỉ:");
        }
    }
    private  void SetInfomation()  {
        Scanner sc = new Scanner(System.in);
        System.out.println("nhap ten moi:");
        String newName = sc.nextLine();
        System.out.println("nhap so dien thoai moi :");
        String newPhone = sc.nextLine();
        System.out.println("nhap so dia chi moi:");
        String newAddress = sc.nextLine();
        Customer newCustomer = new Customer(newName, newPhone, newAddress);
        managerCustomer.SetNewCustomer(newCustomer.getId(),newCustomer);
        sc.nextLine();
    }
    private  void DisplayAll()  {
        List<Customer> customerList = managerCustomer.DisplayALl();
        for (Customer customer: customerList) {
            managerCustomer.AddCustomer(customer);
            System.out.println(customer);
        }
        managerCustomer.SaveFile();
    }

  }

