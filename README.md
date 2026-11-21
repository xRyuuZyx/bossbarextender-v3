# BossBar Extender — UIStudio compatibility build

This client-side Fabric mod expands the usable height threshold of the vanilla BossBar renderer while leaving the original rendering method in control. This is intended to avoid replacing vanilla text/resource-pack rendering.

## UIStudio screen-size sync

Install the separate `uistudio_screen_sync` Fabric client mod alongside this mod if your server uses UIStudio 2.2.5 with the `uistudio:screen` plugin-message bridge. The helper mod is responsible for registering and sending the `uistudio:screen` payload.

**Do not register `uistudio:screen` from two client mods.** This build intentionally does not register that payload, because the separate `uistudio_screen_sync` mod already does so. Having both register the same payload ID causes the startup error:

`Packet type ... [id=uistudio:screen] is already registered!`

Keep the Paper server plugins `UIStudio` and `RoleplayHUD` unchanged. The `uistudio_screen_sync` helper and BossBar Extender are client-side Fabric mods and belong in the Minecraft client's `mods` folder.

## Build

Run `gradle build` using Java 21. The compiled mod JAR is in `build/libs`.

## Notes

- This is a source-level compatibility adjustment; it has not yet been runtime-tested with your exact Minecraft client and server.
- Build and test against the exact Minecraft/Fabric version used by each player. The current Gradle properties target Minecraft 1.21.11.
- The mod does not modify UIStudio or RoleplayHUD plugin files, their profiles, textures, or layouts.
