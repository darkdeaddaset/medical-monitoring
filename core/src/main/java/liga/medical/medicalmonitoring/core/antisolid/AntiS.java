package liga.medical.medicalmonitoring.core.antisolid;

//Суть в том что необходимо разделить в данном случае сортировку и вывод на консоль
public class AntiS {
    private int num;

    public int[] selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] < arr[min]) {
                    min = j;
                }
            }
            int temp = arr[min];
            arr[i] = arr[min];
            arr[min] = temp;
        }
        return arr;
    }

    public void printArray(int[] arr) {
        for (int a : arr) {
            System.out.println(a);
        }
    }
}
