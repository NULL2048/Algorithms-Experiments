import java.util.Scanner;

public class Division {
    public static int[] array = new int[50];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            int temp = scan.nextInt();
            array[i] = temp;
        }
        System.out.println("请输入要查找的数：");
        int target = scan.nextInt();
        System.out.println("搜索的数的下标为：");
        System.out.println(binarySearchRecursive(0, n, target));
    }

    public static int binarySearchRecursive( int low, int high, int target)  {
        if ( low > high ) {
            return -1;
        }

        int mid = ( low + high )/2;

        if (array[mid] == target ) {
            return mid;
        }
        else if ( array[mid] < target ) {
            return binarySearchRecursive(mid+1, high, target);
        }
        else {
            return binarySearchRecursive(low, mid-1, target);
        }
    }

}


