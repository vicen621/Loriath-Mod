package io.github.vicen621.loriath.utils;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;

import java.util.UUID;

/** Handling attributes in easier and shorter way. */
public class AttributeHandler {
	protected final UUID uuid;
	protected final String name;
	protected final EntityAttribute attribute;
	protected final EntityAttributeModifier.Operation operation;
	private double value = 1.0;

	public AttributeHandler( String uuid, String name, EntityAttribute attribute, EntityAttributeModifier.Operation operation ) {
		this.uuid = UUID.fromString( uuid );
		this.name = name;
		this.attribute = attribute;
		this.operation = operation;
	}

	/** Checks whether entity has a given attribute. */
	public static boolean hasAttribute(LivingEntity entity, EntityAttribute attribute ) {
		AttributeContainer attributeMap = entity.getAttributes();

		return attributeMap.hasAttribute( attribute );
	}

	/** Returns current attribute value. */
	public double getValue() {
		return this.value;
	}

	/** Setting current attribute value. */
	public AttributeHandler setValue( double value ) {
		this.value = value;

		return this;
	}

	/**
	 Applying current attribute to the target.
	 @param target Entity to apply attribute.
	 */
	public AttributeHandler apply( LivingEntity target ) {
		EntityAttributeInstance attributeInstance = target.getAttributeInstance(this.attribute );

		if( attributeInstance != null ) {
			attributeInstance.removeModifier( this.uuid );
			EntityAttributeModifier modifier = createAttribute();
			attributeInstance.addPersistentModifier( modifier );
		}

		return this;
	}

	/**
	 Changing current attribute value and applying it to the entity.
	 @param target Entity to apply attribute.
	 @param value  New attribute value.
	 */
	public AttributeHandler setValueAndApply( LivingEntity target, double value ) {
		setValue( value );

		return apply( target );
	}

	/** Returns attribute modifier with given parameters. */
	public EntityAttributeModifier createAttribute() {
		return new EntityAttributeModifier(this.uuid, this.name, this.value, this.operation);
	}

	/** Returns uuid. */
	public UUID getUUID() {
		return this.uuid;
	}
}