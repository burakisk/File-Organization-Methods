package com.bigame.coalescedhashing;

/**
 * Created by burakisik on 20/11/2017.
 */

public class Lisch {
    final int tableSize = 11; //Hash Function=hash(key)=key mod 11
    int[] keys = {27, 18, 29, 28, 39, 13, 16,42,17};
    int[] lischArray = new int[tableSize];
    int[] links = new int[tableSize];


    //I assumed zero values ​​to be null

    public static void main(String[] args) {
        Lisch lisch = new Lisch();
        lisch.placeIntoLischArray();
        lisch.showLastStatus();
    }

    public void placeIntoLischArray() {
        int homeAddress;

        for (int i = 0; i < keys.length; i++) {
            homeAddress = keys[i] % 11;
            if (lischArray[homeAddress] == 0) {
                lischArray[homeAddress] = keys[i];
            } else if (lischArray[homeAddress] != 0 && links[homeAddress] == 0) {
                for (int j = lischArray.length - 1; j >= 0; j--) {
                    if (lischArray[j] == 0) {
                        lischArray[j] = keys[i];
                        links[homeAddress] = j;
                        break;
                    }
                }
            }
            else if(lischArray[homeAddress] != 0 && links[homeAddress] != 0){
                while (links[homeAddress] != 0){
                    homeAddress = links[homeAddress];
                }

                for (int j = lischArray.length - 1; j >= 0; j--) {
                    if (lischArray[j] == 0) {
                        lischArray[j] = keys[i];
                        links[homeAddress] = j;
                        break;
                    }
                }
            }
        }
    }

    public void showLastStatus(){
        //I assumed zero values ​​to be null so there is no value in that index
        System.out.println("Index\tKeys\tLinks");
        for(int i=0;i<lischArray.length;i++)
            System.out.println(i+"\t\t"+lischArray[i]+"\t\t"+links[i]);
    }
}
