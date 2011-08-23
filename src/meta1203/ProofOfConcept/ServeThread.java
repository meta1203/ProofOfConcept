package meta1203.ProofOfConcept;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

import javax.imageio.ImageIO;

import net.minecraft.server.Item;
import net.minecraft.server.ItemBlock;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class ServeThread extends Thread {
	private Socket socket = null;
	private ProofOfConcept plugin;
	
	public ServeThread(Socket ss, ProofOfConcept plug) {
		super("ServeThread");
		plugin = plug;
		socket = ss;
	}
	
	public void run() {
		PrintWriter out = null;
	    try {
	    	out = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int x = 0; x < plugin.blockTypesToDefine.size(); x++) {
			out.println("sweet");
			out.println(Integer.toString(plugin.blockTypesToDefine.get(x)));
			out.println(Float.toString(plugin.blockHardness.get(x)));
			out.println(Float.toString(plugin.blockResistance.get(x)));
			out.println(Integer.toString(plugin.blockStepSound.get(x)));
			out.println(Integer.toString(plugin.blockMaterialType.get(x)));
			out.println(plugin.blockName.get(x));
			
			for (int current = 0; current < 6; current++) {
				System.out.println(plugin.blockTexture.get(x)[current]);
				out.println(plugin.blockTexture.get(x)[current]);
			}
		}
	}
}
