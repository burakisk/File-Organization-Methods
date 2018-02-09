package com.bigame.computedchain;

import java.util.ArrayList;
import java.util.List;

public class ComputedChaining {
    final int tableSize = 11; //Hash Function=hash(key)=key mod 11
    int[] keys = {27, 18, 29, 28, 39, 13, 16, 38, 53}; //{16,27,38,49,60,71,82,28,17,39,21};  //{27,18,29,28, 39,13,16,38,53,49,48};//{27,18,29,28,39,13,16,42,17,41,60}; //

    int[] ccArray = new int[tableSize]; //cc = computed Chain
    String[] links = new String[tableSize];

    List<Integer> temKeyList = new ArrayList<>();
    List<String> tempLinkList = new ArrayList<>();
    int nof = 0; //number of offsets
    int homeAddress;
    int stepSize;
    int newAddress;
    int tempAddress;
    int ptrAddress;
    String ptrLink;

    public static void main(String[] args) {
        ComputedChaining cc = new ComputedChaining();
        cc.placeIntoccArray();
        cc.display();
        System.out.println();
        cc.search(16);
        cc.search(53);
        cc.search(98);
    }

    protected void placeIntoccArray() {

        for (int i = 0; i < keys.length; i++) {
            homeAddress = keys[i] % tableSize;
            tempAddress = homeAddress;
            nof = 0;
            if (ccArray[homeAddress] == 0) {//if homeaddress is empty
                ccArray[homeAddress] = keys[i];
            }
            else if (ccArray[homeAddress] != 0 && (ccArray[homeAddress] % tableSize != homeAddress)) { //If the value in the address is not in the it's homeaddress

                ptrAddress = ccArray[homeAddress] % tableSize;

                while (ccArray[ptrAddress] != ccArray[homeAddress]){
                    if (ccArray[(ptrAddress + ccArray[ptrAddress] /tableSize * Integer.parseInt(links[ptrAddress])) % tableSize] == ccArray[homeAddress])
                        break;
                    ptrAddress = ptrAddress + ccArray[ptrAddress] /tableSize * Integer.parseInt(links[ptrAddress]);
                    ptrAddress %=tableSize;
                }

                tempLinkList.clear();
                temKeyList.clear();
                while (links[tempAddress] !=null){ //keep all addresses linked to homeaddress
                    temKeyList.add(ccArray[tempAddress]);
                    tempLinkList.add(links[tempAddress]);
                    int before = tempAddress;
                    tempAddress =tempAddress+(ccArray[tempAddress]/tableSize*Integer.parseInt(links[tempAddress]))%tableSize;
                    tempAddress %=tableSize;
                    ccArray[before] = 0;
                    links[before] = null;
                }
                temKeyList.add(ccArray[tempAddress]);
                tempLinkList.add(links[tempAddress]);
                ccArray[tempAddress] = 0;
                links[tempAddress] = null;

                //place the key in the homeaddress
                ccArray[homeAddress] = keys[i];
                links[homeAddress] = null;

                ptrLink = links[ptrAddress];

                int j=0;
                while (ptrLink != null){
                    stepSize = ccArray[ptrAddress]/tableSize;
                    int before =ptrAddress;
                    while (ccArray[ptrAddress] != 0){
                        ptrAddress = ptrAddress +stepSize;
                        ptrAddress %= 11;
                        nof++;
                    }

                    links[before] = nof+"";
                    ccArray[ptrAddress]=temKeyList.get(j);
                    ptrLink = tempLinkList.get(j);
                    j++;
                    nof=0;
                }
            }
            else if(ccArray[homeAddress] != 0 && (ccArray[homeAddress] % tableSize == homeAddress)) { //if homeaddress is not empty

                nof = 1;
                if (links[homeAddress] == null) {
                    stepSize = ccArray[homeAddress] / tableSize;
                    newAddress = stepSize + homeAddress;
                    newAddress %= tableSize;

                    while (ccArray[newAddress] != 0) {
                        newAddress += stepSize;
                        newAddress %= tableSize;
                        nof++;
                    }
                }
                else {
                    while (links[homeAddress] != null) {
                        homeAddress = (homeAddress + ccArray[homeAddress] / tableSize * Integer.parseInt(links[homeAddress])) % tableSize;
                    }
                    stepSize = ccArray[homeAddress] /tableSize;
                    newAddress = stepSize + homeAddress;
                    newAddress %= tableSize;

                    while (ccArray[newAddress] != 0) {
                        newAddress += stepSize;
                        newAddress %= tableSize;
                        nof++;
                    }
                }
                links[homeAddress] = nof + "";
                ccArray[newAddress] = keys[i];
            }
        }
    }

    protected void display(){
        System.out.println("Index"+"\t"+"Key" + "\t\t" + "NOF");
        for (int i = 0; i <tableSize; i++)
            System.out.println(" "+i+"\t\t"+ccArray[i] + "\t\t" + links[i]);
    }

    protected void search(int key){

        int probe=1; //checking homeaddress is one probe
        homeAddress = key % tableSize;

        while (ccArray[homeAddress] != key && links[homeAddress] != null){
            probe += Integer.parseInt(links[homeAddress]);
            homeAddress = homeAddress+(ccArray[homeAddress]/tableSize) * Integer.parseInt(links[homeAddress])%tableSize;
            homeAddress %= tableSize;

        }
        if(ccArray[homeAddress] == key)
            System.out.println(key +" is found in "+probe+" probes");
        else
            System.out.println(key +" is not found");
    }
}
