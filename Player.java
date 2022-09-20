import java.util.Comparator;
import java.util.Scanner;
import java.util.Arrays;

public class Player {
    public static final Comparator<Player> BY_NAME = new ByName();
    public static final Comparator<Player> BY_HEIGHT = new ByRating();
    public static final Comparator<Player> BY_AGE = new ByAge();
    private final String name;
    private final int rating;
    private final int age;
    
    public Player(String name, int rating, int age) {
        this.name = name;
        this.rating = rating;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }
    public int getRating() {
        return this.rating;
    }
    public int getAge() {
        return this.age;
    }

    @Override
    public String toString() {
        return String.format("%20s%5d%4d",
                this.name, this.rating, this.age);
    }

    private static class ByName implements Comparator<Player> {
        public int compare(Player a, Player b) {
            return a.name.compareTo(b.name); 
        }
    }
    private static class ByRating implements Comparator<Player> {
        public int compare(Player a, Player b) {
            return b.rating - a.rating;
        }
    }
    private static class ByAge implements Comparator<Player> {
        public int compare(Player a, Player b) {
            return a.age - b.age;
        }
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Player[] a = new Player[N];

        Scanner in = new Scanner(System.in);
        for (int i = 0; i < N; i++) {
            String[] fields = (in.nextLine()).split("\\s*;\\s*");
            String name = fields[0];
            int rating = Integer.parseInt(fields[1]);
            int age = Integer.parseInt(fields[2]);
            a[i] = new Player(name, rating, age);
        }

        Arrays.sort(a, Player.BY_NAME);
        for (int i = 0; i < N; i++)
            System.out.println(a[i]);
        System.out.println();

        Arrays.sort(a, Player.BY_HEIGHT);
        for (int i = 0; i < N; i++)
            System.out.println(a[i]);
        System.out.println();

        Arrays.sort(a, Player.BY_AGE);
        for (int i = 0; i < N; i++)
            System.out.println(a[i]);
    }
}
