import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.*;

public class AStarPathingStrategy implements PathingStrategy {

    public class Node {

        public Point pos;
        public int g;
        public int h;
        public Node prev;

        public Node(Point pos, int g, int h, Node prev) {
            this.pos = pos;
            this.g = g;
            this.h = h;
            this.prev = prev;
        }

        public int getF() {
            return g + h;
        }

    }

    public List<Point> computePath(Point start, Point end,
                                   Predicate<Point> canPassThrough,
                                   BiPredicate<Point, Point> withinReach,
                                   Function<Point, Stream<Point>> potentialNeighbors) {

        List<Point> path = new LinkedList<>();
        Comparator<Node> comp = Comparator.comparing(Node::getF);

        PriorityQueue<Node> openList = new PriorityQueue<Node>(comp);
        HashMap<Point, Node> closedList = new HashMap<Point, Node>();
        HashMap<Point, Node> hash = new HashMap<>();

        Node current = new Node(start, 0, Math.abs(start.x - end.x) + Math.abs(end.y - end.y), null);
        openList.add(current);
        hash.put(current.pos, current);

        while (!withinReach.test(current.pos, end)) {
            Node current2 = current;
            List<Node> neighborNodes = potentialNeighbors.apply(current2.pos)
                    .filter(canPassThrough)
                    .map(p -> new Node(p, current2.g + 1,
                            Math.abs(p.x - current2.pos.x) + Math.abs(p.y - current2.pos.y), current2))
                    .collect(Collectors.toList());
            for (Node n : neighborNodes) {
                if (!closedList.containsKey(n.pos)) {
                    if (!hash.containsKey(n.pos)) {
                        openList.add(n);
                        hash.put(n.pos, n); }
                    for (Node node : openList) {
                        if (n.pos.equals(node.pos)) {
                            if (n.g > node.g) {
                                node.g = n.g;
                                node.prev = current2;
                            }
                        }
                    }
                }
            }
            openList.remove(current2);
            closedList.put(current2.pos, current2);
            if (openList.size() == 0) {
                return path;
            }
            current = openList.peek();
        }

        while (current.prev != null) {
            path.add(0, current.pos);
            current = current.prev;
        }
        return path;
    }

}