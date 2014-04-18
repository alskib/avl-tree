// Franklin Leung
package cosc5319.assignment4;

public class TreeNode {
    private TreeNode LLink, RLink;
    private boolean visited;
    private Widget widget;
    
    public TreeNode(Widget w) {
        this.widget = w;
        LLink = RLink = null;
        visited = false;
    }
    
    public void setAllAttr(TreeNode ll, TreeNode rl) {
        LLink = ll;
        RLink = rl;
    }
    
    public void setLeft(Widget w) {
        if (LLink == null)
            this.LLink = new TreeNode(w);
        else
            this.LLink.setWidget(w);
    }
    
    public void setRight(Widget w) {
        if (RLink == null)
            this.RLink = new TreeNode(w);
        else
            this.RLink.setWidget(w);
    }
    
    public void setWidget(Widget w) {
        this.widget = w;
    }
    
    // Left link get/set
    public void setLLink(TreeNode n) {
        this.LLink = n;
    }
    
    public TreeNode getLLink() {
        return LLink;
    }
    
    // Right link get/set   
    public void setRLink(TreeNode n) {
        this.RLink = n;
    }
    
    public TreeNode getRLink() {
        return RLink;
    }
    
    // Visited get/set
    public void setVisited(boolean v) {
        visited = v;
    }
    
    public boolean getVisited() {
        return visited;
    }
    
    public void visit() {
        System.out.println("[" + this.widget.getWidgetID() + 
                           "] \"" + this.widget.getDescription() +
                           "\" - $" + this.widget.getPrice());
    }
    
    public boolean isWidgetNull() {
        if (this.widget == null) {
            return true;
        } else {
            return false;
        }
    }
    
    public int getWidgetID() {
        return this.widget.getWidgetID();
    }
        
    public void setWidgetID(int id) {
        this.widget.setWidgetID(id);
    }
    
    public int getPrice() {
        return this.widget.getPrice();
    }

    public void setPrice(int pr) {
        this.widget.setPrice(pr);
    }

    public String getDescription() {
        return this.widget.getDescription();
    }

    public void setDescription(String desc) {
        this.widget.setDescription(desc);
    }
    
    private int height() {
        int leftHeight = (LLink == null) ? 0:LLink.height();
        int rightHeight = (RLink == null) ? 0:RLink.height();
        
        return 1 + Math.max(leftHeight, rightHeight);
    }
    
    
    public int getBalance() {
        int leftHeight = (LLink == null) ? 0:LLink.height();
        int rightHeight = (RLink == null) ? 0:RLink.height();
        
        return rightHeight - leftHeight;
    }
    
}
