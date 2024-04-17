import java.util.Scanner;

public class ConnectGraph {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        int n = sc.nextInt();
        int m = sc.nextInt();
        Node[] graph = new Node[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new Node();
        }


        for (int i = 0; i < m; i++) {
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();
            graph[n1-1].addNeighbor(graph[n2-1]);
            graph[n2-1].addNeighbor(graph[n1-1]);
        }

        int sectors = 0;

        for (int i = 0; i < n; i++) {
            if(!graph[i].visited){
                graph[i].visitAll();
                sectors++;
            }
        }

        System.out.println(sectors-1);




    }


}


class Node {
    Node[] neighbors;
    int neighborCount;
    boolean visited;

    public Node(){
        this.neighbors = new Node[10];
        this.neighborCount = 0;
    }

    public boolean isVisited(){
        return visited;
    }

    public void addNeighbor(Node n){
        if(neighborCount == neighbors.length){
            increaseCapacity();
        }
        neighbors[neighborCount] = n;
        neighborCount++;
    }

    public void visitAll(){
        visited = true;
        for (int i = 0; i < neighborCount; i++) {
            if(!neighbors[i].isVisited()){
                neighbors[i].visitAll();
            }
        }
    }




    private void increaseCapacity(){

        Node[] newArr = new Node[neighbors.length*10];

        for (int i = 0; i < neighbors.length; i++) {
            newArr[i] = neighbors[i];
        }

        neighbors = newArr;

    }


}






