package model;
public class VerifieCommande
{
    private final static String commandes[] = {"LINK", "COPY", "GRAB", "DROP", "ADDI", "SUBI", "MULI", "HALT", "JUMP", "FJMP", "TEST","WIPE"};

    public VerifieCommande() {} ; 

    public static boolean commandeValide(String commande)
    {
        for(int i = 0;i < commandes.length;i++)
        {
            if(commandes[i].equals(commande))
            {
                return true;
            }
        }
        return false;
    }
}