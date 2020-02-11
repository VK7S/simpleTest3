import org.testng.Reporter;

public class MainPage {



    public MainPage() {
        Element face = new Element("//div[@id='surface']");
        face.waitToAppear();
    }



    public boolean isBoardPresent(String board){
        System.out.println("Click on \"" + board + "\"");
        Element boardXpath = new Element(
                "//h3[text()='Personal Boards']/ancestor::div[@class='boards-page-board-section mod-no-sidebar']//div[@title='%s']",
                board);
        return boardXpath.isPresent();

    }
}
