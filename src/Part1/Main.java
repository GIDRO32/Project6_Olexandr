package Part1;

import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args)
    {
        LinkedList<Integer> list = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new LinkedList<>();
        list.add(0, 6);
        list.add(1, 134);
        list.add(2, 6);
        list.add(3, 152);
        list.add(4, 32);
        list.add(5, 23);
        list.add(6,23);
        // Push elements onto the stack
        stack.push(1);
        stack.push(2);
        stack.push(3);
        // Enqueue elements into the queue
        queue.add(1);
        queue.add(2);
        queue.add(3);


        for (int value : list) {
                System.out.println(value);
        }
        System.out.println("Favorite number: " + list.get(4));
        // Pop elements from the stack and print them
        System.out.println("\nStack:");
        while (!stack.isEmpty()) {
            int item = stack.pop();
            System.out.println(item);
        }
        // Dequeue elements from the queue and print them
        System.out.println("\nQueue:");
        queue.remove();
        while (!queue.isEmpty()) {
            int item = queue.remove();
            System.out.println(item);
        }
    }
}
