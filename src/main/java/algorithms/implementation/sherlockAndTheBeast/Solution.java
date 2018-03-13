package algorithms.implementation.sherlockAndTheBeast;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    private static String makeDecentString(int num5s, int num3s) {
        char[] chars = new char[num5s + num3s];
        Arrays.fill(chars, 0, num5s, '5');
        Arrays.fill(chars, num5s, chars.length, '3');
        return new String(chars);
    }
    
    private static String makeDecentNumber(int n) {
        if (n < 3 || n == 4 || n == 7) {
            return "-1";
        } else if (n == 5) {
            return "33333";
        }
        int num5s = n / 3;
        int num3s = 0;
        switch (n % 3) {
            case 0:
                break;
            case 1:
                num5s -= 3;
                num3s += 2;
                break;
            case 2:
                num5s--;
                num3s++;
                break;
        }
        return makeDecentString(num5s * 3, num3s * 5);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numTests = in.nextInt();
        for (int i = 0; i < numTests; i++) {
            System.out.println(makeDecentNumber(in.nextInt()));
        }
    }
    
    public static void main___(String[] args) {    
        Scanner scanner = new Scanner(System.in);
        int numbersOfTestCase = scanner.nextInt();
        for (int i = 0; i < numbersOfTestCase; i++) {
            int y = scanner.nextInt();
            int z = y;
            while ( z > 0 && z % 3 != 0 ) {
                z -= 5;
                if (z < 0)
                    System.out.print("-1");
                else {
                    for (int j = 0; j < z; j++) {
                        System.out.print("5");
                    }
                    for (int j = 0; j < (y - z); j++) {
                        System.out.print("3");
                    }
                }
            }
            System.out.println("");
        }
    }
            
    public static void print(int count5, int count3) {
        if (count5 != 0 || count3 != 0) {
            String s = "";
            for (int j = 0; j < count5; j++) {
                s += "555";
            }
            for (int j = 0; j < count3; j++) {
                s += "33333";
            }
            System.out.println(s);
        } else {
            System.out.println("-1");
        }        
    }
    
    public static boolean isDivisibleFor3(int n) {
        String s = Integer.toString(n);
        int sum = 0;
        for (int index = 0; index < s.length(); index++) {
            String c = Character.toString(s.charAt(index));
            sum += Integer.parseInt(c);
        }
        return (sum % 3) == 0;
    }
    public static boolean isDivisibleFor5(int n) {
        String s = Integer.toString(n);
        String last = Character.toString(s.charAt(s.length() - 1));
        return (last.equals("5") || last.equals("0"));
    }

    public static void main__(String[] args) {    
        Scanner scanner = new Scanner(System.in);
        int numbersOfTestCase = scanner.nextInt();
        for (int i = 0; i < numbersOfTestCase; i++) {
            int n = scanner.nextInt();
            int count5 = 0, count3 = 0;
            
            if (n % 3 == 0) {
                count5 = n / 3; 
                print(count5, count3);
            } else if (n % 5 == 0) {
                count3 = n / 5; 
                print(count5, count3);
            } else {
                boolean founded = false;
                int index = 0;
                while (index <= n && !founded) {
                    index += 3;
                    if (index <= n) {
                        if (isDivisibleFor5(n - index)) {
                            count5 = index / 3;
                            count3 = (n - index) / 5;
                            print(count5, count3);
                            founded = true;
                        }
                    }
                }
                index = 0;
                while (index <= n && !founded) {
                    index += 5;
                    if (index <= n) {
                        if (isDivisibleFor3(n - index)) {
                            count3 = index / 5;
                            count5 = (n - index) / 3;
                            print(count5, count3);
                            founded = true;
                        }
                    }
                }
                
                if (!founded)
                    print(count5, count3);
            }
        }
    }
    
    
    public static void main_1(String[] args) {    
        Scanner scanner = new Scanner(System.in);
        int numbersOfTestCase = scanner.nextInt();
        for (int i = 0; i < numbersOfTestCase; i++) {
            List<Results> results = new ArrayList<>();
            int n = scanner.nextInt();
            int resultIndexMax = -1;
            int resultCountMax = -1;
            int count5 = 0, count3 = 0;
            for (int j = 1; j <= n; j++) {
                if (j > 0 && (n - j) > 0) {
                    int m = mcm(j, n - j);
                    if (m >= 15 && m % 15 == 0) {
                        if (j % 3 == 0 && (n - j) % 5 == 0) {
                            count5 += j / 3;
                            count3 += (n - j) / 5;
                            results.add(new Results(count5, count3));
                            if (count5 > resultCountMax) {
                                resultCountMax = count5;
                                resultIndexMax = results.size() - 1;
                            }
                            count3 = 0;
                            count5 = 0;
                        } else if (j % 5 == 0 && (n - j) % 3 == 0) {
                            count3 += j / 5;
                            count5 += (n - j) / 3;
                            results.add(new Results(count5, count3));
                            if (count5 > resultCountMax) {
                                resultCountMax = count5;
                                resultIndexMax = results.size() - 1;
                            }
                            count3 = 0;
                            count5 = 0;
                        }
                    }     
                } else if (j % 3 == 0) {
                    count5 += j / 3; 
                    results.add(new Results(count5, count3));
                    if (count5 > resultCountMax) {
                        resultCountMax = count5;
                        resultIndexMax = results.size() - 1;
                    }
                    count3 = 0;
                    count5 = 0;
                } else if (j % 5 == 0) {
                    count3 += j / 5;
                    results.add(new Results(count5, count3));
                    if (count5 > resultCountMax) {
                        resultCountMax = count5;
                        resultIndexMax = results.size() - 1;
                    }
                    count3 = 0;
                    count5 = 0;
                } else if ( (n - j) % 3 == 0) {
                    count5 += (n - j) / 3; 
                    results.add(new Results(count5, count3));
                    if (count5 > resultCountMax) {
                        resultCountMax = count5;
                        resultIndexMax = results.size() - 1;
                    }
                    count3 = 0;
                    count5 = 0;
                } else if ( (n - j) % 5 == 0) {
                    count3 += (n - j) / 5;
                    results.add(new Results(count5, count3));
                    if (count5 > resultCountMax) {
                        resultCountMax = count5;
                        resultIndexMax = results.size() - 1;
                    }
                    count3 = 0;
                    count5 = 0;
                }
            }
            System.out.println(results.get(resultIndexMax));
        }
    }

    public static final int mcm(int x1, int x2) {
        if (x1 <= 0 || x2 <= 0) {
            throw new IllegalArgumentException("Cannot compute the least common multiple of two numbers if one, at least, is negative.");
        }
        int max, min;
        if (x1 > x2) {
            max = x1;
            min = x2;
        } else {
            max = x2;
            min = x1;
        }
        for (int i = 1; i <= min; i++) {
            if ( (max * i) % min == 0 ) {
                return i*max;
            }
        }
        throw new Error("Cannot find the least common multiple of numbers " + x1 + " and " + x2);
    }

    public static class Results {
        private int count5;
        private int count3;

        public Results(int count5, int count3) {
            this.count5 = count5;
            this.count3 = count3;
        }

        @Override
        public String toString() {
            if (count5 != 0 || count3 != 0) {
                String s = "";
                for (int j = 0; j < count5; j++) {
                    s += "555";
                }
                for (int j = 0; j < count3; j++) {
                    s += "33333";
                }
                return s;
            } else {
                return  "-1";
            }
        }
    }
}
