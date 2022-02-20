package app.entities;

import java.util.ArrayList;
import java.util.Arrays;

public class Array {
    int[] myArray = new int[5];
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;






    public Array() {

    }

    public Array(String[] numbers) {

        new ArrayList<>(Arrays.asList(numbers));
        //метод реализующий сортировку методом пузырька
        sortArray();
    }

    //метод реализующий сортировку методом пузырька
    private void sortArray(){
        boolean isSorted = false;
        int buf;
        while(!isSorted) {
            isSorted = true;
            for (int i = 0; i < myArray.length-1; i++) {
                if(myArray[i] > myArray[i+1]){
                    isSorted = false;
                    buf = myArray[i];
                    myArray[i] = myArray[i+1];
                    myArray[i+1] = buf;
                }
            }
        }
    }

    public int[] getArray(){
        return myArray;
    }

    public String toString(){
        return "Array{" + "myArray='" + Arrays.toString(myArray) + '\'' + '}';
    }

    //клонирование объекта array, сравнение и проверка с создаваемым классом
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass()!= o.getClass()) return false;

        Array array = (Array) o;

        if(!Arrays.equals(myArray, array.myArray)) return false;
        return Arrays.equals(myArray, array.myArray);
    }

    @Override
    public int hashCode(){
        int result = myArray != null ? Arrays.hashCode(myArray) : 0;
        result = 31 * result + ((myArray != null) ? Arrays.hashCode(myArray) : 0);
        return result;
    }
}
