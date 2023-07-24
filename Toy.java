import java.util.PriorityQueue;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class Toy {
    private int id;
    private String name;
    private int frequency;

    public Toy(int id, String name, int frequency) {
        this.id = id;
        this.name = name;
        this.frequency = frequency;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFrequency() {
        return frequency;
    }

    public static void main(String[] args) {
        PriorityQueue<Toy> queue = new PriorityQueue<Toy>((t1, t2) -> t2.getFrequency() - t1.getFrequency());

        Toy[] toys = {
                new Toy(1, "конструктор", 2),
                new Toy(2, "робот", 2),
                new Toy(3, "кукла", 6)
        };

        for (Toy toy : toys) {
            queue.offer(toy);
        }

        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(getRandomToy(queue, random).getName());
            sb.append(System.lineSeparator());
        }

        try (FileWriter writer = new FileWriter("result.txt")) {
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Toy getRandomToy(PriorityQueue<Toy> queue, Random random) {
        int total = queue.stream().mapToInt(Toy::getFrequency).sum();
        int rand = random.nextInt(total);
        int count = 0;
        for (Toy toy : queue) {
            count += toy.getFrequency();
            if (count > rand) {
                return toy;
            }
        }
        throw new IllegalStateException("PriorityQueue is empty");
    }
}