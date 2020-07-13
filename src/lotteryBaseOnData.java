import java.util.Arrays;
import java.util.Scanner;


class Check{

    private int[] value= new int[45];
    void set(int key){
        this.value[key]++;
    }
    String getMost(){
        int max_value=0;
        int max_key =0;
        for(int i=0; i<value.length; i++){
            if(max_value<=value[i]){
                max_value=value[i];
                max_key =i;
            }
        }
        value[max_key]=-1;
        max_key++;
        return "["+max_key+"] : "+max_value;
    }
}
public class lotteryBaseOnData {
    private static void GenerateLotto(int[] number){
        int[] cnt = {154, 145, 142, 150, 142, 140, 142 ,142, 114,
                147, 143 ,152, 152, 147, 138, 144, 156, 146,
                145, 150, 144, 115, 125, 144, 135, 145, 160,
                128, 123, 136, 145, 128, 152, 158, 135, 136,
                145, 146, 150, 148, 124, 138, 165, 137, 145}; //1~45까지 각 번호별 당첨 횟
        for(int i=0; i<number.length; i++){
            int sum =0;
            number[i] = (int)(Math.random()*6398)+1; //1~6398 랜덤 숫자 생성, 6398은 1회부터 지금까지 각 번호별 당첨된 횟수를 합한 것
            for(int j=0; j<cnt.length; j++){
                if((sum<number[i]) && (number[i] <= (sum+cnt[j]))){ //헤당 범위에 포함되면 그 숫자
                    number[i] = j+1; //각 번호별로 당첨 횟수를 확인하여 확률을 높이기 위함
                    break;
                }
                sum += cnt[j]; //포함이 안되있으면 계속 더해가면 확인
            }
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
