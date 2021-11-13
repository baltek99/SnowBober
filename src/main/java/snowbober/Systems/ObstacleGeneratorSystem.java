package snowbober.Systems;

import snowbober.Components.*;
import snowbober.ECS.System;
import snowbober.ECS.World;
import snowbober.Util.Util;

import snowbober.Util.Texture;

public class ObstacleGeneratorSystem implements System {
    public int min, max, current, spawnRate;
    public Texture texBox;
    public Texture texRail;

    public ObstacleGeneratorSystem(int min, int max) {
        this.min = min;
        this.max = max;
        current = min;
        spawnRate = 270;
        texBox = Util.loadImage("assets/skrzynka.png", 0.6f);
        texRail = Util.loadImage("assets/rail.png", 0.7f);
    }
    @Override
    public void update(long gameFrame, World world) throws InterruptedException {
        if (gameFrame % spawnRate == 0) {
            int obstacle = current;
            current = ((current + 1) % (max - min)) + min;

            if (obstacle % 3 == 0) createBox(world, obstacle);
            else createRail(world, obstacle);
        }
    }

    public void createRail(World world, int rail) {
        world.addComponentToEntity(rail, new Position(1600, 500));
        world.addComponentToEntity(rail, new Visual(texRail));
        world.addComponentToEntity(rail, new Move(-3));
        world.addComponentToEntity(rail, new Collision(texRail.imgWidth, texRail.imgHeight, (texRail.imgWidth + texRail.imgHeight)/2 - 80, ObstacleType.RAIL));

    }

    public void createBox(World world, int box) {
        world.addComponentToEntity(box, new Position(1600, 500));
        world.addComponentToEntity(box, new Visual(texBox));
        world.addComponentToEntity(box, new Move(-3));
        world.addComponentToEntity(box, new Collision(texBox.imgWidth, texBox.imgHeight, (texBox.imgWidth+ texBox.imgHeight)/2-40, ObstacleType.BOX));

    }
}
