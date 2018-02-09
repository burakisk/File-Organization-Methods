package com.bigame.coalescedhashing;

/**
 * Created by burakisik on 20/11/2017.
 */


import java.util.Random;

public class Rlisch {

    final int tableSize = 11; //Hash Function=hash(key)=key mod 11
    int[] keys = {27, 18, 29, 28, 39, 13, 16, 42, 17};
    int[] rLischArray = new int[tableSize];
    String[] links = new String[tableSize];

    Random rnd = new Random();

    public static void main(String[] args) {
        Rlisch rlisch = new Rlisch();
        rlisch.placeIntoRlischArray();
        rlisch.showLastStatus();
    }

    public void placeIntoRlischArray() {
        int homeAddress;
        int randomAddress;
        for (int i = 0; i < keys.length; i++) {
            homeAddress = keys[i] % 11;
            if (rLischArray[homeAddress] == 0) {
                rLischArray[homeAddress] = keys[i];
            } else if (rLischArray[homeAddress] != 0 && links[homeAddress] == null) {

                do{
                    randomAddress = rnd.nextInt(11);
                } while (rLischArray[randomAddress] != 0);

                rLischArray[randomAddress] = keys[i];
                links[homeAddress] = randomAddress + "";

            } else if (rLischArray[homeAddress] != 0 && links[homeAddress] != null) {

                while (links[homeAddress] != null) {
                    homeAddress = Integer.parseInt(links[homeAddress]);
                }

                do{
                    randomAddress = rnd.nextInt(11);
                } while (rLischArray[randomAddress] != 0);

                rLischArray[randomAddress] = keys[i];
                links[homeAddress] = randomAddress + "";
            }
        }
    }

    public void showLastStatus() {
        System.out.println("Index\tKeys\tLinks");
        for (int i = 0; i < rLischArray.length; i++)
            System.out.println(i + "\t\t" + rLischArray[i] + "\t\t" + links[i]);
    }
}
