# Distributed Systems — ViSiDiA TPs

Travaux pratiques de l'UE Systèmes Distribués réalisés avec la librairie [ViSiDiA](http://visidia.labri.fr/), qui permet de simuler des algorithmes distribués sur des graphes.

> Ce projet est réalisé à des fins purement académiques dans le cadre de l'UE Systèmes Distribués à Polytech Dijon.

## Prérequis

- **JDK 7** (requis pour la compatibilité avec ViSiDiA)
- l'archive `ViSiDiA.jar` (disponible sur le site de ViSiDiA)

## Installation

```bash
git clone https://github.com/ThierryNgoupaye/Distributed-System.git
cd Distributed-System
```

## Compilation et lancement

Remplacer `chemin_vers_jdk7` par le chemin d'installation de ton JDK 7, et `chemin_vers_ViSiDiA.jar` par l'emplacement du fichier jar.

**Linux / macOS**
```bash
/chemin_vers_jdk7/bin/javac -cp /chemin_vers_ViSiDiA.jar nom_du_fichier.java
```

Exemple :
```bash
/usr/lib/jvm/java-7-openjdk/bin/javac -cp ./lib/ViSiDiA.jar td1/SpanningTree.java
```

**Windows**
```cmd
"C:\chemin_vers_jdk7\bin\javac.exe" -cp C:\chemin_vers_ViSiDiA.jar nom_du_fichier.java
```

Exemple :
```cmd
"C:\Program Files\Java\jdk1.7.0\bin\javac.exe" -cp .\lib\ViSiDiA.jar td1\SpanningTree.java
```

## Structure du projet

```
td1/     Arbre couvrant (algorithme LC0)
tp1/     Partage d'informations entre voisins
tp2/     Parcours en profondeur (DFS, LC0)
tp3/     Election d'un leader (LC0)
tp4/     Election d'un leader et arbre couvrant (LC1 — étoile ouverte)
tp5/     Election d'un leader et arbre couvrant (LC2 — étoile fermée)
tp6/     Détection de terminaison locale
tp2025/  Comptage client/serveur dans un arbre couvrant
```

## Contenu des TPs

### TD1 — Arbre couvrant (LC0)
Algorithme de base pour construire un arbre couvrant à partir d'un noeud racine initialement marqué `A`. Règle : `A --- N` devient `A --- A`.

### TP1 — Informations partagées (LC0)
Chaque noeud collecte les identifiants de ses voisins et met à jour son label selon son rang relatif parmi eux.

### TP2 — Parcours en profondeur (LC0)
Construction d'un arbre couvrant par DFS. Un noeud `A` propage l'état `A` à ses voisins `N`, et remonte vers son père une fois tous ses voisins visités. Le comptage de noeuds est propagé vers la racine.

### TP3 — Election d'un leader (LC0)
Algorithme d'élagage : les noeuds de degré 1 sont éliminés progressivement. Le dernier noeud restant est élu leader (`E`), et son voisin devient finaliste (`F`).

### TP4 — Election et arbre couvrant (LC1)
Mêmes algorithmes que TP3 et TD1, réécrits avec la synchronisation par étoile ouverte (LC1).

### TP5 — Election et arbre couvrant (LC2)
Versions LC2 (étoile fermée) des algorithmes du TP4, avec détection de terminaison locale.

### TP6 — Détection de terminaison locale
Algorithme combinant la construction d'un arbre couvrant et un mécanisme de détection de terminaison basé sur un compteur de profondeur `a`. Inclut également `SyncNeighbor`, un algorithme de synchronisation par capteurs.

### TP2025 — Comptage client/serveur
Extension du DFS du TP2 : chaque noeud est classé client ou serveur selon la parité de son identifiant, et l'arbre couvrant calcule le nombre de clients et de serveurs dans chaque sous-arbre.

## Graphes fournis

| Fichier | Description |
|---|---|
| `test_Graph.gml` | Graphe simple en chaîne (5 noeuds) |
| `recover_tree.gml` | Graphe en arbre (12 noeuds) |
| `graphe_cycles.gml` | Graphe avec cycles (17 noeuds) |
| `tp2025_graph.gml` | Graphe avec labels T/F pour le TP2025 |

## Synchronisations utilisées

- **LC0** : chaque règle s'applique sur une arête (étoile à 2 noeuds)
- **LC1** : synchronisation par étoile ouverte (le centre voit ses voisins un par un)
- **LC2** : synchronisation par étoile fermée (le centre voit tous ses voisins simultanément)