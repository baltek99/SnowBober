package snowbober.Systems;

import snowbober.Components.*;
import snowbober.ECS.Component;
import snowbober.ECS.System;
import snowbober.ECS.World;
import snowbober.Util.Util;

import java.util.ArrayList;

public class PlayerCollisionSystem implements System {

    @Override
    public void update(long gameFrame, World world) {
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

            if (cr.obstacle == ObstacleType.BOX || (cr.obstacle == ObstacleType.RAIL && pc.playerState == PlayerState.IDLE)) {
                world.killEntity(entity);
            } else if (cr.obstacle == ObstacleType.RAIL && pc.playerState == PlayerState.JUMPING) {
                pos.y = 370;
                pc.playerState = PlayerState.SLIDING;
                vis.texture = Util.loadImage("assets/bober-rail.png", 0.7f);
            }
        }


    }
}
