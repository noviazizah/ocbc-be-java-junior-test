final class MinYearsToSurpass {

    private MinYearsToSurpass() {
    }

    public static int minYearsToSurpass(double firstValue, double firstGrowthRate,
                                        double secondValue, double secondGrowthRate) {
        if (firstValue > secondValue) {
            return 0;
        }

        if (firstGrowthRate <= secondGrowthRate) {
            return -1;
        }

        int years = 0;
        double currentFirst = firstValue;
        double currentSecond = secondValue;

        while (currentFirst <= currentSecond) {
            currentFirst += currentFirst * firstGrowthRate;
            currentSecond += currentSecond * secondGrowthRate;
            years++;
        }

        return years;
    }
}
