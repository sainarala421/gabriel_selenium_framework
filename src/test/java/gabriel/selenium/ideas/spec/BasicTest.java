package gabriel.selenium.ideas.spec;

import gabriel.selenium.ideas.pageObjects.aut.basic.RequiredElementTimeoutException;
import gabriel.selenium.ideas.pageObjects.aut.concrete.OnetMainPage;
import gabriel.selenium.ideas.utilities.Utilities;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class BasicTest extends BasicAbstractSpec {
    private static OnetMainPage onetMainPage = new OnetMainPage();

    /**
     * If you use Factory this will not throw StaleException but if you do it old selenium way
     * it will. That is beacuse FActory is lazy and on each use it will do find.
     * To simulate stale exception. You need to initiate this element with find old-way
     * and then duplicate element and remoce originbal and voilaa
     * that is why mr_checker relies on selectors and not web elements I guess
     * @throws Exception
     */
    @Test
    public void staleElementExceptionTest() throws Exception {

        onetMainPage.visit();
        try {
            onetMainPage.waitForPopUpToLoad();
            if (onetMainPage.hasPopUp()) {
                System.out.println("Time to modify DOM now. You have 30s.");
                System.out.println("1. Open console 2. Find element. 3. Create a copy. 4. Remove original. 5. You will run into " +
                        "stale exception if @Cache is on, otherwise it will work because factory" +
                        "is doing a find on each use. Check with @Cache and without or w/o find on old selenium ");
                Thread.sleep(40000);
                System.out.println("This click should work with Facttory but should throw Stale" +
                        "element excetion if it is cached.");
                onetMainPage.acceptPopUp();
            }
        } catch (RequiredElementTimeoutException e) {
            ; //is one off
        }
        if (onetMainPage.isLoaded()) {
            System.out.println("is loaded");
        } else {
            System.out.println("is not loaded");
        }
        onetMainPage.clickOnetLogo();
        onetMainPage.getDriver().switchTo().defaultContent(); //another popUp
        assert onetMainPage.isAtOnetPages() == true;

    }
    //@Test
    public void withPopUpAttachedToPageTest() throws Exception {

        onetMainPage.visit();
        try {
            onetMainPage.waitForPopUpToLoad();
            if (onetMainPage.hasPopUp()) {
                Thread.sleep(30000);

                onetMainPage.acceptPopUp();
            }
        } catch (RequiredElementTimeoutException e) {
            ; //is one off
        }
        if (onetMainPage.isLoaded()) {
            System.out.println("is loaded");
        } else {
            System.out.println("is not loaded");
        }

        onetMainPage.clickOnetLogo();

        onetMainPage.getDriver().switchTo().defaultContent(); //another popUp
        assert onetMainPage.isAtOnetPages() == true;

    }
}