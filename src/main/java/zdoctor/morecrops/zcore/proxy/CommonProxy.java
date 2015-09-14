package zdoctor.morecrops.zcore.proxy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import zdoctor.morecrops.zcore.common.ISubEvent;
import zdoctor.morecrops.zcore.config.ConfigGuiFactory;
import zdoctor.morecrops.zcore.events.CoreEvents;
import zdoctor.morecrops.zcore.events.CoreEvents.ErrorRegistry;

public class CommonProxy {
	protected static List<ISubEvent> subs = new ArrayList<ISubEvent>();
	
	public void preInit(FMLPreInitializationEvent e) {
		ConfigGuiFactory.load(e);
		CoreEvents.load();
		alertAll(e);
	}
	
	public void init(FMLInitializationEvent e) {
		alertAll(e);
	}
	
	public void postInit(FMLPostInitializationEvent e) {
		alertAll(e);
		subs.clear();
		
		ErrorRegistry.errorReport();
	}
	
	public static void subEvent(ISubEvent object) {
			subs.add(object);
	}

	public static void alertAll(FMLPreInitializationEvent e){
		Iterator<ISubEvent> iteratorSubs = subs.iterator();
		while(iteratorSubs.hasNext()){
			iteratorSubs.next().fire(e);
		}
	}
	public static void alertAll(FMLInitializationEvent e){
		Iterator<ISubEvent> iteratorSubs = subs.iterator();
		while(iteratorSubs.hasNext()){
			iteratorSubs.next().fire(e);
		}
	}
	public static void alertAll(FMLPostInitializationEvent e){
		Iterator<ISubEvent> iteratorSubs = subs.iterator();
		while(iteratorSubs.hasNext()){
			iteratorSubs.next().fire(e);
		}
	}
}
