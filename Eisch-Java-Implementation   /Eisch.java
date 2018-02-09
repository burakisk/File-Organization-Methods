package com.bigame.coalescedhashing;

/**
 * Created by burakisik on 20/11/2017.
 */


public class Eisch {

    final int tableSize = 11; //Hash Function=hash(key)=key mod 11
    int[] keys = {27, 18, 29, 28, 39, 13, 16,42,17};
    int[] eischArray = new int[tableSize];
    String[] links = new String[tableSize];


    public static void main(String[] args) {
        Eisch eisch = new Eisch();
        eisch.placeIntoEischArray();
        eisch.showLastStatus();
    }

    public void placeIntoEischArray() {
        int homeAddress;


        for (int i = 0; i < keys.length; i++) {
            homeAddress = keys[i] % 11;

            if (eischArray[homeAddress] == 0) {
                eischArray[homeAddress] = keys[i];
            } else if (eischArray[homeAddress] != 0 && links[homeAddress] == null) {
                for (int j = eischArray.length - 1; j >= 0; j--) {
                    if (eischArray[j] == 0) {
                        eischArray[j] = keys[i];
                        links[homeAddress] = j+"";
                        break;
                    }
                }
            }
            else if(eischArray[homeAddress] != 0 && links[homeAddress] != null){
                for (int j = eischArray.length - 1; j >= 0; j--) {
                    if (eischArray[j] == 0) {
                        eischArray[j] = keys[i];
                        int tempAddress = Integer.parseInt(links[homeAddress]);
                        links[homeAddress] = j+"";
                        links[j] = tempAddress+"";
                        break;
                    }
                }
            }
        }
    }

    public void showLastStatus(){
        System.out.println("Index\tKeys\tLinks");
        for(int i=0;i<eischArray.length;i++)
            System.out.println(i+"\t\t"+eischArray[i]+"\t\t"+links[i]);
    }
}
