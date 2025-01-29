public class Element {
    private String charRepr;

    public Element(String charRepr) {
        this.charRepr = charRepr;
    }

    @Override
    public String toString() {
        return charRepr;
    }
}
