
public class Best {
    static int inf =1000000000;
    static int startIndex;
    static int endIndex;
    static int sum;

    public static void cycle_best(int[] a){
        int sum2=0;
        int[] b = new int[a.length];
        for (int i = 0; i <a.length ; i++) {
            b[i] = -a[i];
            sum2+=a[i];
        }
        int sum1 = calculate(b);
        int maxim = Math.max(sum2-(-sum1), calculate(a));
        System.out.println("Cycle Best sum is: "+maxim);

    }

    public static void Calculate(int[] a) {
        int max = a[0],
                temp_max = 0,start = 0,
                end = 0, s = 0;

        for (int i = 0; i < a.length; i++) {
            temp_max += a[i];
            if (max < temp_max) {
                max = temp_max;
                start = s;
                end = i;
            }
            if (temp_max < 0) {
                temp_max = 0;
                s = i + 1;
            }
        }
        sum =  max;
        startIndex =  start;
        endIndex = end;

    }

    public static int calculate(int[] a) {
        int max = a[0],
                temp_max = 0,start = 0,
                end = 0, s = 0;

        for (int i = 0; i < a.length; i++) {
            temp_max += a[i];
            if (max < temp_max) {
                max = temp_max;
                start = s;
                end = i;
            }
            if (temp_max < 0) {
                temp_max = 0;
                s = i + 1;
            }
        }
        sum =  max;
        startIndex =  start;
        endIndex = end;

        return sum;

    }
    public static void fw(int[][] mat){
        int size = mat.length;
        int[][] re = new int[size][size];
        for (int k = 0; k < size ; k++) {
            for (int i = 0; i < size ; i++) {
                for (int j = 0; j < size ; j++) {
                    if(mat[i][k]!= inf && mat[k][j]!= inf){
                        if(mat[i][k] + mat[k][j] < mat[i][j]){
                            mat[i][j] = mat[i][k] + mat[k][j];
                        }
                    }
                }
            }
        }

        for (int i = 0; i < mat.length ; i++) {
            for (int j = 0; j < mat.length; j++) {
                System.out.print(mat[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 6, -5, -2, 11, 2, -14};
        int[] arr2 = {1, 5, 6, -11, -2, 7, 5, 4, 2};

        Calculate(arr);
        System.out.println("And the sum is: "+sum+"! From index "+startIndex+" to: "+endIndex);
        Calculate(arr2);
        System.out.println("And the sum is: "+sum+"! From index "+startIndex+" to: "+endIndex);
        cycle_best(arr2);

//
//        int[][] mat = {
//                {  0   , inf ,   2 ,    5   },
//                {inf   ,   0 ,   1 ,    1   },
//                {  2   ,   1 ,   0 ,  inf   },
//                {  5   ,   1 , inf ,    0   }  };
//
//        fw(mat);
    }
}