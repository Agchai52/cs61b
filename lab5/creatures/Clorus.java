package creatures;
import huglife.*;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature{
    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;
    /**
     * fraction of energy to retain when replicating.
     */
    private double repEnergyRetained = 0.5;
    /**
     * fraction of energy to bestow upon offspring.
     */
    //private double repEnergyGiven = 0.5;

    /**
     * creates plip with energy equal to E.
     */
    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    /**
     * creates a plip with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }

    /**
     * Should return a color with red = 34, blue = 0, and green = 231
     */
    public Color color() {
        return color(r, g, b);
    }

    /**
     * Attack Plip
     */
    public void attack(Creature c) {
        // do nothing.
        energy += c.energy();
    }

    /**
     * Plips should lose 0.15 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        // TODO
        energy -= 0.03;
        if (energy <= 0.) {
            energy = 0.;
        }
    }


    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    public void stay() {
        // TODO
        energy -= 0.01;
        if (energy <= 0.) {
            energy = 0.;
        }
    }

    /**
     * Clorus and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    public Clorus replicate() {
        energy = energy * repEnergyRetained;
        double babyEnergy = energy;
        return new Clorus(babyEnergy);
    }

    /**
     * Clorus take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent spaces, STAY.
     * 2. Otherwise, if energy >= 1, REPLICATE towards an empty direction
     * chosen at random.
     * 3. Otherwise, if any Cloruses, MOVE with 50% probability,
     * towards an empty direction chosen at random.
     * 4. Otherwise, if nothing else, STAY
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        // TODO
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        // for () {...}
        for (Direction dir : neighbors.keySet()) {
            Occupant o = neighbors.get(dir);
            if (o.name().equals("empty")){
                emptyNeighbors.add(dir);
            } else if (o.name().equals("plip")) {
                plipNeighbors.add(dir);
            }
        }

        if (emptyNeighbors.size() == 0 && plipNeighbors.size() == 0) { // FIXME
            // TODO
            stay();
            return new Action(Action.ActionType.STAY);
        }


        // Rule 2
        if (plipNeighbors.size() > 0) {

            Direction dir_poss = HugLifeUtils.randomEntry(plipNeighbors);
            Creature p = (Creature) neighbors.get(dir_poss);
            attack(p);
            return new Action(Action.ActionType.ATTACK, dir_poss);

            }

        // Rule 3
        // HINT: randomEntry(emptyNeighbors)
        if (energy >= 1.) {
            Clorus c_re = replicate();
            Direction dir_poss = HugLifeUtils.randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.REPLICATE, dir_poss);
        }


        // Rule 4
        if (emptyNeighbors.size() > 0) {
            Direction dir_poss = HugLifeUtils.randomEntry(emptyNeighbors);
            move();
            return new Action(Action.ActionType.MOVE, dir_poss);
        }

        stay();
        return new Action(Action.ActionType.STAY);

    }
}


