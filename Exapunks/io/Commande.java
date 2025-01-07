public class Commande
{
    private String first; //mot commande
    private String second: //premier argument
    private String third;   //deuxieme arg
    private String fourth;  // troisieme arg
    //j'evite d'utiliser une liste vu qu'on a que 4 arguments maximum

    public Commande(String un,String deux,String trois,String quatre)
    {
        if(un == null || !(un.commandeValide()))
        {
            throw new IllegalArgumentException("commande invalide\n");
        }
        this.first = un;
        this.second = deux;
        this.third = trois;
        this.fourth = quatre;
    }
    //getFirst().commandeValide == true 
    public String getFirst() // le mot commande
    {
        return first; 
    }

    public String getSecond()
    {
        return second;
    }

    public String getThird()
    {
        return third;
    }

    public String getFourth()
    {
        return fourth;
    }

    public boolean hasSecond()
    {
        return (second != null);
    }

    public boolean hasThird()
    {
        return (third != null);
    }
    
    public boolean hasFourth()
    {
        return (fourth != null);
    }

    public String toString() {
		if (!getFirst().commandeValide()) {
			return " Commande inconnue !";
		}

		String result = "Commande: " + getFirst();
		if (hasSecond()) {
			result += " ,arg1: " + getSecond();
		}

		if (hasThird()) {
			result += " ,arg2: " + getThird();
		}
	
		if (hasFourth()) {
			result += " ,arg3: " + getFourth();
		}

		return result;
	}

}