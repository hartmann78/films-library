package inputInformation;

import databaseService.ActorService;
import entity.Actor;

import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public class ActorInput {
    private final Scanner scanner = new Scanner(System.in);
    private final ActorService actorService = new ActorService();

    public String input() {
        int limit = 64;
        while (true) {
            try {
                System.out.println("Введите имя актёра:");
                String actorName = scanner.nextLine();

                if (actorName.length() > limit) {
                    throw new Exception();
                }

                return actorName;
            } catch (Exception e) {
                System.out.println("Имя актёра не должно превышать " + limit + " символов!");
            }
        }
    }

    public Set<Actor> addActorToMovie() {
        Set<Actor> actors = new HashSet<>();
        int limit = 64;
        while (true) {
            try {
                if (!actors.isEmpty()) {
                    System.out.println("Добавленные актёры:" + actors);
                }

                System.out.println("Введите имя актёра. Чтобы сохранить, введите 0:");
                String actorName = scanner.nextLine();

                if (actorName.equals("0")) {
                    return actors;
                }

                if (actorName.length() > limit) {
                    throw new Exception();
                }

                Optional<Actor> actor = actorService.findActorByName(actorName);

                if (actor.isPresent()) {
                    actors.add(actor.get());
                } else {
                    Actor newActor = new Actor(null, actorName, new HashSet<>());
                    actorService.addActor(newActor);
                    actors.add(newActor);
                }
            } catch (Exception e) {
                System.out.println("Имя режиссёра не должно превышать " + limit + " символов!");
            }
        }
    }
}
