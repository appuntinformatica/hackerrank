package algorithms.dynamicProgramming.abbreviation;

import java.util.*;

/* https://www.hackerrank.com/challenges/abbr */
public class Solution {

    public static void main(String[] args) {
        final int diff = 'A' - 'a';
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for (int index = 0; index < q; index++) {        
            String a = in.next();
            String b = in.next();
            int i = 0;
            int j = 0;
            boolean result = true;
            char lastB = 0;

            while ( j < b.length() && result == true ) {
                if (a.charAt(i) == b.charAt(j) || (char) (a.charAt(i) + diff) == b.charAt(j)) {
                    lastB = b.charAt(j);
                    i++;
                    j++;
                } else if (!(a.charAt(i) >= 'A' && a.charAt(i) <= 'Z') || (a.charAt(i) == lastB)) {
                    i++;
                } else {
                    result = false;
                }
                if (i == a.length() && j != b.length()) {
                    result = false;
                }
            }
            while ( result && i < a.length() ) {
                if (a.charAt(i) >= 'A' && a.charAt(i) <= 'Z') {
                    if (lastB != a.charAt(i)) {
                        result = false;
                    }
                }
                i++;
            }
            if (result) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    public static void main_(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();

        for (int index = 0; index < q; index++) {
            String a = in.next();
            String b = in.next();

            char[] originArrayA = a.toCharArray();
            char[] originArrayB = b.toCharArray();
            char[] arrayA = a.toLowerCase().toCharArray();
            char[] arrayB = b.toLowerCase().toCharArray();
            
            int beginIndexA = 0;
            int indexA = 0;
            int indexB = 0;
            int count = 0;
            int charsErased = 0;
            boolean exit = false;
      
            while ( !exit ) {                
                if ( arrayA[indexA] == arrayB[indexB] ) {
                    indexA++;
                    indexB++;
                    count++;
                } else {
                    indexA++;
                    if ( indexA < arrayA.length && originArrayA[indexA - 1] == arrayA[indexA - 1] )
                        charsErased++;
                }
                if ( count == arrayB.length ) {
                    exit = true;
                } else {
                    if ( indexA >= arrayA.length || indexB >= arrayB.length ) {
                        beginIndexA++;
                        indexA = beginIndexA;
                        indexB = 0;
                        count = 0;
                        charsErased = 0;
                    }
                    if ( beginIndexA >= arrayA.length ) {
                        exit = true;
                    }
                }
            }

            if ( count == arrayB.length && charsErased > 0 ) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}