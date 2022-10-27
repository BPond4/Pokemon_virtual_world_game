final class Node {
    private Node prior;
    private Point location;
    private int f;
    private int h;
    private int g;
    public Node(Node prior, Point location, int g, int h, int f){
        this.prior = prior;
        this.location = location;
        this.f = f;
        this.h = h;
        this.g = g;
    }
    public int getF(){
        return this.f;
    }
    public Point getLocation(){
        return this.location;
    }
    public int getG(){
        return this.g;
    }
    public Node getPrior(){
        return this.prior;
    }
}
