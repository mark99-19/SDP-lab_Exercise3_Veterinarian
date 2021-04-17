public class WaitingRoom {
    private boolean catPresent = false;
    private boolean dogPresent = false;
    private Integer dogsNumber;

    public WaitingRoom() {
        dogsNumber = 0;
    }

    // come cazzo si mette qui il @not null
    public synchronized void enterRoom(Animal animal) throws Exception {

        switch (animal.getSpecies()) {
            case "DOG" -> {
                if (catPresent)
                //cane non può entrare
                {
                    System.out.printf("%s    %d  non può entrare, gatto presente.%n",
                            animal.getSpecies(), animal.getId());
                    wait();
                } else if (dogsNumber == 4)
                //max numero di cani raggiunto
                {
                    System.out.printf("%s    %d  non può entrare, troppi cani presente.%n",
                            animal.getSpecies(), animal.getId());
                    wait();
                }
                System.out.printf("%s    %d  entra.%n",
                        animal.getSpecies(), animal.getId());
                dogsNumber++;
                dogPresent = true;
                System.out.println(this.getStatus());
            }
            case "CAT" -> {
                if (catPresent || dogPresent)
                //caso gatto non può entrare
                {
                    System.out.printf("%s    %d  non può entrare.\n",
                            animal.getSpecies(), animal.getId());
                    wait();
                }
                System.out.printf("%s    %d   entra.\n",
                        animal.getSpecies(), animal.getId());
                catPresent = true;
                System.out.println(this.getStatus());
            }
            default -> System.err.println("Non dovrei essere qui...\n");
        }
    }

    public synchronized void exitRoom(Animal animal) {
        switch (animal.getSpecies()) {
            case "DOG" -> {
                if (dogsNumber > 0) {
                    dogsNumber--;
                }
                if (dogsNumber == 0) dogPresent = false;
                System.out.printf("%s    %d  esce.%n",
                        animal.getSpecies(), animal.getId());
                notify();
                System.out.println(this.getStatus());
            }
            case "CAT" -> {
                catPresent = false;
                System.out.printf("%s    %d  esce.%n",
                        animal.getSpecies(), animal.getId());
                notify();
                System.out.println(this.getStatus());
            }
            default -> System.err.println("Non dovrei essere qui...\n");
        }
    }

    public String getStatus(){
        return String.format(
                """

                        ###\tDog present: %s
                        ###\tCat present: %s
                        ###\tNumber of dogs: %d

                        """,
                dogPresent, catPresent, dogsNumber);
    }

}
