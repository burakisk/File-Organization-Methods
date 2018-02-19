import java.util.Random;

public class BrentMethod{

    Random rnd;
    int [] randomArr;
    String [] brentArr;
    public static final int tableSize = 11;

    public static void main(String [] args){

     BrentMethod brentMethod = new BrentMethod();
     brentMethod.createRandomNumber();
     brentMethod.placeNumber();
    }

    public void createRandomNumber(){
        randomArr = new int[tableSize];
        rnd = new Random();

        for(int i=0;i<tableSize;i++){
            randomArr[i] = rnd.nextInt(100);
            //System.out.println(randomArr[i]);
        }
    }

    public void placeNumber(){
        brentArr = new String[tableSize]; //i will create string arr so that able to choose collision thanks to null value
        for (int i=0;i<brentArr.length;i++) //initializing to avoid possible error
             brentArr[i] ="null";

        int index,tempIndex1,tempIndex2;
        int brentIndexValue;
        int probeHomeAddress = 1;
        int probeChangeHomeAddres = 1;

        for(int i=0;i<randomArr.length;i++){
            index = randomArr[i]%11;
            tempIndex1 = tempIndex2 = index; // keep the brentArr[index] index(because it may be change)
            //System.out.println(randomArr[i] +":" +index);

            if(brentArr[index] == "null"){
                brentArr[index] = randomArr[i]+""; // +"" is like convert function
            }
            else{
                brentIndexValue = Integer.parseInt(brentArr[index]); // keep the home adress value
                //if new value move to an other place so don't move home address
                while(brentArr[index] != "null"){
                    index += randomArr[i]/11;
                    index %= 11;
                    probeHomeAddress++;
                }

                tempIndex2 = index; // keep the index because it will change in second while loop
                index = tempIndex1; //assign to starting value

                //if home address move to an other place
                while(!brentArr[index].equals("null")){
                    index += brentIndexValue/11;
                    index %= 11;
                    probeChangeHomeAddres++;
                }

                if(probeChangeHomeAddres < probeHomeAddress){
                    brentArr[tempIndex1] = randomArr[i]+"";
                    brentArr[index] = brentIndexValue+""; //home address value
                }
                else{
                    brentArr[tempIndex2] = randomArr[i]+"";
                }
                probeChangeHomeAddres = 1;
                probeHomeAddress = 1;
          }
        }
        //print the result
        for (int i=0;i<tableSize;i++){
            System.out.println(i+"->"+brentArr[i]);
        }
    }
}
