import java.util.Random;

public class PrograssiveOverflow{

    Random rnd;
    int [] randomArr;
    String [] prograssiveOverflowArr;
    public static final int tableSize = 11;

    public static void main(String [] args){

        PrograssiveOverflow prograssiveOverflow = new PrograssiveOverflow(); //main method is static but ccreateRandomNumber() and placePrograssiveOverflow() are not.we create obj so that call to these func.
        prograssiveOverflow.createRandomNumber(); //create random array
        prograssiveOverflow.placeNumber(); //fix the prograssiveOverflowArr
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

        prograssiveOverflowArr = new String[tableSize]; //i will create string arr so that able to choose collision thanks to null value
        for (int i=0;i<tableSize;i++) //initializing for avoiding possible error
            prograssiveOverflowArr[i] ="null";

        int index;

        //get value from randomArr then place the prograssiveOverflowArr
        for(int i=0;i<randomArr.length;i++){
            index = randomArr[i]%11;
            //System.out.println(randomArr[i] +":" +index);
            if(prograssiveOverflowArr[index] == "null"){
                prograssiveOverflowArr[index] = randomArr[i]+""; // <+""> is like convert function
            }
            else{
                while(prograssiveOverflowArr[index] != "null"){
                    index += 1;
                    index %= 11; // to avoid index out of array
                }
                prograssiveOverflowArr[index] = randomArr[i]+"";
            }
        }

        //print result
        for (int i=0;i<tableSize;i++){
            System.out.println(i+"->"+prograssiveOverflowArr[i]);
        }
    }
}
