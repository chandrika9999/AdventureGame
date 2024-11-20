import java.util.Scanner;

class AdventureGame {

    static String currentRoom = "Entrance";
    static String[] inventory = new String[10];
    static int inventoryIndex = 0;

    static boolean keyInDungeon = true;
    static boolean potionInForest = true;
    static boolean goblinAlive = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Enhanced Text-Based Adventure Game!");
        System.out.println("Your goal is to collect the treasure in the Treasure Room.");
        System.out.println("Commands: go north, go south, check inventory, talk, attack, collect <item>");

        while (true) {
            printRoomDescription();

            System.out.print("\nEnter your command: ");
            String command = scanner.nextLine().toLowerCase();

            if (command.startsWith("collect ")) {
                collectItem(command.substring(8));
            } else {
                switch (command) {
                    case "go north":
                        moveNorth();
                        break;
                    case "go south":
                        moveSouth();
                        break;
                    case "check inventory":
                        checkInventory();
                        break;
                    case "talk":
                        talkToNPC();
                        break;
                    case "attack":
                        attack();
                        break;
                    default:
                        System.out.println("Unknown command. Try again.");
                }
            }

            if (currentRoom.equals("Treasure Room") && hasItem("key")) {
                System.out.println("\nCongratulations! You've unlocked the treasure chest and found the treasure!");
                System.out.println("You win!");
                break;
            }
        }

        scanner.close();
    }

    public static void printRoomDescription() {
        switch (currentRoom) {
            case "Entrance":
                System.out.println("You are at the entrance. There are exits to the North (Forest).");
                break;
            case "Forest":
                System.out.println("You are in the forest. There are exits to the South (Entrance) and North (Dungeon).");
                if (potionInForest) System.out.println("You see a potion on the ground.");
                break;
            case "Dungeon":
                System.out.println("You are in the dungeon. There are exits to the South (Forest) and North (Treasure Room).");
                if (goblinAlive) System.out.println("A goblin blocks your path!");
                if (keyInDungeon) System.out.println("You see a key on the ground.");
                break;
            case "Treasure Room":
                System.out.println("You are in the Treasure Room. A treasure chest stands before you.");
                break;
            default:
                System.out.println("Unknown room.");
        }
    }

    public static void moveNorth() {
        if (currentRoom.equals("Entrance")) {
            currentRoom = "Forest";
            System.out.println("You move north into the Forest.");
        } else if (currentRoom.equals("Forest")) {
            currentRoom = "Dungeon";
            System.out.println("You move north into the Dungeon.");
        } else if (currentRoom.equals("Dungeon")) {
            currentRoom = "Treasure Room";
            System.out.println("You move north into the Treasure Room.");
        } else {
            System.out.println("You can't go further north.");
        }
    }

    public static void moveSouth() {
        if (currentRoom.equals("Forest")) {
            currentRoom = "Entrance";
            System.out.println("You move south back to the Entrance.");
        } else if (currentRoom.equals("Dungeon")) {
            currentRoom = "Forest";
            System.out.println("You move south back to the Forest.");
        } else if (currentRoom.equals("Treasure Room")) {
            currentRoom = "Dungeon";
            System.out.println("You move south back to the Dungeon.");
        } else {
            System.out.println("You can't go further south.");
        }
    }

    public static void checkInventory() {
        System.out.println("Your Inventory:");
        if (inventoryIndex == 0) {
            System.out.println("Your inventory is empty.");
        } else {
            for (int i = 0; i < inventoryIndex; i++) {
                System.out.println("- " + inventory[i]);
            }
        }
    }

    public static void talkToNPC() {
        if (currentRoom.equals("Forest")) {
            System.out.println("NPC: 'The Dungeon is dangerous. A goblin guards the path to the treasure.'");
        } else if (currentRoom.equals("Dungeon")) {
            System.out.println("NPC: 'You must defeat the goblin to proceed further!'");
        } else {
            System.out.println("There's no one to talk to here.");
        }
    }

    public static void attack() {
        if (currentRoom.equals("Dungeon") && goblinAlive) {
            System.out.println("You engage in combat with the goblin!");
            goblinAlive = false;
            System.out.println("You defeated the goblin!");
        } else if (currentRoom.equals("Dungeon")) {
            System.out.println("The goblin has already been defeated.");
        } else {
            System.out.println("There's nothing to attack here.");
        }
    }

    public static void collectItem(String item) {
        if (currentRoom.equals("Forest") && item.equals("potion") && potionInForest) {
            inventory[inventoryIndex++] = "potion";
            potionInForest = false;
            System.out.println("You collected a potion.");
        } else if (currentRoom.equals("Dungeon") && item.equals("key") && keyInDungeon) {
            inventory[inventoryIndex++] = "key";
            keyInDungeon = false;
            System.out.println("You collected a key.");
        } else {
            System.out.println("There's no " + item + " here to collect.");
        }
    }

    public static boolean hasItem(String item) {
        for (String invItem : inventory) {
            if (item.equals(invItem)) {
                return true;
            }
        }
        return false;
    }
}
