/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemset;
import org.junit.*;
import org.junit.internal.runners.SuiteMethod;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;
import java.util.*;
@RunWith(Parameterized.class)
/**
 *
 * @author ongajong
 */
public class QuickSortParametrizedTest {
    public int inArr[];
    public int endArr[];

    public QuickSortParametrizedTest (int endArr[], int inArr[]) {
        this.inArr = inArr;
        this.endArr = endArr;
    }

    @Parameters 
    public static Collection<int[][]> parameters() {
        System.out.print("here");
        return Arrays.asList (new int [][][]{{{1,2,3},{2,3,1}},{{1,2,4},{2,3,1}}});
    }

    @Test public void additionTest()
    {
        QuickSort qs = new QuickSort();
        assertTrue(testhere(endArr,qs.sort(inArr)));
    }
    public boolean testhere(int[] Arr1, int[] Arr2) {
        boolean result = false;
        for (int i = 0; i<Arr1.length; i++){
            if (Arr1[i]==Arr2[i]){
                result = true;
            }
            else if (Arr1[i]!=Arr2[i]){
                return false;
            }
        }return result;
    }


}