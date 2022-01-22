package org.example.priority;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Index {

    public static void main(String[] args) {

        String str = "aaabccd"; // abacacd

        String[] arr = str.split("");

        Queue<Item> queue = new PriorityQueue<>(Comparator.comparingLong(Item::getCount).reversed());

        Arrays.stream(arr).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().map(Item::new).forEach(queue::add);

        StringBuilder stringBuilder = new StringBuilder();

        while (!queue.isEmpty()) {

            Item item = queue.poll();
            stringBuilder.append(item);

            item.decrementPriority();

            if (!queue.isEmpty()) {
                Item next = queue.poll();

                stringBuilder.append(next);

                next.decrementPriority();

                if (next.count > 0) queue.add(next);
            }

            if (item.count > 0) queue.add(item);
        }

        System.out.println(stringBuilder);
    }
}

class Item {
    String character;
    long count;

    public Item(Map.Entry<String, Long> entry) {
        this.character = entry.getKey();
        this.count = entry.getValue();
    }

    public void decrementPriority() {
        this.count--;
    }

    @Override
    public String toString() {
        return this.character;
    }

    public long getCount() {
        return count;
    }
}
