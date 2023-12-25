package edu.cdu.ua;

import java.util.Objects;

public class LosesStatistic {

    private final int tanks;
    private final int armouredFightingVehicles;
    private final int cannons;
    private final int multipleRocketLaunchers;
    private final int antiAirDefenseDevices;
    private final int planes;
    private final int helicopters;
    private final int drones;
    private final int cruiseMissiles;
    private final int shipsOrBoats;
    private final int carsAndTankers;
    private final int specialEquipment;
    private final int personnel;

    private LosesStatistic(
            int tanks,
            int armouredFightingVehicles,
            int cannons,
            int multipleRocketLaunchers,
            int antiAirDefenseDevices,
            int planes,
            int helicopters,
            int drones,
            int cruiseMissiles,
            int shipsOrBoats,
            int carsAndTankers,
            int specialEquipment,
            int personnel
    ) {
        this.tanks = tanks;
        this.armouredFightingVehicles = armouredFightingVehicles;
        this.cannons = cannons;
        this.multipleRocketLaunchers = multipleRocketLaunchers;
        this.antiAirDefenseDevices = antiAirDefenseDevices;
        this.planes = planes;
        this.helicopters = helicopters;
        this.drones = drones;
        this.cruiseMissiles = cruiseMissiles;
        this.shipsOrBoats = shipsOrBoats;
        this.carsAndTankers = carsAndTankers;
        this.specialEquipment = specialEquipment;
        this.personnel = personnel;
    }

    /**
     * User-friendly print version of data.
     *
     * @return a user-friendly representation of statistic
     */
    public String asPrintVersion() {
        return "Втрати російської армії в Україні: "
                + "Танки - " + tanks
                + ", Бойові броньовані машини (різних типів) - " + armouredFightingVehicles
                + ", Гармати - " + cannons
                + ", Реактивні системи залпового вогню - " + multipleRocketLaunchers
                + ", Засоби ППО - " + antiAirDefenseDevices
                + ", Літаки - " + planes
                + ", Гелікоптери - " + helicopters
                + ", Безпілотні літальні апарати (оперативно-тактичного рівня) - " + drones
                + ", Крилаті ракети - " + cruiseMissiles
                + ", Кораблі (катери) - " + shipsOrBoats
                + ", Автомобілі та автоцистерни - " + carsAndTankers
                + ", Спеціальна техніка - " + specialEquipment
                + ", Особовий склад - близько " + personnel + " осіб";
    }

    /**
     * This method should return an "empty object" instance with 0 as default value for fields.
     *
     * @return an "empty object" instance
     */
    public static LosesStatistic empty() {
        return LosesStatistic.newStatistic()
                .withTanks(0)
                .withArmouredFightingVehicles(0)
                .withCannons(0)
                .withMultipleRocketLaunchers(0)
                .withAntiAirDefenseDevices(0)
                .withPlanes(0)
                .withHelicopters(0)
                .withDrones(0)
                .withCruiseMissiles(0)
                .withShipsOrBoats(0)
                .withCarsAndTankers(0)
                .withSpecialEquipment(0)
                .withPersonnel(0)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LosesStatistic that = (LosesStatistic) o;
        return tanks == that.tanks
                && armouredFightingVehicles == that.armouredFightingVehicles
                && cannons == that.cannons
                && multipleRocketLaunchers == that.multipleRocketLaunchers
                && antiAirDefenseDevices == that.antiAirDefenseDevices
                && planes == that.planes
                && helicopters == that.helicopters
                && drones == that.drones
                && cruiseMissiles == that.cruiseMissiles
                && shipsOrBoats == that.shipsOrBoats
                && carsAndTankers == that.carsAndTankers
                && specialEquipment == that.specialEquipment
                && personnel == that.personnel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                tanks,
                armouredFightingVehicles,
                cannons,
                multipleRocketLaunchers,
                antiAirDefenseDevices,
                planes,
                helicopters,
                drones,
                cruiseMissiles,
                shipsOrBoats,
                carsAndTankers,
                specialEquipment,
                personnel);
    }

    @Override
    public String toString() {
        return "LosesStatistic{"
                + "tanks=" + tanks
                + ", armouredFightingVehicles=" + armouredFightingVehicles
                + ", cannons=" + cannons
                + ", multipleRocketLaunchers=" + multipleRocketLaunchers
                + ", antiAirDefenseDevices=" + antiAirDefenseDevices
                + ", planes=" + planes
                + ", helicopters=" + helicopters
                + ", drones=" + drones
                + ", cruiseMissiles=" + cruiseMissiles
                + ", shipsOrBoats=" + shipsOrBoats
                + ", carsAndTankers=" + carsAndTankers
                + ", specialEquipment=" + specialEquipment
                + ", personnel=" + personnel
                + '}';
    }

    public static LosesStatisticBuilder newStatistic() {
        return new LosesStatisticBuilder();
    }

    public static final class LosesStatisticBuilder {
        private int tanks;
        private int armouredFightingVehicles;
        private int cannons;
        private int multipleRocketLaunchers;
        private int antiAirDefenseDevices;
        private int planes;
        private int helicopters;
        private int drones;
        private int cruiseMissiles;
        private int shipsOrBoats;
        private int carsAndTankers;
        private int specialEquipment;
        private int personnel;

        private LosesStatisticBuilder() {
        }

        public LosesStatisticBuilder withTanks(int tanks) {
            this.tanks = tanks;
            return this;
        }

        public LosesStatisticBuilder withArmouredFightingVehicles(int armouredFightingVehicles) {
            this.armouredFightingVehicles = armouredFightingVehicles;
            return this;
        }

        public LosesStatisticBuilder withCannons(int cannons) {
            this.cannons = cannons;
            return this;
        }

        public LosesStatisticBuilder withMultipleRocketLaunchers(int multipleRocketLaunchers) {
            this.multipleRocketLaunchers = multipleRocketLaunchers;
            return this;
        }

        public LosesStatisticBuilder withAntiAirDefenseDevices(int antiAirDefenseDevices) {
            this.antiAirDefenseDevices = antiAirDefenseDevices;
            return this;
        }

        public LosesStatisticBuilder withPlanes(int planes) {
            this.planes = planes;
            return this;
        }

        public LosesStatisticBuilder withHelicopters(int helicopters) {
            this.helicopters = helicopters;
            return this;
        }

        public LosesStatisticBuilder withDrones(int drones) {
            this.drones = drones;
            return this;
        }

        public LosesStatisticBuilder withCruiseMissiles(int cruiseMissiles) {
            this.cruiseMissiles = cruiseMissiles;
            return this;
        }

        public LosesStatisticBuilder withShipsOrBoats(int shipsOrBoats) {
            this.shipsOrBoats = shipsOrBoats;
            return this;
        }

        public LosesStatisticBuilder withCarsAndTankers(int carsAndTankers) {
            this.carsAndTankers = carsAndTankers;
            return this;
        }

        public LosesStatisticBuilder withSpecialEquipment(int specialEquipment) {
            this.specialEquipment = specialEquipment;
            return this;
        }

        public LosesStatisticBuilder withPersonnel(int personnel) {
            this.personnel = personnel;
            return this;
        }

        public LosesStatistic build() {
            return new LosesStatistic(
                    tanks,
                    armouredFightingVehicles,
                    cannons,
                    multipleRocketLaunchers,
                    antiAirDefenseDevices,
                    planes,
                    helicopters,
                    drones,
                    cruiseMissiles,
                    shipsOrBoats,
                    carsAndTankers,
                    specialEquipment,
                    personnel
            );
        }
    }
}
