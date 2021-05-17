import java.util.concurrent.*;
import java.util.Arrays;
public class NBADraft {
    public static void main (String[] args) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int N = random.nextInt(1, 32);
        System.out.println("num = " + N);
        int h[] = new int[N];
        for(int i = 0; i < h.length; ++i) {
            h[i] = random.nextInt(150, 250);
        }

        int w[] = new int[N];
        for(int i = 0; i < w.length; ++i) {
            w[i] = random.nextInt(150, 270);
        }
        int s[] = new int[N];
        for(int i = 0; i < s.length; ++i) {
            s[i] = random.nextInt(5, 35);
        }
        int r[] = new int[N];
        for(int i = 0; i < r.length; ++i) {
            r[i] = random.nextInt(0, 10);
        }
        int p[] = new int[N];
        for(int i = 0; i < p.length; ++i) {
            p[i] = random.nextInt(0, 10);
        }

        String hstring = Arrays.toString(h);
        System.out.println(hstring);

        String wstring = Arrays.toString(w);
        System.out.println(wstring);

        String sstring = Arrays.toString(s);
        System.out.println(sstring);

        String rstring = Arrays.toString(r);
        System.out.println(rstring);

        String pstring = Arrays.toString(p);
        System.out.println(pstring);

        Assaign assaign = new Assaign();
        assaign.check(N, h, w, s, r, p);
    }
}
class Assaign{
    int checkh;
    int checkw;
    int checks;
    int checkr;
    int checkp;
    int sum;

    void check(int N, int[] h, int[] w, int[] s, int[] r, int[] p){
        outer: for (int i = 0; i < N; ++i){

            if (h[i] > 220)
                checkh = 5;
            else if (h[i] >= 205 & h[i] <= 220)
                checkh = 2;
            else if (h[i] > 190)
                checkh = 1;
            else
                checkh = 0;

            if (w[i] > 250)
                checkw = 5;
            else if (w[i] >= 225 & w[i] <= 250)
                checkw = 2;
            else if (w[i] > 200)
                checkw = 1;
            else
                checkw = 0;

            if (s[i] > 20)
                checks = 3;
            else if (s[i] >= 15 & s[i] <= 20)
                checks = 2;
            else if (s[i] > 10)
                checks = 1;
            else
                checks = 0;

            if (r[i] > 6)
                checkr = 3;
            else if (r[i] >= 4 & r[i] <= 6)
                checkr = 2;
            else if (r[i] > 2)
                checkr = 1;
            else
                checkr = 0;

            if (p[i] > 7)
                checkp = 3;
            else if (p[i] >= 5 & p[i] <= 7)
                checkp = 2;
            else if (p[i] > 3)
                checkp = 1;
            else
                checkp = 0;

            sum = checkh + checkw + checks + checkr + checkp;
            int sumHWS = checkh + checkw + checks;
            int sumHSR = checkh + checks + checkr;
            int sumHSP = checkh + checks + checkp;
            int sumHRP = checkh + checkr + checkp;
            int sumHWR = checkh + checkw + checkr;
            int sumHWP = checkh + checkw + checkp;

            int sumWSR = checkw + checks + checkr;
            int sumWSP = checkw + checks + checkp;
            int sumWRP = checkw + checkr + checkp;

            int sumSRP = checks + checkr + checkp;

            int [] checker = {sumHWS, sumHSR, sumHSP, sumHRP, sumHWR, sumHWP, sumWSR, sumWSP, sumWRP};
            int [] checker2 = {sumHWS, sumHSR, sumHSP, sumHRP, sumHWR, sumHWP, sumWSR, sumWSP, sumWRP, sumSRP};

            if (sum >= 11){
                for (int j = 0; j < checker.length; ++j) {
                    if (checker[j] >= 11) {
                        if (checkh == checkw & checker[j] < 13) {
                            System.out.println("Игрок " + (i + 1) + " категория 1");
                            sum = 0;
                            checker[j] = 0;
                            continue outer;
                        }
                        else {
                            System.out.println("Игрок " + (i + 1) + " категория 0");
                            sum = 0;
                            checker[j] = 0;
                            continue outer;
                        }
                    }
                    else if (checker[j] >= 8) {
                            System.out.println("Игрок " + (i + 1) + " категория 1");
                            sum = 0;
                            checker[j] = 0;
                            continue outer;
                        }
                    else {
                        System.out.println("Игрок " + (i + 1) + " категория 2");
                        sum = 0;
                        checker[j] = 0;
                        continue outer;
                    }
                }
            }

            else if (sum >= 8){
                if (sum>=8 & checkh != 0 & checkw != 0 & checks != 0 & checkr != 0 & checkp != 0){
                    System.out.println("Игрок " + (i+1) + " категория 1");
                    sum = 0;
                    continue outer;
                }
                else {
                    for (int j = 0; j < checker2.length; ++j) {
                        if (checker2[j] >= 8 & checker2[j] != 9 & checkh != 0 & checkw != 0 & checks != 0 & checkr != 0 & checkp != 0){
                            System.out.println("Игрок " + (i + 1) + " категория 1");
                            sum = 0;
                            checker2[j] = 0;
                            continue outer;
                        }
                        else {
                            System.out.println("Игрок " + (i + 1) + " категория 2");
                            sum = 0;
                            checker[j] = 0;
                            continue outer;
                        }
                    }
                }
            }

            else if (sum >=5){
                if (checkh == checkw & checkh == checks & checkh == checkr & checkh == checkp)
                    System.out.println("Игрок " + (i+1) + " категория 3");
                else
                    System.out.println("Игрок " + (i+1) + " категория 2");
            }

            else
                System.out.println("Игрок " + (i+1) + " категория 3");
        }
    }
}