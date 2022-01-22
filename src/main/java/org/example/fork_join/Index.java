package org.example.fork_join;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

public class Index {

    public static void main(String[] args) {

        Queue<Integer> queue = new LinkedBlockingQueue<>(7);
        queue.add(3);
        queue.add(2);
        queue.add(5);
        queue.add(10);
        queue.add(67);
        queue.add(1);
        queue.add(0);

        CustomRecursiveAction<Integer> customRecursiveAction = new CustomRecursiveAction<>(queue);

        ForkJoinPool pool = new ForkJoinPool(3);
        pool.invoke(customRecursiveAction);

        queue.add(3);
        queue.add(2);
        queue.add(5);
        queue.add(10);
        queue.add(67);
        queue.add(1);
        queue.add(0);

        CustomRecursiveTask customRecursiveTask = new CustomRecursiveTask(queue);
        System.out.println("Sum - " + pool.invoke(customRecursiveTask));

        pool.shutdown();
    }
}

class CustomRecursiveTask extends RecursiveTask<Integer> {

    Queue<Integer> queue;

    public CustomRecursiveTask(Queue<Integer> list) {
        this.queue = list;
    }

    @Override
    protected Integer compute() {

        if (queue.size() > 1) {
            return ForkJoinTask.invokeAll(createSubTasks()).stream().mapToInt(ForkJoinTask::join).sum();
        } else {
            return queue.poll();
        }
    }

    private List<CustomRecursiveTask> createSubTasks() {

        List<CustomRecursiveTask> subtasks = new ArrayList<>();

        while (!queue.isEmpty()) {

            Queue<Integer> subQueue = new LinkedBlockingQueue<>(2);
            subQueue.add(queue.poll());

            subtasks.add(new CustomRecursiveTask(subQueue));
        }

        return subtasks;
    }
}

class CustomRecursiveAction<T> extends RecursiveAction {

    Queue<T> queue;

    public CustomRecursiveAction(Queue<T> list) {
        this.queue = list;
    }

    @Override
    protected void compute() {

        if (queue.size() > 1) {
            ForkJoinTask.invokeAll(createSubActions());
        } else {
            T t = queue.poll();

            System.out.println(t);
        }
    }

    private List<CustomRecursiveAction<T>> createSubActions() {

        List<CustomRecursiveAction<T>> subtasks = new ArrayList<>();

        while (!queue.isEmpty()) {

            Queue<T> subQueue = new LinkedBlockingQueue<>(2);
            subQueue.add(queue.poll());

            subtasks.add(new CustomRecursiveAction<T>(subQueue));
        }

        return subtasks;
    }
}