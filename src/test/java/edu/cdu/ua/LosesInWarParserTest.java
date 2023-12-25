package edu.cdu.ua;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LosesInWarParserTest {
    /**
     * As of date is 25 December 2023
     */
    private static final String STATISTIC_INPUT = """
        Танки — 5877
        ББМ — 10919
        Гармати — 8347
        РСЗВ — 934
        Засоби ППО — 613
        Літаки — 329
        Гелікоптери — 324
        БПЛА — 6436
        Крилаті ракети — 1616
        Кораблі (катери) — 22
        Автомобілі та автоцистерни — 11075
        Спеціальна техніка — 1234
        Особовий склад — близько 353950 осіб
        """;

    private LosesInWarParser losesInWarParser;

    @BeforeEach
    void setUp() {
        losesInWarParser = new LosesInWarParser();
    }

    @Test
    void failed_parse_null_input() {
        assertThrows(
                NullPointerException.class,
                () -> losesInWarParser.parseLosesStatistic(null)
        );
    }

    @Test
    void failed_parse_empty_input() {
        assertThrows(
                IllegalArgumentException.class,
                () -> losesInWarParser.parseLosesStatistic("")
        );
    }

    @Test
    void parse_input_without_any_statistic_information() {
        LosesStatistic statistic = losesInWarParser
                .parseLosesStatistic("some long text without statistic data");

        assertEquals(LosesStatistic.empty(), statistic);
    }

    @Test
    void parse_input_without_values() {
        var statistic = losesInWarParser.parseLosesStatistic(
                """
                    Танки —
                    ББМ —
                    Гармати —
                    РСЗВ —
                    Засоби ППО —
                    Літаки —
                    Гелікоптери —
                    БПЛА —
                    Крилаті ракети —
                    Кораблі (катери) —
                    Автомобілі та автоцистерни —
                    Спеціальна техніка —
                    Особовий склад —
                    """
        );

        var expectedStatistic = LosesStatistic.newStatistic()
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

        assertEquals(expectedStatistic, statistic);
    }

    @Test
    void parse_input_with_zero_values() {
        var statistic = losesInWarParser.parseLosesStatistic(
                """
                    Танки — 0
                    ББМ — 0
                    Гармати — 0
                    РСЗВ — 0
                    Засоби ППО — 0
                    Літаки — 0
                    Гелікоптери — 0
                    БПЛА — 0
                    Крилаті ракети — 0
                    Кораблі (катери) — 0
                    Автомобілі та автоцистерни — 0
                    Спеціальна техніка — 0
                    Особовий склад — близько 0 осіб
                    """
        );

        var expectedStatistic = LosesStatistic.newStatistic()
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

        assertEquals(expectedStatistic, statistic);
    }

    @Test
    void parse_input_with_statistic_values() {
        var statistic = losesInWarParser.parseLosesStatistic(STATISTIC_INPUT);

        var expectedStatistic = LosesStatistic.newStatistic()
                .withTanks(5877)
                .withArmouredFightingVehicles(10919)
                .withCannons(8347)
                .withMultipleRocketLaunchers(934)
                .withAntiAirDefenseDevices(613)
                .withPlanes(329)
                .withHelicopters(324)
                .withDrones(6436)
                .withCruiseMissiles(1616)
                .withShipsOrBoats(22)
                .withCarsAndTankers(11075)
                .withSpecialEquipment(1234)
                .withPersonnel(353950)
                .build();

        assertEquals(expectedStatistic, statistic);
    }

    @Test
    void print_version_is_implemented_as_expected() {
        var statistic = losesInWarParser.parseLosesStatistic(STATISTIC_INPUT);
        var printVersion = statistic.asPrintVersion();

        var expectedPrintVersion = "Втрати російської армії в Україні: Танки - 5877, Бойові броньовані машини (різних типів) - 10919, Гармати - 8347, Реактивні системи залпового вогню - 934, Засоби ППО - 613, Літаки - 329, Гелікоптери - 324, Безпілотні літальні апарати (оперативно-тактичного рівня) - 6436, Крилаті ракети - 1616, Кораблі (катери) - 22, Автомобілі та автоцистерни - 11075, Спеціальна техніка - 1234, Особовий склад - близько 353950 осіб";

        assertEquals(expectedPrintVersion, printVersion);
    }

    @Test
    void pint_version_is_not_uses_toString_implementation_as_a_reference() {
        var statistic = losesInWarParser.parseLosesStatistic(STATISTIC_INPUT);
        var printVersion = statistic.asPrintVersion();

        assertNotEquals(printVersion, statistic.toString());
    }

    @Test
    void check_that_toString_is_implemented_for_debug_purpouses() {
        var statistic = losesInWarParser.parseLosesStatistic(STATISTIC_INPUT);
        String toStringResult = statistic.toString();

        assertFalse(toStringResult.contains("edu.geekhub.homework"));
        assertFalse(toStringResult.contains("@"));
    }

    @Test
    void parse_input_with_statistic_values_that_contains_new_entries_data() {
        var december25Input = """
            Танки — 5877 (+19)
            ББМ — 10919 (+31)
            Гармати — 8347 (+33)
            РСЗВ — 934 (+2)
            Засоби ППО — 613 (+2)
            Літаки — 329 (+2)
            Гелікоптери — 324 (+0)
            БПЛА — 6436 (+32)
            Крилаті ракети — 1616 (+2)
            Кораблі (катери) — 22 (+0)
            Автомобілі та автоцистерни — 11075 (+53)
            Спеціальна техніка — 1234 (+5)
            Особовий склад — близько 353950 осіб (+760)
            """;

        var statistic = losesInWarParser.parseLosesStatistic(december25Input);

        var expectedStatistic = LosesStatistic.newStatistic()
                .withTanks(5877)
                .withArmouredFightingVehicles(10919)
                .withCannons(8347)
                .withMultipleRocketLaunchers(934)
                .withAntiAirDefenseDevices(613)
                .withPlanes(329)
                .withHelicopters(324)
                .withDrones(6436)
                .withCruiseMissiles(1616)
                .withShipsOrBoats(22)
                .withCarsAndTankers(11075)
                .withSpecialEquipment(1234)
                .withPersonnel(353950)
                .build();

        assertEquals(expectedStatistic, statistic);
    }

    @Test
    void parse_raw_data_input() {
        var november14Input = """
            Taнки — 5877 (+19)
            ББM — 10919 (+31)
            Гapмaти — 8347 (+33)
            PC3B — 934 (+2)
            3acoби ПП0 — 613 (+2)
            Лiтaки — 329 (+2)
            Гeлiкoптepи — 324 (+0)
            БПЛA — 6436 (+32)
            Kpилaтi paкeти — 1616 (+2)
            Koрaблi (кaтepи) — 22 (+0)
            Aвтoмoбiлi тa aвтoциcтepни — 11075 (+53)
            Cпeцiaльнa тeхнiкa — 1234 (+5)
            Ocoбoвий cклaд — близькo 353950 oсiб (+760)
            """;

        var statistic = losesInWarParser.parseLosesStatistic(november14Input);

        var expectedStatistic = LosesStatistic.newStatistic()
                .withTanks(5877)
                .withArmouredFightingVehicles(10919)
                .withCannons(8347)
                .withMultipleRocketLaunchers(934)
                .withAntiAirDefenseDevices(613)
                .withPlanes(329)
                .withHelicopters(324)
                .withDrones(6436)
                .withCruiseMissiles(1616)
                .withShipsOrBoats(22)
                .withCarsAndTankers(11075)
                .withSpecialEquipment(1234)
                .withPersonnel(353950)
                .build();

        assertEquals(expectedStatistic, statistic);
    }

    @Test
    @DisplayName("parse_HTML_input")
    void parse_HTML_input() {
        var november14Input = """
                <div class="casualties">
                    <div>
                        <ul>
                            <li>Танки&nbsp;— 2848 <small>(+8)</small></li>
                            <li><abbr class="idx-help" title="Бойові броньовані машини (різних типів)">ББМ</abbr>&nbsp;— 5748 <small>(+6)</small>
                            </li>
                            <li>Гармати&nbsp;— 1839 <small>(+2)</small></li>
                            <li><abbr class="idx-help" title="Реактивні системи залпового вогню">РСЗВ</abbr>&nbsp;— 393</li>
                            <li>Засоби ППО&nbsp;— 206</li>
                            <li>Літаки&nbsp;— 278</li>
                            <li>Гелікоптери&nbsp;— 261</li>
                            <li><abbr class="idx-help" title="Безпілотні літальні апарати (оперативно-тактичного рівня)">БПЛА</abbr>&nbsp;—
                                1509 <small>(+2)</small></li>
                            <li>Крилаті <span class="idx-help" title="збито">ракети</span>&nbsp;— 399</li>
                            <li>Кораблі (катери)&nbsp;— 16</li>
                            <li>Автомобілі та автоцистерни&nbsp;— 4316 <small>(+21)</small></li>
                            <li>Спеціальна техніка&nbsp;— 160</li>
                            <li>Особовий склад&nbsp;— близько 81370 <span class="idx-help" title="ліквідовано">осіб</span>
                                <small>(+510)</small></li>
                        </ul>
                    </div>
                </div>
            """;

        var statistic = losesInWarParser.parseLosesStatistic(november14Input);

        var expectedStatistic = LosesStatistic.newStatistic()
                .withTanks(2848)
                .withArmouredFightingVehicles(5748)
                .withCannons(1839)
                .withMultipleRocketLaunchers(393)
                .withAntiAirDefenseDevices(206)
                .withPlanes(278)
                .withHelicopters(261)
                .withDrones(1509)
                .withCruiseMissiles(399)
                .withShipsOrBoats(16)
                .withCarsAndTankers(4316)
                .withSpecialEquipment(160)
                .withPersonnel(81370)
                .build();

        assertEquals(expectedStatistic, statistic);
    }
}
