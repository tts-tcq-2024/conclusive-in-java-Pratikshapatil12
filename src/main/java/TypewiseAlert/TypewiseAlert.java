package TypewiseAlert;
// Enum definitions for BreachType and CoolingType
public enum BreachType {
    NORMAL,
    TOO_LOW,
    TOO_HIGH
}

public enum CoolingType {
    PASSIVE_COOLING,
    HI_ACTIVE_COOLING,
    MED_ACTIVE_COOLING
}

public class BatteryCharacter {
    public CoolingType coolingType;
    public String brand;
}

public class TypewiseAlert {
    private static final int PASSIVE_COOLING_LOW = 0;
    private static final int PASSIVE_COOLING_HIGH = 35;
    private static final int HI_ACTIVE_COOLING_LOW = 0;
    private static final int HI_ACTIVE_COOLING_HIGH = 45;
    private static final int MED_ACTIVE_COOLING_LOW = 0;
    private static final int MED_ACTIVE_COOLING_HIGH = 40;

    private static final Map<CoolingType, int[]> coolingLimits = Map.of(
        CoolingType.PASSIVE_COOLING, new int[]{PASSIVE_COOLING_LOW, PASSIVE_COOLING_HIGH},
        CoolingType.HI_ACTIVE_COOLING, new int[]{HI_ACTIVE_COOLING_LOW, HI_ACTIVE_COOLING_HIGH},
        CoolingType.MED_ACTIVE_COOLING, new int[]{MED_ACTIVE_COOLING_LOW, MED_ACTIVE_COOLING_HIGH}
    );

    public static BreachType inferBreach(double value, double lowerLimit, double upperLimit) {
        if (value < lowerLimit) {
            return BreachType.TOO_LOW;
        }
        if (value > upperLimit) {
            return BreachType.TOO_HIGH;
        }
        return BreachType.NORMAL;
    }

    public static BreachType classifyTemperatureBreach(CoolingType coolingType, double temperatureInC) {
        int[] limits = coolingLimits.getOrDefault(coolingType, new int[]{0, 0});
        return inferBreach(temperatureInC, limits[0], limits[1]);
    }

    public static void checkAndAlert(AlertTarget alertTarget, BatteryCharacter batteryChar, double temperatureInC) {
        BreachType breachType = classifyTemperatureBreach(batteryChar.coolingType, temperatureInC);
        switch (alertTarget) {
            case TO_CONTROLLER:
                sendToController(breachType);
                break;
            case TO_EMAIL:
                sendToEmail(breachType);
                break;
        }
    }

    private static void sendToController(BreachType breachType) {
        int header = 0xfeed;
        System.out.printf("%i : %s\n", header, breachType);
    }

    private static void sendToEmail(BreachType breachType) {
        String recipient = "a.b@c.com";
        switch (breachType) {
            case TOO_LOW:
                System.out.printf("To: %s\n", recipient);
                System.out.println("Hi, the temperature is too low\n");
                break;
            case TOO_HIGH:
                System.out.printf("To: %s\n", recipient);
                System.out.println("Hi, the temperature is too high\n");
                break;
            case NORMAL:
                // No action required for normal temperature
                break;
        }
    }
}
