package com.bigame.coalescedhashing;

/**
 * Created by burakisik on 20/11/2017.
 */


public class Eich {

    final int tableSize = 11; //Hash Function=hash(key)=key mod 11
    int[] keys = {27,18,29,28,39,13,16,42,17,25};
    // key mod7-->6  4  1  0  4  6  2  0  3  4
    int[] eichArray = new int[tableSize];
    String[] links = new String[tableSize];


    public static void main(String[] args) {
        Eich eich = new Eich();
        eich.placeIntoEichArray();
        eich.showLastStatus();
    }

    public void placeIntoEichArray() {
        int homeAddress;
        //I set the primary area's size to 7 and the overflow area's size to 4
        // tableSize * 0,86 --> primary area size  , tableSize * 0,14 --> overflow area size
        // i didn't calculate these field's size in the sample but if you want u can do it

        for (int i = 0; i < keys.length; i++) {
            homeAddress = keys[i] % 7;

            if (eichArray[homeAddress] == 0) {
                eichArray[homeAddress] = keys[i];
            } else if (eichArray[homeAddress] != 0 && links[homeAddress] == null) {
                for (int j = eichArray.length - 1; j >= 0; j--) {
                    if (eichArray[j] == 0) {
                        eichArray[j] = keys[i];
                        links[homeAddress] = j+"";
                        break;
                    }
                }
            }
            else if(eichArray[homeAddress] != 0 && links[homeAddress] != null){
                for (int j = eichArray.length - 1; j >= 0; j--) {
                    if (eichArray[j] == 0) {
                        eichArray[j] = keys[i];
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
        for(int i=0;i<eichArray.length;i++) {
            if(i==7) {
                System.out.println("---------------------");
                System.out.println("\t" + "Overflow Area");
                System.out.println("---------------------");
            }
            System.out.println(i + "\t\t" + eichArray[i] + "\t\t" + links[i]);
        }
    }
}
