import java.util.Arrays;
import java.util.Scanner;

public class LotteryRandom {
    private static void GenerateLotto(int[] number){
        for(int i=0; i<number.length; i++){
            number[i] = (int)(Math.random()*45)+1;
            for(int j=0; j<i; j++) //중복 확인
                if(number[j] == number[i])
                    i--; //앞에서 발생한 숫자랑 겹치게 되면 다시 생성
        }
    }
    public static void main(String[] args){
        int lottoLine;
        String isContinue;
        int[] lotto = new int[6];
        Check check = new Check();

        Scanner s = new Scanner(System.in);
        while(true){
            System.out.print("How many line to Creat( 0-> exit) : ");
            lottoLine = s.nextInt();
            if(lottoLine == 0)
                break;
            for(int i = 0; i<lottoLine; i++){
                GenerateLotto(lotto);
                Arrays.sort(lotto); //Arrays의 sort 메소드 활용
                System.out.print(i+1+"th Lotto : ");
                for(int j=0; j<lotto.length; j++){
                    check.set(lotto[j]-1);
                    System.out.print(lotto[j]+" ");
                }
                System.out.println();
            }
            System.out.println("Start analyzing numbers");
            for(int i =0; i<45; i++){
                System.out.println(check.getMost());
            }
            System.out.println("\n"+"Press 'Y' to continue, Other to Exit");
            isContinue = s.next();
            if(!(isContinue.equals("Y") || isContinue.equals("y")))
                break;
        }
    }
}