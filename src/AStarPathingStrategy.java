import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class AStarPathingStrategy implements PathingStrategy
{
    public List<Point> computePath(Point start, Point end,
                                   Predicate<Point> canPassThrough,
                                   BiPredicate<Point, Point> withinReach,
                                   Function<Point, Stream<Point>> potentialNeighbors)
    {
        PriorityQueue<Node> openPriority = new PriorityQueue<>(Comparator.comparingInt(Node::getF));
        HashMap<Point, Node> openHash = new HashMap<>();
        HashMap<Point, Node> closedHash = new HashMap<>();
        Node current = null;
        LinkedList<Point> path = new LinkedList<>();
        int g, h, f;

        g = 0;
        h = Math.abs(start.x - end.x) + Math.abs(start.y - end.y);
        f= g+h;
        current = new Node(null, start, g, h, f);
        openPriority.add(current);
        openHash.put(current.getLocation(), current);
// 2
        while(!withinReach.test(current.getLocation(), end) && openPriority.size() != 0){
            current = openPriority.peek();

            List<Point> validNeighbors = potentialNeighbors.apply(current.getLocation()).filter(canPassThrough).filter(pt -> !closedHash.containsKey(pt)).toList();
            //3
            for (Point pt: validNeighbors) {
                //a.
                g = current.getG() + (Math.abs(pt.x - current.getLocation().x) + Math.abs(pt.y - current.getLocation().y));
                //b.
                if(openHash.containsKey(pt)){
                    if(g >= openHash.get(pt).getG()){
                        continue;
                    }
                }
                //c.
                h = Math.abs(pt.x - end.x) + Math.abs(pt.y - end.y);
                //d.
                f = g+h;
                //e.

                //f.
                Node newNode = new Node(current, pt, g, h, f);
                if(openHash.containsKey(pt)){
                    Node temp = openHash.get(pt);
                    openHash.remove(pt);
                    openPriority.remove(temp);
                }

                openHash.put(pt, newNode);
                openPriority.add(newNode);
            }
            closedHash.put(current.getLocation(), current);
            openPriority.remove(current);
            openHash.remove(current.getLocation(), current);
        }
        if(openPriority.size() == 0 && !closedHash.isEmpty()) {
            return path;
        }
        if(current.getLocation() != start){
            while(current.getPrior().getLocation() != start){
                path.addFirst(current.getLocation());
                current = current.getPrior();
            }
        }

        path.addFirst(current.getLocation());
        return path;
    }
}
