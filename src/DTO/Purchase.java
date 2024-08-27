package DTO;

import java.util.ArrayList;
import java.util.List;

public class Purchase {

   private int purchaseNo;
   private int memberNo;
   private int sunRoof;
   private int seat;
   private int aroundView;
   private String color;
   private String purchaseDate;
   private int price;
   private int dealerNo;
   private String carNo;

   public Purchase(int purchaseNo, int memberNo, int sunRoof, int seat, int aroundView, String color,
         String purchaseDate, int price, int dealerNo, String carNo) {
      this.purchaseNo = purchaseNo;
      this.memberNo = memberNo;
      this.sunRoof = sunRoof;
      this.seat = seat;
      this.aroundView = aroundView;
      this.color = color;
      this.purchaseDate = purchaseDate;
      this.price = price;
      this.dealerNo = dealerNo;
      this.carNo = carNo;
   }

   public int getPurchaseNo() {
      return purchaseNo;
   }

   public void setPurchaseNo(int purchaseNo) {
      this.purchaseNo = purchaseNo;
   }

   public int getMemberNo() {
      return memberNo;
   }

   public void setMemberNo(int memberNo) {
      this.memberNo = memberNo;
   }

   public int getSunRoof() {
      return sunRoof;
   }

   public void setSunRoof(int sunRoof) {
      this.sunRoof = sunRoof;
   }

   public int getSeat() {
      return seat;
   }

   public void setSeat(int seat) {
      this.seat = seat;
   }

   public int getAroundView() {
      return aroundView;
   }

   public void setAroundView(int aroundView) {
      this.aroundView = aroundView;
   }

   public String getColor() {
      return color;
   }

   public void setColor(String color) {
      this.color = color;
   }

   public String getPurchaseDate() {
      return purchaseDate;
   }

   public void setPurchaseDate(String purchaseDate) {
      this.purchaseDate = purchaseDate;
   }

   public int getPrice() {
      return price;
   }

   public void setPrice(int price) {
      this.price = price;
   }

   public int getDealerNo() {
      return dealerNo;
   }

   public void setDealerNo(int dealerNo) {
      this.dealerNo = dealerNo;
   }

   public String getCarNo() {
      return carNo;
   }

   public void setCarNo(String carNo) {
      this.carNo = carNo;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("[구매번호 = ");
      builder.append(purchaseNo);
      builder.append(", 회원번호 = ");
      builder.append(memberNo);
      builder.append(", 선루프 = ");
      builder.append(sunRoof);
      builder.append(", 통풍시트 = ");
      builder.append(seat);
      builder.append(", 어라운드= ");
      builder.append(aroundView);
      builder.append(", 차량색상= ");
      builder.append(color);
      builder.append(", 구매일 = ");
      builder.append(purchaseDate);
      builder.append(", 구매금액 = ");
      builder.append(price);
      builder.append(", 딜러번호 = ");
      builder.append(dealerNo);
      builder.append(", 차량모델번호 = ");
      builder.append(carNo);
      builder.append("]");
      return builder.toString();
   }

}
