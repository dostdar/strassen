import java.util.Scanner;

public class ArrayClass {

//sazande class
    public ArrayClass() {
    }
    // endof ArrayClass()

    // tebe ejraee barname
    public void start() {
        Scanner scanner= new Scanner(System.in);
        System.out.print("tdade khane ha ra vared konid ::)");
        int arrLength = scanner.nextInt();
        int[][] first_arr = new int[arrLength][arrLength];
        int[][] second_arr = new int[arrLength][arrLength];
        int[][] arr;

        get_array(scanner , first_arr, second_arr);
        arr = strassen(first_arr, second_arr);
        System.out.print("avalin arraye:");
        chap(first_arr);
        System.out.println("dovomin arraye:");
        chap(second_arr);
        System.out.println("hasele zarb:");
        chap(arr);
    }
    // endof start()


//    public ArrayClass(int[][] first_arr, int[][] second_arr) {
//        this.first_arr = first_arr;
//        this.second_arr = second_arr;
//        length = first_arr.length;
//    }

    // algorithm srassen dar in tebe ejra mishavad
    private int[][] strassen(int[][] first_arr, int[][] second_arr )
    {
        int n = first_arr.length;
        int [][] haselezarb = new int[n][n];


        //agar tebe tool matrice be 2 bakhsh pazir nabood va matrice tak khane nabood in halghe ejra mishvad
        // ta jaee ke emkan darad radif va sotoone 0 ezafe kond ta matrice sharayete lazem baraye taghsim shodan ra dashte bashd.
        if((n%2 != 0 ) && (n !=1))
        {
            int[][] a1, b1, c1;
            int n1 = n+1;
            a1 = new int[n1][n1];
            b1 = new int[n1][n1];
            for(int i=0; i<n; i++)
                for(int j=0; j<n; j++)
                {
                    a1[i][j] =first_arr[i][j];
                    b1[i][j] =second_arr[i][j];
                }

             //anghadar in dastor be sorate bazgashti ejra mishavad
            // ta tabe shart lazem baraye atghsim shodan ra dashte bashad
            c1 = strassen(a1, b1);

            //khane haee ke bad az n ezafe shodan ezafi va hane sefr hastan
            // dar natije faght be n satr va n sotoon aval niazmand hastim
            for(int i=0; i<n; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    haselezarb[i][j] = c1[i][j];
                }
            }
            return haselezarb;
        }

        //dar sorti ke tabe anghadar ejra shavad ta be yek khane beresad in if ejra mishavad
        //dar vaghe sharte payani barname hast
        if(n == 1)
        {
            haselezarb[0][0] = first_arr[0][0] * second_arr[0][0];
        }

