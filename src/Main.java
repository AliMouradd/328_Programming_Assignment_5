// Ali Mourad

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new File("input.txt"));
        ArrayList<BigInteger> temp = new ArrayList<BigInteger>();
        ArrayList<BigInteger> maxi = new ArrayList<BigInteger>();
        ArrayList<BigInteger> mini = new ArrayList<BigInteger>();
        HashMap<BigInteger, Integer> padType = new HashMap<>();
        int num = 0;
        int miniCount = 0;
        int maxiCount = 0;
        int maxPossiblePaths = 0;
        String path = "";

        while(reader.hasNext()){
            temp.add(reader.nextBigInteger());
            num++;

        }
        Collections.sort(temp);

        BigInteger[] lilyPad = new BigInteger[num];
        for(int i = 0; i < num; i++){
            lilyPad[i] = temp.get(i);
        }

        //Checking for minimal
        for(int i = 0; i < num; i ++) {
            for(int j = 0; j < num; j++){
                if(lilyPad[i].compareTo(lilyPad[j]) > 0){
                    if(!lilyPad[i].gcd(lilyPad[j]).equals(BigInteger.ONE)){
                        padType.put(lilyPad[i],0);
                        break;
                    }
                }else if(lilyPad[i].compareTo(lilyPad[j]) == 0){
                    mini.add(lilyPad[i]);
                    miniCount++;
                    break;
                }
            }
        }

        //Checking for Maximal
        for(int i = 0; i < num; i ++) {
            for(int j = num-1; j >= 0 ; j--){
                if(lilyPad[j].compareTo(lilyPad[i]) >0){
                    if(!lilyPad[i].gcd(lilyPad[j]).equals(BigInteger.ONE)){
                        break;
                    }
                }else if(lilyPad[i].compareTo(lilyPad[j]) == 0){
                    maxi.add(lilyPad[i]);
                    maxiCount++;
                    break;
                }
            }
        }


        if(maxiCount < miniCount){
            maxPossiblePaths = maxiCount;
        }else if(miniCount < maxiCount){
            maxPossiblePaths = miniCount;
        }else{
            maxPossiblePaths = miniCount;
        }
//        System.out.println(miniCount + " "+ maxiCount);

        for(int i = 1; i < maxPossiblePaths; i++) {
            for (int j = 0; j < num; j++){
                if(mini.contains(lilyPad[j]) && maxi.contains(lilyPad[j])){


            }
        }
//
//        BigInteger x = new BigInteger("10820322439476171969239561972831622363591587095567017595903261292872410082008573133717357915377891644427637");
//        BigInteger y = new BigInteger("364193336868995826520225067823931193797505032165230974786049684644031691576405293124655104470188748932531065147357");
//
//        System.out.println(x.gcd(y));

        FileWriter writer = new FileWriter("output.txt");
        writer.write(path);
        writer.close();


    }
}
