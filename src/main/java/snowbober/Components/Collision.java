package snowbober.Components;

import snowbober.ECS.ComponentWithId;

public class Collision extends ComponentWithId {
    public int offSetX, offSetY;
    public int radius;
    int collisionMask; // eg. 0b001
    public ObstacleType type;

    public Collision(int x, int y, int rad, ObstacleType type) {
        super(CmpId.COLLISION.ordinal());
        offSetX = x;
        offSetY = y;
        radius = rad;
        this.type = type;
    }
}
