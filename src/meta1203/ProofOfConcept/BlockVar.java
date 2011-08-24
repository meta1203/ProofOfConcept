package meta1203.ProofOfConcept;

import net.minecraft.server.Block;

public class BlockVar extends Block {
	public BlockVar(int id, net.minecraft.server.Material mat, float durab) {
        super(id, mat);
        this.durability = durab;
    }
	
	public int getID() {
		return this.id;
	}
}
