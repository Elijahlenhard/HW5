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
            graph[i] = new Node(i+1==t);
            graph[i].index =i+1;
        }

        for (int i = 0; i < m; i++) {
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();
            graph[n1-1].addNeighbor(graph[n2-1]);
            graph[n2-1].addNeighbor(graph[n1-1]);
        }

        Queue queue = new Queue(n);

        queue.enqueue(graph[s-1]);
        graph[s-1].visited = true;

        int goalFoundAt = -1;
        int pathsFound =0;

        while(!queue.empty()){

            Node current = queue.dequeue();
            if(current.searchDepth > goalFoundAt-1 && goalFoundAt !=-1){
                break;
            }

            for (int i = 0; i < current.neighborCount; i++) {
                Node nextNeighbor = current.neighbors[i];

                if(nextNeighbor.isGoal){
                    goalFoundAt = current.searchDepth+1;
                    nextNeighbor.searchDepth = goalFoundAt;
                    pathsFound++;
                    continue;
                }

                if (!nextNeighbor.visited){
                    nextNeighbor.searchDepth = current.searchDepth+1;
                    queue.enqueue(nextNeighbor);
                    nextNeighbor.visited = true;
                }
            }

        }

        Stack stack = new Stack();
        stack.push(graph[s-1]);
        int paths =0;


        while(!stack.empty()){
            Node current = stack.pop();
            if(current.isGoal){
                paths++;
            }
            for (int i = 0; i < current.neighborCount; i++) {
                int nextSD = current.neighbors[i].searchDepth;
                if(nextSD<=goalFoundAt && nextSD > current.searchDepth){
                    stack.push(current.neighbors[i]);
                }
            }


        }


        System.out.println(paths);


    }

}

class Stack{
    Node[] arr;
    int size;


    public Stack(){
        arr = new Node[10];
    }

    public void push(Node n){
        if(arr.length == size){
            increaseCap();
        }
        arr[size] = n;
        size++;
    }
    public Node pop(){
        size--;
        return arr[size];
    }

    public boolean empty(){
        return size==0;
    }

    private void increaseCap(){
        Node[] newArr = new Node[arr.length*10];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
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
    int index;
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

    @Override
    public String toString(){
        return ""+index;
    }



    private void increaseCapacity(){

        Node[] newArr = new Node[neighbors.length*10];

        for (int i = 0; i < neighbors.length; i++) {
            newArr[i] = neighbors[i];
        }

        neighbors = newArr;

    }


}


