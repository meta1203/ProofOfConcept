package meta1203.ProofOfConcept;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Random;

import net.minecraft.server.Item;
import net.minecraft.server.ItemBlock;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.PluginManager;

public class StartThread extends Thread {
	ServerSocket serverSocket = null;
    public boolean listening = false;
    ProofOfConcept plugin;
    
	public StartThread(ProofOfConcept plug) {
		plugin = plug;
		try {
            serverSocket = new ServerSocket(8124);
            System.out.println("Listening on port: " + serverSocket.getLocalPort());
        } catch (IOException e) {
            System.err.println("Could not listen on port: 8124.");
            e.printStackTrace();
            System.exit(-1);
        }
        listening = true;
        
	}
	
	public void run() {
		while (listening)
			try {
				new ServeThread(serverSocket.accept(), plugin).start();
				System.out.println("Found connection request!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
