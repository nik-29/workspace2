import java.io.File;
import java.util.*;

public class FirstClass {

    public static void mergeSort(int[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }
    public static void merge(
            int[] a, int[] l, int[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }


    public static void main(String[] args) throws  Exception {
        long time=0;
        File object = new File("number.txt");
        Scanner reader = new Scanner(object);
        List<Integer> numbers = new ArrayList<Integer>();
        boolean flag = false;
        while (reader.hasNextLine()) {
            while (reader.hasNextLine()) {
                String arr = reader.nextLine();
                String[] string = arr.split(",");
                for (String number : string) {
                    numbers.add(Integer.parseInt(number));

                    if (numbers.size() >= 1000) {
                        flag = true;
                        break;
                    }
                }
                if (flag) break;
            }
            int arr[] = numbers.stream()
                    .mapToInt(Integer::intValue)
                    .toArray();
            long lStartTime = new Date().getTime();
            mergeSort(arr, arr.length);
            long lEndTime = new Date().getTime();
            long difference = lEndTime - lStartTime;
            time+=difference;
            System.out.println(difference+" "+Arrays.toString(arr));
        }
        System.out.println(time);
    }
}
