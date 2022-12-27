# Loriath
Este mod agrega una variedad de items y mecanicas al juego

* [Items](#Items)
	* [Hats](#Hats)
	* [Accesorios](#Accessories)
	* [Mistery Boxes](#mistery-boxes)
* [Tags](#Tags)
* [Enchantments](#Enchantments)

## Items
Se agregan 3 tipos de items

* [Hats](#Hats)
* [Accessories](#Accessories)
* [Mistery Boxes](#mistery-boxes)

### Hats
Estos items son puramente decorativos y se pueden encontrar en cofres y [Mistery Boxes](#mistery-boxes)
Son 121 en total y estan todos bajo la tag `loriath:hats`

### Accessories
Estos items son funcionales, al igual que los [Hats](#Hats) se pueden encontrar en cofres y [Mistery Boxes](#mistery-boxes) son 16 y se encuentran bajo 5 tags diferentes

[comment]: <> (Terminar la tabla)

| Accesorio | Rareza | Descripcion |
| --------- | ------ | ----------- |
|           |        |             |

| Tag                 | Cantidad de items | Items                                                                                           |
| ------------------- | ----------------- | ----------------------------------------------------------------------------------------------- |
| common_accessory    | 3                 | `loriath:adhesive_bandage`, `loriath:bezoar`, `loriath:fast_clock`                              |
| uncommon_accessory  | 3                 | `loriath:cloak_of_invisibility`, `loriath:frog_leg`, `loriath:diving:gear`                      |
| rare_accessory      | 4                 | `loriath:medicated_bandage`, `loriath:shackle`, `loriath:shiny_stone`, `loriath:panic_necklace` |
| epic_accessory      | 3                 | `loriath:lava_charm`, `loriath:digging_claws`, `loriath:frog_flipper`                           |
| legendary_accessory | 3                 | `loriath:destroyer_emblem`, `loriath:hermes_boots`, `loriath:titan_glove`                                                                                                |

### Mistery Boxes
Estos items son muy especiales, ya que al usarlos giras una ruleta que te da un item de una loot table en especifica que depende de la rareza de la misma. Hay 5 rarezas
* Common Mistery Box
* Uncommon Mistery Box
* Rare Mistery Box
* Epic Mistery Box
* Legendary Mistery Box

## Tags
Ademas de las tags de los accesories y hats este mod agrega dos tags mas

| Tag               | Cantidad de items | Description                            |
| ----------------- | ----------------- | -------------------------------------- |
| concrete_powder   | 16                | Todos los bloques de concrete powder   |
| glazed_terracotta | 16                | Todos los bloques de glazed terracotta |

## Enchantments
Se agregan 8 encantamientos, distribuidos de manera que son 7 encantamientos y 1 curse.

| Encantamiento         | Max level | Enchantment Target | Curse | Description                                                                                                                                                                                                                                                               |
| --------------------- | --------- | ------------------ | ----- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Death Wish            | 1         | Armas              | No    | Tu daño se incrementa en base a la cantidad de vida que te falta. Por otro lado, al tener este encantamiento recibirás 25% más de daño. Ecuación para calcular el daño: `1.0 + (1.0 - entity.getHealth() / entity.getMaxHealth())`                                          |
| Dodge                 | 2         | Pantalones         | No    | Hay una posibilidad de poder evitar cualquier tipo de daño. La posibilidad base es de 12.5% y se multiplica por el nivel del encantamiento                                                                                                                                |
| Elder Guardian Favor  | 1         | Tridente           | No    | Despues de un hit, este encantamiento ataca al enemigo con un láser que no puede ser esquivado o bloqueado (parecido al láser del Elder Guardian). Al estar en el agua, este láser hace un 50% más de daño                                                                |
| Immortality           | 1         | Escudo             | No    | Este encantamiento permite obtener el uso de un tótem al costo de romper el escudo en el cual está aplicado                                                                                                                                                               |
| Phoenix Dive          | 3         | Botas              | No    | Al recibir daño de caída de 3 bloques o más, prende fuego a todas las entidades en un radio de 5 bloques. El daño que hace este fuego disminuye cuanto más lejos esté la entidad de vos. Además te pone unas partículas de fuego re facheras en los pies mientras caminas |
| Smelter               | 1         | Herramientas       | No    | Al romper un bloque, te dará el estado cocinado de ese bloque                                                                                                                                                                                                             |
| Vitality              | 3         | Escudo             | No    | Al tener en la mano un escudo con este encantamiento se te otorgara un contenedor de vida por cada nivel de encantamiento                                                                                                                                                 |
| Incompatibility Curse | 1         | Todo               | Si    | Esta curse no te permite poner ningún otro encantamiento                                                                                                                                                                                                                  |
