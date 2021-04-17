import java.util.Random;

public class Animal extends Thread {
    private final String animal;
    private final Random rand = new Random();
    private final WaitingRoom room;

    public Animal(String animal, WaitingRoom room)
    {
        this.animal = animal;
        this.room = room;
    }

    public void run()
    {
        try {
            System.out.printf("%s    %d  prova a entrare...%n",
                    this.animal.toUpperCase(), this.getId());
            room.enterRoom(this);
            Thread.sleep(rand.nextInt(5) * 1000);
            System.out.printf("%s    %d  prova a uscire...%n",
                    this.animal.toUpperCase(), this.getId());
            room.exitRoom(this);
        }
        catch(Exception errore)
        {
            System.err.println("Ho fatto un errore... (cit), cioè\n");
            errore.printStackTrace();
        }
    }

    public String getSpecies()
    {
        return this.animal.toUpperCase();
    }


}
