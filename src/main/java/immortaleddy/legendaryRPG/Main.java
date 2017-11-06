package immortaleddy.legendaryRPG;

import immortaleddy.legendaryRPG.proxy.CommonProxy;
import immortaleddy.legendaryRPG.tabs.LRPGMainTab;
import immortaleddy.legendaryRPG.util.Reference;
import immortaleddy.legendaryRPG.util.handlers.DropHandler;
import immortaleddy.legendaryRPG.util.handlers.RegistryHandlers;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Main 
	{
	
	// Creative Tab Init
	public static final CreativeTabs lrpgmaintab = new LRPGMainTab("lrpgmaintab");
	
	@Instance
	public static Main Instance;
	
	@SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) 
	{
		RegistryHandlers.otherRegistries();
		MinecraftForge.EVENT_BUS.register(new DropHandler());
		
	}	
	
	@EventHandler
	public static void init(FMLInitializationEvent event) 
	{
		
		
	}	
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) 
	{
		
		
	}
	
	

}
