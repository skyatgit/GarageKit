package cn_sky.garagekit;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class GarageKit extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        if(getDataFolder().exists() || getDataFolder().mkdir()){
            if(!new File(getDataFolder(),"config.yml").exists()){
                System.out.println("初始化配置文件");
                saveDefaultConfig();
            }
            System.out.println("配置文件加载成功");
            getServer().getPluginManager().registerEvents(new NameTag(this),this);
        }else{
            System.out.println("未发现配置文件夹且创建配置文件夹失败!");
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
