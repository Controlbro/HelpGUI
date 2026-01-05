# HelpGUI

HelpGUI is a lightweight, fully configurable Minecraft Paper plugin that replaces the default `/help` command with a clickable GUI.  
Players can view server commands in a clean menu and run them instantly by clicking items.

Built for **Paper 1.21.11** using **Java 21**.

---

## features

- replaces `/help` with a modern GUI
- fully configurable via `config.yml`
- click items to execute commands as the player
- optional auto-close per item
- reload configuration without restarting
- permission based access
- no dependencies required

---

## compatibility

- minecraft: **paper 1.21.11**
- java: **java 21**
- api-version: **1.21**

---

## commands

| command | description | permission |
|-------|-------------|------------|
| `/help` | opens the help gui | `helpgui.use` |
| `/helpgui reload` | reloads the config | `helpgui.reload` |

---

## permissions

| permission | description | default |
|-----------|------------|---------|
| `helpgui.use` | allows opening the help gui | true |
| `helpgui.reload` | allows reloading the config | op |

---

## configuration

all gui settings are controlled from `config.yml`.
