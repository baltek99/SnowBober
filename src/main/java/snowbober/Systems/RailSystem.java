package snowbober.Systems;

import snowbober.Components.*;
import snowbober.ECS.Component;
import snowbober.ECS.System;
import snowbober.ECS.World;
import snowbober.Util.Game;
import snowbober.Util.Util;

import java.util.ArrayList;

public class RailSystem implements System {
    @Override
    public void update(long gameFrame, World world) throws InterruptedException {
        ArrayList<Component[]> components = world.getEntitiesWithComponents(new int[]{
                CmpId.PLAYER_CONTROLLED.ordinal(),
                CmpId.COLLISION_RESPONSE.ordinal(),
                CmpId.POSITION.ordinal(),
                CmpId.VISUAL.ordinal()
        });

        for (int entity = 0; entity < world.MAX_ENTITIES; entity++) {
            if (World.isEntityOk(entity, components) == false) continue;

            CollisionResponse cr = (CollisionResponse) components.get(1)[entity];
            Position pos = (Position) components.get(2)[entity];
            PlayerControlled pc = (PlayerControlled) components.get(0)[entity];
            Visual vis = (Visual) components.get(3)[entity];

            //java.lang.System.out.println(((Position) components.get(2)[cr.collidingEntityId]).x);
            if (pc.playerState == PlayerState.SLIDING && ((Position) components.get(2)[cr.collidingEntityId]).x <= -200) {
                world.removeComponentFromEntity(entity, cr);
                pc.playerState = PlayerState.IDLE;
                pos.y = 400;
                vis.texture = Util.loadImage("assets/bober-stand.png", 0.7f);
                Game.score++;
            }

        }
    }
}
