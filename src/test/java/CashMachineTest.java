import static org.junit.jupiter.api.Assertions.assertEquals;

import com.andycodereview.core.CashMachineUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CashMachineTest {

    CashMachineUtils utils;

    @BeforeEach
    void setUp(){
        utils = new CashMachineUtils();
    }

    @Test
    void testCalculate(){

        Integer enterCashAmount;
        List<Integer> enterBanknotes;

        // Test 1
        enterCashAmount = 1234;
        enterBanknotes = new ArrayList<>();

        enterBanknotes.add(1);
        enterBanknotes.add(2);
        enterBanknotes.add(10);
        enterBanknotes.add(100);

        assertEquals(enterCashAmount, getSum(utils.calculate(enterCashAmount, enterBanknotes)),
                "Calculate: Test 1");

        // Test 2
        enterCashAmount = 1234;
        enterBanknotes = new ArrayList<>();

        enterBanknotes.add(10);
        enterBanknotes.add(100);

        assertEquals(0, getSum(utils.calculate(enterCashAmount, enterBanknotes)),
                "Calculate: Test 2");

        // Test 3
        enterCashAmount = 0;
        enterBanknotes = new ArrayList<>();

        enterBanknotes.add(10);
        enterBanknotes.add(100);

        assertEquals(0, getSum(utils.calculate(enterCashAmount, enterBanknotes)),
                "Calculate: Test 3");

    }

    @Test
    void testGetBanknotesFromString(){

        String enterUserInput;
        List<Integer> expectedList;

        // Test 1
        enterUserInput = "1,2,10";
        expectedList = new ArrayList<>();

        expectedList.add(1);
        expectedList.add(2);
        expectedList.add(10);

        assertEquals(expectedList, utils.getBanknotesFromString(enterUserInput),
                "GetBanknotesFromString: Test 1");

        // Test 2
        enterUserInput = "";
        expectedList = new ArrayList<>();

        assertEquals(expectedList, utils.getBanknotesFromString(enterUserInput),
                "GetBanknotesFromString: Test 2");

        // Test 3
        enterUserInput = "1,3,4";
        expectedList = new ArrayList<>();

        expectedList.add(1);

        assertEquals(expectedList, utils.getBanknotesFromString(enterUserInput),
                "GetBanknotesFromString: Test 3");

        // Test 4
        enterUserInput = "1 2";
        expectedList = new ArrayList<>();

        assertEquals(expectedList, utils.getBanknotesFromString(enterUserInput),
                "GetBanknotesFromString: Test 4");

    }

    @Test
    void testGetTrueDeclension(){

        int count;
        String expectedDeclension;

        // Test 1
        count = 11;
        expectedDeclension = "купюр";

        assertEquals(expectedDeclension, utils.getTrueDeclension(count),
                "GetTrueDeclension: Test 1");

        // Test 2
        count = 1021;
        expectedDeclension = "купюра";

        assertEquals(expectedDeclension, utils.getTrueDeclension(count),
                "GetTrueDeclension: Test 2");

        // Test 3
        count = 0;
        expectedDeclension = "купюр";

        assertEquals(expectedDeclension, utils.getTrueDeclension(count),
                "GetTrueDeclension: Test 3");

        // Test 4
        count = 12234;
        expectedDeclension = "купюры";

        assertEquals(expectedDeclension, utils.getTrueDeclension(count),
                "GetTrueDeclension: Test 4");

    }

    private int getSum(Map<Integer, Integer> ans){

        int sum = 0;

        for (Integer banknote : ans.keySet())
            sum += (banknote * ans.get(banknote));

        return sum;
    }

}
