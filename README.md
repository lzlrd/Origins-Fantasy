# Origins-Fantasy

Unofficial fork of [Origins-Fantasy](https://modrinth.com/plugin/origins-fantasy) 1.1.14 by cometcake575, ported to Minecraft 26.3. Upstream source: <https://github.com/cometcake575/Origins-Fantasy>.

An addon for [Origins-Reborn](https://modrinth.com/plugin/origins-reborn) that adds nine origins.

## Install

Needs Paper 26.3 or a fork of it, with Origins-Reborn 2.10.11 installed. Put the jar in `plugins/` and restart the server.

Tested on Paper 26.3 build 248 and Leaf 26.3 build 22.

## Build

```sh
./gradlew build
```

Needs a JDK 25. The jar lands in `build/libs/`.

## Origins

Each origin keeps its upstream file name and identifier, so saved player origins and existing configs carry over. Only the name and description shown to players changed.

| Origin | Upstream name | File |
| --- | --- | --- |
| Allaykin | Bard | `bard.json` |
| Saddleborn | Centaur | `centaur.json` |
| Dragonling | Dragonborn | `dragonborn.json` |
| Deepling | Dwarf | `dwarf.json` |
| Fletcher | Elf | `elf.json` |
| Stone Golem | Gargoyle | `gargoyle.json` |
| Giant | Giant | `giant.json` |
| Seaheart | Naiad | `naiad.json` |
| Zombified | Vampire | `vampire.json` |

## Changes from upstream 1.1.14

- The sources match the 1.1.14 release. Upstream's repository stops at an earlier version, so the difference was recovered from the released jar and checked against it class by class.
- Heat Resistant stops magma block damage again. Since Minecraft 26.2 the server reports that damage as contact damage, which upstream does not cancel.
- Skilled Archer arrows launch at vanilla speed. Upstream launches them at a third of it on every Minecraft version. This is a gameplay change, not part of the port.
- Origins and a few abilities have Minecraft names and descriptions, listed above.

## Licence

Upstream publishes no licence file and lists the plugin as All Rights Reserved on Modrinth. This fork adds no licence of its own.
