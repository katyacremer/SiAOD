import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class task1
{
    public static void main(String[] args)
    {

        String[] test1 = {"3","2","3","4"};
        perimeter(test1);

        int[] test2 = {7, 12, 59};
        maxNumber(test2);

        int[][] test3 = {{3,3,1,1},
                {2,2,1,2},
                {1,1,1,2}
        };
        diagonal(test3);
    }

    public static void perimeter(String[] args)
    {
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 0; i < args.length; i++)
        {
            arr.add(Integer.parseInt(args[i]));
        }
        Collections.sort(arr, Collections.reverseOrder());
        int max1 = arr.get(0), max2 = arr.get(1), perimeter = 0;
        arr.remove(0);
        arr.remove(0);
        boolean notFound = true;
        while(notFound)
        {
            if(max1 < max2 + arr.get(0)) //check if triangle possible
            {
                notFound = false;
                perimeter = max1 + max2 + arr.get(0);
            }
            else
            {
                max1 = max2;
                max2 = arr.get(0);
                arr.remove(0);
                if(arr.size() == 0)
                {
                    break;
                }
            }
        }
        System.out.println(perimeter);
    }

    public static void diagonal(int[][] matrix) {
        System.out.println(Arrays.deepToString(matrix));
        for(int i = 0, j = matrix[0].length-2; i < matrix.length-1 || j > 0;)
        {
            for(int k = j, l = i; k < matrix[0].length && l < matrix.length;)
            {
                int min = matrix[l][k], indexJ = k, indexI = l, changer;
                for(int k1 = k, l1 = l; k1 < matrix[0].length && l1 < matrix.length;)
                {
                    if(matrix[l1][k1] < min)
                    {
                        min = matrix[l1][k1];
                        indexJ = k1;
                        indexI = l1;
                    }
                    k1++;
                    l1++;
                }

                changer = matrix[l][k];
                matrix[l][k] = min;
                matrix[indexI][indexJ] = changer;

                k++;
                l++;
            }
            if(j - 1 >= 0)
            {
                j--;
            }
            else
            {
                if(i + 1 <= matrix.length-1)
                {
                    i++;
                }
            }
        }
        System.out.println(Arrays.deepToString(matrix));

    }
//        catch(Exception ex)
//        {
//            System.out.println("Arguments error, input form: java Tasks <count_of_lines> <count_of_columns> <min_limit> <max_limit>");
//            ex.printStackTrace();
//        }



    public static String maxNumber(int[] array)
    {
        String[] args = new String[array.length];
        for(int i = 0; i < args.length; i++)
        {
            args[i] = Integer.toString(array[i]);
        }
        for(int i = 1; i < args.length; i++)
        {
            String changer = "";
            for(int j = 0; j < i; j++)
            {
                String num1 = args[i] + args[j];
                String num2 = args[j] + args[i];
                if(num2.compareTo(num1) < 0)
                {
                    changer = args[i] + "";
                    args[i] = args[j] + "";
                    args[j] = changer + "";
                }
            }
        }
        String result = "";
        for(String s : args)
        {
            result += s;
        }
        System.out.println(result);
        return result;
    }

}