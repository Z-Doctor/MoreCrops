package zdoctor.zcore.common;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLStateEvent;

public interface ISubEvent {
	//public void fire(FMLStateEvent e);
	
	public void fire(FMLPreInitializationEvent e);
	public void fire(FMLInitializationEvent e);
	public void fire(FMLPostInitializationEvent e);
	
}
