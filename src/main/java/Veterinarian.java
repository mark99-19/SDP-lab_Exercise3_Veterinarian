import java.util.Random;

public class Veterinarian {
    static final int animalsNumber = 5;
    static final WaitingRoom room = new WaitingRoom();
    static final Random picker = new Random();

    public static void main(String[] args) {
        System.out.println("Sto per creare 5 animali....\n");

        for (int i= 0; i < animalsNumber; i++)
        {
            createAnimal();
        }
    }


    static private void createAnimal()
    {
        int specie = picker.nextInt(2);
        switch (specie) {
            case 0 -> {
                Animal dog = new Animal("dog", room);
                dog.start();
            }
            case 1 -> {
                Animal cat = new Animal("cat", room);
                cat.start();
            }
            default -> System.err.println("Come ci sei finito qui?\n");
        }
    }


}
