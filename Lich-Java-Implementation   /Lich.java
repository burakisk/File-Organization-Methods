package com.bigame.coalescedhashing;

/**
 * Created by burakisik on 20/11/2017.
 */


public class Lich {

    final int tableSize = 11; //Hash Function=hash(key)=key mod 11
    int[] keys = {27, 18, 29, 28, 39, 13,16,42,17};
    int[] lichArray = new int[tableSize];
    String[] links = new String[tableSize];

    public static void main(String[] args) {
        Lich lich = new Lich();
        lich.placeIntoLichArray();
        lich.showLastStatus();
    }

    public void placeIntoLichArray() {
        int homeAddress;
        //I set the primary area's size to 7 and the overflow area's size to 4
        // tableSize * 0,86 --> primary area size  , tableSize * 0,14 --> overflow area size
        // i didn't calculate these field's size in the sample but if you want u can do it
        for (int i = 0; i < keys.length; i++) {
            homeAddress = keys[i] % 7;
            if (lichArray[homeAddress] == 0) {
                lichArray[homeAddress] = keys[i];
            } else if (lichArray[homeAddress] != 0 && links[homeAddress] == null) {
                for (int j = lichArray.length - 1; j >= 0; j--) {
                    if (lichArray[j] == 0) {
                        lichArray[j] = keys[i];
                        links[homeAddress] = j+"";
                        break;
                    }
                }
            }
            else if(lichArray[homeAddress] != 0 && links[homeAddress] != null){
                while (links[homeAddress] != null){
                    homeAddress = Integer.parseInt(links[homeAddress]);
                }

                for (int j = lichArray.length - 1; j >= 0; j--) {
                    if (lichArray[j] == 0) {
                        lichArray[j] = keys[i];
                        links[homeAddress] = j+"";
                        break;
                    }
                }
            }
        }
    }

    public void showLastStatus(){
        System.out.println("Index\tKeys\tLinks");
        for(int i=0;i<lichArray.length;i++) {
            if(i==7) {
                System.out.println("---------------------");
                System.out.println("\t" + "Overflow Area");
                System.out.println("---------------------");
            }
            System.out.println(i + "\t\t" + lichArray[i] + "\t\t" + links[i]);
        }
    }
}
