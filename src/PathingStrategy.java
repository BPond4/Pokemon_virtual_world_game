
import java.util.function.Predicate;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.List;
import java.util.LinkedList;

interface PathingStrategy
{
   /*
    * Returns a prefix of a path from the start point to a point within reach
    * of the end point.  This path is only valid ("clear") when returned, but
    * may be invalidated by movement of other entities.
    *
    * The prefix includes neither the start point nor the end point.
    */
   List<Point> computePath(Point start, Point end,
      Predicate<Point> canPassThrough,
      BiPredicate<Point, Point> withinReach,
      Function<Point, Stream<Point>> potentialNeighbors);

   static final Function<Point, Stream<Point>> CARDINAL_NEIGHBORS =
      point ->
         Stream.<Point>builder()
            .add(new Point(point.x, point.y - 1))
            .add(new Point(point.x, point.y + 1))
            .add(new Point(point.x - 1, point.y))
            .add(new Point(point.x + 1, point.y))
            .build();
}
//
//   fairy fairy_13_10 13 10 51 51
//        fairy fairy_39_29 39 29 51 51
//        fairy fairy_0_0 0 0 51 51
//        fairy fairy_28_4 28 4 51 51