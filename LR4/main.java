import java.io. *;
import java.util.*;
import java.lang. *;

public class main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Choose task(1 - 11 or -1 to exit): ");
        int num = 0;
        while(num != -1){
            System.out.print("\nTask number ");
            num = in.nextInt();
            System.out.println();
            switch(num){
                case 1:
                    task1();
                    break;
                case 2:
                    task2();
                    break;
                case 3:
                    task3();
                    break;
                case 4:
                    task4();
                    break;
                case 5:
                    task5();
                    break;
                case 6:
                    task6();
                    break;
                case 7:
                    task7();
                    break;
                case 8:
                    task8();
                    break;
                case 9:
                    task9();
                    break;
                case 10:
                    task10();
                    break;
                case 11:
                    task11();
                default:
                    break;
            }
        }

    }

    static void task1(){
        Deque<String> deque1 = new Deque<String>();
        Deque<String> deque2 = new Deque<String>();

        try{
            FileReader fr = new FileReader("task1.txt");
            BufferedReader reader = new BufferedReader(fr);
            String line;


            while((line = reader.readLine()) != null){

                if(deque1.isEmpty())
                    deque1.addFirst(line);
                else{
                    help1(line, deque1, deque2);
                    while(!deque2.isEmpty()){
                        deque1.addFirst(deque2.removeFirst());
                    }

                }
            }

            while(!deque1.isEmpty()){
                System.out.println(deque1.removeFirst());
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

    static void help1(String trueLine, Deque<String> deque1, Deque<String> deque2){

        if(deque1.isEmpty()){
            deque1.addFirst(trueLine);
            return;
        }else{
            String first = deque1.getFirst();
            String line = trueLine.toLowerCase();
            first = first.toLowerCase();
            line = line.replace(" ","");
            first = first.replace(" ","");

            for(int i = 0; i < line.length() && i < first.length(); i++){
                if((int) line.charAt(i) < (int) first.charAt(i)){
                    deque1.addFirst(trueLine);
                    break;
                }
                else if((int) line.charAt(i) > (int) first.charAt(i)){
                    deque2.addFirst(deque1.removeFirst());
                    if(!deque1.isEmpty()){
                        first = deque1.getFirst().toLowerCase();
                    }
                    help1(trueLine, deque1, deque2);
                    break;
                }
            }
        }
    }

    static void task2(){
        Scanner in = new Scanner(System.in);

        Deque<Character> deque = new Deque<Character>();
        int iter = 0;
        for(int i = 126; i > 31; i--){
            deque.addFirst((char) i);
            ++iter;
        }
        //получаем на вход строку, которая будет расшифровкой
        System.out.println("Input line that must be decrypted: ");
        String line = in.nextLine();
        String sentence = "";
        //шифруем предложение и записываем в файл
        for(int i = 0; i < line.length(); i++){
            while(deque.getFirst() != line.charAt(i)){
                deque.addLast(deque.removeFirst());
            }
            for(int j = 0; j < 2; j++){
                deque.addFirst(deque.removeLast());
            }
            sentence += deque.getFirst();
        }

        try(FileWriter writer = new FileWriter("task2_1.txt", false)){
            writer.write(sentence);

            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        //читаем созданный нами файл
        String line1 = "";
        try{
            FileReader fr = new FileReader("task2_1.txt");
            BufferedReader reader = new BufferedReader(fr);
            line1 = reader.readLine();

        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        //расшифровываем сообщение и записываем в новый файл
        sentence = "";
        for(int i = 0; i < line1.length(); i++){
            while(deque.getFirst() != line1.charAt(i)){
                deque.addLast(deque.removeFirst());
            }
            for(int j = 0; j < 2; j++){
                deque.addLast(deque.removeFirst());
            }
            sentence += deque.getFirst();
        }
        sentence = sentence.substring(0, sentence.length());
        sentence = sentence.replace("\"","");
        try(FileWriter writer = new FileWriter("task2_2.txt", false)){
            writer.write(sentence);

            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

    }

    static void task3(){
        Integer k = 0;
        Stack<Integer> A = new Stack<>();
        Stack<Integer> B = new Stack<>();
        Stack<Integer> C = new Stack<>();

        File f = new File("task3.txt");
        Scanner in = new Scanner(System.in);
        try{
            in = new Scanner(f);
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return;
        }
        while(in.hasNextInt()){
            A.push(in.nextInt());
            ++k;
        }
        hanoi_tower(A, C, B, k);
        System.out.print("A: ");
        while(!A.isEmpty())
            System.out.print(A.pop() + " ");

        System.out.print("\nB: ");
        while(!B.isEmpty())
            System.out.print(B.pop() + " ");
        System.out.print("\nC: ");
        while(!C.isEmpty())
            System.out.print(C.pop() + " ");
        System.out.println();
    }
    static void hanoi_tower(Stack<Integer> A, Stack<Integer> B, Stack<Integer> C, int n){
        if(n <= 0){
            return;
        }
        hanoi_tower(A, C, B, n - 1);
        B.push(A.pop());
        hanoi_tower(C, B, A, n - 1);
    }


    static void task4(){
        try(FileReader reader = new FileReader("task4.txt")){
            int c;
            Stack<Integer> stack = new Stack<>();
            while((c = reader.read()) > 0){
                if(c == '('){
                    stack.push(c);
                }
                else if(c == ')'){
                    if(stack.isEmpty()){
                        System.out.println("Fail!");
                        return;
                    }
                    else{
                        stack.pop();
                    }
                }
            }
            if(stack.isEmpty()){
                System.out.println("Success!");
                return;
            }
            else{
                System.out.println("Fail!");
                return;
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
    static void task5(){
        try(FileReader reader = new FileReader("task5.txt")){
            int c;
            Deque<Integer> deque = new Deque<Integer>();
            while((c = reader.read()) > 0){
                if(c == '['){
                    deque.addFirst(c);
                }
                else if(c == ']'){
                    if(deque.isEmpty()){
                        System.out.println("Fail!");
                        return;
                    }
                    else{
                        deque.removeFirst();
                    }
                }
            }
            if(deque.isEmpty()){
                System.out.println("Success!");
                return;
            }
            else{
                System.out.println("Fail!");
                return;
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
    static void task6(){
        int c;
        Deque<Character> deque = new Deque<Character>();

        try(FileReader reader = new FileReader("task6.txt")){
            while((c = reader.read()) > 0){
                if(c >= 48 && c <= 57){
                    deque.addFirst((char) c);
                }
                else if(c >= 65 && c <= 90 || c >= 97 && c <= 122){
                    if(deque.isEmpty())
                        deque.addFirst((char) c);
                    int iter = 0;
                    if(!((int) deque.getFirst() >= 48 && (int) deque.getFirst() <= 57)){
                        deque.addFirst((char) c);
                    }
                    else{

                        while(!(deque.getLast() >= 48 && deque.getLast() <= 57) && !(deque.getLast() >= 65 && deque.getLast() <= 90 || deque.getLast() >= 97 && deque.getLast() <= 122)){
                            deque.addFirst(deque.removeLast());
                            ++iter;
                        }
                        deque.addLast((char) c);
                        for(int i = 0; i < iter; i++){
                            deque.addLast(deque.removeFirst());
                        }
                    }
                }
                else{
                    deque.addLast((char) c);
                }

            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

        while(!(deque.isEmpty())){
            System.out.print(deque.removeFirst() + " ");
        }
        System.out.println();
    }

    static void task7(){
        int c;
        String line = "";
        Deque<Integer> deque = new Deque<Integer>();

        try(FileReader reader = new FileReader("task7.txt")){
            while((c = reader.read()) > 0){
                if(c != ' '){
                    line += (char) c;
                }
                else{
                    if(Integer.parseInt(line) >= 0){
                        deque.addLast(Integer.parseInt(line));
                    }
                    else{
                        if(deque.isEmpty() || deque.getFirst() >= 0)
                            deque.addFirst(Integer.parseInt(line));
                        else{
                            int iter = 0;
                            if(deque.getLast() >= 0){
                                while(deque.getFirst() < 0){
                                    deque.addLast(deque.removeFirst());
                                    ++iter;
                                }
                                deque.addFirst(Integer.parseInt(line));
                                for(int i = 0; i < iter; i++){
                                    deque.addFirst(deque.removeLast());
                                }
                            }
                            else{
                                for(int i = 0; i < deque.size(); i++){
                                    deque.addLast(deque.removeFirst());
                                }
                                deque.addFirst(Integer.parseInt(line));
                                for(int i = 0; i < deque.size() - 1; i++){
                                    deque.addFirst(deque.removeLast());
                                }
                            }
                        }
                    }
                    line = "";
                    continue;

                }
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }


        System.out.println("Result: ");

        while(!deque.isEmpty()){
            System.out.print(deque.removeFirst() + " ");
        }
    }
    static void task8(){
        Stack<String> stack = new Stack<>();

        try{
            FileReader fr = new FileReader("task8.txt");
            BufferedReader reader = new BufferedReader(fr);
            String line;

            while((line = reader.readLine()) != null){
                stack.push(line);
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

        try(FileWriter writer = new FileWriter("task8_1.txt", false))
        {
            // запись всей строки
            while(!stack.isEmpty()){
                writer.write(stack.pop() + "\n");
            }
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

    static void task9(){
        Stack<Integer> stack = new Stack<Integer>();
        String line = "";
        try(FileReader reader = new FileReader("task9.txt")){
            int c;
            while((c = reader.read()) > 0){
                if(c == ' ')
                    continue;
                if(c == 'T' || c == 'F'){
                    line += (char) c;
                }
                else if(c == '('){
                    stack.push(c);
                }
                else if(c == ')'){
                    int r;
                    while((!stack.isEmpty()) && (r = stack.pop()) != '('){
                        line += (char) r;
                    }
                }
                else if(c == 'N'){
                    stack.push(c);
                }
                else if(c == 'A' || c == 'X' || c == 'O'){
                    int r = 0;
                    while(!(stack.isEmpty()) && priority(r = stack.pop()) >= priority(c)){
                        line += (char) r;
                    }
                    if(!stack.isEmpty())
                        stack.push(r);
                    stack.push(c);
                }
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        while(!stack.isEmpty()){
            int a = stack.pop();
            line += (char) a;
        }

        System.out.println(line);

        for(int i = 0; i < line.length(); i++){
            if(line.charAt(i) == 'T' || line.charAt(i) == 'F'){
                stack.push((int) line.charAt(i));
            }
            else if(line.charAt(i) == 'N'){
                int a = stack.pop();
                if(a == 'F')
                    a = (int) 'T';
                else if(a == 'T')
                    a = (int) 'F';
                stack.push(a);
            }
            else if(line.charAt(i) == 'A'){
                int a = stack.pop();
                int b = stack.pop();
                if(a == 'F' || b == 'F'){
                    stack.push((int) 'F');
                }
                else{
                    stack.push((int) 'T');
                }
            }
            else if(line.charAt(i) == 'O'){
                int a = stack.pop();
                int b = stack.pop();
                if(a == 'F' && b == 'F'){
                    stack.push((int) 'F');
                }
                else{
                    stack.push((int) 'T');
                }
            }
            else if(line.charAt(i) == 'X'){
                int a = stack.pop();
                int b = stack.pop();
                if(a == b){
                    stack.push((int) 'F');
                }
                else{
                    stack.push((int) 'T');
                }
            }
        }
        int p = stack.pop();
        line = "" + (char) p;
        System.out.println(line);
    }
    static int priority(int c){
        switch (c){
            case '(': case ')':
                return 0;
            case 'X':
                return 1;
            case 'O':
                return 1;
            case 'A':
                return 2;
            case 'N':
                return 3;
            default:
                return 0;
        }
    }

    static void task10(){
        Stack<Integer> stack = new Stack<Integer>();
        String line = "";
        try(FileReader reader = new FileReader("task10.txt")){
            int c;
            while((c = reader.read()) > 0){
                if(c == ' ')
                    continue;
                if(c >= '0' && c <= '9'){
                    line += (char) c;
                }
                else if(c == 'M' || c == 'N'){
                    line += (char) c;
                }
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        while(!stack.isEmpty()){
            int a = stack.pop();
            line += (char) a;
        }

        System.out.println("Polish notation: " + line);

        for(int i = line.length() - 1; i >= 0; i--){
            if(line.charAt(i) >= '0' && line.charAt(i) <= '9'){
                stack.push((int) line.charAt(i));
            }
            else if(line.charAt(i) == 'N'){
                int a = stack.pop();
                int b = stack.pop();
                if(a < b){
                    stack.push(a);
                }
                else{
                    stack.push(b);
                }
            }
            else if(line.charAt(i) == 'M'){
                int a = stack.pop();
                int b = stack.pop();
                if(a > b){
                    stack.push(a);
                }
                else{
                    stack.push(b);
                }
            }
        }
        int p = stack.pop();
        line = "" + (char) p;
        System.out.println("Result: " + line);
    }

    static void task11(){
        Stack<Integer> stack = new Stack<Integer>();
        String line = "";
        try(FileReader reader = new FileReader("task11.txt")){
            int c;
            while((c = reader.read()) > 0){
                if(c == ' ')
                    continue;
                if(c == 'x' || c == 'y' || c == 'z'){
                    line += (char) c;
                }
                else if(c == '('){
                    stack.push(c);
                }
                else if(c == ')'){
                    int r = -1;
                    while((!stack.isEmpty()) && (r = stack.pop()) != '('){
                        line += (char) r;
                    }
                    if(stack.isEmpty() && r == -1){
                        System.out.println("Ops");
                        return;
                    }
                }
                else if(c == '+' || c == '-'){
                    int r = 0;
                    while(!(stack.isEmpty()) && priority1(r = stack.pop()) >= priority1(c)){
                        line += (char) r;
                    }
                    if(!stack.isEmpty())
                        stack.push(r);
                    stack.push(c);
                }
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        while(!stack.isEmpty()){
            int a = stack.pop();
            if(a != '+' && a != '-'){
                System.out.println("Ops");
                return;
            }
            line += (char) a;
        }
        int l1 = 0;
        int l2 = 0;
        for(int i = 0; i < line.length(); i++){
            if(line.charAt(i) == 'x' || line.charAt(i) == 'y' || line.charAt(i) == 'z'){
                l1++;
            }
            else if(line.charAt(i) == '+' || line.charAt(i) == '-'){
                l2++;
            }
        }
        if(l1 - l2 != 1){
            System.out.println("Ops");
            return;
        }
        System.out.println(line);
    }
    static int priority1(int c){
        switch (c){
            case '(': case ')':
                return 0;
            case '+': case '-':
                return 1;
            default:
                return 0;
        }
    }
}