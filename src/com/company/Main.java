//Portfolio del 1
package com.company;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        //Opretter container vessel
        ContainerVessel c = new ContainerVessel("Danmark", 5.5, 10.0, 20.5); //objekt
        c.loadingCargo(10,5);
        System.out.println("The capacity of the container vessel is " + c.utilityLevelOfCapacity()+ "%");

        //Opretter Tanker vessel
        TankerVessel t = new TankerVessel("Tyskland", 5, 20, 40); //objekt
        t.loadingCargo(8);
        System.out.println("Numbers of compartments: " + t.getTal());
        t.utilityLevelOfCapacity();

        //Opretter RoRo vessel
        RoRoVessel r = new RoRoVessel("Kroatien", 10, 500, 2000, 1000);
        r.loadingCargo(4,7);
        System.out.println(r.utilityLevelOfCapacity() + " % of the lane is used");
    }
}
abstract class Vessels {
    private String flagNation;
    private double draft;
    private double length;
    private double width;

    //opretter constructor som kan anvendes i de forskellige metoder, i stedet for at der er en constructor i hver
    public Vessels (String flagNation, double draft, double width, double length){
        this.flagNation = flagNation;
        this.draft = draft;
        this.width = width;
        this.length = length;
    }
}

class ContainerVessel extends Vessels {
    private double containerCapacity;  //skal de her være private eller skal vi bare lade dem stå som de gør nu?
    private int numbersOfContainer;
    private double positionX;
    private double positionY;
    private String destination;

    public ContainerVessel(String flagNation, double draft, double width, double length) {
        super(flagNation, draft, width, length);
    }
    public double getContainerCapacity(){return containerCapacity;}
    public int getNumbersOfContainer(){return numbersOfContainer;}
    public void setPositionX(double positionX) { //tilføjet
        //er trackingContainer et godt navn for en setter metode jeg tænker at det passer bedre til en getter metode
        // i bør have begge. For at kunne sætte position og hente position.
        this.positionX = positionX;
    }
    public void setPositionY(double positionY) { //tilføjet
        this.positionY = positionY;
    }
    public double getPositionX(){return positionX;} //tilføjet
    public double getPositionY(){return positionY;}
    public String getDestination(){return destination;} //tilføjet

    public void loadingCargo(int containerCapacity, int numbersOfContainer ) {
        this.containerCapacity = containerCapacity;
        this.numbersOfContainer = numbersOfContainer;
    }

    public double utilityLevelOfCapacity() {
        return (numbersOfContainer/containerCapacity)*100;
    }

    //Metoderne i skriver for part 5 behøver ikke at have noget implementeret i dem. I skal bare skrive alle de metoder
    // der skal til for at systemmet der blev beskrevet kan virke. Firmaer kunne have behov for at vide hvor meget plads
    // der er på skibet, hvor det er, hvor hurtigt det kan sejle, og andet information de har behov for, for at vælge skib
    // til at transportere deres vare.

    public void futureDestination(String destination){
        this.destination = destination;
    }
}
class TankerVessel extends Vessels {
    private int cargo;
    private int compartments;
    public boolean isCompartmentFilled = false;
    Random r = new Random();
    private int tal = r.nextInt(10 + 1);


    public TankerVessel(String flagNation, double draft, double width, double length) { //constructor
        super(flagNation, draft, width, length);
    }
    public int getCargo() {
        return cargo;
    }

    public void setTal(int tal) { //tilføjet
        this.tal = tal;
    }

    public int getTal() {
        return tal;
    }

    public void loadingCargo(int cargo) {
        //ingen sikkerheds tjek for at se om der er plads?
        this.compartments = tal;    //virker som noget der burde ske i constructoren + this. er unødvendigt
        this.cargo = cargo;
    }
    public void utilityLevelOfCapacity () {
        if(compartments == cargo ){
            isCompartmentFilled = true;
            System.out.println("All the compartments are full");
        }
        else if (compartments > cargo){
            isCompartmentFilled = false;
            int n = compartments - cargo;
            System.out.println(n + " of the compartments are empty!");
        }
        else {
            isCompartmentFilled= false;
            int h = cargo - compartments;
            System.out.println("There is not room for the last " + h + " cargos");
        }
    }
}
class RoRoVessel extends Vessels {
    private double laneMeters;
    public int cars; //8 meter
    public int trucks; //30 meters

    public RoRoVessel(String flagNation, int draft, int width, int length, double laneMeters){
        super(flagNation, draft, width, length);
        this.laneMeters = laneMeters;
    }
    public void loadingCargo (int cars, int trucks) {
        this.cars = cars;
        this.trucks = trucks;
    }
    public double utilityLevelOfCapacity() {
        return (cars*8+trucks*30) / laneMeters*100;
    }
}
