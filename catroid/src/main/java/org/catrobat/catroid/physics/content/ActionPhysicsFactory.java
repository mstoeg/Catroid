/*
 * Catroid: An on-device visual programming system for Android devices
 * Copyright (C) 2010-2018 The Catrobat Team
 * (<http://developer.catrobat.org/credits>)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * An additional term exception under section 7 of the GNU Affero
 * General Public License, version 3, is available at
 * http://developer.catrobat.org/license_additional_term
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.catrobat.catroid.physics.content;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import org.catrobat.catroid.ProjectManager;
import org.catrobat.catroid.content.ActionFactory;
import org.catrobat.catroid.content.Scope;
import org.catrobat.catroid.content.Sprite;
import org.catrobat.catroid.content.actions.GlideToPhysicsAction;
import org.catrobat.catroid.content.actions.IfOnEdgeBouncePhysicsAction;
import org.catrobat.catroid.content.actions.SetBounceFactorAction;
import org.catrobat.catroid.content.actions.SetFrictionAction;
import org.catrobat.catroid.content.actions.SetGravityAction;
import org.catrobat.catroid.content.actions.SetMassAction;
import org.catrobat.catroid.content.actions.SetPhysicsObjectTypeAction;
import org.catrobat.catroid.content.actions.SetVelocityAction;
import org.catrobat.catroid.content.actions.TurnLeftSpeedAction;
import org.catrobat.catroid.content.actions.TurnRightSpeedAction;
import org.catrobat.catroid.formulaeditor.Formula;
import org.catrobat.catroid.physics.PhysicsLook;
import org.catrobat.catroid.physics.PhysicsObject;
import org.catrobat.catroid.physics.PhysicsObject.Type;
import org.catrobat.catroid.physics.PhysicsWorld;

public class ActionPhysicsFactory extends ActionFactory {

	private PhysicsObject getPhysicsObject(Sprite sprite) {
		return getPhysicsWorld().getPhysicsObject(sprite);
	}

	private PhysicsWorld getPhysicsWorld() {
		return ProjectManager.getInstance().getCurrentlyPlayingScene().getPhysicsWorld();
	}

	// OVERRIDE
	@Override
	public Action createIfOnEdgeBounceAction(Sprite sprite) {
		IfOnEdgeBouncePhysicsAction action = Actions.action(IfOnEdgeBouncePhysicsAction.class);
		action.setSprite(sprite);
		action.setPhysicsWorld(getPhysicsWorld());
		return action;
	}

	@Override
	public Action createGlideToAction(Sprite sprite, SequenceAction sequence, Formula x, Formula y,
			Formula duration) {
		GlideToPhysicsAction action = Actions.action(GlideToPhysicsAction.class);
		action.setPosition(x, y);
		action.setDuration(duration);
		Scope scope = new Scope(ProjectManager.getInstance().getCurrentProject(), sprite, sequence);
		action.setScope(scope);
		action.setPhysicsLook((PhysicsLook) sprite.look);
		return action;
	}

	// PHYSICS
	@Override
	public Action createSetBounceFactorAction(Sprite sprite, SequenceAction sequence,
			Formula bounceFactor) {
		SetBounceFactorAction action = Actions.action(SetBounceFactorAction.class);
		Scope scope = new Scope(ProjectManager.getInstance().getCurrentProject(), sprite, sequence);
		action.setScope(scope);
		action.setPhysicsObject(getPhysicsObject(sprite));
		action.setBounceFactor(bounceFactor);
		return action;
	}

	@Override
	public Action createSetFrictionAction(Sprite sprite, SequenceAction sequence, Formula friction) {
		SetFrictionAction action = Actions.action(SetFrictionAction.class);
		Scope scope = new Scope(ProjectManager.getInstance().getCurrentProject(), sprite, sequence);
		action.setScope(scope);
		action.setPhysicsObject(getPhysicsObject(sprite));
		action.setFriction(friction);
		return action;
	}

	@Override
	public Action createSetGravityAction(Sprite sprite, SequenceAction sequence, Formula gravityX,
			Formula gravityY) {
		SetGravityAction action = Actions.action(SetGravityAction.class);
		Scope scope = new Scope(ProjectManager.getInstance().getCurrentProject(), sprite, sequence);
		action.setScope(scope);
		action.setPhysicsWorld(getPhysicsWorld());
		action.setGravity(gravityX, gravityY);
		return action;
	}

	@Override
	public Action createSetMassAction(Sprite sprite, SequenceAction sequence, Formula mass) {
		SetMassAction action = Actions.action(SetMassAction.class);
		Scope scope = new Scope(ProjectManager.getInstance().getCurrentProject(), sprite, sequence);
		action.setScope(scope);
		action.setPhysicsObject(getPhysicsObject(sprite));
		action.setMass(mass);
		return action;
	}

	@Override
	public Action createSetPhysicsObjectTypeAction(Sprite sprite, Type type) {
		SetPhysicsObjectTypeAction action = Actions.action(SetPhysicsObjectTypeAction.class);
		action.setPhysicsObject(getPhysicsObject(sprite));
		action.setType(type);
		return action;
	}

	@Override
	public Action createSetVelocityAction(Sprite sprite, SequenceAction sequence, Formula velocityX,
			Formula velocityY) {
		SetVelocityAction action = Actions.action(SetVelocityAction.class);
		Scope scope = new Scope(ProjectManager.getInstance().getCurrentProject(), sprite, sequence);
		action.setScope(scope);
		action.setPhysicsObject(getPhysicsObject(sprite));
		action.setVelocity(velocityX, velocityY);
		return action;
	}

	@Override
	public Action createTurnLeftSpeedAction(Sprite sprite, SequenceAction sequence, Formula speed) {
		TurnLeftSpeedAction action = Actions.action(TurnLeftSpeedAction.class);
		Scope scope = new Scope(ProjectManager.getInstance().getCurrentProject(), sprite, sequence);
		action.setScope(scope);
		action.setPhysicsObject(getPhysicsObject(sprite));
		action.setSpeed(speed);
		return action;
	}

	@Override
	public Action createTurnRightSpeedAction(Sprite sprite, SequenceAction sequence, Formula speed) {
		TurnRightSpeedAction action = Actions.action(TurnRightSpeedAction.class);
		Scope scope = new Scope(ProjectManager.getInstance().getCurrentProject(), sprite, sequence);
		action.setScope(scope);
		action.setPhysicsObject(getPhysicsObject(sprite));
		action.setSpeed(speed);
		return action;
	}
}
