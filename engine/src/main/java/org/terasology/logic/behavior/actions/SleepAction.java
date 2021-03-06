/*
 * Copyright 2017 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.logic.behavior.actions;

import org.terasology.logic.behavior.BehaviorAction;
import org.terasology.logic.behavior.core.Actor;
import org.terasology.logic.behavior.core.BaseAction;
import org.terasology.logic.behavior.core.BehaviorState;
import org.terasology.module.sandbox.API;
import org.terasology.rendering.nui.properties.Range;

/**
 * Sleeps for a given amount of time (RUNNING),
 * then returns with SUCCESS.
 */
@API
@BehaviorAction(name = "sleep")
public class SleepAction extends BaseAction {

    @Range(min = 0, max = 20)
    private float time;

    @Override
    public void construct(Actor actor) {
        actor.setValue(getId(), time);
    }

    @Override
    public BehaviorState modify(Actor actor, BehaviorState result) {

        float timeRemaining = 0;
        try {// TODO figure out the delegation issue
            timeRemaining = actor.getValue(getId());
        } catch (NullPointerException e) {
            construct(actor);
        }
        timeRemaining -= actor.getDelta();
        actor.setValue(getId(), timeRemaining);
        return timeRemaining > 0 ? BehaviorState.RUNNING : BehaviorState.SUCCESS;
    }

}
