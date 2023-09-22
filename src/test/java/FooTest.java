import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.Foo;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class FooTest {

    List<String> testData = Foo.getDataAsList(ConstantsTest.TESTPATH_FOR_MODULE_STATS);
    String testRow = "modulX,1000";

    @DisplayName("Check if data for tests exists:")
    @BeforeEach
    void init() {
        assertThat(testData).isNotEmpty();
        assertThat(new File(ConstantsTest.TESTPATH_FOR_MODULE_STATS)).exists();
    }

    @DisplayName("Check if data in file is in format 'x,y':")
    @Test
    void test_if_data_is_in_correct_convention() {
        List<String> l = Foo.getDataAsList(ConstantsTest.TESTPATH_FOR_MODULE_STATS);
        String element = l.get(0);
        String[] splittedElement = element.split(",");

        assertThat(splittedElement[0]).isEqualTo("modul1");
        assertThat(splittedElement[1]).matches(e -> Integer.parseInt(e) == 9);
    }

    @DisplayName("Check if module was successfully added to to file:")
    @Test
    void test_if_modul_added() {
        int n = testData.size();
        String testAdd = "modulX,0";
        Foo.addModuleToFile(testAdd, ConstantsTest.TESTPATH_FOR_MODULE_STATS);
        List<String> l2 = Foo.getDataAsList(ConstantsTest.TESTPATH_FOR_MODULE_STATS);
        int m = l2.size();
        assertThat(m).isGreaterThan(n);
    }

    @DisplayName("Check if method return correct modul names:")
    @Test
    void test_getModuleNames() {
        String[] testArray = Foo.getModuleNames(testData);
        assertThat(testArray[0]).isEqualTo("modul1");
        assertThat(testArray.length).isEqualTo(testData.size());
    }

    @DisplayName("Check if desired modul gets returned")
    @Test
    void find_row_in_list() {
        String modulToFind = "modul4";
        String whatWasFound = Foo.getRowOfModule(testData, modulToFind);
        assertThat(whatWasFound).isEqualTo("modul4,9");
    }

    @DisplayName("Check if desired modul is not in list")
    @Test
    void find_nothing_in_list() {
        String modulToFind = "modul10";
        String whatWasFound = Foo.getRowOfModule(testData, modulToFind);
        assertThat(whatWasFound).isEqualTo(null);
    }

    @DisplayName("Check if row is updated correctly")
    @Test
    void test_if_row_is_updated() {
        int time = 2000;
        String updatedString = Foo.updateRowData(testRow, time);
        String[] tempSplit = updatedString.split(",");
        int updatedTime = Integer.parseInt(tempSplit[1]);
        assertThat(updatedTime).isEqualTo(3000);
    }

}
