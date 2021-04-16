public class WaitingRoom {
    private boolean catPresent = false;
    private boolean dogPresent = false;
    private Integer dogsNumber;

    public WaitingRoom() {
        dogsNumber = 0;
    }

    // come cazzo si mette qui il @not null
    public synchronized void enterRoom(String animal) throws Exception {

        switch (animal) {
            case "dog":
                if (catPresent)
                //cane non può entrare
                {
                    System.out.println("Cane     non può entrare, gatto presente.");
                    wait();
                } else if (dogsNumber == 4)
                //max numero di cani raggiunto
                {
                    System.out.println("Cane     non può entrare, troppi cani presente.");
                    wait();
                } else {
                    System.out.println("Cane     entra.");
                    dogPresent = true;
                    dogsNumber++;
                }
                break;

            case "cat":
                if (catPresent || dogPresent)
                //caso gatto non può entrare
                {
                    System.out.println("Gatto   non può entrare.");
                    wait();
                } else {
                    System.out.println("Gatto   entra.");
                    catPresent = true;
                }
                break;

            default:
                System.out.println("Non dovrei essere qui...\n");
        }
    }

    public synchronized void exitRoom(String animal) throws Exception {
        switch (animal) {
            case "dog":
                if(dogsNumber > 0) {
                    dogsNumber--;
                }

                if (dogsNumber == 0) dogPresent = false;
                notify();

                break;

            case "cat":
                catPresent = false;
                notify();

            default:
                System.out.println("Non dovrei essere qui...\n");
        }
    }

}
