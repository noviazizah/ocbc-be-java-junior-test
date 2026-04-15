final class MinYearsToSurpass {

    private MinYearsToSurpass() {
    }

    public static int minYearsToSurpass(double aliceInitial, double bobBonus,
                                        double aliceRate, double bobRate) {
        double alice = aliceInitial;
        double bob = bobBonus;

        if (bob > alice) {
            return 0;
        }
        if (bobRate <= aliceRate) {
            return -1;
        }

        int years = 0;
        while (bob <= alice) {
            alice *= 1 + aliceRate;
            bob *= 1 + bobRate;
            years++;
        }

        return years;
    }
}
