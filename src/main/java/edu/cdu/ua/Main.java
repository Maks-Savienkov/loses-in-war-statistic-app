package edu.cdu.ua;

public class Main {
    public static final String TODAY_LOSES = """
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

    public static void main(String[] args) {
        var losesInWarParser = new LosesInWarParser();
        var losesStatistic = losesInWarParser.parseLosesStatistic(TODAY_LOSES);

        System.out.println(losesStatistic.asPrintVersion());
    }
}