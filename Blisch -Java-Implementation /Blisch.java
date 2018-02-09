package com.bigame.coalescedhashing;

/**
 * Created by burakisik on 20/11/2017.
 */

public class Blisch {

    final int tableSize = 11; //Hash Function=hash(key)=key mod 11
    int[] keys = {27, 18, 29, 28, 39, 13,16,42,17};
    int[] BlischArray = new int[tableSize];
    String[] links = new String[tableSize];


    public static void main(String[] args) {
        Blisch blisch = new Blisch();
        blisch.placeIntoLischArray();
        blisch.showLastStatus();
    }

    public void placeIntoLischArray() {
        int homeAddress;
        boolean position = true; // if position is tail place in last else place in head
        for (int i = 0; i < keys.length; i++) {
            homeAddress = keys[i] % 11;
            if (BlischArray[homeAddress] == 0) {
                BlischArray[homeAddress] = keys[i];
            } else if (BlischArray[homeAddress] != 0 && links[homeAddress] == null) {
                if(position) {
                    for (int j = BlischArray.length - 1; j >= 0; j--) {
                        if (BlischArray[j] == 0) {
                            BlischArray[j] = keys[i];
                            links[homeAddress] = j+"";
                            break;
                        }
                    }
                    position = false;

                }else{
                    for (int j = 0; j < BlischArray.length; j++) {
                        if (BlischArray[j] == 0) {
                            BlischArray[j] = keys[i];
                            links[homeAddress] = j+"";
                            break;
                        }
                    }
                    position = true;
                }
            }
            else if(BlischArray[homeAddress] != 0 && links[homeAddress] != null){
                while (links[homeAddress] != null){
                    homeAddress = Integer.parseInt(links[homeAddress]);
                }
                if(position) {
                    for (int j = BlischArray.length - 1; j >= 0; j--) {
                        if (BlischArray[j] == 0) {
                            BlischArray[j] = keys[i];
                            links[homeAddress] = j+"";
                            break;
                        }
                    }
                    position = false;
                }else{
                    for (int j = 0; j<BlischArray.length; j++) {
                        if (BlischArray[j] == 0) {
                            BlischArray[j] = keys[i];
                            links[homeAddress] = j+"";
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
        for(int i=0;i<BlischArray.length;i++)
            System.out.println(i+"\t\t"+BlischArray[i]+"\t\t"+links[i]);
    }
}