        //bakhsh asli tabe ke har matrice rabe 4 bakh taghsim va kar haye strassen ra anjam midahad
        else
        {
            int [][] A11 = new int[n/2][n/2];
            int [][] A12 = new int[n/2][n/2];
            int [][] A21 = new int[n/2][n/2];
            int [][] A22 = new int[n/2][n/2];
            int [][] B11 = new int[n/2][n/2];
            int [][] B12 = new int[n/2][n/2];
            int [][] B21 = new int[n/2][n/2];
            int [][] B22 = new int[n/2][n/2];

            //har matrice be 4 bakhsh taghsim va dar 4 matrice digar save mishvad
            // anjam in kar ba tabe fasl ast
            fasl_arr(first_arr, A11, 0 , 0);
            fasl_arr(first_arr, A12, 0 , n/2);
            fasl_arr(first_arr, A21, n/2, 0);
            fasl_arr(first_arr, A22, n/2, n/2);

            fasl_arr(second_arr, B11, 0 , 0);
            fasl_arr(second_arr, B12, 0 , n/2);
            fasl_arr(second_arr, B21, n/2, 0);
            fasl_arr(second_arr, B22, n/2, n/2);

            // jomalat moredec niaze stassen ba asas neveshte haye ketabe clrs
            int[][] s1 = kam(B12,B22); // b12 + b22
            int[][] s2 = jam(A11,A12); // a11 + a12
            int[][] s3 = jam(A21,A22); // a21 + a22
            int[][] s4 = kam(B21,B11); // b21 + b11
            int[][] s5 = jam(A11, A22);// a11 + a22
            int[][] s6 = jam(B11, B22);// b11 + b22
            int[][] s7 = kam(A12,A22); // a12 + a22
            int[][] s8 = jam(B21,B22); // b21 + b22
            int[][] s9 = kam(A11,A21); // a11 + a21
            int[][] s10 = jam(B11,B12); // b11 + b12


            // jomalat moredec niaze stassen
            // dar vaghe barye anjame amale
            int [][] P1 = strassen(A11,s1); // a11 * s1
            int [][] P2 = strassen(s2,B22); // s2 * b22
            int [][] P3 = strassen(s3,B11); // s3 * b11
            int [][] P4 = strassen(A22,s4); // a22 * s4
            int [][] P5 = strassen(s5,s6);  // s5 * s6
            int [][] P6 = strassen(s7,s8);  // s7 * s8
            int [][] P7 = strassen(s9,s10); // s9 * s10

            int [][] C11 = jam(jam(P5,P6), kam(P4,P2)); //P5 + P6 + P4 -2
            int [][] C12 = jam(P1,P2);                 //P1 + P2
            int [][] C21 = jam(P3,P4);                 //P3 + P4
            int [][] C22 = kam(jam(P1,P5), jam(P3,P7)); // P1 + P5 - P3 - P7

            // baad az in ke amale strassen anjam shod 4 matrice da ha marhale be dast miayad ke be komake tabe mitavan an ha ra be ham oeyvand dad
            vasl_arr(C11, haselezarb, 0 , 0);
            vasl_arr(C12, haselezarb, 0 , n/2);
            vasl_arr(C21, haselezarb, n/2, 0);
            vasl_arr(C22, haselezarb, n/2, n/2);
        }
        return haselezarb;
    }

    //2 araye daryeft karde va araye haey nazir be nazir ba ham jam mishavand
    private  int [][] jam(int[][] A, int[][] B)
    {
        int n = A.length;

        int [][] result = new int[n][n];

        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                result[i][j] = A[i][j] + B[i][j];

        return result;
    }

    //2 araye daryeft karde va araye haey nazir be nazir az ham kam mishavand
    private  int [][] kam(int[][] A, int[][] B)
    {
        int n = A.length;

        int [][] result = new int[n][n];

        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                result[i][j] = A[i][j] - B[i][j];

        return result;
    }


    //2 araye daryaft karde va ba tavajo be size small va noghate shroroe arraye big ra dar small copy mikonad
    private static void fasl_arr(int[][] big, int[][] small, int shoroe_ofoghi, int shoroe_amodi)
    {
        for(int i1 = 0, i2=shoroe_ofoghi; i1<small.length; i1++, i2++)
            for(int j1 = 0, j2=shoroe_amodi; j1<small.length; j1++, j2++)
            {
                small[i1][j1] = big[i2][j2];
            }
    }

    //tabe vasl va fasl ==

    //2 araye daryaft karde va ba tavajo be size small va noghate shroroe arraye small ra dar big copy mikonad
    private static void vasl_arr(int[][] small, int[][] big, int shoroe_ofoghi, int shoroe_amodi)
    {
        for(int i1 = 0, i2=shoroe_ofoghi; i1<small.length; i1++, i2++)
            for(int j1 = 0, j2=shoroe_amodi; j1<small.length; j1++, j2++)
            {
                big[i2][j2] = small[i1][j1];
            }
    }


    // tabe baraye chap yek arraye delkhah
    public  void chap(int[][] array)
    {
        int n = array.length;

        System.out.println();
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }


    //do araye daryaft va ba komak karbar an ra meghdar dehi mikond
    private static void get_array(Scanner scanner, int[][] arr1, int[][] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            for (int ii = 0; ii < arr1.length; ii++) {
                System.out.print("a[" + (i + 1) + "][" + (ii + 1) + "]=");
                arr1[i][ii] = scanner.nextInt();
            }
        }
        for (int i = 0; i < arr2.length; i++) {
            for (int ii = 0; ii < arr2.length; ii++) {
                System.out.print("b[" + (i + 1) + "][" + (ii + 1) + "]=");
                arr2[i][ii] = scanner.nextInt();
            }
        }


    }



}
