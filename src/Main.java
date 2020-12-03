// Ali Mourad

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
        for(int i = 0; i < num; i ++){
            if(i == 0) { // if its the first index it will always be minimal
                padType.put(lilyPad[i], 1);
            }
            for(int j = 0; j < i; j++){
                if(!lilyPad[i].gcd(lilyPad[j]).equals(BigInteger.ONE)){ //compare i index to every j
                    padType.put(lilyPad[i], 0);
                }
            }
            if(padType.get(lilyPad[i]) == null){
                padType.put(lilyPad[i],1);
                miniCount++;
            }
        }

        //Checking for Maximal
        for(int i = 0; i < num; i ++) {
            if (i == num - 1) { // if its the first index it will always be minimal
                padType.put(lilyPad[i], padType.get(lilyPad[i]) + 2);
                maxiCount++;
                break;
            }
            for (int j = i; j < num-1; j++) {
                if (!lilyPad[i].gcd(lilyPad[j+1]).equals(BigInteger.ONE)) {
                    break;
                }
                if (lilyPad[i + 1].equals(lilyPad[num - 1])) {
                    padType.put(lilyPad[i], padType.get(lilyPad[i]) + 2);
                    maxiCount++;
                }
            }
        }

        for (Map.Entry<BigInteger, Integer> entry : padType.entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }

        if(maxiCount < miniCount){
            maxPossiblePaths = maxiCount;
        }else if(miniCount < maxiCount){
            maxPossiblePaths = miniCount;
        }else{
            maxPossiblePaths = miniCount;
        }


        for(int i = 0; i < maxPossiblePaths; i++){
            // step one: check if last input is both(3)
            if(padType.get(lilyPad[num-1]) == 3){
                path = path + "1 "+ lilyPad[num-1]+" \n";
                padType.put(lilyPad[num-1], padType.get(lilyPad[num-1])+1);
            }else{

                for(BigInteger a : lilyPad){
                    ArrayList<BigInteger> GCD = new ArrayList<>();
                    for(BigInteger b : lilyPad){
                        if(!a.equals(b) && !a.gcd(b).equals(BigInteger.ONE)){
                            GCD.add(b);
                        }
                    }
                    Collections.sort(GCD);
                    System.out.println(GCD);
                }
            }
        }


    }
}
