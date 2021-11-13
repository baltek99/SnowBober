package snowbober.Systems;

import snowbober.Components.*;
import snowbober.ECS.Component;
import snowbober.ECS.System;
import snowbober.ECS.World;

import java.util.ArrayList;

public class CollisionSystem implements System {
    @Override
    public void update(long gameFrame, World world) {

        ArrayList<Component[]> components = world.getEntitiesWithComponents(new int[] {
                CmpId.POSITION.ordinal(),
                CmpId.COLLISION.ordinal(),
                CmpId.PLAYER_CONTROLLED.ordinal()
        });

        for (int entityA = 0; entityA < world.MAX_ENTITIES; entityA++) {

            Position posA = (Position) components.get(0)[entityA];
            Collision colA = (Collision) components.get(1)[entityA];
            if (posA == null || colA == null) continue;

            for (int entityB = entityA + 1; entityB < world.MAX_ENTITIES; entityB++) {

                Position posB = (Position) components.get(0)[entityB];
                Collision colB = (Collision) components.get(1)[entityB];
                if (posB == null || colB == null) continue;

                CollisionType type = intersects(posA, colA, posB, colB);
                if (type == CollisionType.NONE)
                {
                    continue;
                }

                world.addComponentToEntity(entityA, new CollisionResponse(entityB, type, colB.type));
                world.addComponentToEntity(entityB, new CollisionResponse(entityA, type, colA.type));

            }
        }
    }

    CollisionType intersects(Position posA, Collision colA, Position posB, Collision colB)
    {
        int distance = (int)Math.sqrt(((posA.x - posB.x) * (posA.x - posB.x)) + ((posA.y - posB.y) * (posA.y - posB.y)));
        //java.lang.System.out.println("Dist: " + distance + " Pos: " + posA.x  + " " + posB.x);
        if (distance == colA.radius + colB.radius)
        {
            return CollisionType.TOUCH;
        }
        else if (distance < colA.radius + colB.radius) {
            return CollisionType.INTERSECT;
        }

        return CollisionType.NONE;
    }
}
