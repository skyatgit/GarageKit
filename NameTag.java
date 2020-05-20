package cn_sky.garagekit;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class NameTag implements Listener {
    String name=null;
    String tips=null;
    NameTag(Plugin plugin){
        name= (String) plugin.getConfig().get("name");
        tips=(String) plugin.getConfig().get("tips");
        System.out.println(name);
        System.out.println(tips);
    }
    @EventHandler
    public void listenPlayerInteractEntityEvent(PlayerInteractEntityEvent event){
        Player player= event.getPlayer();
        ItemStack itemStack = null;
        if(event.getHand()== EquipmentSlot.HAND){
            itemStack= player.getInventory().getItemInMainHand();
        }else if(event.getHand()==EquipmentSlot.OFF_HAND){
            itemStack= player.getInventory().getItemInOffHand();
        }
        assert itemStack != null;
        if(itemStack.getType()== Material.NAME_TAG){
            ItemMeta itemMeta= itemStack.getItemMeta();
            assert itemMeta != null;
            if(itemMeta.getDisplayName().equals(name) && event.getRightClicked() instanceof LivingEntity){
                if(!tips.equals("none")){
                    player.sendMessage(tips);
                }
                LivingEntity livingEntity= (LivingEntity) event.getRightClicked();
                livingEntity.setAI(false);
            }
        }

    }
}
