package snowbober.Systems;

import snowbober.Components.CmpId;
import snowbober.Components.Move;
import snowbober.Components.Position;
import snowbober.ECS.Component;
import snowbober.ECS.System;
import snowbober.ECS.World;

import java.util.ArrayList;

public class MoveSystem implements System {
    @Override
    public void update(long gameFrame, World world) {
        ArrayList<Component[]> components = world.getEntitiesWithComponents(new int[]{
                CmpId.POSITION.ordinal(),
                CmpId.MOVE.ordinal()
        });

        for (int entity = 0; entity < world.MAX_ENTITIES; entity++) {
            if (World.isEntityOk(entity, components) == false) continue;

            Position pos = (Position) components.get(0)[entity];
            Move mov = (Move) components.get(1)[entity];
            pos.x += mov.speed;

        }
    }
}
