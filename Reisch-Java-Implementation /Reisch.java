package com.bigame.coalescedhashing;

import java.util.Random;

/**
 * Created by burakisik on 20/11/2017.
 */

public class Reisch {

    final int tableSize = 11; //Hash Function=hash(key)=key mod 11
    int[] keys = {27, 18, 29, 28, 39, 13, 16, 42, 17};
                 //5  7   7   6   6   2   5   9   6
    int[] reischArray = new int[tableSize];
    String[] links = new String[tableSize];

    Random rnd = new Random();


    public static void main(String[] args) {
        Reisch reisch = new Reisch();
        reisch.placeIntoEischArray();
        reisch.showLastStatus();
    }

    public void placeIntoEischArray() {
        int homeAddress;
        int randomAddress;

        for (int i = 0; i < keys.length; i++) {
            homeAddress = keys[i] % 11;

            if (reischArray[homeAddress] == 0) {
                reischArray[homeAddress] = keys[i];
            }
            else if (reischArray[homeAddress] != 0 && links[homeAddress] == null) {

                do {
                    randomAddress = rnd.nextInt(11);
                } while (reischArray[randomAddress] != 0);

                reischArray[randomAddress] = keys[i];
                links[homeAddress] = randomAddress + "";
            }
            else if (reischArray[homeAddress] != 0 && links[homeAddress] != null) {

                do {
                    randomAddress = rnd.nextInt(11);
                } while (reischArray[randomAddress] != 0);

                reischArray[randomAddress] = keys[i];
                int tempAddress = Integer.parseInt(links[homeAddress]);
                links[homeAddress] = randomAddress + "";
                links[randomAddress] = tempAddress + "";
            }
        }
    }

    public void showLastStatus() {
        System.out.println("Index\tKeys\tLinks");
        for (int i = 0; i < reischArray.length; i++)
            System.out.println(i + "\t\t" + reischArray[i] + "\t\t" + links[i]);
    }
}