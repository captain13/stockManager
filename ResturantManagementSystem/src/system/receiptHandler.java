/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

/**
 *
 * @author Andrew
 */
public class receiptHandler {

    String head;
    String body;
    String tail;

    public void header() {
        head = "********************************** \n"
                + "COMPANY NAME                       \n"
                + "DATE                               \n"
                + "********************************** ";

    }

    public void body() {
        body = "                                     \n"
                + "item1         Qty            Price \n"
                + "item2         Qty            Price \n"
                + "item3         Qty            Price \n"
                + "item4         Qty            Price \n"
                + "item5         Qty            Price \n"
                + "item6         Qty            Price \n"
                + "                                    ";
    }

    public void tail() {
        tail = "********************************** \n"
                + "THANK YOU PLEASE COME AGAIN        \n"
                + "WAITER                             \n"
                + "********************************** ";
    }

    public void printReceipt() {
        header();
        body();
        tail();
        System.out.println(head);
        System.out.println(body);
        System.out.println(tail);
    }

    public static void main(String[] args) {
        receiptHandler newHandler = new receiptHandler();
        newHandler.printReceipt();
    }

}
