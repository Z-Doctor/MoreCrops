package zcore.common;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLStateEvent;

/**
 * Needs to be extended if you want to be told when these event take place (May change later)
 * @author Z_Doctor
 */
public interface ISubEvent {
	public void fire(FMLPreInitializationEvent e);
	public void fire(FMLInitializationEvent e);
	public void fire(FMLPostInitializationEvent e);
}
