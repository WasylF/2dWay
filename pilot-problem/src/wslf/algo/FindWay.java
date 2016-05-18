package wslf.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeSet;
import javafx.util.Pair;
import wslf.geometry.Point;

/**
 *
 * @author Wsl_F
 */
public class FindWay {

    Point start;
    Point finish;
    Visibility visibility;

    public FindWay() {
    }

    public void setFinish(Point finish) {
        this.finish = finish;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public class PairComparator implements Comparator<Pair<Double, Integer>> {

        @Override
        public int compare(Pair<Double, Integer> p1, Pair<Double, Integer> p2) {
            if (p1 == p2) {
                return 0;
            }
            if (p1.getKey() < p2.getKey()) {
                return -1;
            }

            return 1;
        }

    }

    public LinkedList<Integer> findWay() {
        ArrayList<ArrayList<Pair<Integer, Double>>> visGraph = visibility.updateVisibilityGraph(start, finish);

        int n = visGraph.size();
        // swaped to simplify dijkstra come back part
        int startIndex = n - 1;
        int finishIndex = n - 2;

        double[] distance = new double[n];
        Arrays.fill(distance, Double.POSITIVE_INFINITY);
        distance[startIndex] = 0;
        int[] comeFrom = new int[n];
        comeFrom[startIndex] = -1;

        TreeSet<Pair<Double, Integer>> queue = new TreeSet<>(new PairComparator());
        queue.add(new Pair<>(0.0, startIndex));

        int i = 0;
        while (!queue.isEmpty()) {
            i++;
            int vertex = queue.first().getValue();
            queue.remove(queue.first());
            System.out.println(i + ") " + vertex);

            for (Pair<Integer, Double> adj : visGraph.get(vertex)) {
                int to = adj.getKey();
                double leng = adj.getValue();
                if (distance[vertex] + leng < distance[to]) {
                    queue.remove(new Pair<>(distance[to], to));
                    distance[to] = distance[vertex] + leng;
                    queue.add(new Pair<>(distance[to], to));
                    comeFrom[to] = vertex;
                }
            }
        }

        LinkedList<Integer> way = new LinkedList<>();
        int cur = finishIndex;
        while (cur != -1) {
            way.add(cur);
            cur = comeFrom[cur];
        }
        return way;
    }

}
