package algorithms.greedy.jimAndTheOrders;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/* https://www.hackerrank.com/challenges/jim-and-the-orders */
public class Solution {

    private static class Customer implements Comparable<Customer> {
        public int id;
        public int time;
        public int delay;

        public Customer(int id, int time, int delay) {
            this.id = id;
            this.time = time;
            this.delay = delay;
        }
        
        @Override
        public int compareTo(Customer o) {
            return ((this.time + this.delay) - (o.delay + o.time));
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Customer[] customer = new Customer[n];
        for (int index = 0; index < n; index++) {
            int t = scanner.nextInt();
            int d = scanner.nextInt();
            customer[index] = new Customer(index + 1, t, d);
        }
        Arrays.sort(customer);
        
        for (int index = 0; index < customer.length - 1; index++) {
            System.out.print(customer[index].id + " ");
        }
        System.out.println(customer[customer.length - 1].id);
    }
}
