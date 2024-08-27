package view;

import java.util.Scanner;

import controller.LoginController;
import controller.MemberRegisterController;

public class MenuView {
   
   private static Scanner sc = new Scanner(System.in);
   
   public static void menu() {
      while(true) {
         
         MenuView.printMenu();
         
         int menu = Integer.parseInt(sc.nextLine());
         switch(menu) {
         case 1 :
            MenuView.register(); // 회원가입
            break;
            
         case 2 :
            MenuView.login();// 로그인
            break;

         case 9 : 
            System.out.println("안녕히 가십시오 - DriveX");
            System.exit(0);
         
         default:
            System.out.println("1, 2, 9 중에서만 입력 바랍니다.");
         }
      }
   }

   public static void printMenu() {
      System.out.println("=== DriveX ===");
      System.out.println("1. 회원가입   |   2. 로그인   |  9. 종료");
      System.out.print("입력 : ");
   }
   
   public static void register() {
      System.out.println("회원가입화면입니다");
      
      System.out.print("회원아이디를 입력해주세요 : ");
      String memberId = sc.nextLine();      
      
      System.out.print("회원 비밀번호를 입력해주세요 : ");
      String password = sc.nextLine();
      
      System.out.print("회원 이름을 입력해주세요 : ");
      String name = sc.nextLine();      
      
      System.out.print("회원 나이를 입력해주세요 : ");
      int age = Integer.parseInt(sc.nextLine());
      
      System.out.print("회원 주소를 입력해주세요 : ");
      String address = sc.nextLine();      
      
      //컨트롤러의register함수호출
      MemberRegisterController.memberInsert(memberId,name,age,address,password);
   }
   
   public static void login() {
       System.out.print("아이디 : ");
       String memberId = sc.nextLine();
       
       System.out.print("비번 : ");
       String password = sc.nextLine();
       
       //컨트롤러의login함수호출(인수: userId, userPwd); 
       LoginController.login(memberId, password);
       
   }
   
}