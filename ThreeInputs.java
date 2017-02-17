/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threeinputs;

import java.util.Scanner;

/**
 *
 * @author zanesmith
 */
public class ThreeInputs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Initiates the scanner
        Scanner sc = new Scanner(System.in);
        
        //prints a line to prompt the user to add their first number
        System.out.println("Please enter first number");
        //sets the user input to a variable
        int one = sc.nextInt();
        
        //prints a line to prompt the user to add their second number
        System.out.println("Please enter second number");
        //sets the user input to a variable
        int two = sc.nextInt();
        
        //prints a line to prompt the user to add their third number
        System.out.println("Please enter third number");
        //sets the user input to a variable
        int three = sc.nextInt();
        
        //works out the sum of the three inputs and sets to variable
        int sum = one + two + three;
        System.out.println("the sum of the three number is equal to :" + sum);
        
        //works out the product of the three inputs and sets to variable
        int prod = one * two * three;
        System.out.println("the product of the three number is equal to :" + prod);
        
        //works out the average of the three inputs and sets to variable
        int ave = sum/3 ;
        System.out.println("the average of the three number is equal to :" + ave);
        
        /*if statements compares the two variables to determine the larger 
            number and prints out the appropriate message*/
        if( one > two && one > three){
            System.out.println(one + " is larger");
        }else if(two > one && two > three){
            System.out.println(two + " is larger");
        }else if (three > one && three > two){
            System.out.println(three + " is larger");
        }else{
            System.out.println("These numbers are equal");
        }
        
         /*if statements compares the two variables to determine the smaller 
            number and prints out the appropriate message*/
        if( one < two && one < three){
            System.out.println(one + " is smaller");
        }else if(two < one && two < three){
            System.out.println(two + " is smaller");
        }else if (three < one && three < two){
            System.out.println(three + " is smaller");
        }
    }
    
}
