package meta1203.ProofOfConcept;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import net.minecraft.server.Item;
import net.minecraft.server.ItemBlock;

import org.bukkit.entity.Player;
import org.bukkit.Server;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

/**
 * ProofOfConcept for Bukkit
 *
 * @author meta1203
 */
public class ProofOfConcept extends JavaPlugin {
	public Logger log = Logger.getLogger("Minecraft");
    private final ProofOfConceptPlayerListener playerListener = new ProofOfConceptPlayerListener(this);
    private final ProofOfConceptBlockListener blockListener = new ProofOfConceptBlockListener(this);
    private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>();
    public List<Integer> blockTypesToDefine = new ArrayList<Integer>();
    public List<Float> blockHardness = new ArrayList<Float>();
    public List<Float> blockResistance = new ArrayList<Float>();
    public List<Integer> blockStepSound = new ArrayList<Integer>();
    public List<int[]> blockTexture = new ArrayList<int[]>();
    public List<Integer> blockMaterialType = new ArrayList<Integer>();
    public List<String> blockName = new ArrayList<String>();
    public List<Integer> itemTypesToDefine = new ArrayList<Integer>();
    private StartThread startT = new StartThread(this);

    public ProofOfConcept() {
        // TODO: Place any custom initialisation code here
        // NOTE: Event registration should be done in onEnable not here as all events are unregistered when a plugin is disabled
    }

   

    public void onEnable() {
        // TODO: Place any custom enable code here including the registration of any events

    	
        // Register our events
        PluginManager pm = getServer().getPluginManager();
        // pm.registerEvent(Event.Type.<EVENT_NAME>, <controllerFunction> , Priority.<priority>, this);
        startT.start();
        // EXAMPLE: Custom code, here we just output some info so we can check all is well
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
    }
    public void onDisable() {
        // TODO: Place any custom disable code here

        // NOTE: All registered events are automatically unregistered when a plugin is disabled

        // EXAMPLE: Custom code, here we just output some info so we can check all is well
    	startT.listening = false;
    	/* try {
			startT.serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */ 
		PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println(pdfFile.getName() + " version " + pdfFile.getVersion() + " is disabled.");
    }

    @Deprecated
    public void newBlock(int id, float hardness, float resistance, int stepSound, int[] textureId, int materialCase, String name) {
    	blockTypesToDefine.add(id);
    	blockHardness.add(hardness);
    	blockResistance.add(resistance);
    	blockStepSound.add(stepSound);
    	blockTexture.add(textureId);
    	blockMaterialType.add(materialCase);
    	blockName.add(name);
    	reloadBlocks();
    }
    
    public BlockVar allocateFreeBlock(net.minecraft.server.Material mat, float hardness, float resistance, int stepSound, int[] textureId, int materialCase, String name) {
    	BlockVar newBlock;
    	int id = 97;
    	if (blockTypesToDefine.size() == 0) {
    		id = 97;
    	}
    	else {
    		id = blockTypesToDefine.get(blockTypesToDefine.size()-1)+1;
    	}
    	newBlock = new BlockVar(id, mat, 1F);
    	blockTypesToDefine.add(id);
    	blockHardness.add(hardness);
    	blockResistance.add(resistance);
    	blockStepSound.add(stepSound);
    	blockTexture.add(textureId);
    	blockMaterialType.add(materialCase);
    	blockName.add(name);
    	reloadBlocks();
    	return newBlock;
    }
    
    public ItemVar allocateFreeItem() {
    	int id = 358;
    	if (itemTypesToDefine.size() != 0) {
    		id = itemTypesToDefine.get(itemTypesToDefine.size()-1)+1;
    	}
    	ItemVar toReturn = new ItemVar(id);
    	reloadItems();
    	return toReturn;
    }
    
    public void reloadBlocks() {
        // System.out.println(blockTypesToDefine.size());
        for (int x : blockTypesToDefine) {
        try {
        	// System.out.println("Adding block...");
        	Item.byId[x] = new ItemBlock(x-256);
	    }
	    catch (Exception e){
	        log.warning("Failed loading recipies: ");
	        e.printStackTrace();
	    }
        }
    }
    
    public void reloadItems() {
    	for (int x : itemTypesToDefine) {
            try {
            	// System.out.println("Adding block...");
            	Item.byId[x] = new ItemBlock(x-256);
    	    }
    	    catch (Exception e){
    	        log.warning("Failed loading recipies: ");
    	        e.printStackTrace();
    	    }
            }
    }
    
}

