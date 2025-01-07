public class VerifieCommande
{
    private final static String commandes[] = {"LINK", "COPY", "GRAB", "DROP", "ADDI", "SUBI", "MULI", "HALT", "WIPE"};

    public VerifieCommande() {} ; 

    public boolean commandeValide()
    {
        for(int i = 0;i < commandes.length();++i)
        {
            if(commandes[i].equals(this))
            {
                return true;
            }
        }
        return flase;
    }
    
}