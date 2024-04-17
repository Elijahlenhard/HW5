import java.util.Scanner;
/*
public class Prerequisites {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Node[] graph = new Node[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new Node();
            graph[i].index = i+1;
        }

        for (int i = 0; i < n; i++) {
            int next = sc.nextInt()-1;
            while(next!=-1){
                graph[i].addNeighbor(graph[next]);
                graph[next].addPrereqOf(graph[i]);
                next = sc.nextInt()-1;
            }
        }

        int totalLength = 0;

        //pick random
        Node start = graph[0];

        //Look forward
        int maxForward = 0;

        Stack stack = new Stack();
        stack.push(start);

        while(!stack.empty()){
            Node current = stack.pop();
            int currentMax = current.getMaxForward(stack);
            if(currentMax > maxForward){
                maxForward = currentMax;
            }
            for (int i = 0; i < current.prereqOfCount; i++) {
                if(!current.prereqOf[i].visited)
                    stack.push(current.prereqOf[i]);
            }

        }

        System.out.println(maxForward+1);

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
class Node {
    int index;
    int maxForward = -1;
    Node[] neighbors;
    Node[] prereqOf = new Node[10];//Out of bounds probably here
    int prereqOfCount;
    int neighborCount;
    boolean visited = false;

    int searchDepth = 0;

    boolean isGoal;

    public Node(){
        this.neighbors = new Node[10];
        this.neighborCount = 0;
    }

    public int getMaxForward(Stack toCheck){
        visited = true;
        int max = 0;
        if(this.maxForward!=-1){
            return this.maxForward;
        }

        for (int i = 0; i < neighborCount; i++) {
            int next = neighbors[i].getMaxForward(toCheck)+1;
            if(next>max){
                max=next;
            }

        }
        this.maxForward = max;
        for (int j = 0; j < prereqOfCount; j++) {
            if (!prereqOf[j].visited) {
                prereqOf[j].visited = true;
                toCheck.push(prereqOf[j]);
            }
        }
        return max;
    }

    public boolean isVisited(){
        return visited;
    }

    public void addPrereqOf(Node n){
        if(prereqOfCount == prereqOf.length){
            Node[] newArr = new Node[prereqOfCount*10];
            for (int i = 0; i < prereqOfCount; i++) {
                newArr[i] = prereqOf[i];
            }
            prereqOf=newArr;
        }
        prereqOf[prereqOfCount] = n;
        prereqOfCount++;
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

 */