import java.io. *;
import java.util.*;
import java.lang. *;

public class task27feb{
    public static void main(String[] args){
        System.out.println("Parlament of Three-Nine Kingdom");
        System.out.println(parl());
        System.out.println("Polygon");
        System.out.println(polygon());
        System.out.println("IT competition");
        System.out.println(capcha());
    }

    static boolean parl(){
        int K;
        String line = "";
        MyStack<Character> stack = new MyStack<Character>();

        Scanner in = new Scanner(System.in);
        System.out.print("Input number of operations (from 1 to 1000): ");
        K = in.nextInt();
        in.nextLine();
        System.out.println("Input operations(Add x or Vote x):");

        for(int i = 0; i < K; i++){
            line = in.nextLine();
            if(line.substring(0, line.indexOf(" ")).equals("Add"))
                stack.push((char) line.charAt(line.length() - 1));
            else if(line.substring(0, line.indexOf(" ")).equals("Vote")){
                if(stack.isEmpty())
                    return false;
                else{
                    if(stack.pop() != (char) (line.charAt(line.length() - 1))){
                        return false;
                    }
                }
            }
        }
        if(!stack.isEmpty())
            return false;
        return true;
    }

    static int polygon(){
        Scanner in = new Scanner(System.in);
        System.out.println("Input N: ");
        int num = in.nextInt();
        for(int i = 3; i < Math.sqrt(num) + 1; i++){
            if(num % i == 0)
                return i;
        }
        if(num > 4 && num % 2 == 0){
            return num / 2;
        }
        return num;
    }

    static ArrayList<Integer> capcha(){
        Scanner in = new Scanner(System.in);
        System.out.println("Input number: ");
        int num = in.nextInt();
        int n = num;
        int sum = 0;
        String s = "" + num;
        List<Integer> line = new ArrayList<Integer>();
        while(num != 0){
            sum += (num % 10);
            num /= 10;
        }
        if(s.length() == 1){
            if(n == 4 || n == 6 || n == 8){
                line.add(10);
                line.add(2);
                return (ArrayList<Integer>) line;
            }
            else if(n == 9){
                line.add(10);
                line.add(3);
                return (ArrayList<Integer>) line;
            }
            else{
                line.add(-1);
                return (ArrayList<Integer>) line;
            }
        }
        else{
            if(sum == 1){
                line.add(10);
                line.add(2);
                return (ArrayList<Integer>) line;
            }
        }
        line.add(sum + 1);
        line.add(sum);
        return (ArrayList<Integer>) line;
    }
}