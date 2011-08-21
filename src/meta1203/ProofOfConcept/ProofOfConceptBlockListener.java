package meta1203.ProofOfConcept;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.Material;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPhysicsEvent;

/**
 * ProofOfConcept block listener
 * @author meta1203
 */
public class ProofOfConceptBlockListener extends BlockListener {
    private final ProofOfConcept plugin;

    public ProofOfConceptBlockListener(final ProofOfConcept plugin) {
        this.plugin = plugin;
    }

    //put all Block related code here
}
