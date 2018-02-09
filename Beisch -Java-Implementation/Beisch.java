package com.bigame.coalescedhashing;

/**
 * Created by burakisik on 20/11/2017.
 */

public class Beisch {

    final int tableSize = 11; //Hash Function=hash(key)=key mod 11
    int[] keys = {27, 18, 29, 28, 39, 13, 16,42,17};
               // 5   7   7   6   6   2   5  9  6
    int[] beischArray = new int[tableSize];
    String[] links = new String[tableSize];

    public static void main(String[] args) {
        Beisch beisch = new Beisch();
        beisch.placeIntoEischArray();
        beisch.showLastStatus();
    }

    public void placeIntoEischArray() {
        int homeAddress;
        boolean position = true;

        for (int i = 0; i < keys.length; i++) {
            homeAddress = keys[i] % 11;

            if (beischArray[homeAddress] == 0) {
                beischArray[homeAddress] = keys[i];
            } else if (beischArray[homeAddress] != 0 && links[homeAddress] == null) {

                if(position) {
                    for (int j = beischArray.length - 1; j >= 0; j--) {
                        if (beischArray[j] == 0) {
                            beischArray[j] = keys[i];
                            links[homeAddress] = j + "";
                            break;
                        }
                    }
                    position = false;
                }
                else{
                    for (int j = 0; j<beischArray.length; j++) {
                        if (beischArray[j] == 0) {
                            beischArray[j] = keys[i];
                            links[homeAddress] = j + "";
                            break;
                        }
                    }
                    position = true;
                }
            }
            else if(beischArray[homeAddress] != 0 && links[homeAddress] != null){

                if(position) {
                    for (int j = beischArray.length - 1; j >= 0; j--) {
                        if (beischArray[j] == 0) {
                            beischArray[j] = keys[i];
                            int tempAddress = Integer.parseInt(links[homeAddress]);
                            links[homeAddress] = j + "";
                            links[j] = tempAddress + "";
                            break;
                        }
                    }
                    position = false;
                }
                else{
                    for (int j = 0; j<beischArray.length; j++) {
                        if (beischArray[j] == 0) {
                            beischArray[j] = keys[i];
                            int tempAddress = Integer.parseInt(links[homeAddress]);
                            links[homeAddress] = j + "";
                            links[j] = tempAddress + "";
                            break;
                        }
                    }
                    position = true;
                }

            }
        }
    }

    public void showLastStatus(){
        System.out.println("Index\tKeys\tLinks");
        for(int i=0;i<beischArray.length;i++)
            System.out.println(i+"\t\t"+beischArray[i]+"\t\t"+links[i]);
    }
}
