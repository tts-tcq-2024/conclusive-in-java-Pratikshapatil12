package TypewiseAlert;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TypewiseAlertTest {

    @Test
    public void testInferBreach() {
        assertEquals(BreachType.NORMAL, TypewiseAlert.inferBreach(30, 0, 35));
        assertEquals(BreachType.TOO_LOW, TypewiseAlert.inferBreach(-5, 0, 35));
        assertEquals(BreachType.TOO_HIGH, TypewiseAlert.inferBreach(50, 0, 35));
    }

    @Test
    public void testClassifyTemperatureBreach() {
        assertEquals(BreachType.NORMAL, TypewiseAlert.classifyTemperatureBreach(CoolingType.PASSIVE_COOLING, 30));
        assertEquals(BreachType.TOO_LOW, TypewiseAlert.classifyTemperatureBreach(CoolingType.PASSIVE_COOLING, -5));
        assertEquals(BreachType.TOO_HIGH, TypewiseAlert.classifyTemperatureBreach(CoolingType.PASSIVE_COOLING, 40));

        assertEquals(BreachType.NORMAL, TypewiseAlert.classifyTemperatureBreach(CoolingType.HI_ACTIVE_COOLING, 30));
        assertEquals(BreachType.TOO_LOW, TypewiseAlert.classifyTemperatureBreach(CoolingType.HI_ACTIVE_COOLING, -5));
        assertEquals(BreachType.TOO_HIGH, TypewiseAlert.classifyTemperatureBreach(CoolingType.HI_ACTIVE_COOLING, 50));
    }

    @Test
    public void testCheckAndAlertToController() {
        BatteryCharacter batteryChar = new BatteryCharacter();
        batteryChar.coolingType = CoolingType.PASSIVE_COOLING;

        TypewiseAlert.checkAndAlert(AlertTarget.TO_CONTROLLER, batteryChar, 30);
        // Validate the output as needed
    }

    @Test
    public void testCheckAndAlertToEmail() {
        BatteryCharacter batteryChar = new BatteryCharacter();
        batteryChar.coolingType = CoolingType.PASSIVE_COOLING;

        TypewiseAlert.checkAndAlert(AlertTarget.TO_EMAIL, batteryChar, 30);
        // Validate the output as needed
    }
}

