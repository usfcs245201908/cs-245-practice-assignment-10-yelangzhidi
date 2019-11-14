import java.util.ArrayList;
import java.util.List;


public class GraphImplementation implements Graph {
    private int[][] graph;
    private int size;
    /**
     * Constructor
     * @param vertices number of vertices in the graph;
     */
    public GraphImplementation(int vertices){
        graph = new int[vertices][vertices];
        size = vertices;
    }

    /**
     * Add Edge to graph
     * @param v1 source vertex
     * @param v2 dest vertex
     * @throws Exception v1 or v2 out of bound
     */
    @Override
    public void addEdge(int v1, int v2) throws Exception {
        if (v1 > size||v2 > size||v1 < 0||v2 < 0)
            throw new Exception();
        graph[v1][v2] = 1;
    }

    /**
     * ToplogicalSort of the graph
     * @return List of Integer in the order of Toplogical Sort
     */
    @Override
    public List<Integer> topologicalSort() {
        List<Integer> schedule = new ArrayList<Integer>();
        int[] sum = new int[size];
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                sum[j] += graph[i][j];
        }
        for(int count = 0; count < size; count++) {
            int n = findZero(sum);
            if(n == -1){
            System.out.print("Ordering not possible, this is a cycle");
            System.exit(0);
        }
            schedule.add(n);
            sum[n] = -1;
            for(int i = 0; i < size; i++)
                sum[i] -= graph[n][i];
        }
        return schedule;
    }

    /**
     * find first value == 0 index in array
     * @param arr the target array
     * @return the first index which == 0 in array
     */
    private int findZero(int[] arr){
        for(int i = 0; i <arr.length; i++){
            if(arr[i] == 0)
                return i;
        }
        return -1;
    }

    /**
     * Give the neighbors of target vertex
     * @param vertex target vertex
     * @return a list of Integer of vertices which are neighbors of target vertex
     * @throws Exception v1 or v2 out of bound
     */
    @Override
    public List<Integer> neighbors(int vertex) throws Exception {
        if(vertex > size || vertex < 0)
            throw new Exception();
        List<Integer> result = new ArrayList<Integer>();
        for(int i = 0; i < size; i++){
            if(graph[vertex][i] == 1)
                result.add(i);
        }
        return result;
    }
}
