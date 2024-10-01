// Author: Eray BOZOGLU
// NetID: ebozoglu
// CSC172 LAB_1

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Function;

public class LabOneFn {
    public static void main(String[] args) {
        Function<Character[], Character> fnMaxChar = (Character[] arr)-> {return Collections.max(Arrays.asList(arr));};

        Character[] charArray = {'H', 'E', 'L', 'L', 'O'};

        System.out.println("max Character is: " + findMax(charArray, fnMaxChar));
    }

    public static Character findMax(Character[] arr, Function<Character[], Character> fnMaxChar) {
        return fnMaxChar.apply(arr);
    }
}
