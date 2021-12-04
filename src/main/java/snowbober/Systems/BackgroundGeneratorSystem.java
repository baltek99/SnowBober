package snowbober.Systems;

import snowbober.Components.CmpId;
import snowbober.Components.Position;
import snowbober.ECS.Component;
import snowbober.ECS.System;
import snowbober.ECS.World;

import java.util.ArrayList;

public class BackgroundGeneratorSystem implements System {
    public int min, max;

    public BackgroundGeneratorSystem(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public void update(long gameFrame, World world) {

        ArrayList<Component[]> components = world.getEntitiesWithComponents(new int[]{
                CmpId.POSITION.ordinal(),
                CmpId.MOVE.ordinal()
        });

        for (int entity = min; entity <= max; entity++) {
            if (World.isEntityOk(entity, components) == false) continue;

            Position pos = (Position) components.get(0)[entity];

            if (pos.x <= -1536) {
                pos.x += 1536 * 2;
            }
        }
    }
}
