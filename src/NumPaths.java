import java.util.Scanner;

public class NumPaths {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);


        int n = sc.nextInt();
        int m = sc.nextInt();

        int s = sc.nextInt();
        int t = sc.nextInt();

        Node[] graph = new Node[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new Node(i==t);
        }

        for (int i = 0; i < m; i++) {
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();
            graph[n1-1].addNeighbor(graph[n2-1]);
            graph[n2-1].addNeighbor(graph[n1-1]);
        }

        Queue queue = new Queue(n);

        queue.enqueue(graph[s]);

        int goalFoundAt = -1;

        while(!queue.empty()){

            Node current = queue.dequeue();
            if(current.searchDepth > goalFoundAt && goalFoundAt !=-1){
                break;
            }

            for (int i = 0; i < current.neighborCount; i++) {
                Node nextNeighbor = current.neighbors[i];

                if(nextNeighbor.isGoal){
                    goalFoundAt = nextNeighbor.searchDepth;
                }

                if (!nextNeighbor.visited){
                    nextNeighbor.searchDepth = current.searchDepth+1;
                    queue.enqueue(nextNeighbor);
                }
            }

        }


    }

}
class Queue{

    Node[] arr;
    int enqueueIndex=0;
    int dequeueIndex=0;

    public Queue(int n){
        arr = new Node[n];
    }

    public void enqueue(Node n){
        arr[enqueueIndex] = n;
        enqueueIndex++;
    }

    public boolean empty(){
        return dequeueIndex == enqueueIndex;
    }

    public Node dequeue(){

        if(empty()){
            return null;
        }
        Node ret = arr[dequeueIndex];
        dequeueIndex++;
        return ret;
    }



}


class Node {
    Node[] neighbors;
    int neighborCount;
    boolean visited;

    int searchDepth = 0;

    boolean isGoal;

    public Node(boolean isGoal){
        this.neighbors = new Node[10];
        this.neighborCount = 0;
        this.isGoal = isGoal;
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
