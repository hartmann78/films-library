package inputInformation;

import dao.impl.ActorDaoImpl;
import entity.Actor;

import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public class ActorInput {
    private final Scanner scanner = new Scanner(System.in);
    private final ActorDaoImpl actorDao = new ActorDaoImpl();

    public Set<Actor> input() {
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

                Optional<Actor> actor = getActor(actorName);

                if (actor.isPresent()) {
                    actors.add(actor.get());
                } else {
                    Actor newActor = new Actor(null, actorName, new HashSet<>());
                    addActor(newActor);
                    actors.add(newActor);
                }
            } catch (Exception e) {
                System.out.println("Имя режиссёра не должно превышать " + limit + " символов!");
            }
        }
    }

    public Optional<Actor> getActor(String name) {
        return actorDao.findByName(name);
    }

    public void addActor(Actor actor) {
        actorDao.add(actor);
    }

    public void updateActor(Actor actor) {
        actorDao.update(actor);
    }

    public void deleteActor(Actor actor) {
        actorDao.delete(actor);
    }
}
