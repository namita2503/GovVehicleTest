package selenium;

import org.openqa.selenium.By;

public class WebPage {
    private ElementByType type;
    private String selector;

    public static enum ElementByType {
        CLASS_NAME("className"), CSS_SELECTOR("cssSelector"), ID("id"), LINK_TEXT("linkText"), NAME(
                "name"), PARTIAL_LINK_TEXT("partialLinkText"), TAG_NAME("tagName"), XPATH("xpath");

        private String typeDesc;

        private ElementByType(String typeDesc) {
            this.typeDesc = typeDesc;
        }

        public String toString() {
            return this.typeDesc;
        }
    }

    public WebPage(ElementByType type, String selector, Object... parameters) {
        this.type = type;
        this.selector = String.format(selector, parameters);
    }

    public By getBy(Object... parameters) {
        By result = null;
        switch (this.type) {
            case CLASS_NAME:
                result = By.className(String.format(this.selector, parameters));
                break;
            case CSS_SELECTOR:
                result = By.cssSelector(String.format(this.selector, parameters));
                break;
            case ID:
                result = By.id(String.format(this.selector, parameters));
                break;
            case LINK_TEXT:
                result = By.linkText(String.format(this.selector, parameters));
                break;
            case NAME:
                result = By.name(String.format(this.selector, parameters));
                break;
            case PARTIAL_LINK_TEXT:
                result = By.partialLinkText(String.format(this.selector, parameters));
                break;
            case TAG_NAME:
                result = By.tagName(String.format(this.selector, parameters));
                break;
            case XPATH:
                result = By.xpath(String.format(this.selector, parameters));
        }
        return result;
    }
}
