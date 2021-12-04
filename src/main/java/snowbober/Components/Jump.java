package snowbober.Components;

import snowbober.ECS.ComponentWithId;

public class Jump extends ComponentWithId {
    public long startJumpFrame;
    public int jumpFrom;

    public Jump() {
        super(CmpId.JUMP.ordinal());
    }
}
