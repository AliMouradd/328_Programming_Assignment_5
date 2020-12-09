// Ali Mourad

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new File("input.txt"));
        ArrayList<BigInteger> temp = new ArrayList<>();
        ArrayList<BigInteger> maxi = new ArrayList<>();
        ArrayList<BigInteger> mini = new ArrayList<>();
        ArrayList<BigInteger> neither = new ArrayList<>();
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
                    padType.put(lilyPad[i], 1);
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
                    padType.replace(lilyPad[i], padType.get(lilyPad[i]) + 2);
                    maxiCount++;
                    break;
                }
            }
        }

        for(BigInteger a: lilyPad){
            if(padType.get(a) == 0){
                neither.add(a);
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


        for(int i = 0; i < maxPossiblePaths; i++) {
            outerLoop:
            for(BigInteger b : lilyPad){// is the first lilypad a maximal pad as well? if yes it becomes its own path
                if(padType.get(b) == 3){
                    path = path +"1 "+ b+ " \n";
                    maxi.remove(b);
                    mini.remove(b);
                    padType.replace(b, 4);
                    break;
                }else if(padType.get(b) == 1){
                    for(BigInteger c : maxi){
                        if(!b.gcd(c).equals(BigInteger.ONE)){
                            path = path +"1 "+b+ " "+ c+ " \n";
                            maxi.remove(c);
                            mini.remove(b);
                            padType.replace(c, 4);
                            padType.replace(b, 4);
                            break outerLoop;
                        }
                    }
                    ArrayList<BigInteger> possiblePath = new ArrayList<>();
                    BigInteger previous = BigInteger.ONE;
                    for(BigInteger d : neither){
                        if(!d.gcd(b).equals(BigInteger.ONE)){
                            for(BigInteger c : maxi){
                                if(!d.gcd(c).equals(BigInteger.ONE)){
                                    path = path + "1 " + b + " " + d + " " + c + " \n";
                                    mini.remove(b);
                                    neither.remove(d);
                                    maxi.remove(c);
                                    padType.replace(b, 4);
                                    padType.replace(c, 4);
                                    padType.replace(d, 4);
                                    break outerLoop;
                                }
                            }
                            if(!d.gcd(b).equals(BigInteger.ONE) ||!d.gcd(previous).equals(BigInteger.ONE)){
                                possiblePath.add(d);
                                previous = d;
                            }

                        }
                    }
                }
            }
        }

//        System.out.println(path);
        FileWriter writer = new FileWriter("output.txt");
        writer.write(path);
        writer.close();


    }
}
