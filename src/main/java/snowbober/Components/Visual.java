package snowbober.Components;

import snowbober.ECS.Component;
import snowbober.ECS.ComponentWithId;
import snowbober.Util.Texture;


public class Visual extends ComponentWithId {
    public Texture texture;

    public Visual(Texture txt) {
        super(CmpId.VISUAL.ordinal());
        texture = txt;
    }
}
