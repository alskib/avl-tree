// Franklin Leung

package cosc5319.assignment4;

public class Widget {
    private int id;
    private String description;
    private int price;
    
    public Widget (int WidgetID, String desc, int pr){
        this.id = WidgetID;
        this.description = desc;
        this.price = pr;
    }
    
    public int getWidgetID() {
        return this.id;
    }
    
    public void setWidgetID(int id) {
        this.id = id;
    }
        
    public int getPrice() {
        return this.price;
    }

    public void setPrice(int pr) {
        this.price = pr;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }
}
