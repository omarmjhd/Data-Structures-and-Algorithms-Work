import java.util.Collection;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;

public class MST {

    /**
     * Using disjoint set(s), run Kruskal's algorithm on the given graph and
     * return the MST. Return null if no MST exists for the graph.
     *
     * @param g The graph to be processed. Will never be null.
     * @return The MST of the graph; null if no valid MST exists.
     */
    public static Collection<Edge> kruskals(Graph g) {

        ArrayList<Edge> mst = new ArrayList<Edge>();
        DisjointSets<Vertex> disjointSets =
                new DisjointSets<Vertex>(g.getVertices());
        PriorityQueue<Edge> edges = new PriorityQueue<Edge>();

        for (Edge edge : g.getEdgeList()) {

            edges.add(edge);

        }

        while (!edges.isEmpty() && (mst.size() < g.getVertices().size() - 1)) {

            Edge e = edges.poll();
            Vertex u = e.getU();
            Vertex v = e.getV();

            if (!disjointSets.sameSet(u, v)) {

                disjointSets.merge(u, v);
                mst.add(e);

            }

            if (g.getVertices().size() - 1 == mst.size()) {

                return mst;

            }

        }

        return null;

    }

    /**
     * Run Prim's algorithm on the given graph and return the minimum spanning
     * tree. If no MST exists, return null.
     *
     * @param g The graph to be processed. Will never be null.
     * @param start The ID of the start node. Will always exist in the graph.
     * @return the MST of the graph; null if no valid MST exists.
     */
    public static Collection<Edge> prims(Graph g, int start) {

        Set<Vertex> cloud = new HashSet<Vertex>();
        Collection<Edge> edges = new HashSet<Edge>();

        Vertex initialNode = null;

        for (Vertex v : g.getVertices()) {

            if (v.getId() == start) {

                initialNode = v;

            }

        }

        cloud.add(initialNode);

        while (cloud.size() < g.getVertices().size()) {

            int minWeight = Integer.MAX_VALUE;
            Edge minEdge = null;

            for (Edge e : g.getEdgeList()) {

                if ((cloud.contains(e.getU())
                        && !cloud.contains(e.getV()))
                        || (cloud.contains(e.getV())
                        && !cloud.contains(e.getU()))) {

                    if (e.getWeight() < minWeight) {

                        minEdge = e;
                        minWeight = e.getWeight();

                    }

                }

            }

            if (minEdge == null) {

                return null;

            }

            cloud.add(minEdge.getV());
            cloud.add(minEdge.getU());
            edges.add(minEdge);

        }

        return edges;

    }

}
