import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner reader = new Scanner(new File("input.txt"));
        ArrayList<BigInteger> temp = new ArrayList<BigInteger>();
        HashMap<BigInteger, Integer> padType = new HashMap<>();
        int num = 0;
        int miniCount = 0;
        int maxiCount = 0;

        while(reader.hasNext()){
            temp.add(reader.nextBigInteger());
            num++;

        }
        Collections.sort(temp);

        BigInteger[][] lilyPad = new BigInteger[num][2];
        for(int i = 0; i < num; i++){
            lilyPad[i][0] = temp.get(i);
        }

        //Checking for minimal
        for(int i = 0; i < num; i ++){
            if(i == 0) { // if its the first index it will always be minimal
                padType.put(lilyPad[i][0], 1);
            }
            for(int j = 0; j < i; j++){
                if(!lilyPad[i][0].gcd(lilyPad[j][0]).equals(BigInteger.ONE)){ //compare i index to every j
                    padType.put(lilyPad[i][0], 0);
                }
            }
            padType.putIfAbsent(lilyPad[i][0], 1);
        }

//        //Checking for Maximal
//        for(int i = 0; i < num; i ++){
//            for(int j = 0; j < num; j++){
//                if(lilyPad[i][0].gcd(lilyPad[j][0]).equals(BigInteger.ONE) && lilyPad[i][0].compareTo(lilyPad[j][0]) < 0){
//                    break;
//                }else{
//                    padType.put(lilyPad[i][0],padType.get(lilyPad[i][0])+2);
//                }
//            }
//        }
//        padType.replace(BigInteger.valueOf(5), padType.get(BigInteger.valueOf(5))+1);
        for (Map.Entry<BigInteger, Integer> entry : padType.entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }


    }
}
