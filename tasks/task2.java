import java.io. *;
import java.util.*;
import java.lang. *;

public class task2{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int a;
        System.out.println("Choose excercise (1, 2, 3): ");
        a = in.nextInt();
        in.nextLine();
        String line1 = in.nextLine();
        String line2 = "";
        if(a == 1){
            line2 = in.nextLine();
        }
        switch(a){
            case 1:
                System.out.println(canWin(line1, line2));
                break;
            case 2:
                System.out.println(palindrome(line1));
                break;
            case 3:
                System.out.println(concatenations(line1));
                break;
            default:
                System.out.println("There is no such excercise.");
        }
    }
    /**отсортирую строки по алфавитному порядку, так как это наиболее
     выгодные позиции для обеих строк. Символы с меньшим индексом одной
     строки будут сравниваться с символами с меньшим индексом другой строки **/
    static boolean canWin(String line1, String line2){
        String s1 = "";
        String s2 = "";
        //сортируем символы в строках по алфавитному порядку
        //считаю, что строки заведомо верно введены и у них одинаковая длина
        for(int i = 0; i < line1.length(); i++){
            s1 += line1.charAt(i);
            s2 += line2.charAt(i);
            for(int j = i; j < line1.length(); j++){
                if(line1.charAt(i) >= line1.charAt(j)){
                    s1 = s1.substring(0, s1.length() - 1);
                    s1 += line1.charAt(j);
                }
                if(line2.charAt(i) >= line2.charAt(j)){
                    s2 = s2.substring(0, s2.length() - 1);
                    s2 += line2.charAt(j);
                }
            }
        }
        boolean status = false;
        //проверяем, может ли первая строка победить
        for(int i = 0; i < s1.length(); i++){
            if(s1.charAt(i) < s2.charAt(i)){
                status = true;
                break;
            }
        }
        if(status == false)
            return true;
        //проверяем, может ли вторая строка победить
        for(int i = 0; i < s1.length(); i++){
            if(s2.charAt(i) < s1.charAt(i)){
                return false;
            }
        }
        return true;
    }

    static String palindrome(String line){
        int l = 0;
        String s;
        String result = "";

        for(int i = 0; i < line.length(); i++){
            s = "";
            //каждый раз подстрока увеличивается на символ
            for(int j = i; j < line.length(); j++){
                s += line.charAt(j);
                //проверка, является ли палиндромом
                if(isPalindrome(s)){
                    //проверяется длина
                    if(l < s.length()){
                        result = s;
                        l = s.length();
                    }
                }
            }
        }

        return result;
    }
    static boolean isPalindrome(String line){
        String reverse = "";
        //переворачиваем строку
        for(int i = 0; i < line.length(); i++){
            reverse += line.charAt(line.length() - i - 1);
        }
        //сравниваем перевернутую cтроку с оригинальной и возвращаем
        //результат сравнения
        return reverse.equals(line);
    }

    static int concatenations(String line){
        String conc = "";
        String[] arr;
        //ищем конкатенации и записываем в переменную подстроки через пробел
        for(int i = 0; i < line.length(); i++){
            for(int j = i; j < line.length(); j++){
                if(isConc(line.substring(i, j + 1))){
                    conc += (line.substring(i, (i + j + 1) / 2)) + " ";
                }
            }
        }
        conc = conc.substring(0, conc.length());
        //записываем полученные строки в массив
        arr = conc.split(" ");
        /**Пример: у нас из входной строки abcabcabc сохранятся подстроки
         abc bca cab abc
         но abc встречается здесь дважды. Чтобы не допустить ошибки в расчетах,
         удаляем строку, если она встречается более одного раза в массиве**/
        for(int i = 0; i < arr.length - 1; i++){
            for(int j = i + 1; j < arr.length; j++){
                if(arr[i].equals(arr[j])){
                    arr[j] = "";
                }
            }
        }
        int count = 0;
        /**все эл-ты массива с пустыми строками игнорируем, считаем
         лишь те, в которых что-то записано(abc bca cab [а здесь уже пусто])**/
        for(int i = 0; i < arr.length; i++){
            if(!arr[i].equals(""))
                count++;
        }

        return count;

    }
    static boolean isConc(String line){
        //проверяем, если длина строки чётна, иначе это не может быть конкатенацией
        if(line.length() % 2 == 0){
            String l1 = line.substring(0, line.length() / 2);
            String l2 = line.substring(line.length() / 2);
            //если две половинки входной строки совпали, возвращаем true
            if(l1.equals(l2))
                return true;
            return false;
        }
        return false;

    }
}