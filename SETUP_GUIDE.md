# CobbleOre Generator - Setup Guide

Based on the original mod by [OffsetMods538](https://github.com/OffsetMods538/CobbleOre-Generator), adapted for Minecraft 1.21.1

## 🎮 For Players (Just Want to Use the Mod)

### Prerequisites
1. [Fabric Loader](https://fabricmc.net/use/) for Minecraft 1.21.1
2. [Fabric API](https://modrinth.com/mod/fabric-api) (version 0.102.0+1.21.1 or newer)
3. Java 21 or higher

### Installation Steps

1. **Build the mod** (see Developer section below)
2. **Place files in mods folder**:
   - Copy `cobbleore_generator-1.0.0.jar` from `build/libs/` to `.minecraft/mods`
   - Copy the Fabric API jar to `.minecraft/mods`
3. **Launch Minecraft** using the Fabric profile
4. **Done!** The mod will create a default config on first run

### Using the Mod

1. **Build a cobble generator** (lava + water)
2. **Watch the magic happen** - 25% of cobblestone will become ores!
3. **Customize** (optional) - Edit `.minecraft/config/cog.json` to change ore weights

---

## 💻 For Developers (Want to Build or Modify)

### Prerequisites
- Java 21 or higher (JDK)
- Git (optional)

### First Time Setup

Before you can build, you need to set up the Gradle wrapper:

#### Option 1: Run the Download Script (Easiest)

**Linux/Mac:**
```bash
chmod +x download-wrapper.sh
./download-wrapper.sh
```

**Windows:**
```cmd
download-wrapper.bat
```

#### Option 2: Use Installed Gradle

If you already have Gradle installed:
```bash
gradle wrapper
```

#### Option 3: Manual Download

1. Download: https://raw.githubusercontent.com/gradle/gradle/v8.8.0/gradle/wrapper/gradle-wrapper.jar
2. Create folder: `gradle/wrapper/`
3. Place the JAR in: `gradle/wrapper/gradle-wrapper.jar`

### Building the Mod

Once the wrapper is set up:

**Linux/Mac:**
```bash
./gradlew build
```

**Windows:**
```cmd
gradlew.bat build
```

**Find your mod:**
- Compiled jar: `build/libs/cobbleore_generator-1.0.0.jar`
- Copy this to your `.minecraft/mods` folder

### Development Setup

For IDE development:

**IntelliJ IDEA:**
```bash
./gradlew idea
```
Then open the project in IntelliJ

**Eclipse:**
```bash
./gradlew eclipse
```
Then import the project

---

## 📝 Configuration Guide

### Config Location
`.minecraft/config/cog.json`

### Understanding Weights

Weights determine the relative probability of each block spawning:

```
Block A weight: 30
Block B weight: 10
Block C weight: 5

Total weight: 45

Probabilities:
- Block A: 30/45 = 66.7%
- Block B: 10/45 = 22.2%
- Block C: 5/45 = 11.1%
```

### Example Configurations

#### Balanced (Default)
```json
{
  "generatableBlocks": {
    "minecraft:coal_ore": 30,
    "minecraft:iron_ore": 25,
    "minecraft:copper_ore": 20,
    "minecraft:redstone_ore": 15,
    "minecraft:lapis_ore": 12,
    "minecraft:gold_ore": 10,
    "minecraft:diamond_ore": 3,
    "minecraft:emerald_ore": 2
  }
}
```

#### Modded Ores Support
```json
{
  "generatableBlocks": {
    "minecraft:iron_ore": 30,
    "minecraft:diamond_ore": 3,
    "create:zinc_ore": 15,
    "thermal:silver_ore": 10,
    "thermal:tin_ore": 12,
    "mekanism:osmium_ore": 8,
    "ae2:quartz_ore": 5
  }
}
```

#### Easy Mode (More Valuable)
```json
{
  "generatableBlocks": {
    "minecraft:diamond_ore": 30,
    "minecraft:emerald_ore": 20,
    "minecraft:gold_ore": 25,
    "minecraft:lapis_ore": 15,
    "minecraft:iron_ore": 10
  }
}
```

#### Chaos Mode (Anything Goes)
```json
{
  "generatableBlocks": {
    "minecraft:cobblestone": 30,
    "minecraft:diamond_block": 5,
    "minecraft:tnt": 3,
    "minecraft:cake": 20,
    "minecraft:sponge": 10,
    "minecraft:dragon_egg": 1
  }
}
```

---

## 🔧 Troubleshooting

### Mod Doesn't Load

**Issue**: Mod won't start  
**Solution**: 
- Verify Fabric Loader 0.16.0+ is installed
- Check Fabric API is in mods folder
- Confirm you're running Minecraft 1.21.1
- Check logs for errors

### Ores Don't Generate

**Issue**: Only cobblestone appears  
**Solution**:
- Verify you have a proper cobble generator (lava + water)
- Remember: only 25% chance for ores
- Check the config file is valid JSON
- Look for error messages in logs

### Config Not Working

**Issue**: Changes to config don't apply  
**Solution**:
- Restart Minecraft after editing config
- Verify JSON syntax is correct (use a JSON validator)
- Check file is at `.minecraft/config/cog.json`
- Make sure block IDs are valid

### Build Errors

**Issue**: Gradle build fails  
**Solution**:
- Ensure Java 21 is installed: `java -version`
- Delete `.gradle` folder and rebuild
- Run `./gradlew clean build`
- Check internet connection (needs to download dependencies)

### Wrong Minecraft Version

**Issue**: Mod is for different version  
**Solution**:
- This mod is for 1.21.1 specifically
- Check `gradle.properties` for version settings
- May need to update yarn mappings and fabric versions

---

## 🎯 How It Works

### Technical Overview

1. **Mixin Injection**: The mod uses Fabric's mixin system to hook into `FluidBlock.receiveNeighborFluids()`
2. **Detection**: When lava and water interact to create cobblestone, the mixin catches it
3. **Randomization**: 25% chance triggers ore generation
4. **Weighted Selection**: Random ore is chosen based on config weights
5. **Replacement**: Cobblestone is replaced with the selected ore

### For Modpack Developers

- **Compatibility**: Works with any mod that adds blocks
- **Configuration**: Distribute custom `cog.json` with your modpack
- **Balance**: Adjust weights to match your pack's difficulty
- **Tags**: Unlike the original, this version doesn't use tags (simpler config)

---

## 💡 Tips & Tricks

### Performance
- The mod is very lightweight
- No lag from ore generation
- Config is loaded once at startup

### Balancing
- Start with default config and adjust
- Test weight changes in creative mode
- Consider your pack's progression

### Creative Ideas
- **Sky block**: Increase valuable ore weights
- **Hard mode**: Decrease diamond/emerald weights
- **Themed packs**: Add modded ores that fit your theme
- **Mini-games**: Random block generators for building challenges

---

## 📄 License

MIT License - Feel free to modify and redistribute!

Based on the original by OffsetMods538:
- GitHub: https://github.com/OffsetMods538/CobbleOre-Generator
- Modrinth: https://modrinth.com/mod/cog
