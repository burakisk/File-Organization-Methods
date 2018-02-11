package com.bigame.kdtree;

/**
 * @author burakisik
 */
public class KDTest {
    public static void main(String []args){
        KD kd = new KD();
        kd.insert("burak",180,75);
        kd.insert("recep",170,80);
        kd.insert("yusuf",175,75);
        kd.insert("abdullah",190,90);
        kd.insert("serkan",185,75);
        kd.insert("ramazan",177,60);
        kd.insert("resul",200,55);
        //kd.inorder();
        //kd.preorder();
        //kd.postorder();
        kd.display();
    }
}
