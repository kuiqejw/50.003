/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemset;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;
import java.util.*;

@RunWith(Parameterized.class)

public class QuickSortTest {
    public int inArr[];
    public int endArr[];

    public QuickSortTest (int endArr[], int inArr[]) {
        this.inArr = inArr;
        this.endArr = endArr;
    }

    @Parameters public static Collection<Object[]> parameters() {
        System.out.print("here");
        return Arrays.asList (new Object [][]{{new int[] {1,1,2},new int[]{2,1,1}},{new int[] {4,5,6}, new int[] {4,6,5}},});
    }

    @Test public void additionTest()    {
        QuickSort qs = new QuickSort();
        qs.sort(inArr);
        for (int i = 0; i< endArr.length;i++){
            assertEquals(endArr[i],inArr[i]);
        }
    }
    public boolean testhere(int[] Arr1, int[] Arr2) {
        boolean result = false;
        for (int i = 0; i<Arr1.length; i++){
            if (Arr1[i]==Arr2[i]){
                result = true;
            }
            if (Arr1[i]!=Arr2[i]){
                return false;
            }
        }
        return result;
    }


}