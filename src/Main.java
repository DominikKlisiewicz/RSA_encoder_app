import javax.swing.*;

public class Main 
{
    public static void main(String[] args) 
    {
        RSA rsa = new RSA();
        String M = "Dominik Klisiewicz zakodowal swoj projekt na zajecia";
        String cypher = rsa.encrypt(M);
        System.out.println(cypher);
        System.out.println( rsa.decrypt(cypher));
        SwingUtilities.invokeLater(
        () -> {
            new GUI(rsa);
        });
    }
}
